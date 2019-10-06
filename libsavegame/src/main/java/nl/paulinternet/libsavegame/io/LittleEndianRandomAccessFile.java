package nl.paulinternet.libsavegame.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;


public class LittleEndianRandomAccessFile extends RandomAccessFile {
    public LittleEndianRandomAccessFile(File file, String mode) throws FileNotFoundException {
        super(file, mode);
    }

    public LittleEndianRandomAccessFile(String name, String mode) throws FileNotFoundException {
        super(name, mode);
    }

    public int readIntLE() throws IOException {
        return Integer.reverseBytes(readInt());
    }

    public float readFloatLE() throws IOException {
        return Float.intBitsToFloat(readIntLE());
    }

    public short readShortLE() throws IOException {
        return Short.reverseBytes(readShort());
    }
}
