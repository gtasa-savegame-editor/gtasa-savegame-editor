package nl.paulinternet.libsavegame.blocks;

import nl.paulinternet.libsavegame.link.Link;
import nl.paulinternet.libsavegame.link.LinkArray;
import nl.paulinternet.libsavegame.link.LinkBoolean;

public class Block04 extends LinkArray {
    public Block04() {
        setLinks(new Link[]{
                new LinkBoolean(vars.loseStuffWasted, 4, 0x4, 1),
                new LinkBoolean(vars.loseStuffBusted, 4, 0x5, 1)
        });
    }
}
