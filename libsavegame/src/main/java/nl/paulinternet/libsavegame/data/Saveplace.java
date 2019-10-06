package nl.paulinternet.libsavegame.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Saveplace
{
	private int id;
	private Savehouse house;
	private int location;
	private int mapX, mapY;
	
	private Saveplace (int id, int house, int location, int mapX, int mapY) {
		this.id = id;
		this.house = Savehouse.getHouse(house);
		this.location = location;
		this.mapX = mapX;
		this.mapY = mapY;
	}
	
	public int getId () {
		return id;
	}

	public Savehouse getHouse () {
		return house;
	}

	public int getLocation () {
		return location;
	}
	
	public boolean isInside () {
		return location != -1;
	}

	public int getMapX () {
		return mapX;
	}

	public int getMapY () {
		return mapY;
	}
	
	private static List<Saveplace> places;
	
	static
	{
		places = new ArrayList<>(37);
		int i = 0;
		places.add(new Saveplace(i++, 13, -1, 105, 563));
		places.add(new Saveplace(i++, 14, -1, 398, 312));
		places.add(new Saveplace(i++, 15, -1, 106, 294));
		places.add(new Saveplace(i++, 16, -1, 240, 214));
		places.add(new Saveplace(i++, 17, -1, 513, 210));
		places.add(new Saveplace(i++, 3, 10, 479, 520));
		places.add(new Saveplace(i++, 12, 15, 559, 510));
		places.add(new Saveplace(i++, 12, 18, 521, 439));
		places.add(new Saveplace(i++, 0, 28, 560, 481));
		places.add(new Saveplace(i++, 3, 36, 399, 474));
		places.add(new Saveplace(i++, 3, 46, 341, 487));
		places.add(new Saveplace(i++, 6, 47, 443, 373));
		places.add(new Saveplace(i++, 1, 48, 436, 387));
		places.add(new Saveplace(i++, 8, 57, 63, 323));
		places.add(new Saveplace(i++, 11, 58, 67, 276));
		places.add(new Saveplace(i++, 6, 70, 106, 314));
		places.add(new Saveplace(i++, 6, 74, 98, 219));
		places.add(new Saveplace(i++, 7, 75, 87, 237));
		places.add(new Saveplace(i++, 7, 100, 39, 227));
		places.add(new Saveplace(i++, 10, 137, 533, 125));
		places.add(new Saveplace(i++, 9, 138, 548, 92));
		places.add(new Saveplace(i++, 7, 139, 592, 94));
		places.add(new Saveplace(i++, 9, 146, 507, 147));
		places.add(new Saveplace(i++, 10, 148, 534, 181));
		places.add(new Saveplace(i++, 3, 151, 554, 240));
		places.add(new Saveplace(i++, 3, 171, 450, 119));
		places.add(new Saveplace(i++, 4, 172, 439, 56));
		places.add(new Saveplace(i++, 3, 173, 402, 108));
		places.add(new Saveplace(i++, 5, 176, 156, 44));
		places.add(new Saveplace(i++, 5, 177, 205, 154));
		places.add(new Saveplace(i++, 2, 190, 352, 55));
		places.add(new Saveplace(i++, 5, 194, 273, 193));
		places.add(new Saveplace(i++, 5, 202, 101, 541));
		places.add(new Saveplace(i++, 5, 204, 389, 361));
		places.add(new Saveplace(i++, 5, 205, 534, 293));
		places.add(new Saveplace(i++, 5, 210, 330, 321));
		places.add(new Saveplace(i, 5, 218, 165, 464));
		places = Collections.unmodifiableList(places);
	}
	
	public static List<Saveplace> getPlaces () {
		return places;
	}
	
	public static Saveplace getPlace (int id) {
		return places.get(id);
	}
	
	public static Saveplace detect (boolean inside, int house, int location) {
		for (Saveplace place : places) {
			if (inside && place.isInside() && location == place.getLocation()) return place;
			if (!inside && !place.isInside() && house == place.getHouse().getId()) return place;
		}
		return null;
	}
}
