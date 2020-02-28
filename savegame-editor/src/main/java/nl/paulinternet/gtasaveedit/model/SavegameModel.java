package nl.paulinternet.gtasaveedit.model;

import nl.paulinternet.gtasaveedit.QuickLoad;
import nl.paulinternet.gtasaveedit.event.ReportableEvent;
import nl.paulinternet.libsavegame.io.ArchiveReader;
import nl.paulinternet.libsavegame.variables.Variable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SavegameModel {
    public static final ReportableEvent gameLoaded = new ReportableEvent();
    public static final ReportableEvent gameClosed = new ReportableEvent();
    public static final ReportableEvent quickLoadUpdate = new ReportableEvent();

    public static final Variable<ArchiveReader> playerImg = new Variable<>();

    private static SavegameModel instance;

    public final List<QuickLoad> quickLoad;
    private final File savegameDirectory;

    public File getSavegameFile(int number) {
        return new File(savegameDirectory, "GTASAsf" + number + ".b");
    }

    public void updateQuickLoad() {
        for (int i = 1; i <= 8; i++) {
            quickLoad.get(i).loadValue(this);
        }
        SavegameModel.quickLoadUpdate.report();
    }

    private SavegameModel(File savegameDirectory) {
        this.savegameDirectory = savegameDirectory;
        // Quick load
        quickLoad = new ArrayList<>(9);
        quickLoad.add(null);
        for (int i = 1; i <= 8; i++) {
            quickLoad.add(new QuickLoad(i, this));
        }
    }

    public static SavegameModel get(File savegameDirectory) {
        if (instance == null) {
            instance = new SavegameModel(savegameDirectory);
        }
        return instance;
    }
}
