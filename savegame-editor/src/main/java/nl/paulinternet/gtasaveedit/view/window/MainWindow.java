package nl.paulinternet.gtasaveedit.view.window;

import nl.paulinternet.gtasaveedit.Settings;
import nl.paulinternet.gtasaveedit.model.SavegameModel;
import nl.paulinternet.gtasaveedit.view.component.TabbedPane;
import nl.paulinternet.gtasaveedit.view.menu.MenuBar;
import nl.paulinternet.gtasaveedit.view.swing.Alignment;

import javax.swing.*;
import nl.paulinternet.libsavegame.variables.Variables;

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
        Variables.get().title.setOnTextChange(s -> onTitleChange());

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

    public void onTitleChange() {
        setTitle(Variables.get().title.getText() + " - GTA SA Savegame Editor");
    }

}
