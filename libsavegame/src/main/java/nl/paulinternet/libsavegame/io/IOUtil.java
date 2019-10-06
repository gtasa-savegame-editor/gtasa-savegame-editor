package nl.paulinternet.libsavegame.io;


import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class IOUtil {
    public static byte[] readFile(String file) throws IOException {
        return readFile(new File(file));
    }

    public static byte[] readFile(File file) throws IOException {
        // Open file
        RandomAccessFile f = new RandomAccessFile(file, "r");

        try {
            // Get and check length
            long longlength = f.length();
            int length = (int) longlength;
            if (length != longlength) throw new IOException("File size >= 2 GB");

            // Read file and return data
            byte[] data = new byte[length];
            f.readFully(data);
            return data;
        } finally {
            f.close();
        }
    }

    public static byte[] readData(InputStream in) throws IOException {
        ByteArrayOutputStream data = new ByteArrayOutputStream();

        byte[] buffer = new byte[4096];
        while (true) {
            int read = in.read(buffer);
            if (read == -1) break;
            data.write(buffer, 0, read);
        }

        return data.toByteArray();
    }

    public static void writeFile(String file, byte[] data) throws IOException {
        writeFile(new File(file), data);
    }

    public static void writeFile(File file, byte[] data) throws IOException {
        RandomAccessFile f = new RandomAccessFile(file, "rw");
        try {
            f.setLength(data.length);
            f.write(data);
        } finally {
            f.close();
        }
    }

    public static byte[] readResource(URL url) throws IOException {
        URLConnection c = url.openConnection();

        DataInputStream in = new DataInputStream(c.getInputStream());
        try {
            byte[] data = new byte[c.getContentLength()];
            in.readFully(data);
            return data;
        } finally {
            in.close();
        }
    }
}
