package nl.paulinternet.gtasaveedit.model.savegame.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class VehicleMod {

    private final String dffName;
    private int id;
    private String type;
    private String name;

    private VehicleMod(String type, String name, String dffName, int id) {
        this.dffName = dffName;
        this.id = id;
        this.type = type;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getDffName() {
        return dffName;
    }

    private static List<VehicleMod> mods;

    static {
        mods = new ArrayList<>();

        mods.add(new VehicleMod("Car Stereo", "Bass Boost", "stereo", 1086));

        mods.add(new VehicleMod("Exhausts", "Alien", "exh_a_l", 1034));
        mods.add(new VehicleMod("Exhausts", "Chromer Exhaust", "exh_lr_sv1", 1129));
        mods.add(new VehicleMod("Exhausts", "Large", "exh_b_l", 1020));
        mods.add(new VehicleMod("Exhausts", "Medium", "exh_b_m", 1021));
        mods.add(new VehicleMod("Exhausts", "Slamin Exhaust", "exh_lr_sv2", 1132));
        mods.add(new VehicleMod("Exhausts", "Small", "exh_b_s", 1022));
        mods.add(new VehicleMod("Exhausts", "Twin", "exh_b_t", 1019));
        mods.add(new VehicleMod("Exhausts", "Upswept", "exh_b_ts", 1018));
        mods.add(new VehicleMod("Exhausts", "X-Flow", "exh_c_l", 1037));

        mods.add(new VehicleMod("Front Bullbars", "Chromer Bullbar", "fbb_lr_slv1", 1115));
        mods.add(new VehicleMod("Front Bullbars", "Slamin Bullbar", "fbb_lr_slv2", 1116));

        mods.add(new VehicleMod("Rear Bullbars", "Chromer Bullbar", "bbb_lr_slv1", 1109));
        mods.add(new VehicleMod("Rear Bullbars", "Slamin Bullbar", "bbb_lr_slv2", 1110));

        mods.add(new VehicleMod("Front Bumper", "Alien", "fbmp_a_l", 1171));
        mods.add(new VehicleMod("Front Bumper", "Chromer Bumper", "fbmp_lr_sv1", 1189));
        mods.add(new VehicleMod("Front Bumper", "Slamin Bumper", "fbmp_lr_sv2", 1188));
        mods.add(new VehicleMod("Front Bumper", "X-Flow", "fbmp_c_l", 1172));

        mods.add(new VehicleMod("Hood", "Champ Scoop", "bnt_b_sc_m", 1004));
        mods.add(new VehicleMod("Hood", "Fury Scoop", "bnt_b_sc_l", 1005));
        mods.add(new VehicleMod("Hood", "Race Scoop", "bnt_b_sc_p_m", 1011));

        mods.add(new VehicleMod("Hydraulics", "Hydraulics", "hydralics", 1087));

        mods.add(new VehicleMod("Lights", "Round Fog Lamp", "lgt_b_rspt", 1013));
        mods.add(new VehicleMod("Lights", "Square Fog Lamp", "wg_l_a_s", 1026));

        mods.add(new VehicleMod("Nitro", "2x Nitrous", "nto_b_s", 1009));
        mods.add(new VehicleMod("Nitro", "5x Nitrous", "nto_b_l", 1008));
        mods.add(new VehicleMod("Nitro", "10x Nitrous", "nto_b_tw", 1010));

        mods.add(new VehicleMod("Rear Bumper", "Alien", "rbmp_c_l", 1148));
        mods.add(new VehicleMod("Rear Bumper", "Chrome", "rbmp_lr_sv1", 1187));
        mods.add(new VehicleMod("Rear Bumper", "Slammin", "rbmp_lr_sv2", 1186));
        mods.add(new VehicleMod("Rear Bumper", "X-Flow", "rbmp_a_l", 1149));

        mods.add(new VehicleMod("Roof", "Alien", "rf_a_l", 1038));
        mods.add(new VehicleMod("Roof", "Hardtop", "rf_lr_sv1", 1130));
        mods.add(new VehicleMod("Roof", "Roof Scoop", "rf_b_sc_r", 1006));
        mods.add(new VehicleMod("Roof", "Softtop", "rf_lr_sv2", 1131));
        mods.add(new VehicleMod("Roof", "X-Flow", "rf_c_l", 1035));

        mods.add(new VehicleMod("Side Skirts", "Alien", "wg_l_a_l", 1036));
        mods.add(new VehicleMod("Side Skirts", "Chrome Strips", "wg_l_lr_sv", 1133)); // hint: previously the id was divided by 1042
        mods.add(new VehicleMod("Side Skirts", "Chrome Trim", "wg_l_lr_slv1", 1118));
        mods.add(new VehicleMod("Side Skirts", "Side Skirt", "wg_l_b_ssk", 1007));
        mods.add(new VehicleMod("Side Skirts", "Wheelcovers", "wg_l_lr_slv2", 1119));
        mods.add(new VehicleMod("Side Skirts", "X-Flow", "wg_l_c_l", 1039));

        mods.add(new VehicleMod("Spoiler", "Alien", "spl_a_l_b", 1147));
        mods.add(new VehicleMod("Spoiler", "Alpha", "spl_b_mab_m", 1003));
        mods.add(new VehicleMod("Spoiler", "Champ", "spl_b_bar_l", 1014));
        mods.add(new VehicleMod("Spoiler", "Drag", "spl_b_bar_m", 1002));
        mods.add(new VehicleMod("Spoiler", "Fury", "spl_b_bbb_m", 1023));
        mods.add(new VehicleMod("Spoiler", "Pro", "spl_b_mar_m", 1000));
        mods.add(new VehicleMod("Spoiler", "Race", "spl_b_bbr_l", 1015));
        mods.add(new VehicleMod("Spoiler", "Win", "spl_b_bab_m", 1001));
        mods.add(new VehicleMod("Spoiler", "Worx", "spl_b_bbr_m", 1016));
        mods.add(new VehicleMod("Spoiler", "X-Flow", "spl_c_l_b", 1146));

        mods.add(new VehicleMod("Vent", "Oval Hood", "bntl_b_ov", 1143));
        mods.add(new VehicleMod("Vent", "Square Hood", "bntl_b_sq", 1145));

        mods.add(new VehicleMod("Wheels", "Access", "wheel_gn5", 1098));
        mods.add(new VehicleMod("Wheels", "Ahab", "wheel_gn3", 1096));
        mods.add(new VehicleMod("Wheels", "Atomic", "wheel_gn2", 1085));
        mods.add(new VehicleMod("Wheels", "Classic", "wheel_lr1", 1077));
        mods.add(new VehicleMod("Wheels", "Cutter", "wheel_sr1", 1079));
        mods.add(new VehicleMod("Wheels", "Dollar", "wheel_lr2", 1083));
        mods.add(new VehicleMod("Wheels", "Grove", "wheel_sr4", 1081));
        mods.add(new VehicleMod("Wheels", "Import", "wheel_gn1", 1082));
        mods.add(new VehicleMod("Wheels", "Mega", "wheel_sr3", 1074));
        mods.add(new VehicleMod("Wheels", "Off Road", "wheel_or1", 1025));
        mods.add(new VehicleMod("Wheels", "Rimshine", "wheel_sr2", 1075));
        mods.add(new VehicleMod("Wheels", "Shadow", "wheel_sr6", 1073));
        mods.add(new VehicleMod("Wheels", "Switch", "wheel_sr5", 1080));
        mods.add(new VehicleMod("Wheels", "Trance", "wheel_lr5", 1084));
        mods.add(new VehicleMod("Wheels", "Twist", "wheel_lr3", 1078));
        mods.add(new VehicleMod("Wheels", "Virtual", "wheel_gn4", 1097));
        mods.add(new VehicleMod("Wheels", "Wires", "wheel_lr4", 1076));

        mods.add(new VehicleMod("None", "None", "wtf", 65535));
        mods.add(new VehicleMod("None", "None", "none", 0));

        mods = Collections.unmodifiableList(mods);
    }

    public static List<VehicleMod> getMods() {
        return mods;
    }

    public static VehicleMod getMod(int id) {
        Optional<VehicleMod> first = mods.stream().filter(mod -> mod.id == id).findFirst();
        if (first.isPresent()) {
            return first.get();
        } else {
            throw new RuntimeException("Invalid modID: '" + id + "'!");
        }
    }

}
