package nl.paulinternet.gtasaveedit;

import nl.paulinternet.gtasaveedit.model.SavegameModel;
import nl.paulinternet.libsavegame.Savegame;
import nl.paulinternet.libsavegame.exceptions.FileFormatException;
import nl.paulinternet.libsavegame.variables.Variable;

import java.io.File;
import java.io.RandomAccessFile;

public class QuickLoad extends Variable<String> {
    private int number;

    public QuickLoad(int number, File savegameDirectory) {
        this.number = number;
        loadValue(savegameDirectory);
    }

    public void loadValue(File savegameDir) {
        // Try to read the bytes of the title
        byte[] data;

        // Open file
        try (RandomAccessFile file = new RandomAccessFile(SavegameModel.get(FileSystem.getSavegameDirectory())
                .getSavegameFile(number), "r")) {
            if (file.length() != Savegame.FILESIZE) throw new FileFormatException();

            // Read bytes
            file.skipBytes(9);
            data = new byte[100];
            file.readFully(data);
        } catch (Exception e) {
            data = null;
        }

        // Set the value
        if (data == null) {
            setValue(null);
        } else {
            int i;
            //noinspection StatementWithEmptyBody
            for (i = 0; i < data.length && data[i] != 0; i++) ;
            String text = new String(data, 0, i)
                    .replace(']', '*')
                    .replace('(', '[')
                    .replace(')', ']');
            setValue(text);
        }
    }
}
