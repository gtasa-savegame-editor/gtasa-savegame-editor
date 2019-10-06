package nl.paulinternet.gtasaveedit.view.window;

import nl.paulinternet.libsavegame.SavegameModel;
import nl.paulinternet.libsavegame.Settings;
import nl.paulinternet.gtasaveedit.view.component.TabbedPane;
import nl.paulinternet.gtasaveedit.view.menu.MenuBar;
import nl.paulinternet.gtasaveedit.view.swing.Alignment;

import javax.swing.*;

public class MainWindow extends JFrame {
    private static MainWindow instance;
    private TabbedPane tabbedPane;

    public static MainWindow getInstance() {
        if (instance == null) {
            instance = new MainWindow();
        }
        return instance;
    }

    private MainWindow() {
        // Exit on close
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Set size
        setSize(Settings.getWindowWidth(), Settings.getWindowHeight());
        if (Settings.getWindowMaximized() == Settings.YES) {
            setExtendedState(MAXIMIZED_BOTH);
        } else {
            setLocationRelativeTo(null);
        }

        // Add
        add(new Alignment(new JLabel("One moment please..."), 0.5f, 0.4f));

        // Set title
        setTitle("GTA SA Savegame Editor");
        // Set menu bar
        setJMenuBar(new MenuBar());
    }

    public void createContent() {
        // Add tabs
        getContentPane().remove(0);
        tabbedPane = new TabbedPane();
        add(tabbedPane);

        // Observe
        SavegameModel.gameClosed.addHandler(this, "onGameClose");
        SavegameModel.gameLoaded.addHandler(this, "onTitleChange");
        SavegameModel.vars.title.onChange().addHandler(this, "onTitleChange");

        invalidate();
        validate();
    }

    public TabbedPane getTabbedPane() {
        return tabbedPane;
    }

    @SuppressWarnings("unused") //used as handler
    public void onGameClose() {
        setTitle("GTA SA Savegame Editor");
    }

    @SuppressWarnings("unused") //used as handler
    public void onTitleChange() {
        setTitle(SavegameModel.vars.title.getText() + " - GTA SA Savegame Editor");
    }

}
