package nl.paulinternet.gtasaveedit.model.savegame.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class VehicleType
{

  private int id;
  private String type;
  private String name;
  private ArrayList<VehicleColor.ColorPair> validColors;


  private VehicleType(String type, String name, int id, VehicleColor.ColorPair... validColors)
  {
    this.id = id;
    this.type = type;
    this.name = name;
    if (validColors != null) {
      this.validColors = new ArrayList<>(Arrays.asList(validColors));
    } else {
      this.validColors = new ArrayList<>();
    }
  }

  public int getId()
  {
    return id;
  }

  public String getType()
  {
    return type;
  }

  public String getName()
  {
    return name;
  }

  public ArrayList<VehicleColor.ColorPair> getValidColors()
  {
    return validColors;
  }

  private static List<VehicleType> types;

  static {
    types = new ArrayList<>();

    types.add(new VehicleType("None", "None", 0,
      new VehicleColor.ColorPair(0, 0)));

    types.add(new VehicleType("Bikes", "BF-400", 581,
      new VehicleColor.ColorPair(54, 1),
      new VehicleColor.ColorPair(58, 1),
      new VehicleColor.ColorPair(66, 1),
      new VehicleColor.ColorPair(72, 1),
      new VehicleColor.ColorPair(75, 1),
      new VehicleColor.ColorPair(87, 1),
      new VehicleColor.ColorPair(101, 1),
      new VehicleColor.ColorPair(36, 1)));

    types.add(new VehicleType("Bikes", "Bike", 509,
      new VehicleColor.ColorPair(7, 1),
      new VehicleColor.ColorPair(74, 1),
      new VehicleColor.ColorPair(61, 1),
      new VehicleColor.ColorPair(16, 1),
      new VehicleColor.ColorPair(25, 1),
      new VehicleColor.ColorPair(30, 1),
      new VehicleColor.ColorPair(36, 1),
      new VehicleColor.ColorPair(53, 1)));

    types.add(new VehicleType("Bikes", "BMX", 481,
      new VehicleColor.ColorPair(1, 1),
      new VehicleColor.ColorPair(3, 3),
      new VehicleColor.ColorPair(6, 6),
      new VehicleColor.ColorPair(46, 46),
      new VehicleColor.ColorPair(65, 9),
      new VehicleColor.ColorPair(14, 1),
      new VehicleColor.ColorPair(12, 9),
      new VehicleColor.ColorPair(26, 1)));

    types.add(new VehicleType("Bikes", "Faggio", 462,
      new VehicleColor.ColorPair(12, 12),
      new VehicleColor.ColorPair(13, 13),
      new VehicleColor.ColorPair(14, 14),
      new VehicleColor.ColorPair(1, 2),
      new VehicleColor.ColorPair(2, 1),
      new VehicleColor.ColorPair(1, 3),
      new VehicleColor.ColorPair(3, 1),
      new VehicleColor.ColorPair(10, 10)));

    types.add(new VehicleType("Bikes", "FCR-900", 521,
      new VehicleColor.ColorPair(74, 74),
      new VehicleColor.ColorPair(75, 13),
      new VehicleColor.ColorPair(87, 118),
      new VehicleColor.ColorPair(92, 3),
      new VehicleColor.ColorPair(115, 118),
      new VehicleColor.ColorPair(25, 118),
      new VehicleColor.ColorPair(36, 0),
      new VehicleColor.ColorPair(118, 118)));

    types.add(new VehicleType("Bikes", "Freeway", 463,
      new VehicleColor.ColorPair(79, 79),
      new VehicleColor.ColorPair(84, 84),
      new VehicleColor.ColorPair(7, 7),
      new VehicleColor.ColorPair(11, 11),
      new VehicleColor.ColorPair(19, 19),
      new VehicleColor.ColorPair(22, 22),
      new VehicleColor.ColorPair(36, 36),
      new VehicleColor.ColorPair(53, 53)));

    types.add(new VehicleType("Bikes", "HPV1000 (cop bike)", 523,
      new VehicleColor.ColorPair(0, 1)));

    types.add(new VehicleType("Bikes", "Mountain Bike", 510,
      new VehicleColor.ColorPair(43, 43),
      new VehicleColor.ColorPair(46, 46),
      new VehicleColor.ColorPair(39, 39),
      new VehicleColor.ColorPair(28, 28),
      new VehicleColor.ColorPair(16, 16),
      new VehicleColor.ColorPair(6, 6),
      new VehicleColor.ColorPair(5, 5),
      new VehicleColor.ColorPair(2, 2)));

    types.add(new VehicleType("Bikes", "NRG-500", 522,
      new VehicleColor.ColorPair(3, 3),
      new VehicleColor.ColorPair(3, 8),
      new VehicleColor.ColorPair(6, 25),
      new VehicleColor.ColorPair(7, 79),
      new VehicleColor.ColorPair(8, 82),
      new VehicleColor.ColorPair(36, 105),
      new VehicleColor.ColorPair(39, 106),
      new VehicleColor.ColorPair(51, 118)));

    types.add(new VehicleType("Bikes", "PCJ-600", 461,
      new VehicleColor.ColorPair(36, 1),
      new VehicleColor.ColorPair(37, 1),
      new VehicleColor.ColorPair(43, 1),
      new VehicleColor.ColorPair(53, 1),
      new VehicleColor.ColorPair(61, 1),
      new VehicleColor.ColorPair(75, 1),
      new VehicleColor.ColorPair(79, 1),
      new VehicleColor.ColorPair(88, 1)));

    types.add(new VehicleType("Bikes", "Pizzaboy", 448,
      new VehicleColor.ColorPair(3, 6)));

    types.add(new VehicleType("Bikes", "Sanchez", 468,
      new VehicleColor.ColorPair(6, 6),
      new VehicleColor.ColorPair(46, 46),
      new VehicleColor.ColorPair(53, 53),
      new VehicleColor.ColorPair(3, 3)));

    types.add(new VehicleType("Bikes", "Wayfarer", 586,
      new VehicleColor.ColorPair(119, 1),
      new VehicleColor.ColorPair(122, 1),
      new VehicleColor.ColorPair(8, 1),
      new VehicleColor.ColorPair(10, 1),
      new VehicleColor.ColorPair(13, 1),
      new VehicleColor.ColorPair(25, 1),
      new VehicleColor.ColorPair(27, 1),
      new VehicleColor.ColorPair(32, 1)));

    types.add(new VehicleType("Cars", "Admiral", 445,
      new VehicleColor.ColorPair(34, 34),
      new VehicleColor.ColorPair(35, 35),
      new VehicleColor.ColorPair(37, 37),
      new VehicleColor.ColorPair(39, 39),
      new VehicleColor.ColorPair(41, 41),
      new VehicleColor.ColorPair(43, 43),
      new VehicleColor.ColorPair(45, 45),
      new VehicleColor.ColorPair(47, 47)));

    types.add(new VehicleType("Cars", "Alpha", 602,
      new VehicleColor.ColorPair(58, 1),
      new VehicleColor.ColorPair(69, 1),
      new VehicleColor.ColorPair(75, 77),
      new VehicleColor.ColorPair(18, 1),
      new VehicleColor.ColorPair(32, 1),
      new VehicleColor.ColorPair(45, 45),
      new VehicleColor.ColorPair(13, 1),
      new VehicleColor.ColorPair(34, 1)));

    types.add(new VehicleType("Cars", "Ambulance", 416,
      new VehicleColor.ColorPair(1, 3)));

    types.add(new VehicleType("Cars", "Baggage", 485,
      new VehicleColor.ColorPair(1, 73),
      new VehicleColor.ColorPair(1, 74),
      new VehicleColor.ColorPair(1, 75),
      new VehicleColor.ColorPair(1, 76),
      new VehicleColor.ColorPair(1, 77),
      new VehicleColor.ColorPair(1, 78),
      new VehicleColor.ColorPair(1, 79)));

    types.add(new VehicleType("Cars", "Bandito", 568,
      new VehicleColor.ColorPair(2, 39),
      new VehicleColor.ColorPair(9, 39),
      new VehicleColor.ColorPair(17, 1),
      new VehicleColor.ColorPair(21, 1),
      new VehicleColor.ColorPair(33, 0),
      new VehicleColor.ColorPair(37, 0),
      new VehicleColor.ColorPair(41, 29),
      new VehicleColor.ColorPair(56, 29)));

    types.add(new VehicleType("Cars", "Banshee", 429,
      new VehicleColor.ColorPair(12, 12),
      new VehicleColor.ColorPair(13, 13),
      new VehicleColor.ColorPair(14, 14),
      new VehicleColor.ColorPair(1, 2),
      new VehicleColor.ColorPair(2, 1),
      new VehicleColor.ColorPair(1, 3),
      new VehicleColor.ColorPair(3, 1),
      new VehicleColor.ColorPair(10, 10)));

    types.add(new VehicleType("Cars", "Barracks", 433,
      new VehicleColor.ColorPair(43, 0)));

    types.add(new VehicleType("Cars", "Benson", 499));
    types.add(new VehicleType("Cars", "BF Injection", 424));
    types.add(new VehicleType("Cars", "Blade", 536));
    types.add(new VehicleType("Cars", "Blista Compact", 496));
    types.add(new VehicleType("Cars", "Bloodra", 504));
    types.add(new VehicleType("Cars", "Bobcat", 422));
    types.add(new VehicleType("Cars", "Boxville", 498));
    types.add(new VehicleType("Cars", "Boxville (exactly the same as other)", 609));
    types.add(new VehicleType("Cars", "Bravura", 401));
    types.add(new VehicleType("Cars", "Broadway", 575));
    types.add(new VehicleType("Cars", "Buccaneer", 518));
    types.add(new VehicleType("Cars", "Buffalo", 402));
    types.add(new VehicleType("Cars", "Bullet", 541));
    types.add(new VehicleType("Cars", "Burrito", 482));
    types.add(new VehicleType("Cars", "Bus", 431));
    types.add(new VehicleType("Cars", "Cabbie", 438));
    types.add(new VehicleType("Cars", "Caddy", 457));
    types.add(new VehicleType("Cars", "Cadrona", 527));
    types.add(new VehicleType("Cars", "Camper", 483));
    types.add(new VehicleType("Cars", "Cement", 524));
    types.add(new VehicleType("Cars", "Cheetah", 415));
    types.add(new VehicleType("Cars", "Clover", 542));
    types.add(new VehicleType("Cars", "Club", 589));
    types.add(new VehicleType("Cars", "Coach", 437));
    types.add(new VehicleType("Cars", "Combine Harvester", 532));
    types.add(new VehicleType("Cars", "Comet", 480));
    types.add(new VehicleType("Cars", "DFT-30", 578));
    types.add(new VehicleType("Cars", "Dozer", 486));
    types.add(new VehicleType("Cars", "Dumper", 406));
    types.add(new VehicleType("Cars", "Dune Rider", 573));
    types.add(new VehicleType("Cars", "Elegant", 507));
    types.add(new VehicleType("Cars", "Elegy", 562));
    types.add(new VehicleType("Cars", "Emperor", 585));
    types.add(new VehicleType("Cars", "Enforcer", 427));
    types.add(new VehicleType("Cars", "Esperanto", 419));
    types.add(new VehicleType("Cars", "Euros", 587));
    types.add(new VehicleType("Cars", "FBI Rancher", 490));
    types.add(new VehicleType("Cars", "FBI Truck", 528));
    types.add(new VehicleType("Cars", "Feltzer", 533));
    types.add(new VehicleType("Cars", "Fire Truck", 407));
    types.add(new VehicleType("Cars", "Fire Truck (with ladder)", 544));
    types.add(new VehicleType("Cars", "Flash", 565));
    types.add(new VehicleType("Cars", "Flatbed", 455));
    types.add(new VehicleType("Cars", "Forklift", 530));
    types.add(new VehicleType("Cars", "Fortune", 526));
    types.add(new VehicleType("Cars", "Glendale", 466));
    types.add(new VehicleType("Cars", "Glendale (beaten up)", 604));
    types.add(new VehicleType("Cars", "Go Kart", 571));
    types.add(new VehicleType("Cars", "Greenwood", 492));
    types.add(new VehicleType("Cars", "Hermes", 474));
    types.add(new VehicleType("Cars", "Hotdog", 588));
    types.add(new VehicleType("Cars", "Hotknife", 434));
    types.add(new VehicleType("Cars", "Hotring Racer (A)", 494));
    types.add(new VehicleType("Cars", "Hotring Racer (B)", 502));
    types.add(new VehicleType("Cars", "Hotring Racer (C) ", 503));
    types.add(new VehicleType("Cars", "Huntley", 579));
    types.add(new VehicleType("Cars", "Hustler", 545));
    types.add(new VehicleType("Cars", "Infernus", 411));
    types.add(new VehicleType("Cars", "Intruder", 546));
    types.add(new VehicleType("Cars", "Jester", 559));
    types.add(new VehicleType("Cars", "Journey", 508));
    types.add(new VehicleType("Cars", "Landstalker", 400));
    types.add(new VehicleType("Cars", "Lawn Mower", 572));
    types.add(new VehicleType("Cars", "Linerunner", 403));
    types.add(new VehicleType("Cars", "Majestic", 517));
    types.add(new VehicleType("Cars", "Manana", 410));
    types.add(new VehicleType("Cars", "Merit", 551));
    types.add(new VehicleType("Cars", "Mesa", 500));
    types.add(new VehicleType("Cars", "Monster (A)", 444));
    types.add(new VehicleType("Cars", "Monster (B)", 556));
    types.add(new VehicleType("Cars", "Monster (C)", 557));
    types.add(new VehicleType("Cars", "Moonbeam", 418));
    types.add(new VehicleType("Cars", "Mr Whoopee", 423));
    types.add(new VehicleType("Cars", "Mule", 414));
    types.add(new VehicleType("Cars", "Nebula", 516));
    types.add(new VehicleType("Cars", "Newsvan", 582));
    types.add(new VehicleType("Cars", "Oceanic", 467));
    types.add(new VehicleType("Cars", "Packer", 443));
    types.add(new VehicleType("Cars", "Patriot", 470));
    types.add(new VehicleType("Cars", "Perenial", 404));
    types.add(new VehicleType("Cars", "Phoenix", 603));
    types.add(new VehicleType("Cars", "Picador", 600));
    types.add(new VehicleType("Cars", "Police (LS)", 596));
    types.add(new VehicleType("Cars", "Police (LV)", 598));
    types.add(new VehicleType("Cars", "Police (SF)", 597));
    types.add(new VehicleType("Cars", "Pony", 413));
    types.add(new VehicleType("Cars", "Premier", 426));
    types.add(new VehicleType("Cars", "Previon", 436));
    types.add(new VehicleType("Cars", "Primo", 547));
    types.add(new VehicleType("Cars", "Quad", 471));
    types.add(new VehicleType("Cars", "Rancher", 489));
    types.add(new VehicleType("Cars", "Rancher (exactly the same as other)", 505));
    types.add(new VehicleType("Cars", "Ranger", 599));
    types.add(new VehicleType("Cars", "Regina", 479));
    types.add(new VehicleType("Cars", "Remington", 534));
    types.add(new VehicleType("Cars", "Rhino", 432));
    types.add(new VehicleType("Cars", "Roadtrain", 515));
    types.add(new VehicleType("Cars", "Romero", 442));
    types.add(new VehicleType("Cars", "Rumpo", 440));
    types.add(new VehicleType("Cars", "S.W.A.T.", 601));
    types.add(new VehicleType("Cars", "Sabre", 475));
    types.add(new VehicleType("Cars", "Sadler", 543));
    types.add(new VehicleType("Cars", "Sadler (beaten up)", 605));
    types.add(new VehicleType("Cars", "Sandking", 495));
    types.add(new VehicleType("Cars", "Savanna", 567));
    types.add(new VehicleType("Cars", "Securicar", 428));
    types.add(new VehicleType("Cars", "Sentinel", 405));
    types.add(new VehicleType("Cars", "Slamvan", 535));
    types.add(new VehicleType("Cars", "Solair", 458));
    types.add(new VehicleType("Cars", "Stafford", 580));
    types.add(new VehicleType("Cars", "Stallion", 439));
    types.add(new VehicleType("Cars", "Stratum", 561));
    types.add(new VehicleType("Cars", "Stretch", 409));
    types.add(new VehicleType("Cars", "Sultan", 560));
    types.add(new VehicleType("Cars", "Sunrise", 550));
    types.add(new VehicleType("Cars", "Super GT", 506));
    types.add(new VehicleType("Cars", "Sweeper", 574));
    types.add(new VehicleType("Cars", "Tahoma", 566));
    types.add(new VehicleType("Cars", "Tampa", 549));
    types.add(new VehicleType("Cars", "Tanker", 514));
    types.add(new VehicleType("Cars", "Taxi", 420));
    types.add(new VehicleType("Cars", "Top Fun", 459));
    types.add(new VehicleType("Cars", "Tornado", 576));
    types.add(new VehicleType("Cars", "Towtruck", 525));
    types.add(new VehicleType("Cars", "Tractor", 531));
    types.add(new VehicleType("Cars", "Trashmaster", 408));
    types.add(new VehicleType("Cars", "Tug", 583));
    types.add(new VehicleType("Cars", "Turismo", 451));
    types.add(new VehicleType("Cars", "Uranus", 558));
    types.add(new VehicleType("Cars", "Utility Van", 552));
    types.add(new VehicleType("Cars", "Vincent", 540));
    types.add(new VehicleType("Cars", "Virgo", 491));
    types.add(new VehicleType("Cars", "Voodoo", 412));
    types.add(new VehicleType("Cars", "Walton", 478));
    types.add(new VehicleType("Cars", "Washing", 421));
    types.add(new VehicleType("Cars", "Willard", 529));
    types.add(new VehicleType("Cars", "Windsor", 555));
    types.add(new VehicleType("Cars", "Yankee", 456));
    types.add(new VehicleType("Cars", "Yosemite", 554));
    types.add(new VehicleType("Cars", "ZR-350", 477));

    types.add(new VehicleType("Trailers", "Airplain Stairs", 608));
    types.add(new VehicleType("Trailers", "Baggage A", 606));
    types.add(new VehicleType("Trailers", "Baggage B", 607));
    types.add(new VehicleType("Trailers", "Filled trailer", 450));
    types.add(new VehicleType("Trailers", "Plough", 610));
    types.add(new VehicleType("Trailers", "Street Clean", 611));
    types.add(new VehicleType("Trailers", "Trailer with random picture", 435));
    types.add(new VehicleType("Trailers", "Trailer with roof", 591));
    types.add(new VehicleType("Trailers", "Xoomer", 584));

    types.add(new VehicleType("Boats", "Coast Guard", 472));
    types.add(new VehicleType("Boats", "Dinghy", 473));
    types.add(new VehicleType("Boats", "Jetmax", 493));
    types.add(new VehicleType("Boats", "Launch", 595));
    types.add(new VehicleType("Boats", "Marquis", 484));
    types.add(new VehicleType("Boats", "Predator", 430));
    types.add(new VehicleType("Boats", "Reefer", 453));
    types.add(new VehicleType("Boats", "Speeder", 452));
    types.add(new VehicleType("Boats", "Squalo", 446));
    types.add(new VehicleType("Boats", "Tropic", 454));
    types.add(new VehicleType("Boats", "Vortex", 539));

    types.add(new VehicleType("Helicopters", "Cargobob", 548));
    types.add(new VehicleType("Helicopters", "Police Maverick", 497));
    types.add(new VehicleType("Helicopters", "Hunter", 425));
    types.add(new VehicleType("Helicopters", "Leviathan", 417));
    types.add(new VehicleType("Helicopters", "Maverick", 487));
    types.add(new VehicleType("Helicopters", "News Chopper", 488));
    types.add(new VehicleType("Helicopters", "Raindance", 563));
    types.add(new VehicleType("Helicopters", "Seasparrow", 447));
    types.add(new VehicleType("Helicopters", "Sparrow", 469));

    types.add(new VehicleType("Planes", "Andromada", 592,
      new VehicleColor.ColorPair(1, 1)));

    types.add(new VehicleType("Planes", "AT-400", 577,
      new VehicleColor.ColorPair(1, 3),
      new VehicleColor.ColorPair(8, 7),
      new VehicleColor.ColorPair(8, 10),
      new VehicleColor.ColorPair(8, 16),
      new VehicleColor.ColorPair(23, 31),
      new VehicleColor.ColorPair(40, 44)));

    types.add(new VehicleType("Planes", "Beagle", 511,
      new VehicleColor.ColorPair(3, 90),
      new VehicleColor.ColorPair(4, 90),
      new VehicleColor.ColorPair(7, 68),
      new VehicleColor.ColorPair(8, 66),
      new VehicleColor.ColorPair(12, 60),
      new VehicleColor.ColorPair(27, 97),
      new VehicleColor.ColorPair(34, 51),
      new VehicleColor.ColorPair(37, 51)));

    types.add(new VehicleType("Planes", "Cropduster", 512,
      new VehicleColor.ColorPair(17, 39),
      new VehicleColor.ColorPair(15, 123),
      new VehicleColor.ColorPair(32, 112),
      new VehicleColor.ColorPair(45, 88),
      new VehicleColor.ColorPair(52, 71),
      new VehicleColor.ColorPair(57, 67),
      new VehicleColor.ColorPair(61, 96),
      new VehicleColor.ColorPair(96, 96)));

    types.add(new VehicleType("Planes", "Dodo", 593,
      new VehicleColor.ColorPair(51, 1),
      new VehicleColor.ColorPair(58, 8),
      new VehicleColor.ColorPair(60, 1),
      new VehicleColor.ColorPair(68, 8),
      new VehicleColor.ColorPair(2, 1),
      new VehicleColor.ColorPair(13, 8),
      new VehicleColor.ColorPair(22, 1),
      new VehicleColor.ColorPair(38, 8)));

    types.add(new VehicleType("Planes", "Hydra", 520));
    types.add(new VehicleType("Planes", "Nevada", 553));
    types.add(new VehicleType("Planes", "Rustler", 476));
    types.add(new VehicleType("Planes", "Shamal", 519));
    types.add(new VehicleType("Planes", "Skimmer", 460));
    types.add(new VehicleType("Planes", "Stunt Plane", 513));

    types.add(new VehicleType("RC", "RC Cam (drivable)", 594));
    types.add(new VehicleType("RC", "RC Bandit", 441,
      new VehicleColor.ColorPair(2, 96),
      new VehicleColor.ColorPair(79, 42),
      new VehicleColor.ColorPair(82, 54),
      new VehicleColor.ColorPair(67, 86),
      new VehicleColor.ColorPair(126, 96),
      new VehicleColor.ColorPair(70, 96),
      new VehicleColor.ColorPair(110, 54),
      new VehicleColor.ColorPair(67, 98)));

    types.add(new VehicleType("RC", "RC Baron", 464, new VehicleColor.ColorPair(14, 75)));
    types.add(new VehicleType("RC", "RC Goblin", 501, new VehicleColor.ColorPair(14, 75)));
    types.add(new VehicleType("RC", "RC Raider", 465, new VehicleColor.ColorPair(14, 75)));
    types.add(new VehicleType("RC", "RC Tiger", 564));

    types.add(new VehicleType("Tram", "Tram", 449, new VehicleColor.ColorPair(1, 74)));
    types.add(new VehicleType("Train", "Freight", 537, new VehicleColor.ColorPair(1, 1)));
    types.add(new VehicleType("Train", "Brown Streak", 538, new VehicleColor.ColorPair(1, 1)));

    types.add(new VehicleType("Waggons", "streakc", 570, new VehicleColor.ColorPair(1, 1)));
    types.add(new VehicleType("Waggons", "freiflat", 569));
    types.add(new VehicleType("Waggons", "freibox", 590));

    types = Collections.unmodifiableList(types);
  }


  public static List<VehicleType> getTypes()
  {
    return types;
  }

  public static VehicleType getType(int id)
  {
    return types.stream().filter(type -> type.id == id).findFirst().get();
  }

}
