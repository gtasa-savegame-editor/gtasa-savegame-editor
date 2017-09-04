package nl.paulinternet.gtasaveedit.view.pages;

import nl.paulinternet.gtasaveedit.model.io.FileSystem;
import nl.paulinternet.gtasaveedit.model.Model;
import nl.paulinternet.gtasaveedit.model.Settings;
import nl.paulinternet.gtasaveedit.model.steam.SettingVariables;
import nl.paulinternet.gtasaveedit.view.*;
import nl.paulinternet.gtasaveedit.view.component.ImageComponent;
import nl.paulinternet.gtasaveedit.view.component.LookAndFeelComboBox;
import nl.paulinternet.gtasaveedit.view.connected.ConnectedCheckbox;
import nl.paulinternet.gtasaveedit.view.connected.ConnectedRadioButtons;
import nl.paulinternet.gtasaveedit.view.connected.ConnectedTextField;
import nl.paulinternet.gtasaveedit.view.Images;
import nl.paulinternet.gtasaveedit.view.swing.PButton;
import nl.paulinternet.gtasaveedit.view.swing.XBox;
import nl.paulinternet.gtasaveedit.view.swing.YBox;
import nl.paulinternet.gtasaveedit.view.window.MainWindow;

import javax.swing.*;
import java.io.File;

public class PageOptions extends Page {
    private PButton buttonApply, buttonCancel;
    private XBox xboxSavegameDirExists, xboxSaDirExists, xboxSaExeExists;

    public PageOptions() {
        super("Options", true);
        SettingVariables settings = Model.editSettings;

        // Create buttons
        PButton buttonBrowse = new PButton("Browse...");
        PButton buttonBrowseSa = new PButton("Browse...");
        PButton buttonCurrentWindowSize = new PButton("Use current size");

        // Create handler for buttons
        buttonBrowse.onClick().addHandler(this, "browseSavegameDir");
        buttonBrowseSa.onClick().addHandler(this, "browseSanAndreasDir");
        buttonCurrentWindowSize.onClick().addHandler(this, "currentWindowSize");

        // init warning boxes
        initWarningBoxes();

        // create radio buttons
        ConnectedRadioButtons savedirButtons = new ConnectedRadioButtons(settings.savegameDirectoryType);
        ConnectedRadioButtons sadirButtons = new ConnectedRadioButtons(settings.sanAndreasDirectoryType);
        ConnectedRadioButtons maximized = new ConnectedRadioButtons(settings.windowMaximized);

        // generate main box!
        // To understand this call, look at that method!
        YBox ybox = generateMainBox(
                settings,
                savedirButtons,
                sadirButtons,
                maximized,
                generateFileSelectionBox(
                        buttonBrowse,
                        savedirButtons.create(Settings.DIR_CUSTOM, "Other directory:"),
                        new ConnectedTextField(settings.customSavegameDirectory)),
                generateFileSelectionBox(
                        buttonBrowseSa,
                        sadirButtons.create(Settings.DIR_CUSTOM, "Other directory:"),
                        new ConnectedTextField(settings.customSanAndreasDirectory)),
                generateLookAndFeelBox(),
                generateApplyBox(),
                generateWindowSizeBox(settings, maximized),
                buttonCurrentWindowSize,
                getDetectedSaDirOrError());

        setComponent(ybox, true);

        // Init
        cancel();
        changesMadeChanged();
        checkSavegameDir();
        checkSaDir();

        // Events
        initEvents(settings);
    }

    /**
     * Adds event handlers.
     *
     * @param settings the settings.
     */
    private void initEvents(SettingVariables settings) {
        settings.savegameDirectoryType.onChange().addHandler(this, "checkSavegameDir");
        settings.customSavegameDirectory.onChange().addHandler(this, "checkSavegameDir");
        settings.customSavegameDirectory.onChange().addHandler(this, "customSavegameDirectoryChanged");
        settings.sanAndreasDirectoryType.onChange().addHandler(this, "checkSaDir");
        settings.customSanAndreasDirectory.onChange().addHandler(this, "checkSaDir");
        settings.customSanAndreasDirectory.onChange().addHandler(this, "customSanAndreasDirectoryChanged");
        settings.windowWidth.onChange().addHandler(this, "windowSizeChanged");
        settings.windowHeight.onChange().addHandler(this, "windowSizeChanged");
        settings.changesMade.onChange().addHandler(this, "changesMadeChanged");
    }

