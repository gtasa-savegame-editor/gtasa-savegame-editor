package nl.paulinternet.gtasaveedit.view;

import nl.paulinternet.gtasaveedit.view.window.MainWindow;

import javax.swing.*;
import java.awt.*;

class GUICreator implements Runnable {
    private boolean secondTime;

    public void run() {
        if (!secondTime) {
            // Set eventqueue to display errors
            Toolkit.getDefaultToolkit().getSystemEventQueue().push(new EventQueueProxy());

            // Initialize theming
            initializeTheming();

            // Create a window
            MainWindow.getInstance().setVisible(true);

            secondTime = true;
        } else {
            // Create main window content
            MainWindow.getInstance().createContent();
        }
    }

    private void initializeTheming() {
        SwingUtilities.invokeLater(ThemeManager::install);
    }
}
