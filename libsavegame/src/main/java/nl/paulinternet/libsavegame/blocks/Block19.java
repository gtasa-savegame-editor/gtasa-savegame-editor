package nl.paulinternet.libsavegame.blocks;

import nl.paulinternet.libsavegame.SavegameData;
import nl.paulinternet.libsavegame.exceptions.FileFormatException;
import nl.paulinternet.libsavegame.link.Link;

public class Block19 implements Link {
    @Override
    public void load(SavegameData io) throws FileFormatException {
        for (int from = 0; from < 32; from++) {
            for (int type = 0; type < 4; type++) {
                int bitmask = io.readInt(19, 20 * from + 4 * (type > 1 ? type + 1 : type));
                for (int to = 0; to < 32; to++) {
                    vars.pedAcq[type][from][to] = (bitmask & (1 << to)) != 0;
                }
            }
        }
    }

    @Override
    public void save(SavegameData io) {
        for (int from = 0; from < 32; from++) {
            for (int type = 0; type < 4; type++) {
                int bitmask = 0;
                for (int to = 0; to < 32; to++) {
                    if (vars.pedAcq[type][from][to]) {
                        bitmask |= 1 << to;
                    }
                }
                io.writeInt(19, 20 * from + 4 * (type > 1 ? type + 1 : type), bitmask);
            }
        }
    }
}
