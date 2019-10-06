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
        Version stableVersion = new Version("v1.0.0");
        Version betaVersion = new Version("v1.0-beta.1");
        Version rcVersion = new Version("v1.0-rc.1");
        Version newerBetaVersion = new Version("v1.0-beta.2");
        Version newerStableVersion = new Version("v1.1.0");

        assertTrue(stableVersion.compareTo(betaVersion) < 0);
        assertTrue(betaVersion.compareTo(rcVersion) < 0);
        assertTrue(betaVersion.compareTo(newerBetaVersion) < 0);
        assertTrue(stableVersion.compareTo(newerBetaVersion) < 0);
        assertTrue(stableVersion.compareTo(newerStableVersion) < 0);
    }

    private void expectException(Handler handler) {
        Exception ex = null;
        try{
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