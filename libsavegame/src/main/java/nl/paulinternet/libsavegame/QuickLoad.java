package nl.paulinternet.libsavegame;

import nl.paulinternet.libsavegame.io.FileSystem;
import nl.paulinternet.libsavegame.variables.Variable;
import nl.paulinternet.libsavegame.exceptions.FileFormatException;

import java.io.File;
import java.io.RandomAccessFile;

public class QuickLoad extends Variable<String> {
    private int number;

    public QuickLoad(int number) {
        this.number = number;
        loadValue(FileSystem.getSavegameDirectory());
    }

    public void loadValue(File savegameDir) {
        // Try to read the bytes of the title
        byte[] data;
        RandomAccessFile file = null;
        try {
            // Open file
            file = new RandomAccessFile(SavegameModel.getSavegameFile(number), "r");
            if (file.length() != Savegame.FILESIZE) throw new FileFormatException();

            // Read bytes
            file.skipBytes(9);
            data = new byte[100];
            file.readFully(data);
        } catch (Exception e) {
            data = null;
        } finally {
            try {
                file.close();
            } catch (Exception ignored) {
            }
        }

        // Set the value
        if (data == null) {
            setValue(null);
        } else {
            int i;
            //noinspection StatementWithEmptyBody
            for (i = 0; i < data.length && data[i] != 0; i++) ;
            String text = new String(data, 0, i).replace(']', '*').replace('(', '[').replace(')', ']');
            setValue(text);
        }
    }
}
