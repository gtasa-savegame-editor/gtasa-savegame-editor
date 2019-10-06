package nl.paulinternet.libsavegame.io;

import java.io.IOException;


public class RWFile extends ArchiveEntry {
    public RWFile(ArchiveEntry archiveEntry) {
        super(archiveEntry);
    }

    public RWFile(LittleEndianRandomAccessFile file, int offset, int length) {
        super(file, offset, length);
    }

    public RWFile getPart(int id) throws IOException {
        LittleEndianRandomAccessFile file = getFile();

        int pos = getOffset();

        while (pos < getOffset() + getLength()) {
            // Read id and size
            file.seek(pos);
            int sectionId = Integer.reverseBytes(file.readInt());
            int sectionSize = Integer.reverseBytes(file.readInt());

            if (sectionId == id) {
                return new RWFile(file, pos + 0xc, sectionSize);
            }

            pos += 0xc + sectionSize;
        }

        return null;
    }
}
