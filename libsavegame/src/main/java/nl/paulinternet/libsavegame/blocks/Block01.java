package nl.paulinternet.libsavegame.blocks;

import nl.paulinternet.libsavegame.SavegameData;
import nl.paulinternet.libsavegame.exceptions.FileFormatException;
import nl.paulinternet.libsavegame.link.Link;
import nl.paulinternet.libsavegame.link.LinkArray;
import nl.paulinternet.libsavegame.link.LinkBoolean;
import nl.paulinternet.libsavegame.link.LinkInt;
import nl.paulinternet.libsavegame.variables.Variable;

import java.util.List;

public class Block01 extends LinkArray {
    private static final int[] DRIVING = new int[]{1, 2, 4, 5, 7, 9, 10, 11, 13, 14, 15, 16};
    private static final int[] FLYING = new int[]{1, 2, 4, 6, 7, 8, 9, 10, 11, 12};

    public Block01() {
        setLinks(new Link[]{
                new LinkBoolean(vars.hotCoffee, 1, 0x1310, 1, LinkBoolean.INVERSE),
                new LinkBoolean(vars.helpCar, 1, 0x158, 1),
                new LinkBoolean(vars.helpWasted, 1, 0x1e0, 1),
                new LinkBoolean(vars.helpBusted, 1, 0x1fc, 1),
                new LinkBoolean(vars.helpSwimming, 1, 0x3e4, 1, LinkBoolean.AT_LEAST, 7),
                new LinkBoolean(vars.helpGym, 1, 0x1514, 1),
                new LinkBoolean(vars.basketballGlitch, 1, 0x1e24, 1),
                new LinkInt(vars.schoolDriving.get(0), 1, 0x1b0),
                new LinkInt(vars.schoolDriving.get(1), 1, 0x1a8),
                new LinkInt(vars.schoolDriving.get(2), 1, 0x18c),
                new LinkInt(vars.schoolDriving.get(3), 1, 0x184),
                new LinkInt(vars.schoolDriving.get(4), 1, 0x17c),
                new LinkInt(vars.schoolDriving.get(5), 1, 0x1a0),
                new LinkInt(vars.schoolDriving.get(6), 1, 0x170),
                new LinkInt(vars.schoolDriving.get(7), 1, 0x198),
                new LinkInt(vars.schoolDriving.get(8), 1, 0x174),
                new LinkInt(vars.schoolDriving.get(9), 1, 0x19c),
                new LinkInt(vars.schoolDriving.get(10), 1, 0x194),
                new LinkInt(vars.schoolDriving.get(11), 1, 0x188),
                new LinkInt(vars.schoolFlying.get(0), 1, 0x1e5c),
                new LinkInt(vars.schoolFlying.get(1), 1, 0x1e60),
                new LinkInt(vars.schoolFlying.get(2), 1, 0x1e64),
                new LinkInt(vars.schoolFlying.get(3), 1, 0x1e68),
                new LinkInt(vars.schoolFlying.get(4), 1, 0x1e6c),
                new LinkInt(vars.schoolFlying.get(5), 1, 0x1e70),
                new LinkInt(vars.schoolFlying.get(6), 1, 0x1e74),
                new LinkInt(vars.schoolFlying.get(7), 1, 0x1e78),
                new LinkInt(vars.schoolFlying.get(8), 1, 0x1e7c),
                new LinkInt(vars.schoolFlying.get(9), 1, 0x1e80),
                new LinkInt(vars.schoolBoat.get(0), 1, 0x1eb0),
                new LinkInt(vars.schoolBoat.get(1), 1, 0x1eb4),
                new LinkInt(vars.schoolBoat.get(2), 1, 0x1eb8),
                new LinkInt(vars.schoolBoat.get(3), 1, 0x1ebc),
                new LinkInt(vars.schoolBoat.get(4), 1, 0x1ec0),
                new LinkInt(vars.schoolBike.get(0), 1, 0x21dc),
                new LinkInt(vars.schoolBike.get(1), 1, 0x21e0),
                new LinkInt(vars.schoolBike.get(2), 1, 0x21d4),
                new LinkInt(vars.schoolBike.get(3), 1, 0x21e4),
                new LinkInt(vars.schoolBike.get(4), 1, 0x21d8),
                new LinkInt(vars.schoolBike.get(5), 1, 0x21e8),
                new LinkBoolean(vars.poolPlayerGlitch, 1, 0x93dc, 1),
        });
    }

