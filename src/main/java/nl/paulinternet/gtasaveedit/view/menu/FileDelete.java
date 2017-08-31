package nl.paulinternet.gtasaveedit.view.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import nl.paulinternet.gtasaveedit.model.FileSystem;
import nl.paulinternet.gtasaveedit.model.Model;
import nl.paulinternet.gtasaveedit.model.Settings;
import nl.paulinternet.gtasaveedit.view.window.MainWindow;

class FileDelete extends JMenuItem implements ActionListener
{
	public FileDelete () {
		super("Delete...");
		addActionListener(this);
	}
	
	@Override
	public void actionPerformed (ActionEvent ev) {
		// Create filechooser
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(FileSystem.getSavegameDirectory());
		fileChooser.setDialogTitle("Delete file(s)");
		fileChooser.setMultiSelectionEnabled(true);
		
		// Show dialog
		int result = fileChooser.showDialog(MainWindow.instance, "Delete");
		
		// Do something
		if (result == JFileChooser.APPROVE_OPTION) {
			File[] files = fileChooser.getSelectedFiles();
			if (Settings.getWarnOverwriteFile() == Settings.YES) {
				// Construct message
				String message, title;
				if (files.length == 1) {
					title = "Delete file?";
					message = "Are you sure you want to delete the file \"" + files[0] + "\"?";
				}
				else {
					title = "Delete files?";
					message = "Are you sure you want to delete the following files?";
					for (File file : files) message += "\n" + file;
				}

				// Show dialog
				result = JOptionPane.showConfirmDialog(
					MainWindow.instance,
					message,
					title,
					JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE
				);
				
				// Delete files
				if (result == JOptionPane.YES_OPTION) {
					for (File file : files) file.delete();
					Model.updateQuickLoad();
				}
			}
			else {
				// Delete files
				for (File file : files) file.delete();
				Model.updateQuickLoad();
			}
		}
	}
}
