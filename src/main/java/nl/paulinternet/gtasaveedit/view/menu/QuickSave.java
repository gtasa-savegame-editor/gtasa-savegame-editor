package nl.paulinternet.gtasaveedit.view.menu;

import nl.paulinternet.gtasaveedit.model.Model;
import nl.paulinternet.gtasaveedit.model.Settings;
import nl.paulinternet.gtasaveedit.model.exceptions.ErrorMessageException;
import nl.paulinternet.gtasaveedit.model.savegame.Savegame;
import nl.paulinternet.gtasaveedit.view.Main;
import nl.paulinternet.gtasaveedit.view.window.MainWindow;
import nl.paulinternet.gtasaveedit.view.swing.PMenuItem;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class QuickSave extends PMenuItem
{
	private int number;
	
	public QuickSave (int number) {
		this.number = number;
		
		// Accelerator
		setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0 + number, (Main.MAC ? InputEvent.META_MASK : InputEvent.CTRL_MASK) | InputEvent.SHIFT_MASK));
		
		// Observe
		Model.quickLoadUpdate.addHandler(this, "updateText");
		onClick().addHandler(this, "saveFile");
		
		// Set text
		updateText();
	}
	
	@SuppressWarnings("WeakerAccess") //used in handler
	public void updateText () {
		String title = Model.quickLoad.get(number).getValue();
		if (title == null) {
			setText(number + ".");
		}
		else {
			setText(number + ". " + title);
		}
	}


	@SuppressWarnings("unused") //used in handler
	public void saveFile () {
		try {
			File file = Model.getSavegameFile(number);
			if (Settings.getWarnOverwriteFile() == Settings.YES && file.exists()) {
				int result = JOptionPane.showConfirmDialog(
					MainWindow.instance,
					"Savegame " + number + " already exists.\nDo you want to overwrite it?",
					"Overwrite file?",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE
				);
				
				if (result == JOptionPane.YES_OPTION) Savegame.save(file);
			}
			else {
				Savegame.save(file);
			}
		}
		catch (ErrorMessageException e) {
			JOptionPane.showMessageDialog(MainWindow.instance, e.getMessage(), e.getTitle(), JOptionPane.ERROR_MESSAGE);
		}
	}
}
