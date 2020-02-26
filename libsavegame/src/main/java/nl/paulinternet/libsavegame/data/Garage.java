package nl.paulinternet.libsavegame.data;

import nl.paulinternet.libsavegame.variables.Variable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Lukas Fülling (lukas@k40s.net)
 */
public class Garage {
    public static final int TOTAL_COUNT = 80;
    private final int id;
    private final Location location;
    private final String name, description;

    public Garage(int id, Location location, String name, String description) {
        this.id = id;
        this.location = location;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public Location getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public static List<Garage> getGarages() {
        return garages;
    }

    public static class Location {
        private final double x;
        private final double y;
        private final double z;

        private Location(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public double getZ() {
            return z;
        }
    }

    private static List<Garage> garages;

    static {
        garages = new ArrayList<>();

        // Data taken from: http://gta.wikia.com/wiki/Garage
        garages.add(new Garage(16, new Location(2502.31, -1699.36, 12.4323), "cjsafe", "Ganton garage (Grove St.)"));
        garages.add(new Garage(17, new Location(319.326, -1768.93, 3.35686), "beacsv", "Santa Maria Beach garage"));
        garages.add(new Garage(18, new Location(2449.5, 695.018, 10.4742), "vEsvgrg", "Rockshore West garage"));
        garages.add(new Garage(24, new Location(-364.439, 1194.37, 18.597), "cn2gar1", "Fort Carson garage"));
        garages.add(new Garage(25, new Location(430.059, 2542.31, 15.166), "cn2gar2", "Verdant Meadows garage"));
        garages.add(new Garage(26, new Location(783.155, -492.75, 16.3361), "burbdo2", "Dillimore garage"));
        garages.add(new Garage(27, new Location(1269.2, 2525.14, 9.80013), "blob69", "Prickle Pine garage"));
        garages.add(new Garage(28, new Location(929.554, 2008.59, 10.115), "blob7", "Whitewood Estates garage"));
        garages.add(new Garage(29, new Location(2227.6, 168.649, 26.4635), "burbdoo", "Palomino Creek garage"));
        garages.add(new Garage(30, new Location(1408.65, 1899.52, 10.115), "blob6", "Redsands West garage"));
        garages.add(new Garage(31, new Location(1694.8, -2088.7, 12.3636), "carlas1", "El Corona garage"));
        garages.add(new Garage(32, new Location(1352.58, -636.657, 108.135), "CEsafe1", "Muholland garage"));
        garages.add(new Garage(33, new Location(1523.92, -1653.23, 4.72837), "imp_la", "Los Santos impound lot"));
        garages.add(new Garage(34, new Location(-1652.78, 647.502, -6.04924), "imp_sf", "San Fierro impound lot"));
        garages.add(new Garage(35, new Location(2218.06, 2448.06, -8.43807), "imp_lv", "Las Venturas impound lot"));
        garages.add(new Garage(36, new Location(2640.78, -2049.99, 12.543), "modlast", "Loco Low Co"));
        garages.add(new Garage(37, new Location(-2728.53, 212.295, 3.45112), "mds1SFS", "Wheels Arch Angel"));
        garages.add(new Garage(39, new Location(-2108.92, 886.553, 75.566), "sav1sfe", "Calton Heights garage"));
        garages.add(new Garage(40, new Location(-2699.12, 821.489, 49.0042), "sav1sfw", "Paradiso garage"));
        garages.add(new Garage(41, new Location(-2043.1, 118.609, 27.821), "hbgdSFS", "Main Doherty garage"));
        garages.add(new Garage(42, new Location(-2454.02, -131.556, 25.0886), "svgsfs1", "Hashbury garage"));
        garages.add(new Garage(44, new Location(1550.98, 1155.36, 8.97329), "vgshngr", "AT 400 hangar"));
        garages.add(new Garage(45, new Location(383.843, 2433.28, 15.166), "dhangar", "Verdant Meadows hangar"));

        //FIXME: Add correct ids
        garages.add(new Garage(0, new Location(-1794.15, 1429.69, 4.37321), "", "Esplanade North garage used in \"Ran Fa Li\""));
        garages.add(new Garage(0, new Location(-1694.78, 1033.15, 44.1937), "", "Downtown garage used in \"Yay Ka-Boom-Boom\""));
        garages.add(new Garage(0, new Location(-2114.42, -2462.27, 29.4809), "amumis", "Angel Pine garage used in \"Puncture Wounds\""));
        garages.add(new Garage(0, new Location(1968.23, 2157.88, 9.59696), "blob1", "Redsands East Pay n Spray"));
        garages.add(new Garage(0, new Location(2002.96, 2303.72, 9.61706), "blob2", "Welding and Wedding bomb shop"));
        garages.add(new Garage(0, new Location(1038.24, -1025.67, 31.1027), "bodLAwN", "Los Santos Transfender"));
        garages.add(new Garage(0, new Location(	-2112.48, -21.214, 34.303	),"brgSFSE","San Fierro burglary garage"));
        garages.add(new Garage(0, new Location(2738.4, -2012.55, 12.5759), "burg_lk", "Los Santos burglary garage"));
        garages.add(new Garage(0, new Location(715.806, -462.403, 14.9635), "CEspray", "Dillimore Pay n Spray"));
        garages.add(new Garage(0, new Location(-103.636, 1112.42, 18.7017), "CN2spry", "Fort Carson Pay n Spray"));
        garages.add(new Garage(0, new Location(-1424.11, 2576.61, 54.8156), "CNspray", "El Quebrados Pay n Spray"));
        garages.add(new Garage(0, new Location(1873.97, -2096.55, 12.487), "duf_LAS", "El Corona garage used in \"Los Desperados\""));
        garages.add(new Garage(0, new Location(-2171.43, 649.416, 49.8742), "fdorsfe", "Wu Zi's garage"));
        garages.add(new Garage(0, new Location(-397.297, 2223.17, 41.3824), "ghostdr", "El Castillo del Diablo garage used in \"Interdiction\""));
        garages.add(new Garage(0, new Location(1843.91, -1858.8, 12.3645), "lasbomb", "Los Santos bomb shop"));
        garages.add(new Garage(0, new Location(-2057.35, 150.803, 27.8286), "LCKSfse", "Doherty garage"));
        garages.add(new Garage(0, new Location(-1941.04, 251.714, 33.4274), "mdsSFSe", "San Fierro Transfender"));
        garages.add(new Garage(0, new Location(-1790.97, 1209.71, 23.763), "michdr", "Michelle's garage"));
        garages.add(new Garage(0, new Location(1809.46, -2150.67, 12.4283), "modgLAS", "Cesar's garage"));
        garages.add(new Garage(0, new Location(1640.37, -1520.07, 12.5118), "mul_lan", "Downtown Los Santos garage used in \"Life's a Beach\""));
        garages.add(new Garage(0, new Location(2056.6, -1835.9, 12.5443), "sprLAE", "Idlewood Pay n Spray"));
        garages.add(new Garage(0, new Location(1021.81, -1018.71, 30.9081), "sprLAe", "Temple Pay n Spray"));
        garages.add(new Garage(0, new Location(491.103, -1747.55, 9.45516), "spLAw2", "Verona Beach Pay n Spray"));
        garages.add(new Garage(0, new Location(-1908.93, 292.353, 40.0413), "sprsfse", "Downtown San Fierro Pay n Spray"));
        garages.add(new Garage(0, new Location(-2430.13, 1013.71, 49.3413), "sprsfw", "Juniper Hollow Pay n Spray"));
        garages.add(new Garage(0, new Location(-2735.46, 60.7331, 3.07005), "tbon", "Ocean Flat garage used in \"T-Bone Mendez\""));
        garages.add(new Garage(0, new Location(2389.6, 1483.26, 9.81843), "timy1", "Las Venturas unused Pay n Spray"));
        garages.add(new Garage(0, new Location(2382.28, 1044, 9.8337), "vEcmod", "Las Venturas Transfender"));
        garages.add(new Garage(0, new Location(2602.6, 1438.84, 9.8337), "vgElock", "Las Venturas burglary garage"));
    }

    public static class Car {
        private final Variable<Integer> id;
        private final Variable<Integer> radioId;
        private final Variable<Integer> paintJob;
        private final Variable<Integer> color1;
        private final Variable<Integer> color2;
        private final Variable<Integer> carId;
        private final List<Variable<Integer>> mods;
        private final Variable<Integer> nitro;

        public static final int MOD_COUNT = 15;

        public Car() {
            this.id = new Variable<>();
            this.radioId = new Variable<>();
            this.paintJob = new Variable<>();
            this.color1 = new Variable<>();
            this.color2 = new Variable<>();
            this.carId = new Variable<>();
            this.nitro = new Variable<>();

            List<Variable<Integer>> tMods = new ArrayList<>(MOD_COUNT);
            for (int i = 0; i < MOD_COUNT; i++) {
                tMods.add(i, new Variable<>());
            }
            mods = Collections.unmodifiableList(tMods);
        }

        public Variable<Integer> getId() {
            return id;
        }

        public Variable<Integer> getRadioId() {
            return radioId;
        }

        public Variable<Integer> getPaintJob() {
            return paintJob;
        }

        public Variable<Integer> getColor1() {
            return color1;
        }

        public Variable<Integer> getColor2() {
            return color2;
        }

        public Variable<Integer> getCarId() {
            return carId;
        }

        public List<Variable<Integer>> getMods() {
            return mods;
        }

        public Variable<Integer> getNitro() {
            return nitro;
        }
    }
}
