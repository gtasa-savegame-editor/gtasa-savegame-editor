package nl.paulinternet.gtasaveedit.extractor;

import nl.paulinternet.gtasaveedit.view.menu.extractor.ExtractedSaveGameMenu;
import nl.paulinternet.gtasaveedit.view.menu.extractor.ExtractorMenu;

import java.util.HashMap;

public class ExtractedSavegameHolder {
    private static ExtractedSavegameHolder instance = null;
    private final HashMap<Integer, ExtractorServer.ExtractedSavegameFile> saveGameFiles;

    private ExtractedSavegameHolder() {
        saveGameFiles = new HashMap<>();
    }

    public static ExtractedSavegameHolder getInstance() {
        if (instance == null) {
            instance = new ExtractedSavegameHolder();
        }
        return instance;
    }

    public static HashMap<Integer, ExtractorServer.ExtractedSavegameFile> getSaveGameFiles() {
        return getInstance().saveGameFiles;
    }

    public static void addSavegame(Integer idx, ExtractorServer.ExtractedSavegameFile file, ExtractorMenu menu) {
        getSaveGameFiles().put(idx, file);
        menu.add(new ExtractedSaveGameMenu(file.getSaveGame(), file.getFileName()));
    }
}