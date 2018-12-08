package nl.paulinternet.gtasaveedit.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashSet;

public class Updater {

    private static final Logger log = LoggerFactory.getLogger(Updater.class);
    private static Updater instance = null;

    private final LinkedHashSet<String> tags = new LinkedHashSet<>();

    private Updater() {
    }

    public static void start() {
        if (instance == null) {
            instance = new Updater();
        }
        instance.checkForUpdate();
        instance.notifyLatestVersion();
    }

    private void notifyLatestVersion() {
        //TODO: split version into semver(-ish) and notify if there is a newer one than current.
    }

    private void checkForUpdate() {
        try {
            URL url = new URL("https", "github.com", 443, "/gtasa-savegame-editor/gtasa-savegame-editor/releases");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                if(line.contains("gtasa-savegame-editor")) {
                    String[] split = line.trim().split("\"");
                    for (String s : split) {
                        if (s.contains("tree")) {
                            String[] splitUrl = s.split("/");
                            log.debug("Found tag: '" + splitUrl[splitUrl.length - 1] + "'");
                            tags.add(splitUrl[splitUrl.length - 1]);
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
