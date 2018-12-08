package nl.paulinternet.gtasaveedit.extractor;

import nl.paulinternet.gtasaveedit.view.menu.extractor.ExtractedSavegameSubmenu;
import nl.paulinternet.gtasaveedit.view.menu.extractor.ExtractorMenu;

import java.util.ArrayList;
import java.util.HashMap;

public class ExtractedSavegameHolder {
    private static ExtractedSavegameHolder instance = null;
    private final ArrayList<ExtractorServer.ExtractedSavegameFile> saveGameFiles;
    private String selectedSavegame = "";

    private ExtractedSavegameHolder() {
        saveGameFiles = new ArrayList<>();
    }

    public static ExtractedSavegameHolder getInstance() {
        if (instance == null) {
            instance = new ExtractedSavegameHolder();
        }
        return instance;
    }

    public static ArrayList<ExtractorServer.ExtractedSavegameFile> getSaveGameFiles() {
        return getInstance().saveGameFiles;
    }

    public static void addSavegame(ExtractorServer.ExtractedSavegameFile file, ExtractorMenu menu) {
        getSaveGameFiles().add(file);
        menu.add(new ExtractedSavegameSubmenu(file.getSaveGame(), file.getFileName()));
    }

    public String getSelectedSavegame() {
        return selectedSavegame;
    }

    public void setSelectedSavegame(String selectedSavegame) {
        this.selectedSavegame = selectedSavegame;
    }
}