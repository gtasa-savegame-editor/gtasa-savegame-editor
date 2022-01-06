package nl.paulinternet.gtasaveedit.view;

import com.github.weisj.darklaf.LafManager;
import com.github.weisj.darklaf.theme.event.ThemeChangeEvent;
import com.github.weisj.darklaf.theme.event.ThemeChangeListener;
import nl.paulinternet.gtasaveedit.Settings;
import nl.paulinternet.gtasaveedit.view.updater.Updater;
import nl.paulinternet.gtasaveedit.view.window.AboutWindow;
import nl.paulinternet.gtasaveedit.view.window.ExceptionDialog;
import nl.paulinternet.gtasaveedit.view.window.MainWindow;
import nl.paulinternet.libsavegame.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static final boolean WINDOWS = System.getProperty("os.name").toLowerCase().startsWith("windows");
    public static final boolean MAC = System.getProperty("os.name").toLowerCase().startsWith("mac");
    public static final boolean LINUX = System.getProperty("os.name").toLowerCase().startsWith("linux");

    public static void main(String[] args) {
        try {

            if(Settings.getGaragesEnabled() != Settings.YES) {
                Config.get().setEnableGarages(false);
            } else {
                log.warn("The garage feature is currently very buggy!");
            }

            // OS X specific
            if (MAC) {
                System.setProperty("com.apple.mrj.application.apple.menu.about.name", "GTA:SA Savegame Editor");
                System.setProperty("apple.laf.useScreenMenuBar", "true");
                System.setProperty("apple.awt.application.appearance", "system");
                System.setProperty("apple.awt.application.name", "GTA:SA Savegame Editor");
                Taskbar.getTaskbar().setIconImage(Images.readImage("icon-256.png"));

                Desktop.getDesktop().setPreferencesHandler(pe -> MainWindow.getInstance().getTabbedPane().onShowPreferences());
                Desktop.getDesktop().setAboutHandler(aboutEvent -> AboutWindow.get().setVisible(true));
            }

            // Set the icons
            List<Image> images = new ArrayList<>(Arrays.asList(Images.readImage("icon-16.png"),
                    Images.readImage("icon-32.png"),
                    Images.readImage("icon-48.png")));
            MainWindow.getInstance().setIconImages(images);

            // Create GUI
            GUICreator guiCreator = new GUICreator();
            SwingUtilities.invokeAndWait(guiCreator);
            SwingUtilities.invokeAndWait(guiCreator); // not a typo, it has to be invoked twice.

            // Load images
            Images.loadImages();

            Updater.start();
        } catch (Throwable e) {
            log.error("Error encountered!", e);
            new ExceptionDialog(e).setVisible(true);
        }
    }
}
