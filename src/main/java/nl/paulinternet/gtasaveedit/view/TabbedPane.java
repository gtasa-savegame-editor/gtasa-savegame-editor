package nl.paulinternet.gtasaveedit.view;

import nl.paulinternet.gtasaveedit.model.Model;
import nl.paulinternet.gtasaveedit.view.menu.MenuBar;
import nl.paulinternet.gtasaveedit.view.pages.*;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class TabbedPane extends JTabbedPane {
    private static Boolean loaded = Boolean.FALSE;
    private List<Page> pages;

    public TabbedPane() {
        // Create pages
        pages = Arrays.asList(
                new PageGeneral(),
                new PageSkills(),
                new PageLocation(),
                new PageSchools(),
                new PageWeapons(),
                new PageGangWeapons(),
                new PageZones(),
                new PagePeds(),
                new PageClothes(),
                new PageCollectables(),
                new PageFix(),
                new PageOptions());

        if (!Main.MAC) {
            new PageAbout();
        }

        // Add tabs
        pages.forEach(p -> addTab(p.getTitle(), p.getComponent()));

        // Set the border
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Observe
        Model.gameLoaded.addHandler(this, "onGameLoaded");
        Model.gameClosed.addHandler(this, "onGameClosed");
        onGameClosed();
    }


    @SuppressWarnings("unused") // used in event
    public void onGameLoaded() {
        if (!loaded) {
            removeAll();
            pages.forEach(p -> addTab(p.getTitle(), p.getComponent()));
            loaded = true;
            MenuBar menubar = (MenuBar) Window.instance.getJMenuBar();
            if (menubar != null) {
                menubar.onSavegameStateChange(true);
            } else {
                System.err.println("Unable to get menuBar: " + Window.instance.getJMenuBar());
            }
            Window.instance.validate();
        }
    }

    @SuppressWarnings("WeakerAccess") // used in event
    public void onGameClosed() {
        removeAll();
        pages.forEach(p -> {
            if (p.isAlwaysVisible()) {
                addTab(p.getTitle(), p.getComponent());
            }
        });
        loaded = false;
        MenuBar menubar = (MenuBar) Window.instance.getJMenuBar();
        if (menubar != null) {
            menubar.onSavegameStateChange(false);
        } else {
            System.err.println("Unable to get menuBar: " + Window.instance.getJMenuBar());
        }
        Window.instance.validate();
    }

    public void updateUI() {
        super.updateUI();
        if (!loaded && pages != null) {
            pages.forEach(p -> {
                if (!p.isAlwaysVisible()) {
                    SwingUtilities.updateComponentTreeUI(p.getComponent());
                }
            });
        }
    }

    void onShowPreferences() {
        pages.forEach(p -> {
            if (p instanceof PageOptions) {
                setSelectedComponent(p.getComponent());
            }
        });
    }
}