    /**
     * See return statement.
     *
     * @return Path to GTA:SA or error message.
     */
    private String getDetectedSaDirOrError() {
        String detectedSaDir;
        if (!Main.MAC && !Main.WINDOWS) {
            detectedSaDir = "Windows and Mac only";
        } else if (Main.WINDOWS && !FileSystem.isDllLoaded()) {
            detectedSaDir = "Detection failed";
        } else if (FileSystem.detectedSaDir == null) {
            detectedSaDir = "Not found";
        } else {
            detectedSaDir = FileSystem.detectedSaDir.getPath();
        }
        return detectedSaDir;
    }

    /**
     * This method was written to de-clutter the constructor.
     * It initializes the settings screen with all views on it.
     *
     * @param settings                the {@link SettingVariables}.
     * @param savedirButtons          The saveDir buttons. Also needed by the customPanel.
     * @param sadirButtons            The saDir buttons. Also needed by the xboxSanAndreas.
     * @param maximized               The maxmized radios. Also needed by the xboxWindowSize.
     * @param customPanel             The custom save game folder panel.
     * @param xboxSanAndreas          The sa installation dir panel.
     * @param xboxLookAndFeel         The look and feel panel.
     * @param xboxApply               The apply box.
     * @param xboxWindowSize          the window size panel.
     * @param buttonCurrentWindowSize the currentWindowSize button.
     * @param detectedSaDir           the detectedSADir string. See {@link PageOptions#getDetectedSaDirOrError()}.
     * @return The generated YBox.
     */
    private YBox generateMainBox(SettingVariables settings, ConnectedRadioButtons savedirButtons, ConnectedRadioButtons sadirButtons, ConnectedRadioButtons maximized, XBox customPanel, XBox xboxSanAndreas, XBox xboxLookAndFeel, XBox xboxApply, XBox xboxWindowSize, PButton buttonCurrentWindowSize, String detectedSaDir) {
        YBox ybox = new YBox();
        ybox.add(new JLabel("You can change settings here if you like."));
        ybox.addSeparator(10);
        ybox.add(new JLabel("<html><b>Where are the savegame files located?"));
        ybox.add(savedirButtons.create(Settings.DIR_DEFAULT, "Default directory (" + FileSystem.getDefaultSavegameDirectory() + ")"));
        ybox.add(savedirButtons.create(Settings.DIR_ACTIVE, "Active directory (" + FileSystem.activeDir + ")"));
        ybox.add(customPanel);
        ybox.add(xboxSavegameDirExists);
        ybox.addSeparator(10);
        ybox.add(new JLabel("<html><b>Where is GTA San Andreas located?"));
        ybox.add(sadirButtons.create(Settings.DIR_DEFAULT, "Detected directory (" + detectedSaDir + ")"));
        ybox.add(xboxSanAndreas);
        ybox.add(xboxSaDirExists);
        ybox.add(xboxSaExeExists);
        ybox.addSeparator(10);
        ybox.add(new ConnectedCheckbox(settings.warnOverwriteFile, "Warn before overwriting a file"));
        ybox.add(new ConnectedCheckbox(settings.warnDeleteFile, "Warn before deleting a file"));
        ybox.addSeparator(10);
        ybox.add(xboxLookAndFeel);
        ybox.addSeparator(10);
        ybox.add(new JLabel("<html><b>MainWindow size at startup:"));
        ybox.add(maximized.create(Settings.YES, "Maximized"));
        ybox.add(xboxWindowSize);
        ybox.addSpace(5);
        ybox.add(buttonCurrentWindowSize, 0, 0.0f, 0.0f);
        ybox.addSeparator(10);
        ybox.add(new ConnectedCheckbox(settings.soundOnAboutPage, "Play sound on about page"));
        ybox.addSeparator(10);
        ybox.add(new JLabel("<html><b>Don't forget to click on Apply after making changes."), 1, 0.5f, 1);
        ybox.add(xboxApply);
        ybox.setBorder(10);
        return ybox;
    }

