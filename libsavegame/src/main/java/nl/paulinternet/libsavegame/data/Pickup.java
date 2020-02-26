package nl.paulinternet.libsavegame.data;

import nl.paulinternet.libsavegame.ByteSequence;
import nl.paulinternet.libsavegame.Savegame;

public class Pickup {
    private int pos;
    private int x, y;

    public Pickup(int pos, int x, int y) {
        this.pos = pos;
        this.x = x;
        this.y = y;
    }

    public void pickup() {
        ByteSequence block6 = Savegame.get().getData().getBlock(6);
        block6.setByte(pos * 0x20 + 0x1c, (byte) 0);
        block6.setByte(pos * 0x20 + 0x1d, (byte) 9);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
