package nl.paulinternet.gtasaveedit.view.updater;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.Optional;
import java.util.Properties;

public class GitDataHandler {

    /**
     * Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(GitDataHandler.class);

    /**
     * Tries to read the current tag from git.properties, otherwise returns "0.0.0-dev.0"
     *
     * @return the current tag from git.properties or "0.0.0-dev.0" if an error occurred
     */
    public static String getCurrentTag() {
        Properties properties = getGitProperties();
        if (properties != null) {
            Optional<String> tagOptional = Arrays.stream(properties.getProperty("git.tags").split(","))
                    .filter(tag -> tag.startsWith("v"))
                    .findFirst();
            if (tagOptional.isPresent()) {
                String version = tagOptional.get();
                log.info("Found version: '" + version + "'!");
                return version;
            }
        }
        log.warn("Unable to determine current version!");
        return "0.0-dev.0";
    }

    /**
     * Tries to read the current commit hash from git.properties, otherwise returns null.
     *
     * @return the current commit hash from git.properties or null
     */
    public static String getCurrentCommit() {
        Properties properties = getGitProperties();
        if (properties != null) {
            Optional<String> tagOptional = Arrays.stream(properties.getProperty("git.commit.id.abbrev").split(","))
                    .filter(tag -> tag.startsWith("v"))
                    .findFirst();
            if (tagOptional.isPresent()) {
                return tagOptional.get();
            }
        }
        return null;
    }

    private static Properties getGitProperties() {
        try {
            URL resource = Updater.class.getResource("git.properties");
            if (resource != null) {
                File file = new File(resource.toURI());
                if (file.exists() && file.isFile()) {
                    try (FileInputStream fis = new FileInputStream(file)) {
                        Properties properties = new Properties();
                        properties.load(fis);
                        return properties;
                    } catch (IOException e) {
                        log.error("Unable to load property file!", e);
                    }
                }
            }
        } catch (URISyntaxException e) {
            log.error("Unable to parse property URI!", e);
        }
        return null;
    }
}
