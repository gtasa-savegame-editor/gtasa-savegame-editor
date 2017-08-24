package nl.paulinternet.gtasaveedit.view;

import com.apple.eawt.Application;
import nl.paulinternet.gtasaveedit.view.pages.PageAbout;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static final boolean WINDOWS = System.getProperty("os.name").toLowerCase().startsWith("windows");
    public static final boolean MAC = System.getProperty("os.name").toLowerCase().startsWith("mac");

    public static void main(String[] args) {
        try {

            // OS X specific
            if (MAC) {
                System.setProperty("apple.laf.useScreenMenuBar", "true");
                System.setProperty("apple.awt.application.name", "GTA SA Savegame Editor");
                Application.getApplication().setDockIconImage(Images.readImage("icon-256.png"));

                Application.getApplication().setPreferencesHandler(pe -> Window.instance.getTabbedPane().onShowPreferences());
                Application.getApplication().setAboutHandler(aboutEvent -> new PageAbout().setVisible(true));
            }

            // Set the icons
            List<Image> images = new ArrayList<>();
            images.addAll(Arrays.asList(Images.readImage("icon-16.png"),
                    Images.readImage("icon-32.png"),
                    Images.readImage("icon-48.png")));
            Window.instance.setIconImages(images);

            // Create GUI
            GUICreator guiCreator = new GUICreator();
            SwingUtilities.invokeAndWait(guiCreator);
            SwingUtilities.invokeAndWait(guiCreator);

            // Load images
            Images.loadImages();
        } catch (Throwable e) {
            new ExceptionDialog(e).setVisible(true);
        }
    }
}
