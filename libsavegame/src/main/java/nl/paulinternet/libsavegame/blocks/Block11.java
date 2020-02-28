package nl.paulinternet.libsavegame.blocks;

import nl.paulinternet.libsavegame.SavegameData;
import nl.paulinternet.libsavegame.exceptions.FileFormatException;
import nl.paulinternet.libsavegame.link.Link;

public class Block11 implements Link {
    @Override
    public void load(SavegameData io) throws FileFormatException {
        int p = 0;
        for (int i = 0; i < 8; i++) {
            vars.gangWeapon.get(p++).setValue(io.readInt(11, i * 0x10 + 0x4));
            vars.gangWeapon.get(p++).setValue(io.readInt(11, i * 0x10 + 0x8));
            vars.gangWeapon.get(p++).setValue(io.readInt(11, i * 0x10 + 0xc));
        }
    }

    @Override
    public void save(SavegameData io) {
        int p = 0;
        for (int i = 0; i < 8; i++) {
            io.writeInt(11, i * 0x10 + 0x4, vars.gangWeapon.get(p++).getValue());
            io.writeInt(11, i * 0x10 + 0x8, vars.gangWeapon.get(p++).getValue());
            io.writeInt(11, i * 0x10 + 0xc, vars.gangWeapon.get(p++).getValue());
        }
    }
}
