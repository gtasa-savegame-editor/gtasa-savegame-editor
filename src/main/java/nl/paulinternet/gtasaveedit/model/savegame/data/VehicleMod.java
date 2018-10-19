package nl.paulinternet.gtasaveedit.model.savegame.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VehicleMod {

  private int id;
  private String type;
  private String name;


  private VehicleMod(String type, String name, int id) {
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


  private static List<VehicleMod> mods;

  static {
    mods = new ArrayList<VehicleMod>();

    mods.add(new VehicleMod("Car Stereo", "Bass Boost", 1086));

    mods.add(new VehicleMod("Exhausts", "Alien", 1034));
    mods.add(new VehicleMod("Exhausts", "Chromer Exhaust", 1129));
    mods.add(new VehicleMod("Exhausts", "Large", 1020));
    mods.add(new VehicleMod("Exhausts", "Medium", 1021));
    mods.add(new VehicleMod("Exhausts", "Slamin Exhaust", 1132));
    mods.add(new VehicleMod("Exhausts", "Small", 1022));
    mods.add(new VehicleMod("Exhausts", "Twin", 1019));
    mods.add(new VehicleMod("Exhausts", "Upswept", 1018));
    mods.add(new VehicleMod("Exhausts", "X-Flow", 1037));

    mods.add(new VehicleMod("Front Bullbars", "Chromer Bullbar", 1115));
    mods.add(new VehicleMod("Front Bullbars", "Slamin Bullbar", 1116));

    mods.add(new VehicleMod("Rear Bullbars", "Chromer Bullbar", 1109));
    mods.add(new VehicleMod("Rear Bullbars", "Slamin Bullbar", 1110));

    mods.add(new VehicleMod("Front Bumper", "Alien", 1171));
    mods.add(new VehicleMod("Front Bumper", "Chromer Bumper", 1189));
    mods.add(new VehicleMod("Front Bumper", "Slamin Bumper", 1188));
    mods.add(new VehicleMod("Front Bumper", "X-Flow", 1172));

    mods.add(new VehicleMod("Hood", "Champ Scoop", 1004));
    mods.add(new VehicleMod("Hood", "Fury Scoop", 1005));
    mods.add(new VehicleMod("Hood", "Race Scoop", 1011));

    mods.add(new VehicleMod("Hydraulics", "Hydraulics", 1087));

    mods.add(new VehicleMod("Lights", "Round Fog Lamp", 1013));
    mods.add(new VehicleMod("Lights", "Square Fog Lamp", 1026));

    mods.add(new VehicleMod("Nitro", "2x Nitrous", 1009));
    mods.add(new VehicleMod("Nitro", "5x Nitrous", 1008));
    mods.add(new VehicleMod("Nitro", "10x Nitrous", 1010));

    mods.add(new VehicleMod("Rear Bumper", "Alien", 1148));
    mods.add(new VehicleMod("Rear Bumper", "Chrome", 1187));
    mods.add(new VehicleMod("Rear Bumper", "Slammin", 1186));
    mods.add(new VehicleMod("Rear Bumper", "X-Flow", 1149));

    mods.add(new VehicleMod("Roof", "Alien", 1038));
    mods.add(new VehicleMod("Roof", "Hardtop", 1130));
    mods.add(new VehicleMod("Roof", "Roof Scoop", 1006));
    mods.add(new VehicleMod("Roof", "Softtop", 1131));
    mods.add(new VehicleMod("Roof", "X-Flow", 1035));

    mods.add(new VehicleMod("Side Skirts", "Alien", 1036));
    mods.add(new VehicleMod("Side Skirts", "Chrome Strips", 1133 / 1042));
    mods.add(new VehicleMod("Side Skirts", "Chrome Trim", 1118));
    mods.add(new VehicleMod("Side Skirts", "Side Skirt", 1007));
    mods.add(new VehicleMod("Side Skirts", "Wheelcovers", 1119));
    mods.add(new VehicleMod("Side Skirts", "X-Flow", 1039));

    mods.add(new VehicleMod("Spoiler", "Alien", 1147));
    mods.add(new VehicleMod("Spoiler", "Alpha", 1003));
    mods.add(new VehicleMod("Spoiler", "Champ", 1014));
    mods.add(new VehicleMod("Spoiler", "Drag", 1002));
    mods.add(new VehicleMod("Spoiler", "Fury", 1023));
    mods.add(new VehicleMod("Spoiler", "Pro", 1000));
    mods.add(new VehicleMod("Spoiler", "Race", 1015));
    mods.add(new VehicleMod("Spoiler", "Win", 1001));
    mods.add(new VehicleMod("Spoiler", "Worx", 1016));
    mods.add(new VehicleMod("Spoiler", "X-Flow", 1146));

    mods.add(new VehicleMod("Vent", "Oval Hood", 1143));
    mods.add(new VehicleMod("Vent", "Square Hood", 1145));

    mods.add(new VehicleMod("Wheels", "Access", 1098));
    mods.add(new VehicleMod("Wheels", "Ahab", 1096));
    mods.add(new VehicleMod("Wheels", "Atomic", 1085));
    mods.add(new VehicleMod("Wheels", "Classic", 1077));
    mods.add(new VehicleMod("Wheels", "Cutter", 1079));
    mods.add(new VehicleMod("Wheels", "Dollar", 1083));
    mods.add(new VehicleMod("Wheels", "Grove", 1081));
    mods.add(new VehicleMod("Wheels", "Import", 1082));
    mods.add(new VehicleMod("Wheels", "Mega", 1074));
    mods.add(new VehicleMod("Wheels", "Off Road", 1025));
    mods.add(new VehicleMod("Wheels", "Rimshine", 1075));
    mods.add(new VehicleMod("Wheels", "Shadow", 1073));
    mods.add(new VehicleMod("Wheels", "Switch", 1080));
    mods.add(new VehicleMod("Wheels", "Trance", 1084));
    mods.add(new VehicleMod("Wheels", "Twist", 1078));
    mods.add(new VehicleMod("Wheels", "Virtual", 1097));
    mods.add(new VehicleMod("Wheels", "Wires", 1076));

    mods = Collections.unmodifiableList(mods);
  }


  public static List<VehicleMod> getMods() {
    return mods;
  }

  public static VehicleMod getMod(int id) {
    return mods.stream().filter(mod -> mod.id == id).findFirst().get();
  }


}
