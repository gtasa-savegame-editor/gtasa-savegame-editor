package nl.paulinternet.gtasaveedit.view.pages;

import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

import nl.paulinternet.gtasaveedit.model.FileSystem;
import nl.paulinternet.gtasaveedit.model.Model;
import nl.paulinternet.gtasaveedit.model.steam.SettingVariables;
import nl.paulinternet.gtasaveedit.model.Settings;
import nl.paulinternet.gtasaveedit.view.ConnectedCheckbox;
import nl.paulinternet.gtasaveedit.view.ConnectedRadioButtons;
import nl.paulinternet.gtasaveedit.view.ConnectedTextField;
import nl.paulinternet.gtasaveedit.view.ImageComponent;
import nl.paulinternet.gtasaveedit.view.Images;
import nl.paulinternet.gtasaveedit.view.LookAndFeelComboBox;
import nl.paulinternet.gtasaveedit.view.Main;
import nl.paulinternet.gtasaveedit.view.Window;
import nl.paulinternet.gtasaveedit.view.swing.PButton;
import nl.paulinternet.gtasaveedit.view.swing.XBox;
import nl.paulinternet.gtasaveedit.view.swing.YBox;

public class PageOptions extends Page
{
	private PButton buttonApply, buttonCancel;
	private XBox xboxSavegameDirExists, xboxSaDirExists, xboxSaExeExists;
	
