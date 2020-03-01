package nl.paulinternet.gtasaveedit.view.menu;

import nl.paulinternet.gtasaveedit.FileSystem;
import nl.paulinternet.gtasaveedit.model.Model;
import nl.paulinternet.gtasaveedit.model.SavegameModel;
import nl.paulinternet.gtasaveedit.view.menu.extractor.ExtractorMenu;
import nl.paulinternet.gtasaveedit.view.window.MainWindow;
import nl.paulinternet.libsavegame.Savegame;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Objects;

import static nl.paulinternet.gtasaveedit.view.Main.MAC;
import static nl.paulinternet.gtasaveedit.view.Main.WINDOWS;

public class MenuBar extends JMenuBar {
    private static class ExitMenuItem extends JMenuItem implements ActionListener {
        public ExitMenuItem() {
            super("Exit");
            addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    private static class CloseMenuItem extends JMenuItem implements ActionListener {
        public CloseMenuItem() {
            super("Close");
            setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, MAC ? InputEvent.META_DOWN_MASK : InputEvent.CTRL_DOWN_MASK));
            addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Savegame.get().close();
        }
    }

    private static class LoadCompleteSavegame extends JMenuItem implements ActionListener {
        public LoadCompleteSavegame() {
            super("100% Complete savegame");
            setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_9, MAC ? InputEvent.META_DOWN_MASK : InputEvent.CTRL_DOWN_MASK));
            addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent ev) {
            Savegame.get().close();
            Savegame.get().load(MenuBar.class.getResource("/savegame.b"));
        }
    }

    public static class OpenDirMenuItem extends JMenuItem implements ActionListener {
        public OpenDirMenuItem() {
            super("Open savegame directory");
            setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, MAC ? InputEvent.META_DOWN_MASK : InputEvent.CTRL_DOWN_MASK));
            addActionListener(this);
            settingsChanged();
            Model.editSettings.settingsChanged.addHandler(this, "settingsChanged");
        }

        @Override
        public void actionPerformed(ActionEvent ev) {
            try {
                Desktop.getDesktop().open(FileSystem.getSavegameDirectory());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(MainWindow.getInstance(), "Failed to open the savegame directory.", "Opening directory", JOptionPane.WARNING_MESSAGE);
            }
        }

        public void settingsChanged() {
            File savedir = FileSystem.getSavegameDirectory();
            setEnabled(savedir != null && savedir.exists());
        }
    }

    private static class ReloadMenuItem extends JMenuItem implements ActionListener {
        public ReloadMenuItem() {
            super("Reload savegame titles");
            setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
            addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            SavegameModel.get(FileSystem.getSavegameDirectory()).updateQuickLoad();
        }
    }

    public static class RunSaMenuItem extends JMenuItem implements ActionListener {
        public RunSaMenuItem() {
            super("Run GTA San Andreas");
            setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0));
            addActionListener(this);
            settingsChanged();
            Model.editSettings.settingsChanged.addHandler(this, "settingsChanged");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (MAC) {
                    Desktop.getDesktop().open(Objects.requireNonNull(FileSystem.getSanAndreasExecutable()));
                } else if (WINDOWS) {
                    Runtime.getRuntime().exec(new String[]{Objects.requireNonNull(FileSystem.getSanAndreasExecutable()).getPath()}, null, FileSystem.getSanAndreasDirectory());
                } else {
                    LoggerFactory.getLogger(RunSaMenuItem.class).info("OS is not Windows or Mac, trying to launch via Steam. If this doesn't work, try putting 'steam' in your $PATH.");
                    Runtime.getRuntime().exec("steam", new String[]{"steam://run/12120"});
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(MainWindow.getInstance(), "Failed to run GTA San Andreas.", "Run GTA San Andreas", JOptionPane.WARNING_MESSAGE);
            }
        }

        @SuppressWarnings("WeakerAccess")
        public void settingsChanged() {
            setEnabled(FileSystem.getSanAndreasExecutable() != null);
        }
    }


    private JMenu menuSave;
    private JMenuItem menuItemSave;
    private JMenuItem menuItemClose;

    public MenuBar() {
        // File menu
        JMenu menuFile = new JMenu("File");
        menuFile.add(new FileLoad());
        menuFile.add(menuItemSave = new FileSave());
        menuFile.add(menuItemClose = new CloseMenuItem());
        menuFile.add(new FileDelete());
        menuFile.addSeparator();
        menuFile.add(new ReloadMenuItem());
        menuFile.add(new OpenDirMenuItem());
        menuFile.add(new RunSaMenuItem());
        menuFile.addSeparator();
        menuFile.add(new ExitMenuItem());

        // Load menu
        JMenu menuLoad = new JMenu("Load");
        for (int i = 1; i <= 8; i++) {
            menuLoad.add(new QuickLoad(i));
        }
        menuLoad.addSeparator();
        menuLoad.add(new LoadCompleteSavegame());

        // Save menu
        menuSave = new JMenu("Save");
        for (int i = 1; i <= 8; i++) {
            menuSave.add(new QuickSave(i));
        }

        // Delete menu
        JMenu menuDelete = new JMenu("Delete");
        for (int i = 1; i <= 8; i++) {
            menuDelete.add(new QuickDelete(i));
        }

        // Extractor menu
        JMenu menuExtractor = new ExtractorMenu();

        // Add menus
        add(menuFile);
        add(menuLoad);
        add(menuSave);
        add(menuDelete);
        add(menuExtractor);

        // set initial state
        menuSave.setEnabled(false);
        menuItemSave.setEnabled(false);
        menuItemClose.setEnabled(false);
    }

    public void onSavegameStateChange(boolean activeSavegame) {
        menuSave.setEnabled(activeSavegame);
        menuItemSave.setEnabled(activeSavegame);
        menuItemClose.setEnabled(activeSavegame);
        MenuBar.this.updateUI(); // causes flickering but works
        MainWindow.getInstance().validate();
    }
}
