package nl.paulinternet.gtasaveedit.view.updater;

import nl.paulinternet.gtasaveedit.view.Main;
import nl.paulinternet.gtasaveedit.view.window.MainWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Updater {

    /**
     * The current version! This has to be manually lifted each release 👀
     */
    public static final String CURRENT_TAG = "v3.3-beta.7";

    /**
     * Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(Updater.class);

    /**
     * Instance.
     */
    private static Updater instance = null;

    /**
     * All available tags (fetched from GitHub).
     */
    private final ArrayList<Version> tags = new ArrayList<>();

    /**
     * The URL of the download page.
     */
    private URL url;

    /**
     * Constructor.
     */
    private Updater() {
        try {
            url = new URL("https", "github.com", 443, "/gtasa-savegame-editor/gtasa-savegame-editor/releases");
        } catch (MalformedURLException e) {
            log.error("Unable to parse url!", e);
        }

        //TODO implement caching instead of always fetching for new releases.
    }

    /**
     * Initializes instance if applicable and checks for a new release.
     */
    public static void start() {
        if (instance == null) {
            instance = new Updater();
        }
        try {
            instance.checkForUpdate();
            instance.notifyLatestVersion();
        } catch (Exception e) {
            log.error("Unable to check for new version!", e);
        }
    }

    /**
     * Checks for a newer release and notifies if one is available.
     *
     * @throws Exception if there is an error parsing a {@link Version}.
     */
    private void notifyLatestVersion() throws Exception {
        final Version current = new Version(CURRENT_TAG);
        List<Version> newerReleases = tags.stream().filter(v -> current.compareTo(v) < 0).collect(Collectors.toList());
        if (newerReleases.size() > 0) {
            String message = "There " + ((newerReleases.size() > 1) ? "are new releases" : "is a new release") + " available: ";
            final StringBuilder appendix = new StringBuilder();
            newerReleases.forEach(v -> appendix.append("\n    • ").append(v.getTag()));
            appendix.append("\n\nDo you want to open the download page now?");
            int chosen = JOptionPane.showConfirmDialog(MainWindow.getInstance(), message + appendix, "New Version available!", JOptionPane.YES_NO_OPTION);
            if (chosen == 0) {
                try {
                    Desktop.getDesktop().browse(url.toURI());
                } catch (Exception e) {
                    log.warn("Unable to open browser!", e);
                    JOptionPane.showMessageDialog(MainWindow.getInstance(),
                            "Unable to open browser!\nPlease go to " + url.toString(),
                            "Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    /**
     * Checks for new tags and adds them to {@link #tags}.
     *
     * @throws Exception when there was an error parsing a version.
     */
    private void checkForUpdate() throws Exception {
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                if (line.contains("gtasa-savegame-editor")) {
                    String[] split = line.trim().split("\"");
                    for (String s : split) {
                        if (s.contains("tree")) {
                            String[] splitUrl = s.split("/");
                            log.debug("Found tag: '" + splitUrl[splitUrl.length - 1] + "'");
                            tags.add(new Version(splitUrl[splitUrl.length - 1]));
                        }
                    }
                }
            }
            rd.close();
        } catch (IOException e) {
            log.error("Unable to check for latest version!", e);
        }
    }
}
