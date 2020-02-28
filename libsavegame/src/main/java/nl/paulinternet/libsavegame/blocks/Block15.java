package nl.paulinternet.libsavegame.blocks;

import nl.paulinternet.libsavegame.link.Link;
import nl.paulinternet.libsavegame.link.LinkArray;
import nl.paulinternet.libsavegame.link.LinkBoolean;
import nl.paulinternet.libsavegame.link.LinkInt;

public class Block15 extends LinkArray {
    public Block15() {

        setLinks(new Link[]{
                new LinkInt(vars.money, 15, 0x4),
                new LinkInt(vars.moneyOnScreen, 15, 0x10),
                new LinkBoolean(vars.infiniteRun, 15, 0x20, 1),
                new LinkBoolean(vars.fastReload, 15, 0x21, 1),
                new LinkBoolean(vars.fireProof, 15, 0x22, 1),
                new LinkBoolean(vars.freeBustedOnce, 15, 0x25, 1),
                new LinkBoolean(vars.freeWastedOnce, 15, 0x26, 1),
                new LinkBoolean(vars.enableDriveby, 15, 0x27, 1),
        });
    }
}
