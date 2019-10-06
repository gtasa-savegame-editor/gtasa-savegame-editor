package nl.paulinternet.libsavegame.data;

import java.util.ArrayList;
import java.util.List;

public class VehiclePaintJob {
    private final int id;
    private final String name;

    private VehiclePaintJob(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private static final List<VehiclePaintJob> paintJobs;

    static {
        paintJobs = new ArrayList<>();

        paintJobs.add(new VehiclePaintJob(255, "No Paintjob"));
        paintJobs.add(new VehiclePaintJob(0, "Paintjob 1"));
        paintJobs.add(new VehiclePaintJob(1, "Paintjob 2"));
        paintJobs.add(new VehiclePaintJob(2, "Paintjob 3"));
    }

    public static List<VehiclePaintJob> getPaintJobs() {
        return paintJobs;
    }
}
