package nl.paulinternet.gtasaveedit.view;

import java.io.File;

import javax.swing.JOptionPane;

import nl.paulinternet.gtasaveedit.model.Model;
import nl.paulinternet.gtasaveedit.model.Settings;
import nl.paulinternet.gtasaveedit.view.swing.PMenuItem;

public class QuickDelete extends PMenuItem
{
	private int number;
	
	public QuickDelete (int number) {
		this.number = number;
		
		// Observe
		Model.quickLoadUpdate.addHandler(this, "updateText");
		onClick().addHandler(this, "deleteSavegame");
		
		// Set text
		updateText();
	}
	
	public void updateText () {
		String title = Model.quickLoad.get(number).getValue();
		if (title == null) {
			setEnabled(false);
			setText(number + ".");
		}
		else {
			setEnabled(true);
			setText(number + ". " + title);
		}
	}

	public void deleteSavegame () {
		File file = Model.getSavegameFile(number);
		if (Settings.getWarnDeleteFile() == Settings.YES) {
			int result = JOptionPane.showConfirmDialog(
				Window.instance,
				"Are you sure you want to delete savegame " + number + "?",
				"Delete file?",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE
			);
			
			if (result == JOptionPane.YES_OPTION) {
				file.delete();
				Model.updateQuickLoad();
			}
		}
		else {
			file.delete();
			Model.updateQuickLoad();
		}
	}
}
