package nl.paulinternet.gtasaveedit.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import nl.paulinternet.gtasaveedit.model.ErrorMessageException;
import nl.paulinternet.gtasaveedit.model.FileSystem;
import nl.paulinternet.gtasaveedit.model.Savegame;

class FileLoad extends JMenuItem implements ActionListener
{
	public FileLoad () {
		super("Load...");
		setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Main.MAC ? ActionEvent.META_MASK : ActionEvent.CTRL_MASK));
		addActionListener(this);
	}
	
	@Override
	public void actionPerformed (ActionEvent ev) {
		// Create filechooser
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(FileSystem.getSavegameDirectory());
		fileChooser.setDialogTitle("Load file");
		
		// Show dialog
		int result = fileChooser.showOpenDialog(Window.instance);
		
		// Do something
		if (result == JFileChooser.APPROVE_OPTION) {
			try {
				Savegame.load(fileChooser.getSelectedFile());
			}
			catch (ErrorMessageException e) {
				JOptionPane.showMessageDialog(Window.instance, e.getMessage(), e.getTitle(), JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
