package nl.paulinternet.libsavegame;

import nl.paulinternet.libsavegame.exceptions.ErrorMessageException;
import nl.paulinternet.libsavegame.variables.Variables;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.net.URL;

import static org.junit.Assert.*;


public class SavegameTests {

    private static final String VALID_PC_SAVE = "valid_pc_save.b";
    private static final String VALID_ANDROID_SAVE = "valid_android_save.b";
    private static final String INVALID_FILE = "invalid_file.b";

    @Test
    public void testLoadValidPcSave() throws ErrorMessageException {
        // load savegame
        Savegame.get().load(loadTestSaveFile(VALID_PC_SAVE));
        Variables variables = Variables.get();
        // validate savegame
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

    @Test
    public void testLoadInvalidFile() {
        ErrorMessageException exception = assertThrows(ErrorMessageException.class, () -> Savegame.get().load(loadTestSaveFile(INVALID_FILE)));
        assertEquals("The file is not a GTA San Andreas PC savegame.", exception.getMessage());
    }

    @Test
    @Ignore //FIXME unignore once Android saves work
    public void testLoadValidAndroidSave() throws ErrorMessageException {
        // load savegame
        Savegame.get().load(loadTestSaveFile(VALID_ANDROID_SAVE));
        Variables variables = Variables.get();
        //TODO: validate savegame
    }

    private File loadTestSaveFile(String filename) {
        // validate test data exists
        URL saveResource = getClass().getClassLoader().getResource(filename);
        assertNotNull(saveResource);
        File save = new File(saveResource.getFile());
        assertTrue(save.exists());
        return save;
    }

}
