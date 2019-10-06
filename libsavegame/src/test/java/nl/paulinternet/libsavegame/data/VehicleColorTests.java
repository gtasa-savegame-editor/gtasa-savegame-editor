package nl.paulinternet.libsavegame.data;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class VehicleColorTests extends TestCase
{
  public void testNoDuplicateNames()
  {
    HashMap<String, ArrayList<Integer>> duplicatesFound = new HashMap<>();
    AtomicBoolean successful = new AtomicBoolean(true);

    VehicleColor.getColors().forEach(c -> {
      if (duplicatesFound.containsKey(c.getName())) {
        ArrayList<Integer> colorIdList = duplicatesFound.get(c.getName());
        colorIdList.add(c.getId());
        duplicatesFound.put(c.getName(), colorIdList);
      } else {
        ArrayList<Integer> colorIdList = new ArrayList<>();
        colorIdList.add(c.getId());
        duplicatesFound.put(c.getName(), colorIdList);
      }
    });

    duplicatesFound.keySet().forEach(name -> {
      ArrayList<Integer> duplicates = duplicatesFound.get(name);
      if (duplicates.size() > 1) {
        successful.set(false);
        System.err.print("Duplicated names found for color '" + name + "': [");
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