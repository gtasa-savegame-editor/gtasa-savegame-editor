package nl.paulinternet.gtasaveedit.model;

import nl.paulinternet.gtasaveedit.FileSystem;
import nl.paulinternet.gtasaveedit.Settings;
import nl.paulinternet.gtasaveedit.event.ReportableEvent;
import nl.paulinternet.gtasaveedit.view.ThemeManager;
import nl.paulinternet.libsavegame.CallbackHandler;
import nl.paulinternet.libsavegame.variables.Variable;

public class SettingVariables {

    public final ReportableEvent settingsChanged = new ReportableEvent();

    public final Variable<Integer> savegameDirectoryType = new Variable<>();
    public final Variable<Integer> sanAndreasDirectoryType = new Variable<>();
    public final Variable<String> customSavegameDirectory = new Variable<String>() {
        @Override
        public String getAllowedCharacters() {
            return super.getAllowedCharacters() + "/\\:_ +";
        }
    };
    public final Variable<String> customSanAndreasDirectory = new Variable<String>() {
        @Override
        public String getAllowedCharacters() {
            return super.getAllowedCharacters() + "/\\:_ ";
        }
    };
    public final Variable<Boolean> showClothes = new Variable<>();
    public final Variable<Boolean> warnOverwriteFile = new Variable<>();
    public final Variable<Boolean> warnDeleteFile = new Variable<>();
    public final Variable<Integer> themeMode = new Variable<>();
    public final Variable<Integer> windowWidth = new Variable<>();
    public final Variable<Integer> windowHeight = new Variable<>();
    public final Variable<Integer> windowMaximized = new Variable<>();
    public final Variable<Boolean> soundOnAboutPage = new Variable<>();
    public final Variable<Boolean> garagesEnabled = new Variable<>();
    public final Variable<Boolean> changesMade = new Variable<>();

    public SettingVariables() {
        CallbackHandler<Void> changed = v -> changesMade.setValue(Boolean.TRUE);

        savegameDirectoryType.addOnChangeListener(v -> changed.handle(null));
        sanAndreasDirectoryType.addOnChangeListener(v -> changed.handle(null));
        customSavegameDirectory.addOnChangeListener(v -> changed.handle(null));
        customSanAndreasDirectory.addOnChangeListener(v -> changed.handle(null));
        showClothes.addOnChangeListener(v -> changed.handle(null));
        warnOverwriteFile.addOnChangeListener(v -> changed.handle(null));
        warnDeleteFile.addOnChangeListener(v -> changed.handle(null));
        themeMode.addOnChangeListener(v -> changed.handle(null));
        windowWidth.addOnChangeListener(v -> changed.handle(null));
        windowHeight.addOnChangeListener(v -> changed.handle(null));
        windowMaximized.addOnChangeListener(v -> changed.handle(null));
        soundOnAboutPage.addOnChangeListener(v -> changed.handle(null));
        garagesEnabled.addOnChangeListener(v -> changed.handle(null));
    }

    public void copyFromSettings() {
        customSavegameDirectory.setValue(Settings.getCustomSavegameDirectory());
        customSanAndreasDirectory.setValue(Settings.getCustomSanAndreasDirectory());
        savegameDirectoryType.setValue(Settings.getSavegameDirectoryType());
        sanAndreasDirectoryType.setValue(Settings.getSanAndreasDirectoryType());
        showClothes.setValue(Settings.getShowClothes() == Settings.YES);
        warnOverwriteFile.setValue(Settings.getWarnOverwriteFile() == Settings.YES);
        warnDeleteFile.setValue(Settings.getWarnDeleteFile() == Settings.YES);
        themeMode.setValue(Settings.getThemeMode());
        windowWidth.setValue(Settings.getWindowWidth());
        windowHeight.setValue(Settings.getWindowHeight());
        windowMaximized.setValue(Settings.getWindowMaximized());
        soundOnAboutPage.setValue(Settings.getSoundOnAboutPage() == Settings.YES);
        garagesEnabled.setValue(Settings.getGaragesEnabled() == Settings.YES);

        changesMade.setValue(Boolean.FALSE);
    }

    public void applySettings() {
        Settings.setSavegameDirectoryType(savegameDirectoryType.getValue());
        Settings.setSanAndreasDirectoryType(sanAndreasDirectoryType.getValue());
        Settings.setCustomSavegameDirectory(customSavegameDirectory.getValue());
        Settings.setCustomSanAndreasDirectory(customSanAndreasDirectory.getValue());
        Settings.setShowClothes(showClothes.getValue() ? Settings.YES : Settings.NO);
        Settings.setWarnOverwriteFile(warnOverwriteFile.getValue() ? Settings.YES : Settings.NO);
        Settings.setWarnDeleteFile(warnDeleteFile.getValue() ? Settings.YES : Settings.NO);
        Settings.setThemeMode(themeMode.getValue());
        Settings.setWindowWidth(windowWidth.getValue());
        Settings.setWindowHeight(windowHeight.getValue());
        Settings.setWindowMaximized(windowMaximized.getValue());
        Settings.setSoundOnAboutPage(soundOnAboutPage.getValue() ? Settings.YES : Settings.NO);
        Settings.setGaragesEnabled(garagesEnabled.getValue() ? Settings.YES : Settings.NO);

        Settings.save();

        // Theme
        ThemeManager.install(Settings.getThemeMode());

        // Update quick load
        SavegameModel.get(FileSystem.getSavegameDirectory()).updateQuickLoad();

        // Update clothes
        Model.updatePlayerImg();

        // Disable buttons
        changesMade.setValue(Boolean.FALSE);

        // Settings changed
        settingsChanged.report();
    }
}
