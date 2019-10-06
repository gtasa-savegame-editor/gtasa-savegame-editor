package nl.paulinternet.libsavegame.blocks;

import nl.paulinternet.libsavegame.SavegameData;
import nl.paulinternet.libsavegame.SavegameModel;
import nl.paulinternet.libsavegame.exceptions.FileFormatException;
import nl.paulinternet.libsavegame.link.Link;

public class Block11 implements Link {
    @Override
    public void load(SavegameData io) throws FileFormatException {
        int p = 0;
        for (int i = 0; i < 8; i++) {
            SavegameModel.vars.gangWeapon.get(p++).setIntValue(io.readInt(11, i * 0x10 + 0x4));
            SavegameModel.vars.gangWeapon.get(p++).setIntValue(io.readInt(11, i * 0x10 + 0x8));
            SavegameModel.vars.gangWeapon.get(p++).setIntValue(io.readInt(11, i * 0x10 + 0xc));
        }
    }

    @Override
    public void save(SavegameData io) {
        int p = 0;
        for (int i = 0; i < 8; i++) {
            io.writeInt(11, i * 0x10 + 0x4, SavegameModel.vars.gangWeapon.get(p++).getIntValue());
            io.writeInt(11, i * 0x10 + 0x8, SavegameModel.vars.gangWeapon.get(p++).getIntValue());
            io.writeInt(11, i * 0x10 + 0xc, SavegameModel.vars.gangWeapon.get(p++).getIntValue());
        }
    }
}
