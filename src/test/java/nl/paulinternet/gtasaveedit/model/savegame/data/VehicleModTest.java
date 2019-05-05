package nl.paulinternet.gtasaveedit.model.savegame.data;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class VehicleModTest extends TestCase {
    public void testVehicleModsNoDuplicateDffNames()
    {
        HashMap<String, ArrayList<Integer>> duplicatesFound = new HashMap<>();
        AtomicBoolean successful = new AtomicBoolean(true);

        VehicleMod.getMods().forEach(c -> {
            if (duplicatesFound.containsKey(c.getDffName())) {
                ArrayList<Integer> idList = duplicatesFound.get(c.getDffName());
                idList.add(c.getId());
                duplicatesFound.put(c.getDffName(), idList);
            } else {
                ArrayList<Integer> idList = new ArrayList<>();
                idList.add(c.getId());
                duplicatesFound.put(c.getDffName(), idList);
            }
        });

        duplicatesFound.keySet().forEach(name -> {
            ArrayList<Integer> duplicates = duplicatesFound.get(name);
            if (duplicates.size() > 1) {
                successful.set(false);
                System.err.print("Duplicated dffNames found for '" + name + "': [");
                AtomicBoolean first = new AtomicBoolean(true);
                duplicates.forEach(c -> {
                    System.err.print(((first.get()) ? "" : ", ") + c);
                    if(first.get()) {
                        first.set(false);
                    }
                });
                System.err.println("]");
            }
        });

        assertTrue(successful.get());
    }
}