    @Override
    public void load(SavegameData io) throws FileFormatException {
        super.load(io);

        // Script version
        int afterGlobalVars = 0x4 + io.readInt(1, 0);

        int version;
        switch (io.readInt(1, afterGlobalVars + 0x8f2)) {
            case 194146:
                version = 1;
                break;
            case 194125:
                version = 2;
                break;
            default:
                version = 0;
        }

        vars.scriptVersion.setValue(version);
        vars.scriptVersion.setEnabled(version != 0);
        vars.scriptVersion.resetChangedState();

        // Driving
        boolean drivingEditable = io.readInt(1, 0xd8) != 0;
        for (int i = 0; i < 11; i++) {
            vars.schoolDriving.get(i).setEnabled(drivingEditable);
        }
        vars.schoolDriving.get(11).setEnabled(io.readBoolean(1, 0x15c));

        // Flying
        vars.schoolFlying.get(9).setEnabled(io.readInt(1, 0x948) > 4);

        // Boat
        vars.schoolBoat.get(4).setEnabled(io.readBoolean(1, 0x1ec8));

        // Two timing date
        int value = io.readInt(1, 1236 * 4 + 4);
        vars.twoTimingDate.setValue(value >= 0);
        vars.twoTimingDate.setEnabled(value <= 0);
        vars.twoTimingDate.resetChangedState();
    }

    @Override
    public void save(SavegameData io) {
        // Script version
        if (vars.scriptVersion.hasChanged()) {
            int version = vars.scriptVersion.getValue();

            // Write scm size
            int afterGlobalVars = 0x4 + io.readInt(1, 0);
            io.writeInt(1, afterGlobalVars + 0x8f2, version == 1 ? 194146 : 194125);

            // Write threads
            int add = version == 1 ? 21 : -21;
            int threads = io.readInt(1, afterGlobalVars + 0x902);
            for (int i = 0; i < threads; i++) {
                int threadStart = afterGlobalVars + 0x906 + i * 0x106;

                // Write Absolute IP and Absolute Return Stack array
                for (int v = 0; v < 9; v++) {
                    int pos = threadStart + 0x16 + 4 * v;
                    int value = io.readInt(1, pos);
                    if (value != 0) io.writeInt(1, pos, value + add);
                }

                // Write Relative IP and Relative Return Stack array
                for (int v = 0; v < 9; v++) {
                    int pos = threadStart + 0xe2 + 4 * v;
                    int value = io.readInt(1, pos);
                    if (value != 0) io.writeInt(1, pos, value + add);
                }
            }
        }

        // Save the number of tags sprayed
        int sprayed = 0;
        for (int value : vars.tags) {
            if (value >= 229) sprayed++;
        }

        io.writeInt(1, 0xc88, sprayed);

        // Driving
        if (hasChanged(vars.schoolDriving)) {
            int i;
            //noinspection StatementWithEmptyBody
            for (i = 11; i >= 0 && vars.schoolDriving.get(i).getValue() < 70; i--) ;
            if (i != 11) i++;
            io.writeInt(1, 0xd8, DRIVING[i]);
        }

        // Flying
        if (hasChanged(vars.schoolFlying)) {
            int i;
            //noinspection StatementWithEmptyBody
            for (i = 9; i >= 0 && vars.schoolFlying.get(i).getValue() < 70; i--) ;
            if (i != 9) i++;
            io.writeInt(1, 0x1e84, FLYING[i]);
            io.writeInt(1, 0x1e88, FLYING[i]);
        }

        // Boat
        if (hasChanged(vars.schoolBoat)) {
            int i;
            if (vars.schoolBoat.get(4).getValue() < 180000 || vars.schoolBoat.get(3).getValue() > 55) i = 5;
            else if (vars.schoolBoat.get(2).getValue() < 120000) i = 4;
            else if (vars.schoolBoat.get(1).getValue() < 40000) i = 3;
            else if (vars.schoolBoat.get(0).getValue() < 12000) i = 2;
            else i = 1;
            io.writeInt(1, 0x1eac, i);
        }

        // Bike
        if (hasChanged(vars.schoolBike)) {
            int i;
            //noinspection StatementWithEmptyBody
            for (i = 5; i >= 0 && vars.schoolBike.get(i).getValue() < 70; i--) ;
            if (i != 5) i++;
            io.writeInt(1, 0x21cc, i + 1);
        }

        // Two timing date
        if (vars.twoTimingDate.hasChanged()) {
            io.writeInt(1, 1236 * 4 + 4, vars.twoTimingDate.getValue() ? 0 : -1);
            vars.twoTimingDate.resetChangedState();
        }

        // Save vars
        super.save(io);
    }

    private boolean hasChanged(List<Variable<Integer>> list) {
        for (Variable<Integer> item : list) {
            if (item.hasChanged()) return true;
        }
        return false;
    }
}
