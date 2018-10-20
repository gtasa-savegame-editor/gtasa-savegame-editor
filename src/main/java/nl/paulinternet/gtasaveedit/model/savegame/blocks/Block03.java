package nl.paulinternet.gtasaveedit.model.savegame.blocks;

import static nl.paulinternet.gtasaveedit.model.Model.vars;

import nl.paulinternet.gtasaveedit.model.exceptions.FileFormatException;
import nl.paulinternet.gtasaveedit.model.link.Link;
import nl.paulinternet.gtasaveedit.model.link.LinkArray;
import nl.paulinternet.gtasaveedit.model.link.LinkFloat;
import nl.paulinternet.gtasaveedit.model.link.LinkInt;
import nl.paulinternet.gtasaveedit.model.savegame.SavegameData;
import nl.paulinternet.gtasaveedit.model.savegame.data.Garage;
import nl.paulinternet.gtasaveedit.model.savegame.data.RadioStation;
import nl.paulinternet.gtasaveedit.model.savegame.data.VehicleMod;
import nl.paulinternet.gtasaveedit.model.savegame.data.VehicleType;

import java.util.ArrayList;


/**
 * @author Sebastian Hardt (s.hardt@micromata.de) Date: 19.10.18 Time: 17:07
 */
public class Block03 extends LinkArray {

    public Block03() {
        ArrayList<Link> links = new ArrayList<>();

        for (int i = 0; i < Garage.TOTAL_COUNT; i++) {
            links.add(new LinkInt(vars.carIds.get(i), 3, 0, 0)); // TODO make sure `pos` and `size are correct`
            links.add(new LinkInt(vars.radioIds.get(i), 3, 0, 0)); // TODO make sure `pos` and `size are correct`
            links.add(new LinkInt(vars.color1Ids.get(i), 3, 0, 0)); // TODO make sure `pos` and `size are correct`
            links.add(new LinkInt(vars.color2Ids.get(i), 3, 0, 0)); // TODO make sure `pos` and `size are correct`
        }

        setLinks((Link[]) links.toArray());
    }

    @Override
    public void load(SavegameData io) throws FileFormatException {
        super.load(io);

        System.out.println("ID;TYPE;NAME;XPOS;YPOS;ZPOS;NITRO;PAINTJOB;RADIO;ANGLX;ANGLY;ANGLZ;COL1;COL2;F1;F2;F3;F4;F5;F6;MODS");

        // lets try something here
        for (int i = 0; i < Garage.TOTAL_COUNT; i++) {

            int size = 64;

            int pos = 0x27 + size * i;

            byte[] bytes = io.getBlock(3).getBytes(pos, pos + size);

            //noinspection PointlessArithmeticExpression zero for clarity
            final float xPos = io.readFloat(3, pos + 0);
            final float yPos = io.readFloat(3, pos + 4);
            final float zPos = io.readFloat(3, pos + 8);

            final int f1 = io.readByte(3, pos + 12);
            final int f2 = io.readByte(3, pos + 13);
            final int f3 = io.readByte(3, pos + 14);
            final int f4 = io.readByte(3, pos + 15);
            final int f5 = io.readByte(3, pos + 16);
            final int f6 = io.readByte(3, pos + 17);

            final int type = io.readInt(3, pos + 18, 2);

            final int mod1 = io.readInt(3, pos + 20, 2);
            final int mod2 = io.readInt(3, pos + 22, 2);
            final int mod3 = io.readInt(3, pos + 24, 2);
            final int mod4 = io.readInt(3, pos + 26, 2);
            final int mod5 = io.readInt(3, pos + 28, 2);
            final int mod6 = io.readInt(3, pos + 30, 2);
            final int mod7 = io.readInt(3, pos + 32, 2);
            final int mod8 = io.readInt(3, pos + 34, 2);
            final int mod9 = io.readInt(3, pos + 36, 2);
            final int mod10 = io.readInt(3, pos + 38, 2);
            final int mod11 = io.readInt(3, pos + 40, 2);
            final int mod12 = io.readInt(3, pos + 42, 2);
            final int mod13 = io.readInt(3, pos + 44, 2);
            final int mod14 = io.readInt(3, pos + 46, 2);
            final int mod15 = io.readInt(3, pos + 48, 2);

            final int color1 = io.readByte(3, pos + 50);
            final int color2 = io.readByte(3, pos + 51);

            final int radioStation = io.readByte(3, pos + 54);

            final int paintJob = io.readByte(3, pos + 58);
            final int nitro = io.readByte(3, pos + 59);

            final int x = io.readByte(3, pos + 60);
            final int y = io.readByte(3, pos + 61);
            final int z = io.readByte(3, pos + 62);


            //TODO where to save `id`? What does it even mean? It's apparently neither the car id nor the garage id.
            final String id = (i < 10) ? " " + i : String.valueOf(i);

            final VehicleType vehicleType = VehicleType.getType(type);

            final RadioStation radioStation1 = RadioStation.getStation(radioStation);

            if(type!=0) {
                vars.carIds.get(i).setIntValue(vehicleType.getId());
                vars.radioIds.get(i).setIntValue(radioStation);
                vars.color1Ids.get(i).setIntValue(color1);
                vars.color2Ids.get(i).setIntValue(color2);
            }

            printDebugCsv(io, pos, xPos, yPos, zPos, type, color1, color2, paintJob, nitro, x, y, z, id, vehicleType, radioStation1);
        }

        //http://gta.wikia.com/wiki/Garage
        ////https://github.com/goodidea82/GarageExtender/tree/master/Source
        int garageOffset = 5159;

        for (int i = 0; i < 50; i++) {
            int size = 80;

            int pos = garageOffset + size * i;

            byte[] bytes = io.getBlock(3).getBytes(pos, pos + size);

            final byte[] nameBytes = getBytes(68, 76, bytes);

            System.out.println("Garage: " + i + " name: "+ new String(nameBytes));

        }

    }

    private void printDebugCsv(SavegameData io, int pos, float xPos, float yPos, float zPos, int type, int color1, int color2, int paintJob, int nitro, int x, int y, int z, String id, VehicleType vehicleType, RadioStation radioStation1) {
        String debugStr = id + ";" +
                vehicleType.getType() + ";" +
                vehicleType.getName() + ";" +
                xPos + ";" +
                yPos + ";" +
                zPos + ";" +
                nitro + ";" +
                paintJob + ";" +
                radioStation1.getName() + ";" +
                x + ";" +
                y + ";" +
                z + ";" +
                color1 + " ;" +
                color2 + ";";

        StringBuilder fString = new StringBuilder();
        for (int j = 0; j < 6; j++) {
            final int fst = io.readByte(3, pos + 12 + j);
            fString.append(fst).append(";");
        }
        debugStr += fString;

        StringBuilder modsString = new StringBuilder();
        for (int j = 0; j < 15; j++) {
            final int mod = io.readInt(3, pos + 20 + j * 2, 2);
            if (mod != 65535 && mod != 0) {
                final VehicleMod vehicleMod = VehicleMod.getMod(mod);
                modsString.append(vehicleMod.getName()).append(" (").append(vehicleMod.getType()).append("), ");
            }
        }
        debugStr += modsString;

        if (type != 0) {
            System.out.println(debugStr);
        }
    }

    public byte[] getBytes(int begin, int end, byte[] array) {

        // Create a new byte array
        byte[] data = new byte[end - begin];

        for (int i = 0; i < end - begin; i++) {
            data[i] = array[begin + i];
        }

        // Return
        return data;
    }

}
