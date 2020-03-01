package nl.paulinternet.libsavegame;

import nl.paulinternet.libsavegame.exceptions.ErrorMessageException;
import nl.paulinternet.libsavegame.exceptions.FileFormatException;
import nl.paulinternet.libsavegame.link.Link;
import nl.paulinternet.libsavegame.link.SavegameLink;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Savegame {
    public static final int FILESIZE = 202752;
    public static final byte[] BLOCK = new byte[]{66, 76, 79, 67, 75};

    private static Savegame instance;

    private Link link = new SavegameLink();
    private SavegameData data;

    private ArrayList<CallbackHandler<Void>>
            onGameLoaded,
            onUpdateQuickload,
            onGameClosed;

    private Savegame() {
    }

    public static Savegame get() {
        if (instance == null) {
            instance = new Savegame();
        }
        return instance;
    }

    public void load(File file) throws ErrorMessageException {
        try {
            // Read data
            SavegameData newData = new SavegameData(file);

            // Read settings
            link.load(newData);
            data = newData;

            //report success
            if (onGameLoaded != null && !onGameLoaded.isEmpty()) {
                onGameLoaded.forEach(h -> h.handle(null));
            }
        } catch (FileFormatException e) {
            try {
                if (data != null) link.load(data);
            } catch (FileFormatException e2) {
                throw new RuntimeException(e2);
            }
            throw new ErrorMessageException("Loading failed", "The file is not a GTA San Andreas PC savegame.");
        } catch (FileNotFoundException e) {
            throw new ErrorMessageException("Loading failed", "The file does not exist.");
        } catch (IOException e) {
            throw new ErrorMessageException("Loading failed", "An error occurred while trying to read the file.");
        } finally {
            if (onUpdateQuickload != null && !onUpdateQuickload.isEmpty()) {
                onUpdateQuickload.forEach(h -> h.handle(null));
            }
        }
    }

    public void load(URL url) {
        data = new SavegameData(url);
        try {
            link.load(data);
        } catch (FileFormatException e) {
            throw new RuntimeException(e);
        }
        if (onGameLoaded != null && !onGameLoaded.isEmpty()) {
            onGameLoaded.forEach(h -> h.handle(null));
        }
    }

    public void save(File file) throws ErrorMessageException {
        // Write settings
        link.save(data);

        // Try to save
        try {
            data.save(file);
        } catch (FileNotFoundException e) {
            throw new ErrorMessageException("Saving failed", "Failed to create file.");
        } catch (IOException e) {
            throw new ErrorMessageException("Saving failed", "An error occurred while writing the file.");
        } finally {
            if (onUpdateQuickload != null && !onUpdateQuickload.isEmpty()) {
                onUpdateQuickload.forEach(h -> h.handle(null));
            }
        }
    }

    public void close() {
        // Remove data
        data = null;
        link = new SavegameLink();
        if (onGameClosed != null && !onGameClosed.isEmpty()) {
            onGameClosed.forEach(h -> h.handle(null));
        }
    }

    public SavegameData getData() {
        return data;
    }

    public void addOnGameClosedHandler(CallbackHandler<Void> onGameClosedHandler) {
        if (onGameClosed == null) {
            onGameClosed = new ArrayList<>();
        }
        onGameClosed.add(onGameClosedHandler);
    }

    public void addOnUpdateQuickloadHandler(CallbackHandler<Void> onUpdateQuickloadHandler) {
        if (onUpdateQuickload == null) {
            onUpdateQuickload = new ArrayList<>();
        }
        onUpdateQuickload.add(onUpdateQuickloadHandler);
    }

    public void addOnGameLoadedHandler(CallbackHandler<Void> onGameLoadedHandler) {
        if (onGameLoaded == null) {
            onGameLoaded = new ArrayList<>();
        }
        onGameLoaded.add(onGameLoadedHandler);
    }
}
