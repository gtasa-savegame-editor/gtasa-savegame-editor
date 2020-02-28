package nl.paulinternet.libsavegame.blocks;

import nl.paulinternet.libsavegame.SavegameData;
import nl.paulinternet.libsavegame.data.Jump;
import nl.paulinternet.libsavegame.link.Link;
import nl.paulinternet.libsavegame.link.LinkArray;
import nl.paulinternet.libsavegame.link.LinkFloat;
import nl.paulinternet.libsavegame.link.LinkInt;

public class Block16 extends LinkArray {
    public Block16() {
        setLinks(new Link[]{
                new LinkFloat(vars.fat, 16, 0x54),
                new LinkFloat(vars.stamina, 16, 0x58),
                new LinkFloat(vars.muscle, 16, 0x5c),
                new LinkFloat(vars.maxHealth, 16, 0x60),
                new LinkFloat(vars.respect, 16, 0x100),
                new LinkFloat(vars.weaponPistol, 16, 0x114),
                new LinkFloat(vars.weaponSilencedPistol, 16, 0x118),
                new LinkFloat(vars.weaponDesertEagle, 16, 0x11c),
                new LinkFloat(vars.weaponShotgun, 16, 0x120),
                new LinkFloat(vars.weaponSawnoffShotgun, 16, 0x124),
                new LinkFloat(vars.weaponCombatShotgun, 16, 0x128),
                new LinkFloat(vars.weaponMachinePistol, 16, 0x12c),
                new LinkFloat(vars.weaponSmg, 16, 0x130),
                new LinkFloat(vars.weaponAk47, 16, 0x134),
                new LinkFloat(vars.weaponM4, 16, 0x138),
                new LinkFloat(vars.sexAppeal, 16, 0x140),
                new LinkFloat(vars.gamblingSkill, 16, 0x144),
                new LinkInt(vars.timesBusted, 16, 0x17c),
                new LinkInt(vars.daysPassed, 16, 0x180),
                new LinkInt(vars.timesWasted, 16, 0x184),
                new LinkInt(vars.timesCheated, 16, 0x18c),
                new LinkInt(vars.drivingSkill, 16, 0x1e8),
                new LinkInt(vars.storyLine, 16, 0x23c),
                new LinkInt(vars.flyingSkill, 16, 0x2e4),
                new LinkInt(vars.lungCapacity, 16, 0x2ec),
                new LinkInt(vars.bikeSkill, 16, 0x2fc),
                new LinkInt(vars.cyclingSkill, 16, 0x300),
                new LinkInt(vars.snapshotsCollected, 16, 0x304),
                new LinkInt(vars.horseshoesCollected, 16, 0x32c),
                new LinkInt(vars.oystersCollected, 16, 0x334),
        });
    }

    @Override
    public void save(SavegameData io) {
        // Save variables
        super.save(io);

        // Save jumps
        int found = 0, completed = 0;

        for (Jump jump : vars.jumps) {
            if (jump.found) found++;
            if (jump.completed) completed++;
        }

        io.writeInt(16, 0x1a8, found);
        io.writeInt(16, 0x1ac, completed);
    }
}