    /**
     * Generates the windowSize box.
     *
     * @param settings  the {@link SettingVariables}.
     * @param maximized the maximized radios.
     * @return the generated XBox.
     */
    private XBox generateWindowSizeBox(SettingVariables settings, ConnectedRadioButtons maximized) {
        XBox xboxWindowSize = new XBox();
        xboxWindowSize.add(maximized.create(Settings.NO, "Size:"));
        xboxWindowSize.addSpace(5);
        xboxWindowSize.add(new ConnectedTextField(settings.windowWidth));
        xboxWindowSize.addSpace(5);
        xboxWindowSize.add(new JLabel("x"));
        xboxWindowSize.addSpace(5);
        xboxWindowSize.add(new ConnectedTextField(settings.windowHeight));
        return xboxWindowSize;
    }

    /**
     * Initializes some views that are fields.
     */
    private void initWarningBoxes() {
        // Create panel
        xboxSavegameDirExists = new XBox();
        xboxSavegameDirExists.add(new ImageComponent(Images.WARNING));
        xboxSavegameDirExists.addSpace(5);
        xboxSavegameDirExists.add(new JLabel("The directory does not exist"));
        xboxSavegameDirExists.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

        xboxSaDirExists = new XBox();
        xboxSaDirExists.add(new ImageComponent(Images.WARNING));
        xboxSaDirExists.addSpace(5);
        xboxSaDirExists.add(new JLabel("The directory does not exist"));
        xboxSaDirExists.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

        xboxSaExeExists = new XBox();
        xboxSaExeExists.add(new ImageComponent(Images.WARNING));
        xboxSaExeExists.addSpace(5);
        xboxSaExeExists.add(new JLabel("The GTA executable file (gta_sa.exe or gta-sa.exe), does not exist in this directory"));
        xboxSaExeExists.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
    }

    /**
     * Generates the "Look and Feel" panel.
     *
     * @return the generated XBox.
     */
    private XBox generateLookAndFeelBox() {
        XBox xboxLookAndFeel = new XBox();
        xboxLookAndFeel.add(new JLabel("Look and feel:"));
        xboxLookAndFeel.addSpace(10);
        xboxLookAndFeel.add(new LookAndFeelComboBox());
        return xboxLookAndFeel;
    }

    /**
     * Generates a panel containing a file selection dialog.
     *
     * @return the generated XBox.
     */
    private XBox generateFileSelectionBox(PButton button, JRadioButton comp, ConnectedTextField comp2) {
        XBox customPanel = new XBox();
        customPanel.add(comp);
        customPanel.add(comp2, 1);
        customPanel.addSpace(5);
        customPanel.add(button);
        return customPanel;
    }

    /**
     * Generates the "Apply" panel.
     *
     * @return the generated XBox.
     */
    private XBox generateApplyBox() {
        // Create apply/cancel xbox
        buttonApply = new PButton("Apply");
        buttonApply.onClick().addHandler(this, "apply");

        buttonCancel = new PButton("Cancel");
        buttonCancel.onClick().addHandler(this, "cancel");

        XBox xboxApply = new XBox();
        xboxApply.addSpace(0, 1);
        xboxApply.add(buttonApply);
        xboxApply.addSpace(10);
        xboxApply.add(buttonCancel);
        return xboxApply;
    }

