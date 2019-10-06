package nl.paulinternet.gtasaveedit.view;

import nl.paulinternet.gtasaveedit.view.window.MainWindow;

import java.awt.*;

class GUICreator implements Runnable {
    private boolean secondTime;

    public void run() {
        if (!secondTime) {
            // Set eventqueue to display errors
            Toolkit.getDefaultToolkit().getSystemEventQueue().push(new EventQueueProxy());

            // Create a window
            MainWindow.getInstance().setVisible(true);

            secondTime = true;
        } else {
            MainWindow.getInstance().createContent();
        }
    }
}
