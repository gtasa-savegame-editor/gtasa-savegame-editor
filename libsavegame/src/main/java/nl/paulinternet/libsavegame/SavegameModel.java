package nl.paulinternet.libsavegame;

import nl.paulinternet.libsavegame.event.ReportableEvent;
import nl.paulinternet.libsavegame.io.ArchiveReader;
import nl.paulinternet.libsavegame.io.FileSystem;
import nl.paulinternet.libsavegame.variables.Variable;
import nl.paulinternet.libsavegame.variables.Variables;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SavegameModel {
    public static final ReportableEvent gameLoaded = new ReportableEvent();
    public static final ReportableEvent gameClosed = new ReportableEvent();

    public static final ReportableEvent quickLoadUpdate = new ReportableEvent();

    public static final Variables vars = new Variables();
    public static final Variable<ArchiveReader> playerImg = new Variable<>();
    public static final List<QuickLoad> quickLoad;

    static {
        // Quick load
        quickLoad = new ArrayList<>(9);
        quickLoad.add(null);
        for (int i = 1; i <= 8; i++) {
            quickLoad.add(new QuickLoad(i));
        }
    }

    public static File getSavegameFile(int number) {
        return new File(FileSystem.getSavegameDirectory(), "GTASAsf" + number + ".b");
    }

    public static void updateQuickLoad() {
        for (int i = 1; i <= 8; i++) {
            quickLoad.get(i).loadValue(FileSystem.getSavegameDirectory());
        }
        SavegameModel.quickLoadUpdate.report();
    }

    private SavegameModel() {
    }

}
