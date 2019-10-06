package nl.paulinternet.libsavegame.data;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class VehicleTypeTests extends TestCase {
    public void testAllCarsHaveValidColors() {
        ArrayList<VehicleType> vehiclesWithoutValidColors = new ArrayList<>();

        VehicleType.getTypes().forEach(vt -> {
            if (vt.getValidColors().size() <= 0) {
                vehiclesWithoutValidColors.add(vt);
            }
        });

        if (vehiclesWithoutValidColors.size() >= 1) {
            vehiclesWithoutValidColors.forEach(vt -> System.out.println("Car has no validColors: '" + vt.getName() + "'"));
        }

        assertTrue(vehiclesWithoutValidColors.size() <= 15); // 15 cars have no color by design
    }

    public void testNoDuplicateValidMods() {
        AtomicBoolean successful = new AtomicBoolean(true);
        VehicleType.getTypes().forEach(type -> {
            ArrayList<String> idx = new ArrayList<>();
            ArrayList<String> duplicates = new ArrayList<>();
            Arrays.asList(type.getValidMods()).forEach(mod -> {
                if (!idx.contains(mod)) {
                    idx.add(mod);
                } else {
                    duplicates.add(mod);
                }
            });
            duplicates.forEach(mod -> {
                successful.set(false);
                System.err.println("Duplicated mod for type '" + type.getName() + "': '" + mod + "'");
            });
        });
        assertTrue(successful.get());
    }

    public void testNoDuplicateIds() {
        HashMap<Integer, ArrayList<String>> duplicatesFound = new HashMap<>();
        AtomicBoolean successful = new AtomicBoolean(true);

        VehicleType.getTypes().forEach(c -> {
            if (duplicatesFound.containsKey(c.getId())) {
                ArrayList<String> nameList = duplicatesFound.get(c.getId());
                nameList.add(c.getName());
                duplicatesFound.put(c.getId(), nameList);
            } else {
                ArrayList<String> nameList = new ArrayList<>();
                nameList.add(c.getName());
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