package nl.paulinternet.gtasaveedit.view.pages;

import nl.paulinternet.gtasaveedit.view.window.AboutWindow;

/**
 * @author Lukas Fülling (lukas@k40s.net)
 */
public class PageAbout extends Page {

    private final AboutWindow aboutWindow;

    public PageAbout() {
        super("About", true);
        aboutWindow = AboutWindow.get();

        setComponent(aboutWindow.getYbox(), false);
    }

    public void play() {
        aboutWindow.play();
    }

    public void stop() {
        aboutWindow.stop();
    }
}
