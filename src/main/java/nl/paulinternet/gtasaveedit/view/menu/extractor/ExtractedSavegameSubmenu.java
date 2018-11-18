package nl.paulinternet.gtasaveedit.view.menu.extractor;

import nl.paulinternet.gtasaveedit.extractor.ExtractedSavegameHolder;
import nl.paulinternet.gtasaveedit.model.exceptions.ErrorMessageException;
import nl.paulinternet.gtasaveedit.model.savegame.Savegame;
import nl.paulinternet.gtasaveedit.view.window.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ExtractedSavegameSubmenu extends JMenu {
    private final File savegameFile;
    private final String savegameName;

    public ExtractedSavegameSubmenu(File savegameFile, String savegameName) {
        this.savegameFile = savegameFile;
        this.savegameName = savegameName;
        setText(this.savegameName);

        SaveExtractedSaveGameItem saveGameItem = new SaveExtractedSaveGameItem();
        saveGameItem.setEnabled(currentSavegameActive());

        LoadExtractedSaveGameItem loadGameItem = new LoadExtractedSaveGameItem(() ->
                saveGameItem.setEnabled(currentSavegameActive()));

        add(saveGameItem);
        add(loadGameItem);
    }

    public class SaveExtractedSaveGameItem extends JMenuItem implements ActionListener {

        SaveExtractedSaveGameItem() {
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

        private Handler onClick;

        LoadExtractedSaveGameItem(Handler onClick) {
            this.onClick = onClick;
            addActionListener(this);
            setText("Load");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Savegame.load(savegameFile);
                ExtractedSavegameHolder.getInstance().setSelectedSavegame(savegameName);
            } catch (ErrorMessageException ex) {
                JOptionPane.showMessageDialog(MainWindow.getInstance(), ex.getMessage(), ex.getTitle(), JOptionPane.ERROR_MESSAGE);
            }
            onClick.handle();
        }
    }

    private boolean currentSavegameActive() {
        return ExtractedSavegameHolder.getInstance().getSelectedSavegame().equals(savegameName);
    }

    private interface Handler {
        void handle();
    }
}
