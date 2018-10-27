package nl.paulinternet.gtasaveedit.view;

import com.apple.eawt.Application;
import com.panayotis.jupidator.ApplicationInfo;
import com.panayotis.jupidator.Updater;
import nl.paulinternet.gtasaveedit.view.window.AboutWindow;
import nl.paulinternet.gtasaveedit.view.window.ExceptionDialog;
import nl.paulinternet.gtasaveedit.view.window.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static final boolean WINDOWS = System.getProperty("os.name").toLowerCase().startsWith("windows");
    public static final boolean MAC = System.getProperty("os.name").toLowerCase().startsWith("mac");
    public static final boolean LINUX = System.getProperty("os.name").toLowerCase().startsWith("linux");
    private static final ApplicationInfo appinfo = new ApplicationInfo(0, null);
    private static final String xmlurl = "https://raw.githubusercontent.com/lfuelling/gtasa-savegame-editor/master/updates.xml";

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
                System.err.println(ex.getMessage());
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

            Updater.start(xmlurl,
                    appinfo,
                    null,
                    null);
        } catch (Throwable e) {
            e.printStackTrace();
            new ExceptionDialog(e).setVisible(true);
        }
    }

    public static ApplicationInfo getAppinfo() {
        return appinfo;
    }

    public static String getXmlurl() {
        return xmlurl;
    }
}
