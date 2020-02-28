package nl.paulinternet.libsavegame.link;

import nl.paulinternet.libsavegame.ByteSequence;
import nl.paulinternet.libsavegame.SavegameData;
import nl.paulinternet.libsavegame.data.Savehouse;
import nl.paulinternet.libsavegame.data.Saveplace;

public class SaveplaceLink implements Link {
    @Override
    public void load(SavegameData io) {
        boolean inside = io.readInt(0, 0xc4) != 0;
        int house = io.readInt(1, 0xdd0);
        int location = inside ? io.readShort(25, 0x4) : -1;

        Saveplace saveplace = Saveplace.detect(inside, house, location);
        vars.savePlace.setValue(saveplace == null ? -1 : saveplace.getId());
        vars.savePlace.resetChangedState();
    }

    @Override
    public void save(SavegameData io) {
        if (vars.savePlace.hasChanged()) {
            Saveplace place = Saveplace.getPlace(vars.savePlace.getValue());

            // Get values
            Savehouse house = place.getHouse();

            boolean oldInside = io.readInt(0, 0xc4) != 0;
            boolean inside = place.isInside();
            int heaven = house.getHeaven();
            int location = place.getLocation();
            int houseId = house.getId();

            // Change the size of the blocks when necessary
            if (inside != oldInside) {
                ByteSequence block25 = io.getBlock(25);
                ByteSequence block27 = io.getBlock(27);

                if (inside) {
                    block25 = block25.getSubSequence(0, 0x4).appendSpace(2).append(block25.getSubSequence(0x4));
                    block27 = block27.getSubSequence(0, 0x8c).append(block27.getSubSequence(0x8e));
                } else {
                    block25 = block25.getSubSequence(0, 0x4).append(block25.getSubSequence(0x6));
                    block27 = block27.getSubSequence(0, 0x8c).appendSpace(2).append(block27.getSubSequence(0x8c));
                }

                io.setBlock(25, block25);
                io.setBlock(27, block27);
            }

            // Write vars
            float[] camera = house.getCamera();

            io.writeFloat(0, 0x70, camera[0]);
            io.writeFloat(0, 0x74, camera[1]);
            io.writeFloat(0, 0x78, camera[2]);
            io.writeInt(0, 0xc4, heaven);
            io.writeBoolean(0, 0xd0, inside);
            io.writeFloat(0, 0xd4, inside ? 1.0f : 0.0f);
            io.writeBoolean(1, 0xa0, !inside);
            io.writeInt(1, 0xdd0, houseId);
            io.writeInt(2, 0x196, 1, heaven);
            io.writeInt(2, 0x198, location);
            io.writeBoolean(25, 0x0, inside);

            if (inside) {
                io.writeShort(25, 0x4, (short) location);

                if (house.getTeleport()) {
                    io.writeShort(25, 0xa + house.getLocationInside() * 0x6, (short) location);
                }
            }

            // Madd dogg
            if (houseId == 1) {
                io.writeByte(25, 0x53d, 0x60);
            }

            vars.savePlace.resetChangedState();
        }
    }
}
