package nl.paulinternet.libsavegame.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class VehicleMod {

    private static final Logger log = LoggerFactory.getLogger(VehicleMod.class);

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

        mods.add(new VehicleMod("Exhausts", "Alien Exhaust", "exh_a_l", 1034));
        mods.add(new VehicleMod("Exhausts", "Chromer Exhaust", "exh_lr_sv1", 1129));
        mods.add(new VehicleMod("Exhausts", "Remington Exhaust 2", "exh_lr_rem2", 1127));
        mods.add(new VehicleMod("Exhausts", "Large Exhaust", "exh_b_l", 1020));
        mods.add(new VehicleMod("Exhausts", "Medium Exhaust", "exh_b_m", 1021));
        mods.add(new VehicleMod("Exhausts", "Slamin Exhaust", "exh_lr_sv2", 1132));
        mods.add(new VehicleMod("Exhausts", "Small Exhaust", "exh_b_s", 1022));
        mods.add(new VehicleMod("Exhausts", "Twin Exhaust", "exh_b_t", 1019));
        mods.add(new VehicleMod("Exhausts", "Upswept Exhaust", "exh_b_ts", 1018));
        mods.add(new VehicleMod("Exhausts", "X-Flow Exhaust", "exh_c_l", 1037));
        mods.add(new VehicleMod("Exhausts", "Slamvan Exhaust", "exh_lr_slv2", 1114));
        mods.add(new VehicleMod("Exhausts", "Sultan Exhaust", "exh_a_s", 1028));
        mods.add(new VehicleMod("Exhausts", "Jester Exhaust", "exh_c_j", 1066));
        mods.add(new VehicleMod("Exhausts", "Blade Exhaust", "exh_lr_bl1", 1104));
        mods.add(new VehicleMod("Exhausts", "Remington Exhaust 1","exh_lr_rem1", 1126));
        mods.add(new VehicleMod("Exhausts", "Broadway Exhaust 1","exh_lr_br1", 1044));
        mods.add(new VehicleMod("Exhausts", "Stratum Exhaust 2", "exh_c_st", 1059));
        mods.add(new VehicleMod("Exhausts", "Flash Exhaust", "exh_c_f", 1045));
        mods.add(new VehicleMod("Exhausts", "Stratum Exhaust 1", "exh_c_s", 1029));
        mods.add(new VehicleMod("Exhausts", "Uranus Exhaust", "exh_c_u", 1089));
        mods.add(new VehicleMod("Exhausts", "Tornado Exhaust", "exh_lr_t1", 1136));

        mods.add(new VehicleMod("Front Bullbars", "Chromer Bullbar", "fbb_lr_slv1", 1115));
        mods.add(new VehicleMod("Front Bullbars", "Slamin Bullbar", "fbb_lr_slv2", 1116));

        mods.add(new VehicleMod("Rear Bullbars", "Chromer Rear Bullbar", "bbb_lr_slv1", 1109));
        mods.add(new VehicleMod("Rear Bullbars", "Slamin Rear Bullbar", "bbb_lr_slv2", 1110));

        mods.add(new VehicleMod("Front Bumper", "Alien Bumper", "fbmp_a_l", 1171));
        mods.add(new VehicleMod("Front Bumper", "Chromer Bumper", "fbmp_lr_sv1", 1189));
        mods.add(new VehicleMod("Front Bumper", "Slamin Bumper", "fbmp_lr_sv2", 1188));
        mods.add(new VehicleMod("Front Bumper", "Remington Bumper 2", "fbmp_lr_rem2", 1185));
        mods.add(new VehicleMod("Front Bumper", "X-Flow Bumper", "fbmp_c_l", 1172));
        mods.add(new VehicleMod("Front Bumper", "Slamvan Bumper", "fbmp_lr_slv1", 1117));
        mods.add(new VehicleMod("Front Bumper", "Sultan Bumper", "fbmp_a_s", 1169));
        mods.add(new VehicleMod("Front Bumper", "Jester Bumper", "fbmp_c_j", 1173));
        mods.add(new VehicleMod("Front Bumper", "Blade Bumper", "fbmp_lr_bl1", 1182));
        mods.add(new VehicleMod("Front Bumper", "Remington Bumper 1", "fbmp_lr_rem1", 1179));
        mods.add(new VehicleMod("Front Bumper", "Broadway Bumper 1", "fbmp_lr_br1", 1174));
        mods.add(new VehicleMod("Front Bumper", "Stratum Bumper", "fbmp_c_st", 1157));
        mods.add(new VehicleMod("Front Bumper", "Flash Front Bumper", "fbmp_c_f", 1152));
        mods.add(new VehicleMod("Front Bumper", "Jester Front Bumper", "fbmp_a_j", 1160));
        mods.add(new VehicleMod("Front Bumper", "Stratum Front Bumper", "fbmp_c_s", 1170));
        mods.add(new VehicleMod("Front Bumper", "Uranus Front Bumper", "fbmp_c_u", 1165));
        mods.add(new VehicleMod("Front Bumper", "Tornado Front Bumper", "fbmp_lr_t1", 1191));

        mods.add(new VehicleMod("Hood", "Champ Scoop", "bnt_b_sc_m", 1004));
        mods.add(new VehicleMod("Hood", "Fury Scoop", "bnt_b_sc_l", 1005));
        mods.add(new VehicleMod("Hood", "Race Scoop", "bnt_b_sc_p_m", 1011));
        mods.add(new VehicleMod("Hood", "Jester Scoop", "bnt_b_sc_p_l", 1012));

        mods.add(new VehicleMod("Hydraulics", "Hydraulics", "hydralics", 1087));
        mods.add(new VehicleMod("Hydraulics", "Hydraulics (Scripted)", "unknown_hydr_1", 65343));

        mods.add(new VehicleMod("Lights", "Round Fog Lamp", "lgt_b_rspt", 1013));
        mods.add(new VehicleMod("Lights", "Square Fog Lamp 2", "wg_l_a_s", 1026));
        mods.add(new VehicleMod("Lights", "Square Fog Lamp", "lgt_b_sspt", 1024));

        mods.add(new VehicleMod("Nitro", "2x Nitrous", "nto_b_s", 1009));
        mods.add(new VehicleMod("Nitro", "5x Nitrous", "nto_b_l", 1008));
        mods.add(new VehicleMod("Nitro", "10x Nitrous", "nto_b_tw", 1010));

        mods.add(new VehicleMod("Rear Bumper", "Alien Rear Bumper", "rbmp_c_l", 1148));
        mods.add(new VehicleMod("Rear Bumper", "Chrome Rear Bumper", "rbmp_lr_sv1", 1187));
        mods.add(new VehicleMod("Rear Bumper", "Slammin Rear Bumper", "rbmp_lr_sv2", 1186));
        mods.add(new VehicleMod("Rear Bumper", "X-Flow Rear Bumper", "rbmp_a_l", 1149));
        mods.add(new VehicleMod("Rear Bumper", "Remington Rear Bumper 2", "rbmp_lr_rem2", 1178));
        mods.add(new VehicleMod("Rear Bumper", "Sultan Rear Bumper", "rbmp_a_s", 1141));
        mods.add(new VehicleMod("Rear Bumper", "Jester Rear Bumper", "rbmp_c_j", 1161));
        mods.add(new VehicleMod("Rear Bumper", "Blade Rear Bumper", "rbmp_lr_bl1", 1184));
        mods.add(new VehicleMod("Rear Bumper", "Remington Rear Bumper 1", "rbmp_lr_rem1", 1180));
        mods.add(new VehicleMod("Rear Bumper", "Broadway Rear Bumper 1", "rbmp_lr_br1", 1176));
        mods.add(new VehicleMod("Rear Bumper", "Stratum Rear Bumper 2", "rbmp_c_st", 1156));
        mods.add(new VehicleMod("Rear Bumper", "Flash Rear Bumper", "rbmp_c_f", 1151));
        mods.add(new VehicleMod("Rear Bumper", "Stratum Rear Bumper 1", "rbmp_c_s", 1140));
        mods.add(new VehicleMod("Rear Bumper", "Uranus Rear Bumper", "rbmp_c_u", 1167));
        mods.add(new VehicleMod("Rear Bumper", "Tornado Rear Bumper", "rbmp_lr_t1", 1192));

        mods.add(new VehicleMod("Roof", "Alien Roof", "rf_a_l", 1038));
        mods.add(new VehicleMod("Roof", "Hardtop Roof", "rf_lr_sv1", 1130));
        mods.add(new VehicleMod("Roof", "Roof Scoop Roof", "rf_b_sc_r", 1006));
        mods.add(new VehicleMod("Roof", "Softtop Roof", "rf_lr_sv2", 1131));
        mods.add(new VehicleMod("Roof", "X-Flow Roof", "rf_c_l", 1035));
        mods.add(new VehicleMod("Roof", "Sultan Roof", "rf_a_s", 1032));
        mods.add(new VehicleMod("Roof", "Jester Roof 2", "rf_c_j", 1068));
        mods.add(new VehicleMod("Roof", "Blade Roof", "rf_lr_bl2", 1103));
        mods.add(new VehicleMod("Roof", "Stratum Roof 2", "rf_c_st", 1061));
        mods.add(new VehicleMod("Roof", "Flash Roof", "rf_c_f", 1053));
        mods.add(new VehicleMod("Roof", "Jester Roof 1", "rf_a_j", 1067));
        mods.add(new VehicleMod("Roof", "Stratum Roof 1", "rf_c_s", 1033));
        mods.add(new VehicleMod("Roof", "Uranus Roof", "rf_c_u", 1091));

        mods.add(new VehicleMod("Side Skirts", "Alien", "wg_l_a_l", 1036));
        mods.add(new VehicleMod("Side Skirts", "Chrome Strips", "wg_l_lr_sv", 1133));
        mods.add(new VehicleMod("Side Skirts", "Chrome Trim", "wg_l_lr_slv1", 1118));
        mods.add(new VehicleMod("Side Skirts", "Side Skirts", "wg_l_b_ssk", 1007));
        mods.add(new VehicleMod("Side Skirts", "Slamvan Wheelcovers", "wg_l_lr_slv2", 1119));
        mods.add(new VehicleMod("Side Skirts", "X-Flow Side Skirts", "wg_l_c_l", 1039));
        mods.add(new VehicleMod("Side Skirts", "Remington Side Skirts 2", "wg_l_lr_rem2", 1106));
        mods.add(new VehicleMod("Side Skirts", "Jester Side Skirts 2", "wg_l_c_j", 1070));
        mods.add(new VehicleMod("Side Skirts", "Blade Side Skirts", "wg_l_lr_bl1", 1108));
        mods.add(new VehicleMod("Side Skirts", "Remington Side Skirts 1", "wg_l_lr_rem1", 1122));
        mods.add(new VehicleMod("Side Skirts", "Broadway Side Skirts 1", "wg_l_lr_br1", 1042));
        mods.add(new VehicleMod("Side Skirts", "Flash Sideskirts", "wg_l_c_f", 1048));
        mods.add(new VehicleMod("Side Skirts", "Jester Side Skirts 1", "wg_l_a_j", 1069));
        mods.add(new VehicleMod("Side Skirts", "Stratum Side Skirts", "wg_l_c_s", 1031));
        mods.add(new VehicleMod("Side Skirts", "Uranus Side Skirts", "wg_l_c_u", 1093));
        mods.add(new VehicleMod("Side Skirts", "Tornado Side Skirts", "wg_l_lr_t1", 1134));

        mods.add(new VehicleMod("Spoiler", "Alien Spoiler", "spl_a_l_b", 1147));
        mods.add(new VehicleMod("Spoiler", "Alpha Spoiler", "spl_b_mab_m", 1003));
        mods.add(new VehicleMod("Spoiler", "Champ Spoiler", "spl_b_bar_l", 1014));
        mods.add(new VehicleMod("Spoiler", "Drag Spoiler", "spl_b_bar_m", 1002));
        mods.add(new VehicleMod("Spoiler", "Fury Spoiler", "spl_b_bbb_m", 1023));
        mods.add(new VehicleMod("Spoiler", "Pro Spoiler", "spl_b_mar_m", 1000));
        mods.add(new VehicleMod("Spoiler", "Race Spoiler", "spl_b_bbr_l", 1015));
        mods.add(new VehicleMod("Spoiler", "Win Spoiler", "spl_b_bab_m", 1001));
        mods.add(new VehicleMod("Spoiler", "Worx Spoiler", "spl_b_bbr_m", 1016));
        mods.add(new VehicleMod("Spoiler", "X-Flow Spoiler", "spl_c_l_b", 1146));
        mods.add(new VehicleMod("Spoiler", "Sultan Spoiler", "spl_c_s_b", 1139));
        mods.add(new VehicleMod("Spoiler", "Jester Spoiler 2", "spl_a_j_b", 1162));
        mods.add(new VehicleMod("Spoiler", "Stratum Spoiler", "spl_c_st_r", 1060));
        mods.add(new VehicleMod("Spoiler", "Flash Spoiler", "spl_a_f_r", 1049));
        mods.add(new VehicleMod("Spoiler", "Jester Spoiler 1", "spl_c_j_b", 1158));
        mods.add(new VehicleMod("Spoiler", "Uranus Spoiler", "spl_c_u_b", 1163));

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

        mods.add(new VehicleMod("Misc", "Remington Lights Chrome Parts", "misc_c_lr_rem3", 1125));

        mods.add(new VehicleMod("None", "None (max)", "noneMaxValue", 65535));
        mods.add(new VehicleMod("None", "None", "noneMinValue", 0));

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
            log.warn("Invalid modID: '" + id + "'!");
            return null;
        }
    }

    public static VehicleMod getModByName(String name) {
        return mods.stream().filter(mod -> mod.name.equals(name)).findFirst().orElse(null);
    }

}
