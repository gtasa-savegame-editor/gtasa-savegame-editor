package nl.paulinternet.libsavegame.io;

import java.io.IOException;

/**
 * Represents a file that is stored in an other file.
 *
 * @see ArchiveReader
 */
public class ArchiveEntry {
    private LittleEndianRandomAccessFile file;
    private int offset, length;

    public ArchiveEntry(ArchiveEntry other) {
        this.file = other.file;
        this.offset = other.offset;
        this.length = other.length;
    }

    public ArchiveEntry(LittleEndianRandomAccessFile file, int offset, int length) {
        this.file = file;
        this.offset = offset;
        this.length = length;
    }

    public LittleEndianRandomAccessFile getFile() {
        return file;
    }

    public int getOffset() {
        return offset;
    }

    public int getLength() {
        return length;
    }

    public byte[] getData() throws IOException {
        byte[] data = new byte[length];
        file.seek(offset);
        file.readFully(data);

        return data;
    }
}