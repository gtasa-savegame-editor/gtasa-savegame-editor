package nl.paulinternet.gtasaveedit.model;

import nl.paulinternet.gtasaveedit.FileSystem;
import nl.paulinternet.gtasaveedit.Settings;
import nl.paulinternet.gtasaveedit.event.MethodInvoker;
import nl.paulinternet.gtasaveedit.event.ReportableEvent;
import nl.paulinternet.gtasaveedit.extractor.FormDataHandler;
import nl.paulinternet.gtasaveedit.view.window.MainWindow;
import nl.paulinternet.libsavegame.CallbackHandler;
import nl.paulinternet.libsavegame.variables.Variable;

import javax.swing.*;

public class SettingVariables {
    public final ReportableEvent settingsChanged = new ReportableEvent();

    public final Variable<Integer> savegameDirectoryType = new Variable<>();
    public final Variable<Integer> sanAndreasDirectoryType = new Variable<>();
    public final Variable<String> customSavegameDirectory = new Variable<>();
    public final Variable<String> customSanAndreasDirectory = new Variable<>();
    public final Variable<Boolean> showClothes = new Variable<>();
    public final Variable<Boolean> warnOverwriteFile = new Variable<>();
    public final Variable<Boolean> warnDeleteFile = new Variable<>();
    public final Variable<String> lookAndFeelClassName = new Variable<>();
    public final Variable<Integer> windowWidth = new Variable<>();
    public final Variable<Integer> windowHeight = new Variable<>();
    public final Variable<Integer> windowMaximized = new Variable<>();
    public final Variable<Boolean> soundOnAboutPage = new Variable<>();
    public final Variable<Boolean> changesMade = new Variable<>();

    public SettingVariables() {
        CallbackHandler<Void> changed = v -> changesMade.setValue(true);

        savegameDirectoryType.setOnChange(v -> changed.handle(null));
        sanAndreasDirectoryType.setOnChange(v -> changed.handle(null));
        customSavegameDirectory.setOnChange(v -> changed.handle(null));
        customSanAndreasDirectory.setOnChange(v -> changed.handle(null));
        showClothes.setOnChange(v -> changed.handle(null));
        warnOverwriteFile.setOnChange(v -> changed.handle(null));
        warnDeleteFile.setOnChange(v -> changed.handle(null));
        lookAndFeelClassName.setOnChange(v -> changed.handle(null));
        windowWidth.setOnChange(v -> changed.handle(null));
        windowHeight.setOnChange(v -> changed.handle(null));
        windowMaximized.setOnChange(v -> changed.handle(null));
        soundOnAboutPage.setOnChange(v -> changed.handle(null));
    }

    public void copyFromSettings() {
        customSavegameDirectory.setValue(Settings.getCustomSavegameDirectory());
        customSanAndreasDirectory.setValue(Settings.getCustomSanAndreasDirectory());
        savegameDirectoryType.setValue(Settings.getSavegameDirectoryType());
        sanAndreasDirectoryType.setValue(Settings.getSanAndreasDirectoryType());
        showClothes.setValue(Settings.getShowClothes() == Settings.YES);
        warnOverwriteFile.setValue(Settings.getWarnOverwriteFile() == Settings.YES);
        warnDeleteFile.setValue(Settings.getWarnDeleteFile() == Settings.YES);
        lookAndFeelClassName.setValue(Settings.getLookAndFeelClassName());
        windowWidth.setValue(Settings.getWindowWidth());
        windowHeight.setValue(Settings.getWindowHeight());
        windowMaximized.setValue(Settings.getWindowMaximized());
        soundOnAboutPage.setValue(Settings.getSoundOnAboutPage() == Settings.YES);

        changesMade.setValue(false);
    }

    public void applySettings() {
        Settings.setSavegameDirectoryType(savegameDirectoryType.getValue());
        Settings.setSanAndreasDirectoryType(sanAndreasDirectoryType.getValue());
        Settings.setCustomSavegameDirectory(customSavegameDirectory.getValue());
        Settings.setCustomSanAndreasDirectory(customSanAndreasDirectory.getValue());
        Settings.setShowClothes(showClothes.getValue() ? Settings.YES : Settings.NO);
        Settings.setWarnOverwriteFile(warnOverwriteFile.getValue() ? Settings.YES : Settings.NO);
        Settings.setWarnDeleteFile(warnDeleteFile.getValue() ? Settings.YES : Settings.NO);
        Settings.setLookAndFeelClassName(lookAndFeelClassName.getValue());
        Settings.setWindowWidth(windowWidth.getValue());
        Settings.setWindowHeight(windowHeight.getValue());
        Settings.setWindowMaximized(windowMaximized.getValue());
        Settings.setSoundOnAboutPage(soundOnAboutPage.getValue() ? Settings.YES : Settings.NO);

        Settings.save();

        // Look and feel
        if (!UIManager.getLookAndFeel().getClass().getName().equals(Settings.getLookAndFeelClassName())) {
            try {
                UIManager.setLookAndFeel(Settings.getLookAndFeelClassName());
                SwingUtilities.updateComponentTreeUI(MainWindow.getInstance());
            } catch (Exception ignored) {
            }
        }

        // Update quick load
        SavegameModel.get(FileSystem.getSavegameDirectory()).updateQuickLoad();

        // Update clothes
        Model.updatePlayerImg();

        // Disable buttons
        changesMade.setValue(false);

        // Settings changed
        settingsChanged.report();
    }
}
