package nl.paulinternet.libsavegame.blocks;

import nl.paulinternet.libsavegame.link.Link;
import nl.paulinternet.libsavegame.link.LinkArray;
import nl.paulinternet.libsavegame.link.LinkBoolean;

public class Block23 extends LinkArray {
    public Block23() {
        setLinks(new Link[]{
                new LinkBoolean(vars.gangWars, 23, 0x4, 1)
        });
    }
}
