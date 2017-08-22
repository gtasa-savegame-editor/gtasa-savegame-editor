package nl.paulinternet.gtasaveedit.view;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import nl.paulinternet.gtasaveedit.model.exceptions.ErrorMessageException;
import nl.paulinternet.gtasaveedit.model.Model;
import nl.paulinternet.gtasaveedit.model.savegame.Savegame;
import nl.paulinternet.gtasaveedit.view.swing.PMenuItem;

public class QuickLoad extends PMenuItem
{
	private int number;
	
	public QuickLoad (int number) {
		this.number = number;
		
		// Accelerator
		setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0 + number, Main.MAC ? ActionEvent.META_MASK : ActionEvent.CTRL_MASK));
		
		// Observe
		Model.quickLoadUpdate.addHandler(this, "updateText");
		onClick().addHandler(this, "loadSavegame");

		// Set text
		updateText();
	}

	public void loadSavegame () {
		try {
			Savegame.load(Model.getSavegameFile(number));
		}
		catch (ErrorMessageException e) {
			JOptionPane.showMessageDialog(Window.instance, e.getMessage(), e.getTitle(), JOptionPane.ERROR_MESSAGE);
		}
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
}
