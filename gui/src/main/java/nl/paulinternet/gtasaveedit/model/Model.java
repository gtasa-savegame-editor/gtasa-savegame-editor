package nl.paulinternet.gtasaveedit.model;

import nl.paulinternet.libsavegame.Settings;
import nl.paulinternet.libsavegame.exceptions.FileFormatException;
import nl.paulinternet.libsavegame.io.ArchiveReader;
import nl.paulinternet.libsavegame.io.FileSystem;

import java.io.File;
import java.io.IOException;

import static nl.paulinternet.libsavegame.SavegameModel.playerImg;

public class Model {
    public static final SettingVariables editSettings = new SettingVariables();

    private static File saDir;

    static {
        updatePlayerImg();
    }

    public static void updatePlayerImg() {
        File newSaDir = FileSystem.getSanAndreasDirectory();
        ArchiveReader archive = null;

        if (Settings.getShowClothes() == Settings.YES && newSaDir != null) {
            if (newSaDir.equals(saDir)) return;

            try {
                if (FileSystem.getPlayerImageFile() != null)
                    archive = new ArchiveReader(FileSystem.getPlayerImageFile());
            } catch (FileFormatException | IOException ignored) {
            }
        }

        playerImg.setValue(archive);
        saDir = archive == null ? null : newSaDir;
    }
}
