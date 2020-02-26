package nl.paulinternet.gtasaveedit.view.menu;

import nl.paulinternet.gtasaveedit.Settings;
import nl.paulinternet.gtasaveedit.FileSystem;
import nl.paulinternet.gtasaveedit.view.window.MainWindow;
import nl.paulinternet.libsavegame.Savegame;
import nl.paulinternet.libsavegame.exceptions.ErrorMessageException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import static nl.paulinternet.libsavegame.Util.MAC;

class FileSave extends JMenuItem implements ActionListener {
    public FileSave() {
        super("Save...");
        setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, MAC ? InputEvent.META_MASK : InputEvent.CTRL_MASK));
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        // Create filechooser
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(FileSystem.getSavegameDirectory());
        fileChooser.setDialogTitle("Save file");

        // Show dialog
        int result = fileChooser.showSaveDialog(MainWindow.getInstance());

        // Do something
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fileChooser.getSelectedFile();
                if (Settings.getWarnOverwriteFile() == Settings.YES && file.exists()) {
                    result = JOptionPane.showConfirmDialog(
                            MainWindow.getInstance(),
                            "The file \"" + file + "\" already exists.\nDo you want to overwrite it?",
                            "Overwrite file?",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );

                    if (result == JOptionPane.YES_OPTION) Savegame.get().save(file);
                } else {
                    Savegame.get().save(file);
                }
            } catch (ErrorMessageException e) {
                JOptionPane.showMessageDialog(MainWindow.getInstance(), e.getMessage(), e.getTitle(), JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