	public PageOptions () {
		super("Options", true);
		SettingVariables settings = Model.editSettings;

		// Create radio button groups
		ConnectedRadioButtons savedirButtons = new ConnectedRadioButtons(settings.savegameDirectoryType);
		ConnectedRadioButtons sadirButtons = new ConnectedRadioButtons(settings.sanAndreasDirectoryType);

		// Create xbox for custom savegame directory
		PButton buttonBrowse = new PButton("Browse...");
		buttonBrowse.onClick().addHandler(this, "browseSavegameDir");

		XBox customPanel = new XBox();
		customPanel.add(savedirButtons.create(Settings.DIR_CUSTOM, "Other directory:"));		
		customPanel.add(new ConnectedTextField(settings.customSavegameDirectory), 1);
		customPanel.addSpace(5);
		customPanel.add(buttonBrowse);

		// Create xbox for custom San Andreas directory
		PButton buttonBrowseSa = new PButton("Browse...");
		buttonBrowseSa.onClick().addHandler(this, "browseSanAndreasDir");
		
		XBox xboxSanAndreas = new XBox();
		xboxSanAndreas.add(sadirButtons.create(Settings.DIR_CUSTOM, "Other directory:"));
		xboxSanAndreas.add(new ConnectedTextField(settings.customSanAndreasDirectory), 1);
		xboxSanAndreas.addSpace(5);
		xboxSanAndreas.add(buttonBrowseSa);

		// Create xbox for look and feel
		XBox xboxLookAndFeel = new XBox();
		xboxLookAndFeel.add(new JLabel("Look and feel:"));
		xboxLookAndFeel.addSpace(10);
		xboxLookAndFeel.add(new LookAndFeelComboBox());
		
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

		String detectedSaDir;
		if (!Main.MAC && !Main.WINDOWS) {
			detectedSaDir = "Windows and Mac only";
		}
		else if (Main.WINDOWS && !FileSystem.isDllLoaded()) {
			detectedSaDir = "Detection failed";
		}
		else if (FileSystem.detectedSaDir == null) {
			detectedSaDir = "Not found";
		}
		else {
			detectedSaDir = FileSystem.detectedSaDir.getPath();
		}
		
		YBox ybox = new YBox();
		ybox.add(new JLabel("<html>You can change settings here if you like.<br>Don't forget to click on Apply after making changes."));
		ybox.addSeparator(10);
		ybox.add(new JLabel("Where are the savegame files located?"));
		ybox.add(savedirButtons.create(Settings.DIR_DEFAULT, "Default directory (" + FileSystem.getDefaultSavegameDirectory() + ")"));
		ybox.add(savedirButtons.create(Settings.DIR_ACTIVE, "Active directory (" + FileSystem.activeDir + ")"));
		ybox.add(customPanel);
		ybox.add(xboxSavegameDirExists);
		ybox.addSeparator(10);
		ybox.add(new JLabel("Where is GTA San Andreas located?"));
		ybox.add(sadirButtons.create(Settings.DIR_DEFAULT, "Detected directory (" + detectedSaDir + ")"));
		ybox.add(xboxSanAndreas);
		ybox.add(xboxSaDirExists);
		ybox.add(xboxSaExeExists);
		ybox.addSeparator(10);
		
		ConnectedRadioButtons maximized = new ConnectedRadioButtons(settings.windowMaximized);
		
		XBox xboxWindowSize = new XBox();
		xboxWindowSize.add(maximized.create(Settings.NO, "Size:"));
		xboxWindowSize.addSpace(5);
		xboxWindowSize.add(new ConnectedTextField(settings.windowWidth));
		xboxWindowSize.addSpace(5);
		xboxWindowSize.add(new JLabel("x"));
		xboxWindowSize.addSpace(5);
		xboxWindowSize.add(new ConnectedTextField(settings.windowHeight));
		
		PButton buttonCurrentWindowSize = new PButton("Use current size");
		buttonCurrentWindowSize.onClick().addHandler(this, "currentWindowSize");
		
		ybox.add(new ConnectedCheckbox(settings.warnOverwriteFile, "Warn before overwriting a file"));
		ybox.add(new ConnectedCheckbox(settings.warnDeleteFile, "Warn before deleting a file"));
		ybox.addSeparator(10);
		ybox.add(xboxLookAndFeel);
		ybox.addSeparator(10);
		ybox.add(new JLabel("Window size at startup:"));
		ybox.add(maximized.create(Settings.YES, "Maximized"));
		ybox.add(xboxWindowSize);
		ybox.addSpace(5);
		ybox.add(buttonCurrentWindowSize, 0, 0.0f, 0.0f);
		ybox.addSeparator(10);
		ybox.add(xboxApply);
		ybox.setBorder(10);
		setComponent(ybox, true);

		// Init
		cancel();
		changesMadeChanged();
		checkSavegameDir();
		checkSaDir();
		
		// Events
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
	
	public void currentWindowSize () {
		boolean maximized = (Window.instance.getExtendedState() & JFrame.MAXIMIZED_BOTH) != 0;
		Model.editSettings.windowMaximized.setIntValue(maximized ? Settings.YES : Settings.NO);
		if (!maximized) {
			Model.editSettings.windowWidth.setIntValue(Window.instance.getWidth());
			Model.editSettings.windowHeight.setIntValue(Window.instance.getHeight());
		}
	}
	
	public void checkSavegameDir () {
		File dir = null;
		
		switch (Model.editSettings.savegameDirectoryType.getIntValue()) {
			case Settings.DIR_DEFAULT: dir = FileSystem.getDefaultSavegameDirectory(); break;
			case Settings.DIR_ACTIVE: dir = FileSystem.activeDir; break;
			case Settings.DIR_CUSTOM: dir = new File(Model.editSettings.customSavegameDirectory.getValue()); break;
		}

		xboxSavegameDirExists.setVisible(!dir.exists());
	}
	
	public void checkSaDir () {
		File dir = null;
		
		switch (Model.editSettings.sanAndreasDirectoryType.getIntValue()) {
			case Settings.DIR_DEFAULT: dir = FileSystem.detectedSaDir; break;
			case Settings.DIR_CUSTOM: dir = new File(Model.editSettings.customSanAndreasDirectory.getValue()); break;
		}

		boolean modelFile = false;
		
		if (dir != null && dir.exists()) {
			modelFile = FileSystem.getPlayerImageFile(dir) != null && FileSystem.getPlayerImageFile(dir).exists();

			xboxSaDirExists.setVisible(false);
			xboxSaExeExists.setVisible(!Main.MAC && (!(new File(dir, "gta_sa.exe").exists() || new File(dir, "gta-sa.exe").exists())));
		}
		else {
			xboxSaDirExists.setVisible(true);
			xboxSaExeExists.setVisible(false);
		}
	}

	public void changesMadeChanged () {
		boolean changes = Model.editSettings.changesMade.getBooleanValue();
		buttonCancel.setEnabled(changes);
		buttonApply.setEnabled(changes);
	}
	
	public void customSavegameDirectoryChanged () {
		Model.editSettings.savegameDirectoryType.setIntValue(Settings.DIR_CUSTOM);
	}
	
	public void customSanAndreasDirectoryChanged () {
		Model.editSettings.sanAndreasDirectoryType.setIntValue(Settings.DIR_CUSTOM);
	}
	
	public void windowSizeChanged () {
		Model.editSettings.windowMaximized.setIntValue(Settings.NO);
	}

	public void browseSavegameDir () {
		// Show file chooser dialog
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Select savegame directory");
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int result = fileChooser.showDialog(Window.instance, "OK");
		
		// Change
		if (result == JFileChooser.APPROVE_OPTION) {
			Model.editSettings.customSavegameDirectory.setText(fileChooser.getSelectedFile().toString());
		}
	}
	
	public void browseSanAndreasDir () {
		// Show file chooser dialog
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Select GTA San Andreas directory");
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int result = fileChooser.showDialog(Window.instance, "OK");
		
		// Change
		if (result == JFileChooser.APPROVE_OPTION) {
			Model.editSettings.customSanAndreasDirectory.setText(fileChooser.getSelectedFile().toString());
		}
	}
	
	public void cancel () {
		Model.editSettings.copyFromSettings();
	}
	
	public void apply () {
		Model.editSettings.applySettings();
	}
}
