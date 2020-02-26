package nl.paulinternet.gtasaveedit.view;

import nl.paulinternet.gtasaveedit.view.window.MainWindow;
import nl.paulinternet.gtasaveedit.Settings;

import javax.swing.*;
import java.awt.*;

class GUICreator implements Runnable {
    private boolean secondTime;

    public void run() {
        if (!secondTime) {
            // Set eventqueue to display errors
            Toolkit.getDefaultToolkit().getSystemEventQueue().push(new EventQueueProxy());

            // Create a window
            MainWindow.getInstance().setVisible(true);
            setSelectedTheme();

            secondTime = true;
        } else {
            MainWindow.getInstance().createContent();
        }
    }

    private void setSelectedTheme() {
        String lookAndFeelClassName = Settings.getLookAndFeelClassName();
        if(lookAndFeelClassName != null && !lookAndFeelClassName.isEmpty()) {
            if (!UIManager.getLookAndFeel().getClass().getName().equals(lookAndFeelClassName)) {
                try {
                    UIManager.setLookAndFeel(lookAndFeelClassName);
                    SwingUtilities.updateComponentTreeUI(MainWindow.getInstance());
                } catch (Exception ignored) {
                }
            }
        }
    }
}
