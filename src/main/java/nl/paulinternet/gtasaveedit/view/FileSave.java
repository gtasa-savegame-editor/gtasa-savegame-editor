package nl.paulinternet.gtasaveedit.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import nl.paulinternet.gtasaveedit.model.exceptions.ErrorMessageException;
import nl.paulinternet.gtasaveedit.model.FileSystem;
import nl.paulinternet.gtasaveedit.model.savegame.Savegame;
import nl.paulinternet.gtasaveedit.model.Settings;

class FileSave extends JMenuItem implements ActionListener
{
	public FileSave () {
		super("Save...");
		setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Main.MAC ? ActionEvent.META_MASK : ActionEvent.CTRL_MASK));
		addActionListener(this);
	}
	
	@Override
	public void actionPerformed (ActionEvent ev) {
		// Create filechooser
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(FileSystem.getSavegameDirectory());
		fileChooser.setDialogTitle("Save file");
		
		// Show dialog
		int result = fileChooser.showSaveDialog(Window.instance);
		
		// Do something
		if (result == JFileChooser.APPROVE_OPTION) {
			try {
				File file = fileChooser.getSelectedFile();
				if (Settings.getWarnOverwriteFile() == Settings.YES && file.exists()) {
					result = JOptionPane.showConfirmDialog(
						Window.instance,
						"The file \"" + file + "\" already exists.\nDo you want to overwrite it?",
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
}
