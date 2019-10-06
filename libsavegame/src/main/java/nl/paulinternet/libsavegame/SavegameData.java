package nl.paulinternet.libsavegame;

import nl.paulinternet.libsavegame.exceptions.FileFormatException;

import java.io.*;
import java.net.URL;
import java.util.zip.CheckedOutputStream;
import java.util.zip.Checksum;

public class SavegameData {
    public static final int FILESIZE = 202752;
    public static final int FILESIZE_ANDROID = 195002;
    public static final byte[] BLOCK = new byte[]{66, 76, 79, 67, 75};

    private ByteSequence[] block = new ByteSequence[30];

    public SavegameData() {
    }

    public SavegameData(File filename) throws IOException, FileFormatException {
        // Read the data
        RandomAccessFile file = null;
        byte[] bytes;
        try {
            file = new RandomAccessFile(filename, "r");
            if (file.length() == FILESIZE) {
                bytes = new byte[FILESIZE - 4];
            } else if (file.length() == FILESIZE_ANDROID) {
                bytes = new byte[FILESIZE_ANDROID - 4];
            } else {
                throw new FileFormatException();
            }

            file.readFully(bytes);
        } finally {
            try {
                file.close();
            } catch (Exception ignored) {
            }
        }

        // Init
        init(bytes);
    }

    public SavegameData(URL url) {
        try {
            byte[] bytes = new byte[FILESIZE - 4];

            DataInputStream in = new DataInputStream(url.openStream());
            in.readFully(bytes);
            in.close();

            init(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void init(byte[] bytes) throws IOException, FileFormatException {
        // Search every occurrence of "BLOCK"
        int pos = 0;
        int[] blockPos = new int[34];
        for (int i = 0; i < 34; i++) {
            blockPos[i] = Util.indexOf(bytes, BLOCK, pos);
            if (blockPos[i] == -1) {
                throw new FileFormatException();
            }
            pos = blockPos[i] + BLOCK.length;
        }

        // Check if the file starts with "BLOCK"
        if (blockPos[0] != 0) throw new FileFormatException();

        // Check length of block 10
        if (blockPos[11] - blockPos[10] - 5 != 18892) throw new FileFormatException();

        // Extract the blocks
        ByteSequence data = new ByteSequence(bytes);

        for (int i = 0; i < block.length; i++) {
            block[i] = data.getSubSequence(blockPos[i] + BLOCK.length, blockPos[i + 1]);
        }
    }

    public ByteSequence[] getBlocks() {
        return block;
    }

    public ByteSequence getBlock(int block) {
        return this.block[block];
    }

    public void setBlock(int block, ByteSequence data) {
        this.block[block] = data;
    }

    public void save(File file) throws IOException {
        OutputStream out = null;
        try {
            // Open file
            Checksum checksum = new SumOfBytes();
            out = new CheckedOutputStream(new BufferedOutputStream(new FileOutputStream(file)), checksum);

            // Write blocks
            int pos = 0;

            for (int i = 0; i < 30; i++) {
                out.write(BLOCK);
                block[i].writeContents(out);
                pos += BLOCK.length + block[i].getLength();
            }

            for (int i = 15; i < 18; i++) {
                out.write(BLOCK);
                block[i].writeContents(out);
                pos += BLOCK.length + block[i].getLength();
            }

            // Write padding
            out.write(BLOCK);
            pos += BLOCK.length;
            out.write(new byte[FILESIZE - 4 - pos]);

            // Write checksum
            int checkSumValue = (int) checksum.getValue();
            out.write(checkSumValue);
            out.write(checkSumValue >> 8);
            out.write(checkSumValue >> 16);
            out.write(checkSumValue >> 24);
        } finally {
            try {
                out.close();
            } catch (Exception e) {
            }
        }
    }

    public int readInt(int block, int pos, int size) {
        // Get data
        byte[] bytes = this.block[block].getBytes(pos, pos + size);

        // Convert it to an integer
        int number = 0;
        for (int i = 0; i < size; i++) {
            number |= (bytes[i] & 0xff) << i * 8;
        }

        // Return
        return number;
    }

    public void writeInt(int block, int pos, int size, int value) {
        // Create a byte array
        byte[] bytes = new byte[size];
        for (int i = 0; i < size; i++) {
            bytes[i] = (byte) (value >> i * 8);
        }

        // Overwrite the data
        this.block[block].overwrite(bytes, pos);
    }

    public int readInt(int block, int pos) {
        return readInt(block, pos, 4);
    }

    public void writeInt(int block, int pos, int value) {
        writeInt(block, pos, 4, value);
    }

    public short readShort(int block, int pos) {
        return (short) readInt(block, pos, 2);
    }

    public void writeShort(int block, int pos, int value) {
        writeInt(block, pos, 2, value);
    }

    public int readByte(int block, int pos) {
        return readInt(block, pos, 1);
    }

    public void writeByte(int block, int pos, int value) {
        writeInt(block, pos, 1, value);
    }

    public boolean readBoolean(int block, int pos) {
        return readInt(block, pos, 1) != 0;
    }

    public void writeBoolean(int block, int pos, boolean value) {
        writeInt(block, pos, 1, value ? 1 : 0);
    }

    public float readFloat(int block, int pos) {
        return Float.intBitsToFloat(readInt(block, pos));
    }

    public void writeFloat(int block, int pos, float value) {
        writeInt(block, pos, Float.floatToRawIntBits(value));
    }
}
