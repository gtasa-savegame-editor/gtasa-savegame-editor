package nl.paulinternet.gtasaveedit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.io.*;
import java.nio.channels.FileChannel;

import static nl.paulinternet.gtasaveedit.view.Main.WINDOWS;

public class Settings implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final Logger log = LoggerFactory.getLogger(Settings.class);

    public static final int UNKNOWN = 0, NO = 1, YES = 2;
    public static final int DIR_DEFAULT = 1, DIR_ACTIVE = 2, DIR_CUSTOM = 3;

    private static final File settingsFile = getSettingsFile();
    private static String configDir;

    private static final Settings instance = loadSettings();

    private int savegameDirectoryType;
    private String customSavegameDirectory;
    private int warnOverwriteFile;
    private int warnDeleteFile;
    private String lookAndFeelClassName;
    private int sanAndreasDirectoryType;
    private String customSanAndreasDirectory;
    private int showClothes;
    private int windowWidth, windowHeight, windowMaximized;
    private int soundOnAboutPage;
    private int garagesEnabled;

    private Settings() {
        if (new File(configDir).mkdirs()) {
            log.info("Config directory created!");
        }
        migrateOldConfig();
    }

    private void migrateOldConfig() {
        File oldSettingsFile = new File(System.getProperty("user.home"), ".gta_sa_savegame_editor");
        if (oldSettingsFile.exists()) {
            try {
                if (settingsFile.createNewFile()) {
                    log.info("New config file created!");
                }
                FileChannel srcStream = new FileInputStream(oldSettingsFile).getChannel();
                new FileOutputStream(settingsFile).getChannel().transferFrom(srcStream, 0, srcStream.size());
            } catch (IOException e) {
                log.warn("Error migrating config!", e);
            }
            if (oldSettingsFile.delete()) {
                log.info("Old config file deleted!");
            } else {
                log.warn("Unable to delete old config file!");
            }
        }
    }

    private static File getSettingsFile() {
        if (WINDOWS) {
            if (!System.getenv("APPDATA").isEmpty()) {
                configDir = System.getenv("APPDATA") + File.separator + "gta-sa_savegame_editor";
            } else {
                log.warn("Unable to determine appdata folder! Falling back to old config dir!");
                return new File(System.getProperty("user.home"), ".gta_sa_savegame_editor");
            }
        } else {
            String xdgConfigHome = System.getenv("XDG_CONFIG_HOME");
            if (xdgConfigHome == null || xdgConfigHome.isBlank() || xdgConfigHome.isEmpty()) {
                configDir = System.getProperty("user.home") + File.separator + ".config" + File.separator + "gta-sa_savegame_editor";
            } else {
                configDir = xdgConfigHome + File.separator + "gta-sa_savegame_editor";
            }
        }
        return new File(configDir, "config");
    }

    public static int getSavegameDirectoryType() {
        return instance.savegameDirectoryType;
    }

    public static void setSavegameDirectoryType(int savegameDirectoryType) {
        instance.savegameDirectoryType = savegameDirectoryType;
    }

    public static String getCustomSavegameDirectory() {
        return instance.customSavegameDirectory;
    }

    public static void setCustomSavegameDirectory(String customSavegameDirectory) {
        instance.customSavegameDirectory = customSavegameDirectory;
    }

    public static int getWarnOverwriteFile() {
        return instance.warnOverwriteFile;
    }

    public static void setWarnOverwriteFile(int warnOverwriteFile) {
        instance.warnOverwriteFile = warnOverwriteFile;
    }

    public static int getWarnDeleteFile() {
        return instance.warnDeleteFile;
    }

    public static void setWarnDeleteFile(int warnDeleteFile) {
        instance.warnDeleteFile = warnDeleteFile;
    }

    public static String getLookAndFeelClassName() {
        return instance.lookAndFeelClassName;
    }

    public static void setLookAndFeelClassName(String lookAndFeelClassName) {
        instance.lookAndFeelClassName = lookAndFeelClassName;
    }

    public static int getSanAndreasDirectoryType() {
        return instance.sanAndreasDirectoryType;
    }

    public static void setSanAndreasDirectoryType(int sanAndreasDirectoryType) {
        instance.sanAndreasDirectoryType = sanAndreasDirectoryType;
    }

    public static String getCustomSanAndreasDirectory() {
        return instance.customSanAndreasDirectory;
    }

    public static void setCustomSanAndreasDirectory(String customSanAndreasDirectory) {
        instance.customSanAndreasDirectory = customSanAndreasDirectory;
    }

    public static int getShowClothes() {
        return instance.showClothes;
    }

    public static void setShowClothes(int showClothes) {
        instance.showClothes = showClothes;
    }

    public static int getWindowWidth() {
        return instance.windowWidth;
    }

    public static void setWindowWidth(int windowWidth) {
        instance.windowWidth = windowWidth;
    }

    public static int getWindowHeight() {
        return instance.windowHeight;
    }

    public static void setWindowHeight(int windowHeight) {
        instance.windowHeight = windowHeight;
    }

    public static int getWindowMaximized() {
        return instance.windowMaximized;
    }

    public static void setWindowMaximized(int windowMaximized) {
        instance.windowMaximized = windowMaximized;
    }

    public static int getSoundOnAboutPage() {
        return instance.soundOnAboutPage;
    }

    public static void setSoundOnAboutPage(int soundOnAboutPage) {
        instance.soundOnAboutPage = soundOnAboutPage;
    }

    public static int getGaragesEnabled() {
        return instance.garagesEnabled;
    }

    public static void setGaragesEnabled(int garagesEnabled) {
        instance.garagesEnabled = garagesEnabled;
    }

    public static void save() {
        // Try to save the settings
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(settingsFile))) {
            out.writeObject(instance);
        } catch (IOException ignored) {
        }
    }

    private static Settings loadSettings() {
        // Read settings

        Settings settings;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(settingsFile))) {
            settings = (Settings) in.readObject();
        } catch (Exception e) {
            settings = new Settings();
        }

        // load default settings if needed
        loadDefaultsIfNeeded(settings);

        // Return
        return settings;
    }

    /**
     * This method checks the settings for {@link Settings#UNKNOWN} and initializes those with the default value.
     *
     * @param settings the settings to check
     */
    private static void loadDefaultsIfNeeded(Settings settings) {
        if (settings.savegameDirectoryType == UNKNOWN) settings.savegameDirectoryType = DIR_DEFAULT;
        if (settings.sanAndreasDirectoryType == UNKNOWN) settings.sanAndreasDirectoryType = DIR_DEFAULT;
        if (settings.customSavegameDirectory == null) settings.customSavegameDirectory = "";
        if (settings.warnOverwriteFile == UNKNOWN) settings.warnOverwriteFile = YES;
        if (settings.warnDeleteFile == UNKNOWN) settings.warnDeleteFile = YES;
        if (settings.lookAndFeelClassName == null)
            settings.lookAndFeelClassName = UIManager.getSystemLookAndFeelClassName();
        if (settings.customSanAndreasDirectory == null) settings.customSanAndreasDirectory = "";
        if (settings.showClothes == UNKNOWN) settings.showClothes = YES;
        if (settings.windowWidth == 0) settings.windowWidth = 900;
        if (settings.windowHeight == 0) settings.windowHeight = 900;
        if (settings.windowMaximized == UNKNOWN) settings.windowMaximized = NO;
        if (settings.soundOnAboutPage == UNKNOWN) settings.soundOnAboutPage = YES;
        if (settings.garagesEnabled == UNKNOWN) settings.garagesEnabled = NO;
    }

}
