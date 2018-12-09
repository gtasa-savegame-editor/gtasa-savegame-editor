package nl.paulinternet.gtasaveedit.view.updater;

import junit.framework.TestCase;

public class VersionTests extends TestCase {

    public void testParseValidStableVersion() throws Exception {
        Version version = new Version("v1.0.0");
        assertEquals(1, version.getMajor());
        assertEquals(0, version.getMinor());
        assertEquals(0, version.getPatch());
        assertEquals(Version.Flag.RELEASE, version.getFlag());
    }

    public void testParseValidBetaVersion() throws Exception {
        Version version = new Version("v1.0-beta.1");
        assertEquals(1, version.getMajor());
        assertEquals(0, version.getMinor());
        assertEquals(1, version.getPatch());
        assertEquals(Version.Flag.BETA, version.getFlag());
    }

    public void testParseRcVersion() throws Exception {
        Version version = new Version("v1.0-rc.1");
        assertEquals(1, version.getMajor());
        assertEquals(0, version.getMinor());
        assertEquals(1, version.getPatch());
        assertEquals(Version.Flag.RC, version.getFlag());
    }

    public void testParseInvalidVersion() {
        try {
            new Version("ABCDEFG");
        } catch (Exception e) {
            return;
        }
        throw new RuntimeException("Expected fail did not occur!");
    }

    public void testCompareTo() throws Exception {
        Version stableVersion = new Version("v1.0.0");
        Version betaVersion = new Version("v1.0-beta.1");
        Version rcVersion = new Version("v1.0-rc.1");
        Version newerBetaVersion = new Version("v1.0-beta.2");

        assertTrue(stableVersion.compareTo(betaVersion) > 0); // stable greater than beta
        assertTrue(rcVersion.compareTo(betaVersion) > 0); // rc greater than beta
        assertTrue(betaVersion.compareTo(newerBetaVersion) < 0); // beta older than newer beta
    }
}