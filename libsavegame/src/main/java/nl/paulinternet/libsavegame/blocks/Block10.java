package nl.paulinternet.libsavegame.blocks;

import nl.paulinternet.libsavegame.SavegameData;
import nl.paulinternet.libsavegame.data.Zone;
import nl.paulinternet.libsavegame.data.Zones;
import nl.paulinternet.libsavegame.link.Link;

public class Block10 implements Link {
    @Override
    public void load(SavegameData io) {
        for (Zone zone : Zones.getZones()) {
            int pos = 0x2f6a + 0x11 * zone.getId();

            // Read gangs
            for (int i = 0; i < 8; i++) {
                zone.setValue(i, io.readByte(10, pos + i));
            }

            // Read other vars
            zone.setValue(Zone.DEALER, io.readByte(10, pos + 0xa));

            int popcycle = io.readByte(10, pos + 0xf);
            zone.setValue(Zone.POPCYCLE, popcycle & 0x1f);
            zone.setValue(Zone.PED, io.readByte(10, pos + 0x10));
            zone.setValue(Zone.GANG_PRESENT, popcycle & 0x20);
            zone.setValue(Zone.DISABLE_FOOTCOPS, popcycle & 0x80);
        }

        // Check sum of discovered areas
        int discovered = 0;

        for (int i = 0; i < 100; i++) {
            if (io.readBoolean(10, 0x4964 + i)) discovered++;
        }

        vars.zoneGlitch.setValue(io.readInt(10, 0x49c8) != discovered);
    }

    @Override
    public void save(SavegameData io) {
        for (Zone zone : Zones.getZones()) {
            int pos = 0x2f6a + 0x11 * zone.getId();

            // Save gangs
            for (int i = 0; i < 8; i++) {
                io.writeByte(10, pos + i, zone.getValue(i));
            }

            // Save other vars
            io.writeByte(10, pos + 0xa, zone.getValue(Zone.DEALER));

            int popcycle = zone.getValue(Zone.POPCYCLE);
            popcycle |= zone.getValue(Zone.GANG_PRESENT) * 0x20;
            popcycle |= zone.getValue(Zone.DISABLE_FOOTCOPS) * 0x80;

            io.writeByte(10, pos + 0xf, popcycle);
            io.writeByte(10, pos + 0x10, zone.getValue(Zone.PED));
        }
    }

}
