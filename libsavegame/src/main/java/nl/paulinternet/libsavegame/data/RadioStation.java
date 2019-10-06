package nl.paulinternet.libsavegame.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RadioStation {

  private int id;
  private String name;


  private RadioStation(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int getId() {
    return id;
  }


  public String getName() {
    return name;
  }


  private static List<RadioStation> stations;

  static {
    stations = new ArrayList<RadioStation>(14);

    stations.add(new RadioStation(0, "Vehicle has no radio"));
    stations.add(new RadioStation(1, "Playback FM"));
    stations.add(new RadioStation(2, "K Rose"));
    stations.add(new RadioStation(3, "K-DST"));
    stations.add(new RadioStation(4, "Bounce FM"));
    stations.add(new RadioStation(5, "SF-UR"));
    stations.add(new RadioStation(6, "Radio Los Santos"));
    stations.add(new RadioStation(7, "Radio X"));
    stations.add(new RadioStation(8, "CSR 103.9"));
    stations.add(new RadioStation(9, "K-Jah West"));
    stations.add(new RadioStation(10, "Master Sounds 98.3"));
    stations.add(new RadioStation(11, "WCTR"));
    stations.add(new RadioStation(12, "User Track Player"));
    stations.add(new RadioStation(13, "Radio Off"));

    stations = Collections.unmodifiableList(stations);
  }


  public static List<RadioStation> getStations() {
    return stations;
  }

  public static RadioStation getStation(int id) {
    return stations.get(id);
  }


}
