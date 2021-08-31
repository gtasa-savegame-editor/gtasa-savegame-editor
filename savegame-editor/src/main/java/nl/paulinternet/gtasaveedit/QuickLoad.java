package nl.paulinternet.gtasaveedit;

import nl.paulinternet.gtasaveedit.model.SavegameModel;
import nl.paulinternet.libsavegame.Savegame;
import nl.paulinternet.libsavegame.exceptions.FileFormatException;
import nl.paulinternet.libsavegame.variables.Variable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

public class QuickLoad extends Variable<String> {

    private final Logger log = LoggerFactory.getLogger(QuickLoad.class);

    private int number;

    public QuickLoad(int number, SavegameModel model) {
        this.number = number;
        loadValue(model);
    }

    public void loadValue(SavegameModel model) {
        // Try to read the bytes of the title
        byte[] data;

        // Open file
        try (RandomAccessFile file = new RandomAccessFile(model.getSavegameFile(number), "r")) {
            if (file.length() != Savegame.FILESIZE) throw new FileFormatException();

            // Read bytes
            file.skipBytes(9);
            data = new byte[100];
            file.readFully(data);
        } catch (Exception e) {
            log.warn("Error opening file!", e);
            data = null;
        }

        // Set the value
        if (data == null) {
            setValue(null);
        } else {
            int i;
            //noinspection StatementWithEmptyBody
            for (i = 0; i < data.length && data[i] != 0; i++) ;
            String text = new String(data, 0, i, StandardCharsets.UTF_8)
                    .replace(']', '*')
                    .replace('(', '[')
                    .replace(')', ']');
            setValue(text);
        }
    }
}