    @SuppressWarnings("unused") // Used as onClick
    public void currentWindowSize() {
        boolean maximized = (MainWindow.instance.getExtendedState() & JFrame.MAXIMIZED_BOTH) != 0;
        Model.editSettings.windowMaximized.setIntValue(maximized ? Settings.YES : Settings.NO);
        if (!maximized) {
            Model.editSettings.windowWidth.setIntValue(MainWindow.instance.getWidth());
            Model.editSettings.windowHeight.setIntValue(MainWindow.instance.getHeight());
        }
    }

    @SuppressWarnings("WeakerAccess") // Used as onClick
    public void checkSavegameDir() {
        File dir = null;

        switch (Model.editSettings.savegameDirectoryType.getIntValue()) {
            case Settings.DIR_DEFAULT:
                dir = FileSystem.getDefaultSavegameDirectory();
                break;
            case Settings.DIR_ACTIVE:
                dir = FileSystem.activeDir;
                break;
            case Settings.DIR_CUSTOM:
                dir = new File(Model.editSettings.customSavegameDirectory.getValue());
                break;
        }

        if (dir != null) {
            xboxSavegameDirExists.setVisible(!dir.exists());
        }
    }

    @SuppressWarnings("WeakerAccess") // Used as onClick
    public void checkSaDir() {
        File dir = null;

        switch (Model.editSettings.sanAndreasDirectoryType.getIntValue()) {
            case Settings.DIR_DEFAULT:
                dir = FileSystem.detectedSaDir;
                break;
            case Settings.DIR_CUSTOM:
                dir = new File(Model.editSettings.customSanAndreasDirectory.getValue());
                break;
        }

        if (dir != null && dir.exists()) {
            xboxSaDirExists.setVisible(false);
            xboxSaExeExists.setVisible(!Main.MAC && (!(new File(dir, "gta_sa.exe").exists() || new File(dir, "gta-sa.exe").exists())));
        } else {
            xboxSaDirExists.setVisible(true);
            xboxSaExeExists.setVisible(false);
        }
    }

    @SuppressWarnings("WeakerAccess") // used in event
    public void changesMadeChanged() {
        boolean changes = Model.editSettings.changesMade.getBooleanValue();
        buttonCancel.setEnabled(changes);
        buttonApply.setEnabled(changes);

        if (Main.MAC) {
            if (changes) {
                com.apple.eawt.Application.getApplication().setDockIconBadge("❕"); // emojis work 😱
            } else {
                com.apple.eawt.Application.getApplication().setDockIconBadge(null);
            }
        }
    }

    @SuppressWarnings("unused") // used in event
    public void customSavegameDirectoryChanged() {
        Model.editSettings.savegameDirectoryType.setIntValue(Settings.DIR_CUSTOM);
    }

    @SuppressWarnings("unused") // used in event
    public void customSanAndreasDirectoryChanged() {
        Model.editSettings.sanAndreasDirectoryType.setIntValue(Settings.DIR_CUSTOM);
    }

    @SuppressWarnings("unused") // used in event
    public void windowSizeChanged() {
        Model.editSettings.windowMaximized.setIntValue(Settings.NO);
    }

    @SuppressWarnings("unused") // used in event
    public void browseSavegameDir() {
        // Show file chooser dialog
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select savegame directory");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showDialog(MainWindow.instance, "OK");

        // Change
        if (result == JFileChooser.APPROVE_OPTION) {
            Model.editSettings.customSavegameDirectory.setText(fileChooser.getSelectedFile().toString());
        }
    }

    @SuppressWarnings("unused") // used in event
    public void browseSanAndreasDir() {
        // Show file chooser dialog
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select GTA San Andreas directory");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showDialog(MainWindow.instance, "OK");

        // Change
        if (result == JFileChooser.APPROVE_OPTION) {
            Model.editSettings.customSanAndreasDirectory.setText(fileChooser.getSelectedFile().toString());
        }
    }

    @SuppressWarnings("WeakerAccess") // used in event
    public void cancel() {
        Model.editSettings.copyFromSettings();
    }

    @SuppressWarnings("unused") // used in event
    public void apply() {
        Model.editSettings.applySettings();
    }
}
