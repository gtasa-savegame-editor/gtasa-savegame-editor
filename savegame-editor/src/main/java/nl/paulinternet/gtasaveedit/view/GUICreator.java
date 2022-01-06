package nl.paulinternet.gtasaveedit.view;

import com.github.weisj.darklaf.LafManager;
import com.github.weisj.darklaf.settings.ThemeSettings;
import com.github.weisj.darklaf.theme.Theme;
import com.github.weisj.darklaf.theme.event.ThemeChangeEvent;
import com.github.weisj.darklaf.theme.event.ThemeChangeListener;
import nl.paulinternet.gtasaveedit.view.window.MainWindow;

import javax.swing.*;
import java.awt.*;

import static com.github.weisj.darklaf.LafManager.getPreferredThemeStyle;

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
        SwingUtilities.invokeLater(() -> {
            Theme preferredTheme = LafManager.themeForPreferredStyle(getPreferredThemeStyle());
            LafManager.install(preferredTheme);
        });
    }
}
