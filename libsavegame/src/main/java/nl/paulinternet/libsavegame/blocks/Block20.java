package nl.paulinternet.libsavegame.blocks;

import nl.paulinternet.libsavegame.SavegameData;
import nl.paulinternet.libsavegame.link.Link;

public class Block20 implements Link {
    @Override
    public void load(SavegameData io) {
        for (int i = 0; i < 100; i++) {
            vars.tags[i] = io.readByte(20, i + 4);
        }
    }

    @Override
    public void save(SavegameData io) {
        for (int i = 0; i < 100; i++) {
            io.writeByte(20, i + 4, vars.tags[i]);
        }
    }

}
