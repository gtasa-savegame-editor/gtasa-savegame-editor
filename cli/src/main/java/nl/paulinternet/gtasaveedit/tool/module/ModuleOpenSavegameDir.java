package nl.paulinternet.gtasaveedit.tool.module;

import nl.paulinternet.gtasaveedit.tool.Dir;

import java.awt.*;
import java.io.File;

/**
 * @author Lukas Fülling (lukas@k40s.net)
 */
public class ModuleOpenSavegameDir implements Module {
    @Override
    public String getName() {
        return "savedir";
    }

    @Override
    public String getDescription() {
        return "Opens the savegame dir";
    }

    @Override
    public void execute(String[] args) throws Exception {
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().open(new File(Dir.get().SAVEGAME_PATH));
        } else {
            Runtime.getRuntime().exec(new String[] {"explorer", new File(Dir.get().SAVEGAME_PATH).getCanonicalPath()});
        }
    }
}
