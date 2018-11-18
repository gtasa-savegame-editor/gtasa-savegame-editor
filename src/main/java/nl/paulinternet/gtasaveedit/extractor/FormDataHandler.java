package nl.paulinternet.gtasaveedit.extractor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class FormDataHandler implements HttpHandler {
    private static final int MAX_SIZE = 1024 * 1024 * 5; // 5MB
    private Pattern namePattern = Pattern.compile("[^e]name=\"(.+?)\"");
    private Pattern fileNamePattern = Pattern.compile("filename=\"(.+?)\"");
    private String escapedSeparator = "\\" + System.getProperty("file.separator");
    private int part;
    private boolean allRead;
    private boolean sizeExceeded;
    protected Set<FileData> files;
    private Handler handler;

    public FormDataHandler(Handler handler) {
        this.handler = handler;
    }

    /**
     * @see <a href="http://tools.ietf.org/html/rfc1867">RFC 1867</a>
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        System.out.println("--- Parsing form submission.");
        part = 0;
        allRead = false;
        sizeExceeded = false;
        files = new HashSet<>();
        String path = exchange.getRequestURI().getPath();
        System.out.println(String.format("Requested URI: %s", path));

        // Get content type.
        // e.g.
        // multipart/form-data, boundary=AaB03x
        List<String> contentTypes = exchange.getRequestHeaders().get("Content-Type");
        String contentType = null;
        String boundary = null;
        if (contentTypes != null && contentTypes.size() > 0) {
            contentType = contentTypes.get(0);
            if (contentType.startsWith("multipart/form-data;")) {
                String[] params = contentType.split("boundary=");
                boundary = params[params.length - 1];
            }
            System.out.println(String.format("Content type: %s", contentType));
        }

        // Handle single image post.
        if (contentType != null && contentType.startsWith("image/")) {
            handlePostedSingleImage(contentType, exchange);
            return;
        }

        // Return error 400.
        if (!"post".equalsIgnoreCase(exchange.getRequestMethod())
                || contentType == null
                || boundary == null) {
            exchange.sendResponseHeaders(400, 0); // Bad request
            exchange.getResponseBody().close();
            System.err.println("Bad request.");
            return;
        }

        // Parse form submission.
        String boundaryLine = "--" + boundary;
        InputStream is = exchange.getRequestBody();
        try {
            while (!allRead) {

                // Wait for the boundary.
                String currentLine = readLine(is);
                if (!currentLine.equals(boundaryLine)) {
                    continue;
                }

                // Read disposition.
                FileData file = new FileData();
                readDisposition(is, file);
                if (file.fileName == null) {
                    // Skip none-file contents.
                    continue;
                }
                files.add(file);

                // Get content type and wait for a blank line.
                readContentType(is, file);
                System.out.println(String.format("  Content type: %s", file.contentType));

                // Get file contents.
                byte[] boundaryBytes = ("\r\n" + boundaryLine).getBytes(StandardCharsets.UTF_8);
                file.data = readUntil(is, boundaryBytes);
                if (file.data.length >= 0) {
                    System.out.println(String.format("  Received: %d bytes", file.data.length));
                    currentLine = readLine(is);

                    // Check if this is the last part.
                    if (currentLine.equals("--")) {
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sendResponse(exchange);
        }
    }

    private void handlePostedSingleImage(String contentType, HttpExchange exchange) {
        FileData file = new FileData();
        InputStream is = exchange.getRequestBody();
        try {
            file.data = readAll(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (file.data.length >= 0) {
            System.out.println(String.format("  Received: %d bytes", file.data.length));
            files.add(file);
        }
        sendResponse(exchange);
    }

    private void sendResponse(HttpExchange exchange) {
        try {
            if (sizeExceeded) {
                exchange.sendResponseHeaders(400, 0); // Bad request
            } else {
                exchange.sendResponseHeaders(200, 0); // OK
            }
            exchange.getResponseBody().close();
            exchange.getRequestBody().close();
            handler.handle(files);
        } catch (IOException e) {
            System.err.println("Unable to handle request!");
            e.printStackTrace();
        }
    }

    /**
     * Get content type and wait for a blank line.
     * <p>
     * e.g.
     * Content-Type: image/gif
     *
     * @param is
     * @param file
     * @throws IOException
     */
    private void readContentType(InputStream is, FileData file) throws IOException {
        while (true) {
            String currentLine = readLine(is);

            // Wait for a blank line.
            if (currentLine.equals("")) {
                break;
            }

            // Get content type.
            if (currentLine.toLowerCase().startsWith("content-type:")) {
                int spaceIndex = currentLine.indexOf(' ');
                if (spaceIndex < 0) {
                    file.contentType = null;
                } else {
                    file.contentType = currentLine.substring(spaceIndex + 1);
                }
            }
        }
    }

    /**
     * Get content disposition.
     * <p>
     * e.g.
     * content-disposition: form-data; name="pics"; filename="file1.txt"
     *
     * @param is
     * @param data
     * @throws IOException
     */
    private void readDisposition(InputStream is, FileData data) throws IOException {
        String disposition = readLine(is);
        Matcher m;
        m = namePattern.matcher(disposition);
        if (m.find()) {
            data.name = m.group(1);
        }
        m = fileNamePattern.matcher(disposition);
        if (m.find()) {
            data.fileName = m.group(1);
            int lastSeparator = data.fileName.lastIndexOf(escapedSeparator);
            if (lastSeparator >= 0) {
                data.fileName = data.fileName.substring(
                        lastSeparator + escapedSeparator.length());
            }
        }

        System.out.println(String.format("Part.%d", ++part));
        System.out.println(String.format("  Name: %s", data.name));
        System.out.println(String.format("  File name: %s", data.fileName));
    }

    private String readLine(InputStream is) throws IOException {
        String line = new String(readUntil(is, "\r\n".getBytes()), "utf-8");
        // System.err.println(line);
        return line;
    }

    private byte[] readAll(InputStream is) throws IOException {
        return readUntil(is, null);
    }

    private byte[] readUntil(InputStream is, byte[] tail) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int b = -1;
        byte[] current = tail != null ? new byte[tail.length] : null;
        byte[] data = null;
        while (!allRead && (b = is.read()) >= 0) {
            bos.write(b);
            if (current != null) {
                System.arraycopy(current, 1, current, 0, current.length - 1);
                current[current.length - 1] = (byte) b;
                if (Arrays.equals(tail, current)) {
                    byte[] rawData = bos.toByteArray();
                    data = new byte[rawData.length - tail.length];
                    System.arraycopy(rawData, 0, data, 0, data.length);
                    break;
                }
            }
            if (bos.size() >= MAX_SIZE) {
                sizeExceeded = true;
                break;
            }
        }
        if (b < 0) {
            allRead = true;
        }
        if (data == null) {
            data = bos.toByteArray();
        }
        return data;
    }

    public static class FileData {
        public String name;
        public String fileName;
        public String contentType;
        public byte[] data;
    }

    public interface Handler {
        void handle(Set<FileData> files);
    }
}
