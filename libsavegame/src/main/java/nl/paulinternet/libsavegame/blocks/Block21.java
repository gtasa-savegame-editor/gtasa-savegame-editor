package nl.paulinternet.libsavegame.blocks;

import nl.paulinternet.libsavegame.SavegameData;
import nl.paulinternet.libsavegame.link.Link;

public class Block21 implements Link {
    private static final int[] toVersion1 = new int[]{0, 59, 60, 61, 5, 6, 7, 8, 9, 10, 11, 12, 13, 1, 2, 3, 4, 14, 15, 16, 17, 18, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 25, 26, 27, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 28, 29, 30, 31, 19, 20, 21, 22, 23, 24, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 72, 73, 74, 75, 76, 77, 67, 68, 69, 70, 71, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 96, 97, 98, 92, 93, 94, 95, 105, 106, 107, 108, 99, 100, 101, 102, 103, 104, 109, 110, 111, 112, 113, 62, 63, 64, 65, 66};
    private static final int[] toVersion2 = new int[]{0, 13, 14, 15, 16, 4, 5, 6, 7, 8, 9, 10, 11, 12, 17, 18, 19, 20, 21, 70, 71, 72, 73, 74, 75, 46, 47, 48, 66, 67, 68, 69, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 1, 2, 3, 159, 160, 161, 162, 163, 118, 119, 120, 121, 122, 112, 113, 114, 115, 116, 117, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 140, 141, 142, 143, 137, 138, 139, 148, 149, 150, 151, 152, 153, 144, 145, 146, 147, 154, 155, 156, 157, 158, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111};

    @Override
    public void load(SavegameData io) {
        // Guess IPL version
        int version = vars.version.getValue();
        int iplVersion = version == 0xfd148df6 || version == 0x5d31cc22 ? 2 : 1;

        vars.currentIplVersion.setValue(iplVersion);
        vars.convertIplVersion.setValue(iplVersion);
    }

    @Override
    public void save(SavegameData io) {
        int currentIpl = vars.currentIplVersion.getValue();
        int convertIpl = vars.convertIplVersion.getValue();

        // Convert
        if (currentIpl != convertIpl) {
            int[] convertArray = convertIpl == 1 ? toVersion1 : toVersion2;

            byte[] data = new byte[convertArray.length];
            for (int i = 0; i < convertArray.length; i++) {
                data[i] = (byte) io.readByte(21, convertArray[i] + 4);
            }

            io.getBlock(21).overwrite(data, 4);

            currentIpl = convertIpl;
            vars.currentIplVersion.setValue(convertIpl);
        }
    }
}
