package nl.paulinternet.gtasaveedit.cli;

import nl.paulinternet.libsavegame.Util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Dir {
    private final static Dir instance = new Dir();
    public final String RESEARCH_PATH;
    public final String SAVEGAME_PATH;

    public static Dir get() {
        return instance;
    }

    private Dir() {
        String[] TEMP = {"", ""};
        Properties prop = new Properties();
        try (InputStream stream = Dir.class.getResourceAsStream("/application.properties")) {
            prop.load(stream);
            TEMP[0] = prop.getProperty("path.workdir");
            TEMP[1] = prop.getProperty("path.savegames");
        } catch (IOException e) {
            e.printStackTrace();
            TEMP[0] = "";
            TEMP[1] = "";
        }
        this.RESEARCH_PATH = TEMP[0];
        this.SAVEGAME_PATH = TEMP[1];
    }

    public static File getSavegameFile(String number) {
        return new File(instance.SAVEGAME_PATH + File.separator + "GTASAsf" + number + ".b");
    }

    public static int[] getBlocks() {
        return new int[]{0, 1, 2, 3, 4, 5, 6, 8, 9, 10, 11, 12, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27};
    }

    private static String getBlockFile(int block) {
        String number = block < 10 ? "0" + block : String.valueOf(block);
        return number + ".block";
    }

    public static File getBlockPath(String path, int block) {
        return new File(getBlockPath(path), getBlockFile(block));
    }

    public static File getBlockPath(String path) {
        return new File(instance.RESEARCH_PATH, path);
    }
}
