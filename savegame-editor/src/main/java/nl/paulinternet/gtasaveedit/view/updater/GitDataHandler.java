package nl.paulinternet.gtasaveedit.view.updater;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Optional;
import java.util.Properties;

public class GitDataHandler {

    /**
     * Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(GitDataHandler.class);

    /**
     * Tries to read the current tag from git.properties, otherwise returns "0.0-DEV"
     *
     * @return the current tag from git.properties or "0.0-DEV" if an error occurred
     */
    public static String getCurrentTag() {
        Properties properties = getGitProperties();
        if (properties != null) {
            Optional<String> tagOptional = Arrays.stream(properties.getProperty("git.tags", "").split(","))
                    .filter(tag -> tag.startsWith("v"))
                    .findFirst();
            if (tagOptional.isPresent()) {
                String version = tagOptional.get();
                log.info("Found version: '" + version + "'!");
                return version;
            }

            // No tag on HEAD — derive from the closest tag
            String closestTag = properties.getProperty("git.closest.tag.name");
            if (closestTag != null && !closestTag.isEmpty()) {
                String baseVersion = closestTag.replaceAll("^(v\\d+\\.\\d+).*$", "$1");
                String version = baseVersion + "-DEV";
                log.info("No tag on HEAD, using closest tag '" + closestTag + "' → '" + version + "'");
                return version;
            }
        }
        log.warn("Unable to determine current version!");
        return "0.0-DEV";
    }

    /**
     * Tries to read the current commit hash from git.properties, otherwise returns null.
     *
     * @return the current commit hash from git.properties or null
     */
    public static String getCurrentCommit() {
        Properties properties = getGitProperties();
        if (properties != null) {
            return properties.getProperty("git.commit.id.abbrev");
        }
        return null;
    }

    private static Properties getGitProperties() {
        try (InputStream is = GitDataHandler.class.getResourceAsStream("/git.properties")) {
            if (is != null) {
                Properties properties = new Properties();
                properties.load(is);
                return properties;
            }
        } catch (Exception e) {
            log.error("Unable to open file!", e);
        }
        return null;
    }
}
