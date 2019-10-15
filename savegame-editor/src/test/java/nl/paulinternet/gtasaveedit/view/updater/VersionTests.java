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

    public void testParseValidRcVersion() throws Exception {
        Version version = new Version("v1.0-rc.1");
        assertEquals(1, version.getMajor());
        assertEquals(0, version.getMinor());
        assertEquals(1, version.getPatch());
        assertEquals(Version.Flag.RC, version.getFlag());
    }

    public void testParseInvalidVersions() {
        expectException(() -> new Version("ABCDEFG"));
        expectException(() -> new Version("x1.0-alpha.lol"));
        expectException(() -> new Version("v1.1.0-beta"));
        expectException(() -> new Version("v1-beta.0.1"));
        expectException(() -> new Version("1.v0.1-beta"));
        expectException(() -> new Version("v.1.0.beta"));
        expectException(() -> new Version("v1.0.beta"));
        expectException(() -> new Version("v1.0.beta.0"));
        expectException(() -> new Version("v1.0.beta-0"));
    }

    public void testCompareTo() throws Exception {
        Version betaVersion = new Version("v1.0-beta.1");
        Version newerBetaVersion = new Version("v1.0-beta.2");
        Version rcVersion = new Version("v1.0-rc.1");
        Version newerRcVersion = new Version("v1.0-rc.10");
        Version stableVersion = new Version("v1.0.0");
        Version newerStableVersion = new Version("v1.1.0");
        Version newerMajorVersion = new Version("v2.0.0");

        assertEquals(0, betaVersion.compareTo(betaVersion));
        assertEquals(0, rcVersion.compareTo(rcVersion));
        assertEquals(0, stableVersion.compareTo(stableVersion));
        assertTrue(betaVersion.compareTo(newerBetaVersion) < 0);
        assertTrue(betaVersion.compareTo(rcVersion) < 0);
        assertTrue(betaVersion.compareTo(newerRcVersion) < 0);
        assertTrue(betaVersion.compareTo(stableVersion) < 0);
        assertTrue(betaVersion.compareTo(newerStableVersion) < 0);
        assertTrue(betaVersion.compareTo(newerMajorVersion) < 0);
        assertTrue(newerBetaVersion.compareTo(rcVersion) < 0);
        assertTrue(newerBetaVersion.compareTo(newerRcVersion) < 0);
        assertTrue(newerBetaVersion.compareTo(stableVersion) < 0);
        assertTrue(newerBetaVersion.compareTo(newerStableVersion) < 0);
        assertTrue(newerBetaVersion.compareTo(newerMajorVersion) < 0);
        assertTrue(rcVersion.compareTo(newerRcVersion) < 0);
        assertTrue(rcVersion.compareTo(stableVersion) < 0);
        assertTrue(rcVersion.compareTo(newerStableVersion) < 0);
        assertTrue(rcVersion.compareTo(newerMajorVersion) < 0);
        assertTrue(stableVersion.compareTo(newerStableVersion) < 0);
        assertTrue(stableVersion.compareTo(newerMajorVersion) < 0);
        assertTrue(newerStableVersion.compareTo(newerMajorVersion) < 0);
    }

    public void testEquals() throws Exception {
        Version stableVersion = new Version("v1.0.0");
        Version stableVersion1 = new Version("v1.0.0");
        Version betaVersion = new Version("v1.0-beta.1");
        Object otherObject = new Object();

        assertEquals(stableVersion, stableVersion1);
        assertNotSame(stableVersion, betaVersion);
        assertNotSame(stableVersion, otherObject);
    }

    private void expectException(Handler handler) {
        Exception ex = null;
        try {
            handler.handle();
        } catch (Exception e) {
            ex = e;
        }
        assertNotNull(ex);
    }

    private interface Handler {
        void handle() throws Exception;
    }
}
