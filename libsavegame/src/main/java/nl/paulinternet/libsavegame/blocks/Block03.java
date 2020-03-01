package nl.paulinternet.libsavegame.blocks;

import nl.paulinternet.libsavegame.SavegameData;
import nl.paulinternet.libsavegame.data.Garage;
import nl.paulinternet.libsavegame.data.RadioStation;
import nl.paulinternet.libsavegame.data.VehicleMod;
import nl.paulinternet.libsavegame.data.VehicleType;
import nl.paulinternet.libsavegame.exceptions.FileFormatException;
import nl.paulinternet.libsavegame.link.Link;
import nl.paulinternet.libsavegame.link.LinkArray;
import nl.paulinternet.libsavegame.link.LinkInt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Sebastian Hardt (s.hardt@micromata.de) Date: 19.10.18 Time: 17:07
 */
public class Block03 extends LinkArray {

    private static final Logger log = LoggerFactory.getLogger(Block03.class);

    public Block03() {
        Link[] links = new Link[Garage.TOTAL_COUNT * (7 + Garage.Car.MOD_COUNT)]; // nGarages * (props + mods)
        for (int i = 0; i < Garage.TOTAL_COUNT; i++) { // for each garage
            int count = i * (7 + Garage.Car.MOD_COUNT); // each "step" has to raise by the number of properties per car/"parking space"
            Garage.Car car = vars.garageCars.get(i); // get current car
            int pos = getPos(i);

            links[count] = new LinkInt(car.getId(), 3, 0, 0);
            links[count + 1] = new LinkInt(car.getCarId(), 3, pos + 18, 2);
            links[count + 2] = new LinkInt(car.getRadioId(), 3, pos + 54);
            links[count + 3] = new LinkInt(car.getColor1(), 3, pos + 50);
            links[count + 4] = new LinkInt(car.getColor2(), 3, pos + 51);
            links[count + 5] = new LinkInt(car.getPaintJob(), 3, pos + 58);
            links[count + 6] = new LinkInt(car.getNitro(), 3, pos + 59);

            for (int j = 0; j < Garage.Car.MOD_COUNT; j++) {
                links[count + 5 + j] = (new LinkInt(car.getMods().get(j), 3, pos + 20 + j, 2)); // mods -> pos20 - pos48
            }
        }
        setLinks(links);
    }

    @Override
    public void load(SavegameData io) throws FileFormatException {
        log.debug("ID;TYPE;NAME;XPOS;YPOS;ZPOS;NITRO;PAINTJOB;RADIO;ANGLX;ANGLY;ANGLZ;COL1;COL2;F1;F2;F3;F4;F5;F6;MODS"); //csvHeader

        // lets try something here
        for (int i = 0; i < Garage.TOTAL_COUNT; i++) {
            int pos = getPos(i);

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

            final int[] mods = new int[Garage.Car.MOD_COUNT];

            mods[0] = io.readInt(3, pos + 20, 2);
            mods[1] = io.readInt(3, pos + 22, 2);
            mods[2] = io.readInt(3, pos + 24, 2);
            mods[3] = io.readInt(3, pos + 26, 2);
            mods[4] = io.readInt(3, pos + 28, 2);
            mods[5] = io.readInt(3, pos + 30, 2);
            mods[6] = io.readInt(3, pos + 32, 2);
            mods[7] = io.readInt(3, pos + 34, 2);
            mods[8] = io.readInt(3, pos + 36, 2);
            mods[9] = io.readInt(3, pos + 38, 2);
            mods[10] = io.readInt(3, pos + 40, 2);
            mods[11] = io.readInt(3, pos + 42, 2);
            mods[12] = io.readInt(3, pos + 44, 2);
            mods[13] = io.readInt(3, pos + 46, 2);
            mods[14] = io.readInt(3, pos + 48, 2);

            final int color1 = io.readByte(3, pos + 50);
            final int color2 = io.readByte(3, pos + 51);

            final int radioStation = io.readByte(3, pos + 54);

            final int paintJob = io.readByte(3, pos + 58);
            final int nitro = io.readByte(3, pos + 59);

            final int x = io.readByte(3, pos + 60);
            final int y = io.readByte(3, pos + 61);
            final int z = io.readByte(3, pos + 62);

            final String id = (i < 10) ? " " + i : String.valueOf(i);

            final VehicleType vehicleType = VehicleType.getType(type);
            final RadioStation radioStation1 = RadioStation.getStation(radioStation);

            if (type != 0) {
                Garage.Car car = vars.garageCars.get(i);
                car.getId().setValue(Integer.parseInt(id.trim()));
                car.getCarId().setValue(vehicleType.getId());
                car.getRadioId().setValue(radioStation);
                car.getColor1().setValue(color1);
                car.getColor2().setValue(color2);
                car.getPaintJob().setValue(paintJob);
                car.getNitro().setValue(nitro);
                for (int j = 0; j < Garage.Car.MOD_COUNT; j++) {
                    car.getMods().get(j).setValue(mods[j]);
                }
            }

            printDebugCsv(io, pos, xPos, yPos, zPos, type, color1, color2, paintJob, nitro, x, y, z, id, vehicleType, radioStation1, mods);
        }

        //http://gta.wikia.com/wiki/Garage
        ////https://github.com/goodidea82/GarageExtender/tree/master/Source
        int garageOffset = 5159;

        for (int i = 0; i < 50; i++) {
            int size = 80;
            int pos = garageOffset + size * i;
            byte[] bytes = io.getBlock(3).getBytes(pos, pos + size);
            final byte[] nameBytes = getBytes(68, 76, bytes);
            log.debug("Garage: " + i + " name: " + new String(nameBytes));
        }

    }

    private int getPos(int i) {
        int size = 64;
        return 0x27 + size * i;
    }

    @Override
    public void save(SavegameData io) {
        for (Link link : links) {
            if (link != null) {
                link.save(io);
            }
        }
    }

    private void printDebugCsv(SavegameData io, int pos, float xPos, float yPos, float zPos, int type, int color1, int color2, int paintJob, int nitro, int x, int y, int z, String id, VehicleType vehicleType, RadioStation radioStation1, int[] mods) {
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
        for (int mod : mods) {
            if (mod != 65535 && mod != 0) {
                final VehicleMod vehicleMod = VehicleMod.getMod(mod);
                if (vehicleMod != null) {
                    modsString.append(vehicleMod.getName())
                            .append(" (").append(vehicleMod.getType()).append(", id:'").append(mod).append("'), ");
                } else {
                    modsString.append("Invalid (id:'").append(mod).append("'), ");
                }
            }
        }
        debugStr += modsString;

        if (type != 0) {
            log.debug(debugStr);
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
