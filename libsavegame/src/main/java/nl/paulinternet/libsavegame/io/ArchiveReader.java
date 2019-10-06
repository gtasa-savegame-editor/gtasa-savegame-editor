package nl.paulinternet.libsavegame.io;

import nl.paulinternet.libsavegame.exceptions.FileFormatException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Reads a GTA SA .img file.
 * The file format is described at http://www.gtamodding.com/index.php?title=IMG_archive
 */
public class ArchiveReader {
    private LittleEndianRandomAccessFile file;
    private Map<String, ArchiveEntry> map;
    private List<String> files;

    public ArchiveReader(File fileName) throws IOException, FileFormatException {
        // Open file
        file = new LittleEndianRandomAccessFile(fileName, "r");

        // Check if header is "VER2"
        if (file.readInt() != 1447383602) throw new FileFormatException();

        // Read entries
        int n = Integer.reverseBytes(file.readInt());

        map = new HashMap<>(n);
        files = new ArrayList<>(n);

        byte[] data = new byte[24];

        for (int i = 0; i < n; i++) {
            int offset = Integer.reverseBytes(file.readInt()) * 2048;
            int size = Integer.reverseBytes(file.readInt()) * 2048;

            file.readFully(data);
            int p;
            //noinspection StatementWithEmptyBody
            for (p = 0; p < data.length && data[p] != 0; p++) ;
            String name = new String(data, 0, p);

            files.add(name);
            map.put(name, new ArchiveEntry(file, offset, size));
        }
    }

    public ArchiveEntry getFile(String name) throws FileNotFoundException {
        ArchiveEntry entry = map.get(name);
        if (entry == null) throw new FileNotFoundException(name);
        return entry;
    }

    public List<String> getFiles() {
        return files;
    }

    public void close() {
        try {
            file.close();
        } catch (IOException ignored) {
        }
    }
}
