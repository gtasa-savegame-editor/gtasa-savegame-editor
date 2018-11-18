package nl.paulinternet.gtasaveedit.view.menu.extractor;

import nl.paulinternet.gtasaveedit.model.exceptions.ErrorMessageException;
import nl.paulinternet.gtasaveedit.model.savegame.Savegame;
import nl.paulinternet.gtasaveedit.view.window.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ExtractedSaveGameMenu extends JMenu {
    private final File savegameFile;

    public ExtractedSaveGameMenu(File savegameFile, String savegameName) {
        this.savegameFile = savegameFile;
        setText(savegameName);
        add(new LoadExtractedSaveGameItem());
        add(new SaveExtractedSaveGameItem());
    }

    public class SaveExtractedSaveGameItem extends JMenuItem implements ActionListener {

        public SaveExtractedSaveGameItem() {
            addActionListener(this);
            setText("Save");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Savegame.save(savegameFile);
            } catch (ErrorMessageException ex) {
                JOptionPane.showMessageDialog(MainWindow.getInstance(), ex.getMessage(), ex.getTitle(), JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public class LoadExtractedSaveGameItem extends JMenuItem implements ActionListener {

        public LoadExtractedSaveGameItem() {
            addActionListener(this);
            setText("Load");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Savegame.load(savegameFile);
            } catch (ErrorMessageException ex) {
                JOptionPane.showMessageDialog(MainWindow.getInstance(), ex.getMessage(), ex.getTitle(), JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
