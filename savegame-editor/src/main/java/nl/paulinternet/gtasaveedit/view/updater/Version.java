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

        flag = Flag.byRepresentation((flagSplit.length >= 2) ? flagSplit[1] : ""); // BETA, RC, RELEASE
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
     *
     * @return the original tag value of this version.
     */
    public String getTag() {
        return tag;
    }

    /**
     * Compares the two Version objects. First the {@link #major} and {@link #minor} versions are compared,
     * then the {@link #flag} version using {@link #compareFlags(Flag)}, and finally the {@link #patch} version.
     *
     * @param o the version to compare to.
     * @return 1 if greater, 0 if equal, -1 if smaller.
     */
    @Override
    public int compareTo(Version o) {
        if (major != o.major) {
            return Integer.compare(major, o.major);
        } else if (minor != o.minor) {
            return Integer.compare(minor, o.minor);
        } else if (compareFlags(o.flag) != 0) {
            return compareFlags(o.flag);
        } else if (patch != o.patch) {
            return Integer.compare(patch, o.patch);
        }
        return 0;
    }

    /**
     * Compares version flags in the following order from lowest to highest: beta, rc, release.
     *
     * @param otherFlag the flag to compare to.
     * @return 1 if the other flag is "smaller", 0 if the flags are equal, -1 if the other flag is greater.
     * @see Flag
     */
    private int compareFlags(Flag otherFlag) {
        if (flag.equals(otherFlag)) {
            return 0;
        } else if (flag.equals(Flag.BETA)) {
            if (otherFlag.equals(Flag.RC) || otherFlag.equals(Flag.RELEASE)) {
                return -1;
            }
        } else if (flag.equals(Flag.RC)) {
            if (otherFlag.equals(Flag.BETA)) {
                return 1;
            } else if (otherFlag.equals(Flag.RELEASE)) {
                return -1;
            }
        } else if (flag.equals(Flag.RELEASE)) {
            if (otherFlag.equals(Flag.RC) || otherFlag.equals(Flag.BETA)) {
                return 1;
            }
        }
        throw new IllegalStateException("Unknown flag: " + otherFlag);
    }

    /**
     * Release flag.
     * This is the bold part of the following tag: 'v3.3-<bold>beta</bold>.4'.
     */
    public enum Flag {

        /**
         * Beta release.
         * This is the "smallest" value.
         */
        BETA("beta"),

        /**
         * Release candidate.
         * This is "greater" than {@link #BETA} but "smaller" than {@link #RELEASE}.
         */
        RC("rc"),

        /**
         * Stable release.
         * This is the "highest" value.
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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Version)) {
            return false;
        } else {
            Version o = (Version) obj;
            return compareTo(o) == 0;
        }
    }
}
