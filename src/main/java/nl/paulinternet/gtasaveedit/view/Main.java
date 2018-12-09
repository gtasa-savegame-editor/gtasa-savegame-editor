package nl.paulinternet.gtasaveedit.view;

import com.apple.eawt.Application;
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

public class Main {

    public static final boolean WINDOWS = System.getProperty("os.name").toLowerCase().startsWith("windows");
    public static final boolean MAC = System.getProperty("os.name").toLowerCase().startsWith("mac");
    public static final boolean LINUX = System.getProperty("os.name").toLowerCase().startsWith("linux");

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try {

            // setup look and feel
            try {
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("GTK+".equals(info.getName()) || "Windows".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
                log.error("Unable to launch main!", ex);
            }

            // OS X specific
            if (MAC) {
                System.setProperty("apple.laf.useScreenMenuBar", "true");
                System.setProperty("apple.awt.application.name", "GTA SA Savegame Editor");
                Application.getApplication().setDockIconImage(Images.readImage("icon-256.png"));

                Application.getApplication().setPreferencesHandler(pe -> MainWindow.getInstance().getTabbedPane().onShowPreferences());
                Application.getApplication().setAboutHandler(aboutEvent -> new AboutWindow(true).setVisible(true));
            }

            // Set the icons
            List<Image> images = new ArrayList<>(Arrays.asList(Images.readImage("icon-16.png"),
                    Images.readImage("icon-32.png"),
                    Images.readImage("icon-48.png")));
            MainWindow.getInstance().setIconImages(images);

            // Create GUI
            GUICreator guiCreator = new GUICreator();
            SwingUtilities.invokeAndWait(guiCreator);
            SwingUtilities.invokeAndWait(guiCreator);

            // Load images
            Images.loadImages();

            Updater.start();
        } catch (Throwable e) {
            e.printStackTrace();
            new ExceptionDialog(e).setVisible(true);
        }
    }
}
