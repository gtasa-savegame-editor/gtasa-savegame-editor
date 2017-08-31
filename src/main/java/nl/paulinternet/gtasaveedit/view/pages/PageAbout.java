package nl.paulinternet.gtasaveedit.view.pages;

import nl.paulinternet.gtasaveedit.view.window.AboutWindow;

/**
 * @author Lukas Fülling (lukas@k40s.net)
 */
public class PageAbout extends Page {
    public PageAbout() {
        super("About", true);
        setComponent(new AboutWindow().getYbox(), false);
    }
}
