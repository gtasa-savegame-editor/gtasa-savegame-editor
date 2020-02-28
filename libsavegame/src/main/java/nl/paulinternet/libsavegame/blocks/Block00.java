package nl.paulinternet.libsavegame.blocks;

import nl.paulinternet.libsavegame.SavegameData;
import nl.paulinternet.libsavegame.exceptions.FileFormatException;
import nl.paulinternet.libsavegame.link.Link;
import nl.paulinternet.libsavegame.link.LinkArray;
import nl.paulinternet.libsavegame.link.LinkBoolean;
import nl.paulinternet.libsavegame.link.LinkInt;
import nl.paulinternet.libsavegame.variables.Variables;

public class Block00 extends LinkArray {
    public Block00() {

        setLinks(new Link[]{
                new LinkInt(vars.version, 0, 0),
                new LinkInt(vars.minuteLength, 0, 0x7c),
                new LinkInt(vars.timeHour, 0, 0x86, 1),
                new LinkInt(vars.timeMinute, 0, 0x87, 1),
                new LinkInt(vars.timeDayOfWeek, 0, 0x88, 1),
                new LinkBoolean(vars.riots, 0, 0xe0, 1),
                new LinkBoolean(vars.taxiNitro, 0, 0x135, 1),
                new LinkBoolean(vars.prostitutesPay, 0, 0x136, 1),
                new LinkBoolean(vars.uncensored, 0, 0xee, 1),
                new LinkBoolean(vars.helpStealVehicle, 0, 0x134, 1),
        });
    }

    @Override
    public void load(SavegameData io) throws FileFormatException {
        // Load other vars
        super.load(io);

        // Load title
        byte[] data = io.getBlock(0).getBytes(4, 104);
        int i;
        //noinspection StatementWithEmptyBody
        for (i = 0; i < data.length && data[i] != 0; i++) ;
        String text = new String(data, 0, i);
        text = text.replace(']', '*').replace('(', '[').replace(')', ']');
        vars.title.setText(text);

        vars.title.resetChangedState();
    }

    @Override
    public void save(SavegameData io) {
        // Save title
        if (vars.title.hasChanged()) {
            String text = vars.title.getText();
            text = text.replace(']', ')').replace('[', '(').replace('*', ']');
            byte[] data = (text + "\0").getBytes();
            io.getBlock(0).overwrite(data, 4);

            vars.title.resetChangedState();
        }

        // Save times cheated
        io.writeBoolean(0, 0x90, vars.timesCheated.getValue() != 0);

        // Save other vars
        super.save(io);
    }
}
