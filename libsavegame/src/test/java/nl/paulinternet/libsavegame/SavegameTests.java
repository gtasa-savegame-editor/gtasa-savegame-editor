package nl.paulinternet.libsavegame;

import nl.paulinternet.libsavegame.exceptions.ErrorMessageException;
import nl.paulinternet.libsavegame.variables.Variables;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.*;


public class SavegameTests {

    private static final String VALID_PC_SAVE = "valid_pc_save.b";
    private static final String VALID_ANDROID_SAVE = "valid_android_save.b";
    private static final String INVALID_FILE = "invalid_file.b";

    /**
     * Test that loads the bundled 100% savegame and validates it using
     * {@link #validate100PercentPcSavegame(Variables)}.
     *
     * @throws ErrorMessageException in case there was an error loading the save
     */
    @Test
    public void testLoadValidPcSave() throws ErrorMessageException {
        Savegame.get().load(getTestResource(VALID_PC_SAVE));
        Variables variables = Variables.get();
        validate100PercentPcSavegame(variables);
    }

    /**
     * Test that loads the bundled 100% savegame, validates it using
     * {@link #validate100PercentPcSavegame(Variables)}, modifies it,
     * saves it to a temp file and then reloads the temp file to validate
     * the modified values..
     *
     * @throws ErrorMessageException in case there was an error loading the save
     */
    @Test
    public void testLoadModSaveReload() throws ErrorMessageException, IOException {
        String testTitle = "TestingTestingTesting";
        int testMoney = 987654321;

        Savegame.get().load(getTestResource(VALID_PC_SAVE));
        Variables variables = Variables.get();
        validate100PercentPcSavegame(variables);

        variables.title.setText(testTitle);
        variables.money.setValue(testMoney);
        variables.roadblockLV.setValue(true);

        File tempFile = File.createTempFile("testsave", ".b");
        tempFile.deleteOnExit();
        Savegame.get().save(tempFile);
        Savegame.get().close();

        Savegame.get().load(tempFile);
        Variables reloadedVars = Variables.get();
        assertTrue(reloadedVars.roadblockLV.getValue());
        assertEquals(testTitle, reloadedVars.title.getText());
        assertEquals(Integer.valueOf(testMoney), reloadedVars.money.getValue());
    }

    /**
     * Test that loads an invalid file.
     */
    @Test
    public void testLoadInvalidFile() {
        ErrorMessageException exception = assertThrows(ErrorMessageException.class, () -> Savegame.get().load(getTestResource(INVALID_FILE)));
        assertEquals("The file is not a GTA San Andreas PC savegame.", exception.getMessage());
    }

    /**
     * Test that reads and validates an Android savegame.
     *
     * @throws ErrorMessageException in case there was an error loading the savegame
     */
    @Test
    @Ignore //FIXME unignore once Android saves work
    public void testLoadValidAndroidSave() throws ErrorMessageException {
        // load savegame
        Savegame.get().load(getTestResource(VALID_ANDROID_SAVE));
        Variables variables = Variables.get();
        //TODO: validate savegame
    }

    /**
     * Utility method that loads a file from the test resources folder.
     *
     * @param filename the name of the file to load
     * @return the requested file
     */
    private File getTestResource(String filename) {
        // validate test data exists
        URL saveResource = getClass().getClassLoader().getResource(filename);
        assertNotNull(saveResource);
        File save = new File(saveResource.getFile());
        assertTrue(save.exists());
        return save;
    }

    /**
     * Utility method that validates the bundled 100% PC savefile.
     *
     * @param variables the variables of the loaded save
     */
    private void validate100PercentPcSavegame(Variables variables) {
        assertEquals("End Of The Line", variables.title.getText());
        assertEquals(Integer.valueOf(1), variables.scriptVersion.getValue());
        assertFalse(variables.roadblockLV.getValue());
        assertFalse(variables.roadblockSF.getValue());
        assertFalse(variables.riots.getValue());
        assertTrue(variables.gangWars.getValue());
        assertTrue(variables.prostitutesPay.getValue());
        assertEquals(Integer.valueOf(27391414), variables.money.getValue());
        assertEquals(80, variables.garageCars.size());
    }

}
