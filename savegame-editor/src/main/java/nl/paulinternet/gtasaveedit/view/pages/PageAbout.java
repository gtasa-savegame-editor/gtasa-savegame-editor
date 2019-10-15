package nl.paulinternet.gtasaveedit.view.pages;

import nl.paulinternet.gtasaveedit.view.window.AboutWindow;

import static nl.paulinternet.libsavegame.Util.MAC;

/**
 * @author Lukas Fülling (lukas@k40s.net)
 */
public class PageAbout extends Page {

    private final AboutWindow aboutWindow;

    public PageAbout() {
        super("About", true);
        aboutWindow = new AboutWindow(false);
        if (!MAC) {
            aboutWindow.getStopButton().onClick()
                    .addHandler(aboutWindow, "stop");
        }
        aboutWindow.getWebsiteButton().onClick()
                .addHandler(aboutWindow, "openWebsite", "www.paulinternet.nl/sa");
        aboutWindow.getRepoButton().onClick()
                .addHandler(aboutWindow, "openWebsite", "github.com/gtasa-savegame-editor/gtasa-savegame-editor");

        setComponent(aboutWindow.getYbox(), false);
    }

    public void play() {
        aboutWindow.play();
    }

    public void stop() {
        aboutWindow.stop();
    }
}
