package nl.paulinternet.libsavegame.data;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class VehicleModTests extends TestCase {

    public void testNoDuplicateDffNames() {
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
                    if (first.get()) {
                        first.set(false);
                    }
                });
                System.err.println("]");
            }
        });

        assertTrue(successful.get());
    }

    public void testNoDuplicateNames() {
        HashMap<String, ArrayList<Integer>> duplicatesFound = new HashMap<>();
        AtomicBoolean successful = new AtomicBoolean(true);

        VehicleMod.getMods().forEach(c -> {
            if (duplicatesFound.containsKey(c.getName())) {
                ArrayList<Integer> idList = duplicatesFound.get(c.getName());
                idList.add(c.getId());
                duplicatesFound.put(c.getName(), idList);
            } else {
                ArrayList<Integer> idList = new ArrayList<>();
                idList.add(c.getId());
                duplicatesFound.put(c.getName(), idList);
            }
        });

        duplicatesFound.keySet().forEach(name -> {
            ArrayList<Integer> duplicates = duplicatesFound.get(name);
            if (duplicates.size() > 1) {
                successful.set(false);
                System.err.print("Duplicated names found for '" + name + "': [");
                AtomicBoolean first = new AtomicBoolean(true);
                duplicates.forEach(c -> {
                    System.err.print(((first.get()) ? "" : ", ") + c);
                    if (first.get()) {
                        first.set(false);
                    }
                });
                System.err.println("]");
            }
        });

        assertTrue(successful.get());
    }

    public void testNoDuplicateIds() {
        HashMap<Integer, ArrayList<String>> duplicatesFound = new HashMap<>();
        AtomicBoolean successful = new AtomicBoolean(true);

        VehicleMod.getMods().forEach(c -> {
            if (duplicatesFound.containsKey(c.getId())) {
                ArrayList<String> nameList = duplicatesFound.get(c.getId());
                nameList.add(c.getDffName());
                duplicatesFound.put(c.getId(), nameList);
            } else {
                ArrayList<String> nameList = new ArrayList<>();
                nameList.add(c.getDffName());
                duplicatesFound.put(c.getId(), nameList);
            }
        });

        duplicatesFound.keySet().forEach(id -> {
            ArrayList<String> duplicates = duplicatesFound.get(id);
            if (duplicates.size() > 1) {
                successful.set(false);
                System.err.print("Duplicated ids found for '" + id + "': [");
                AtomicBoolean first = new AtomicBoolean(true);
                duplicates.forEach(c -> {
                    System.err.print(((first.get()) ? "" : ", ") + c);
                    if (first.get()) {
                        first.set(false);
                    }
                });
                System.err.println("]");
            }
        });

        assertTrue(successful.get());
    }
}