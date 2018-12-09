package nl.paulinternet.gtasaveedit.view.updater;

/**
 * <p>
 * The version is parsed from a git tag which requires the tags to be in a special format.
 * The tag must start with a <pre>v</pre>, contain at least one dot and be at least four characters long.
 * It also can't use values other than numbers for the major, minor or patch portion.
 * </p>
 * <p>This results in a shortest possible version value of <pre>v0.0</pre>.</p>
 * <p>
 * The {@link Version#flag} can be inserted after the minor version and separated from it using a dash.
 * The version flag can contain letters and is separated from the patch level with a dot.
 * </p>
 * <p>An example version with a flag could be <pre>v1.0-rc.1</pre> which would be the first release candidate for version 1.0.</p>
 *
 * @author Lukas Fülling (lukas@k40s.net)
 */
public class Version implements Comparable<Version> {

    /**
     * Major version.
     */
    private final int major;

    /**
     * Minor version.
     */
    private final int minor;

    /**
     * Patch version.
     */
    private final int patch;

    /**
     * Version flag.
     *
     * @see Flag#byRepresentation(String)
     */
    private final Flag flag;

    /**
     * The original tag value.
     */
    private final String tag;

    /**
     * Constructor.
     *
     * @param tag the git tag to parse.
     * @throws Exception if the tag does not contain a valid version.
     */
    public Version(String tag) throws Exception {
        this.tag = tag;
        // v3.3-beta.4 // v3.3 // v3.3.1

        if (!tag.startsWith("v") && !tag.contains(".") && tag.length() < 4) {
            throw new Exception("This does not look like a valid version: '" + tag + "'");
        }

        String[] split = tag.split("\\."); // v3, 3-beta, 4 // v3, 3 // v3, 3, 1
        String[] flagSplit = split[1].split("-"); // 3, beta // 3 // 3

        major = Integer.parseInt(split[0].replaceAll("v", "")); // 3 // 3 // 3
        minor = Integer.parseInt(flagSplit[0]); // 3 // 3 // 3
        patch = Integer.parseInt(((split.length >= 3) ? split[2] : "0")); // 4 // 0 // 1

        flag = Flag.byRepresentation((flagSplit.length >= 2) ? flagSplit[1] : ""); // BETA, RELEASE, RELEASE
    }

    /**
     * Getter for major version.
     *
     * @return major version.
     */
    public int getMajor() {
        return major;
    }

    /**
     * Getter for minor version.
     *
     * @return minor version.
     */
    public int getMinor() {
        return minor;
    }

    /**
     * Getter for patch version.
     *
     * @return patch version.
     */
    public int getPatch() {
        return patch;
    }

    /**
     * Getter for release level flag.
     *
     * @return release flag.
     * @see Flag#byRepresentation(String)
     */
    public Flag getFlag() {
        return flag;
    }

    /**
     * Getter for tag value.
     * @return the original tag value of this version.
     */
    public String getTag() {
        return tag;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(Version o) {
        if (major == o.major &&
                minor == o.minor &&
                patch == o.patch &&
                flag.equals(o.flag)) {
            return 0;
        } else if ((major > o.major ||
                (major >= o.major && minor > o.minor) ||
                (major >= o.major && patch > o.patch)) &&
                (o.flag.equals(Flag.RELEASE) || flag.equals(o.flag))) { //FIXME this is broken
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * Release flag.
     * This is the bold part of the following tag: 'v3.3-<bold>beta</bold>.4'.
     */
    public enum Flag {

        /**
         * Beta release.
         */
        BETA("beta"),

        /**
         * Release candidate.
         */
        RC("rc"),

        /**
         * Stable release.
         */
        RELEASE("");

        /**
         * The representation of the flag in the tag string.
         */
        private final String representation;

        /**
         * Constructor.
         *
         * @param representation the representation of the flag in the tag string.
         */
        Flag(String representation) {
            this.representation = representation;
        }

        /**
         * Finds a enum value for a given representation string.
         * The input is compared with {@link String#equalsIgnoreCase(String)}.
         *
         * @param representation the representation string to search for. Can be of any case.
         * @return a {@link Flag} if found or {@link null}
         */
        private static Flag byRepresentation(String representation) {
            for (Flag flag : Flag.values()) {
                if (flag.representation.equalsIgnoreCase(representation)) {
                    return flag;
                }
            }
            return null;
        }
    }

}
