package nl.paulinternet.gtasaveedit.view;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import nl.paulinternet.gtasaveedit.model.ErrorMessageException;
import nl.paulinternet.gtasaveedit.model.Model;
import nl.paulinternet.gtasaveedit.model.Savegame;
import nl.paulinternet.gtasaveedit.model.Settings;
import nl.paulinternet.gtasaveedit.view.swing.PMenuItem;

public class QuickSave extends PMenuItem
{
	private int number;
	
	public QuickSave (int number) {
		this.number = number;
		
		// Accelerator
		setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0 + number, (Main.MAC ? ActionEvent.META_MASK : ActionEvent.CTRL_MASK) | ActionEvent.SHIFT_MASK));
		
		// Observe
		Model.quickLoadUpdate.addHandler(this, "updateText");
		onClick().addHandler(this, "saveFile");
		
		// Set text
		updateText();
	}
	
	public void updateText () {
		String title = Model.quickLoad.get(number).getValue();
		if (title == null) {
			setText(number + ".");
		}
		else {
			setText(number + ". " + title);
		}
	}

	public void saveFile () {
		try {
			File file = Model.getSavegameFile(number);
			if (Settings.getWarnOverwriteFile() == Settings.YES && file.exists()) {
				int result = JOptionPane.showConfirmDialog(
					Window.instance,
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
			JOptionPane.showMessageDialog(Window.instance, e.getMessage(), e.getTitle(), JOptionPane.ERROR_MESSAGE);
		}
	}
}
