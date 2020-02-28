package nl.paulinternet.libsavegame.blocks;

import nl.paulinternet.libsavegame.SavegameData;
import nl.paulinternet.libsavegame.data.Pickup;
import nl.paulinternet.libsavegame.exceptions.FileFormatException;
import nl.paulinternet.libsavegame.link.Link;

import java.util.ArrayList;

public class Block06 implements Link {
    @Override
    public void load(SavegameData io) throws FileFormatException {
        vars.oysters = new ArrayList<Pickup>();
        vars.snapshots = new ArrayList<Pickup>();
        vars.horseshoes = new ArrayList<Pickup>();

        for (int i = 0; i < 620; i++) {
            int start = 0x20 * i;
            int object = io.readShort(6, start + 0x18);

            if (object == 953 || object == 1253 || object == 954) {
                int avail = io.readByte(6, start + 0x1d);
                if (avail == 0) {
                    int x = io.readShort(6, start + 0x10);
                    int y = io.readShort(6, start + 0x12);
                    Pickup pickup = new Pickup(i, x, y);

                    switch (object) {
                        case 953:
                            vars.oysters.add(pickup);
                            break;
                        case 1253:
                            vars.snapshots.add(pickup);
                            break;
                        case 954:
                            vars.horseshoes.add(pickup);
                            break;
                    }
                }
            }
        }

        vars.helpReplaceWeapon.setValue(io.readByte(6, 0x4d82));
    }

    @Override
    public void save(SavegameData io) {
        io.writeByte(6, 0x4d82, vars.helpReplaceWeapon.getValue());
    }
}
