package nl.paulinternet.gtasaveedit.view.menu;

import nl.paulinternet.gtasaveedit.model.Model;
import nl.paulinternet.gtasaveedit.model.exceptions.ErrorMessageException;
import nl.paulinternet.gtasaveedit.model.savegame.Savegame;
import nl.paulinternet.gtasaveedit.view.Main;
import nl.paulinternet.gtasaveedit.view.Window;
import nl.paulinternet.gtasaveedit.view.swing.PMenuItem;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class QuickLoad extends PMenuItem
{
	private int number;
	
	public QuickLoad (int number) {
		this.number = number;
		
		// Accelerator
		setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0 + number, Main.MAC ? InputEvent.META_MASK : InputEvent.CTRL_MASK));
		
		// Observe
		Model.quickLoadUpdate.addHandler(this, "updateText");
		onClick().addHandler(this, "loadSavegame");

		// Set text
		updateText();
	}

	@SuppressWarnings("unused") //used in event
	public void loadSavegame () {
		try {
			Savegame.load(Model.getSavegameFile(number));
		}
		catch (ErrorMessageException e) {
			JOptionPane.showMessageDialog(Window.instance, e.getMessage(), e.getTitle(), JOptionPane.ERROR_MESSAGE);
		}
	}
	
	@SuppressWarnings({"WeakerAccess", "Duplicates"}) //used in handler
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
