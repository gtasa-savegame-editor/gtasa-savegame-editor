package nl.paulinternet.gtasaveedit.view.menu;

import nl.paulinternet.gtasaveedit.FileSystem;
import nl.paulinternet.gtasaveedit.view.window.MainWindow;
import nl.paulinternet.libsavegame.Savegame;
import nl.paulinternet.libsavegame.exceptions.ErrorMessageException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import static nl.paulinternet.libsavegame.Util.MAC;

class FileLoad extends JMenuItem implements ActionListener {
    public FileLoad() {
        super("Load...");
        setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, MAC ? ActionEvent.META_MASK : ActionEvent.CTRL_MASK));
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        // Create filechooser
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(FileSystem.getSavegameDirectory());
        fileChooser.setDialogTitle("Load file");

        // Show dialog
        int result = fileChooser.showOpenDialog(MainWindow.getInstance());

        // Do something
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                Savegame.get().load(fileChooser.getSelectedFile());
            } catch (ErrorMessageException e) {
                JOptionPane.showMessageDialog(MainWindow.getInstance(), e.getMessage(), e.getTitle(), JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
