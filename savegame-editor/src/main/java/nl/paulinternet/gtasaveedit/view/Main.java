package nl.paulinternet.gtasaveedit.view;

import nl.paulinternet.gtasaveedit.view.updater.Updater;
import nl.paulinternet.gtasaveedit.view.window.AboutWindow;
import nl.paulinternet.gtasaveedit.view.window.ExceptionDialog;
import nl.paulinternet.gtasaveedit.view.window.MainWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static nl.paulinternet.libsavegame.Util.MAC;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try {

            // setup look and feel
            try {
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("GTK+".equals(info.getName()) || "Windows".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                log.error("Unable to set theme!", ex);
            }

            // OS X specific
            if (MAC) {
                System.setProperty("apple.laf.useScreenMenuBar", "true");
                System.setProperty("apple.awt.application.name", "GTA:SA Savegame Editor");
                System.setProperty("com.apple.mrj.application.apple.menu.about.name", "GTA:SA Savegame Editor");
                Taskbar.getTaskbar().setIconImage(Images.readImage("icon-256.png"));

                Desktop.getDesktop().setPreferencesHandler(pe -> MainWindow.getInstance().getTabbedPane().onShowPreferences());
                Desktop.getDesktop().setAboutHandler(aboutEvent -> new AboutWindow().setVisible(true));
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
            e.printStackTrace();
            new ExceptionDialog(e).setVisible(true);
        }
    }
}
