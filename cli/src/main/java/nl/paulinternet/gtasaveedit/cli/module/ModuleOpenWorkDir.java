package nl.paulinternet.gtasaveedit.cli.module;

import nl.paulinternet.gtasaveedit.cli.Dir;

import java.awt.*;
import java.io.File;

public class ModuleOpenWorkDir implements Module {
    public String getName() {
        return "workdir";
    }

    public String getDescription() {
        return "Opens the research directory";
    }

    public void execute(String[] args) throws Exception {
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().open(new File(Dir.get().RESEARCH_PATH));
        } else {
            Runtime.getRuntime().exec(new String[]{"explorer", new File(Dir.get().RESEARCH_PATH).getCanonicalPath()});
        }
    }
}