package nl.paulinternet.gtasaveedit.model.steam;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import nl.paulinternet.gtasaveedit.model.*;
import nl.paulinternet.gtasaveedit.model.variables.*;
import nl.paulinternet.gtasaveedit.view.window.MainWindow;

public class SettingVariables
{
	public final ReportableEvent settingsChanged = new ReportableEvent();
	
	public final VariableInteger savegameDirectoryType = new VariableIntegerImpl();
	public final VariableInteger sanAndreasDirectoryType = new VariableIntegerImpl();
	public final VariableString customSavegameDirectory = new VariableString();
	public final VariableString customSanAndreasDirectory = new VariableString();
	public final VariableBoolean showClothes = new VariableBooleanImpl();
	public final VariableBoolean warnOverwriteFile = new VariableBooleanImpl();
	public final VariableBoolean warnDeleteFile = new VariableBooleanImpl();
	public final VariableString lookAndFeelClassName = new VariableString();
	public final VariableIntegerImpl windowWidth = new VariableIntegerImpl();
	public final VariableIntegerImpl windowHeight = new VariableIntegerImpl();
	public final VariableInteger windowMaximized = new VariableIntegerImpl();
	public final VariableBoolean soundOnAboutPage = new VariableBooleanImpl();
	public final VariableBoolean changesMade = new VariableBooleanImpl();

    public SettingVariables () {
		MethodInvoker changed = new MethodInvoker(changesMade, "setBooleanValue", true);
		
		savegameDirectoryType.onChange().addHandler(changed);
		sanAndreasDirectoryType.onChange().addHandler(changed);
		customSavegameDirectory.onChange().addHandler(changed);
		customSanAndreasDirectory.onChange().addHandler(changed);
		showClothes.onChange().addHandler(changed);
		warnOverwriteFile.onChange().addHandler(changed);
		warnDeleteFile.onChange().addHandler(changed);
		lookAndFeelClassName.onChange().addHandler(changed);
		windowWidth.onChange().addHandler(changed);
		windowHeight.onChange().addHandler(changed);
		windowMaximized.onChange().addHandler(changed);
		soundOnAboutPage.onChange().addHandler(changed);
	}
	
	public void copyFromSettings () {
		customSavegameDirectory.setValue(Settings.getCustomSavegameDirectory());
		customSanAndreasDirectory.setValue(Settings.getCustomSanAndreasDirectory());
		savegameDirectoryType.setIntValue(Settings.getSavegameDirectoryType());
		sanAndreasDirectoryType.setIntValue(Settings.getSanAndreasDirectoryType());
		showClothes.setBooleanValue(Settings.getShowClothes() == Settings.YES);
		warnOverwriteFile.setBooleanValue(Settings.getWarnOverwriteFile() == Settings.YES);
		warnDeleteFile.setBooleanValue(Settings.getWarnDeleteFile() == Settings.YES);
		lookAndFeelClassName.setValue(Settings.getLookAndFeelClassName());
		windowWidth.setIntValue(Settings.getWindowWidth());
		windowHeight.setIntValue(Settings.getWindowHeight());
		windowMaximized.setIntValue(Settings.getWindowMaximized());
		soundOnAboutPage.setBooleanValue(Settings.getSoundOnAboutPage() == Settings.YES);
		
		changesMade.setBooleanValue(false);
	}
	
	public void applySettings () {
		Settings.setSavegameDirectoryType(savegameDirectoryType.getIntValue());
		Settings.setSanAndreasDirectoryType(sanAndreasDirectoryType.getIntValue());
		Settings.setCustomSavegameDirectory(customSavegameDirectory.getValue());
		Settings.setCustomSanAndreasDirectory(customSanAndreasDirectory.getValue());
		Settings.setShowClothes(showClothes.getBooleanValue() ? Settings.YES : Settings.NO);
		Settings.setWarnOverwriteFile(warnOverwriteFile.getBooleanValue() ? Settings.YES : Settings.NO);
		Settings.setWarnDeleteFile(warnDeleteFile.getBooleanValue() ? Settings.YES : Settings.NO);
		Settings.setLookAndFeelClassName(lookAndFeelClassName.getValue());
		Settings.setWindowWidth(windowWidth.getIntValue());
		Settings.setWindowHeight(windowHeight.getIntValue());
		Settings.setWindowMaximized(windowMaximized.getIntValue());
		Settings.setSoundOnAboutPage(soundOnAboutPage.getBooleanValue() ? Settings.YES : Settings.NO);
		
		Settings.save();
		
		// Look and feel
		if (!UIManager.getLookAndFeel().getClass().getName().equals(Settings.getLookAndFeelClassName())) {
			try {
				UIManager.setLookAndFeel(Settings.getLookAndFeelClassName());
				SwingUtilities.updateComponentTreeUI(MainWindow.instance);
			}
			catch (Exception ignored) {}
		}
		
		// Update quick load
		Model.updateQuickLoad();
		
		// Update clothes
		Model.updatePlayerImg();
		
		// Disable buttons
		changesMade.setBooleanValue(false);
		
		// Settings changed
		settingsChanged.report();
	}
}
