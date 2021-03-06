package nl.paulinternet.gtasaveedit.view.menu.extractor;

import nl.paulinternet.gtasaveedit.extractor.ExtractorServer;
import nl.paulinternet.gtasaveedit.view.window.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ExtractorMenu extends JMenu {

    private final NoSavegamesItem noSavegamesItem;

    public ExtractorMenu() {
        super("Savegame Extractor");
        noSavegamesItem = new NoSavegamesItem();
        ServerItem serverItem = new ServerItem(this);
        ServerItem.StopServerItem stopServerItem = serverItem.getStopServerItem();

        add(serverItem);
        add(stopServerItem);
        addSeparator();
        add(new OpenTempDirItem());
        addSeparator();
        add(noSavegamesItem);
    }

    public NoSavegamesItem getNoSavegamesItem() {
        return noSavegamesItem;
    }

    public static class OpenTempDirItem extends JMenuItem implements ActionListener {
        OpenTempDirItem() {
            super("Open Upload Directory...");
            addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            File tempDir = ExtractorServer.getTempDir().toFile();
            try {
                Desktop.getDesktop().open(tempDir);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(MainWindow.getInstance(), ex.getMessage(),
                        "Unable to open '" + tempDir.getAbsolutePath() + "'!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static class NoSavegamesItem extends JMenuItem {

        public NoSavegamesItem() {
            super("No savegames...");
            setEnabled(false);
        }
    }
}
