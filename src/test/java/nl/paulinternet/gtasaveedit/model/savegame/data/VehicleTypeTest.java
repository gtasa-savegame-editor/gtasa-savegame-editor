package nl.paulinternet.gtasaveedit.model.savegame.data;

import junit.framework.TestCase;

import java.util.ArrayList;

public class VehicleTypeTest extends TestCase
{
  public void testValidateAllCarsHaveValidColors() {
    ArrayList<VehicleType> vehiclesWithoutValidColors = new ArrayList<>();

    VehicleType.getTypes().forEach(vt -> {
      if(vt.getValidColors().size() <= 0){
        vehiclesWithoutValidColors.add(vt);
      }
    });

    if(vehiclesWithoutValidColors.size()>= 1) {
      vehiclesWithoutValidColors.forEach(vt -> System.out.println("Car has no validColors: '" + vt.getName() + "'"));
    }

    assertTrue(vehiclesWithoutValidColors.size() == 0);
  }
}