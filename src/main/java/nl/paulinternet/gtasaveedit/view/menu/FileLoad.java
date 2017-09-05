package nl.paulinternet.gtasaveedit.view.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import nl.paulinternet.gtasaveedit.model.exceptions.ErrorMessageException;
import nl.paulinternet.gtasaveedit.model.io.FileSystem;
import nl.paulinternet.gtasaveedit.model.savegame.Savegame;
import nl.paulinternet.gtasaveedit.view.Main;
import nl.paulinternet.gtasaveedit.view.window.MainWindow;

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
		int result = fileChooser.showOpenDialog(MainWindow.getInstance());
		
		// Do something
		if (result == JFileChooser.APPROVE_OPTION) {
			try {
				Savegame.load(fileChooser.getSelectedFile());
			}
			catch (ErrorMessageException e) {
				JOptionPane.showMessageDialog(MainWindow.getInstance(), e.getMessage(), e.getTitle(), JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
