package nl.paulinternet.libsavegame.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Savehouse
{
	private int id;
	private int heaven;
	private int locationInside;
	private float[] camera;
	private boolean teleport;
	
	private Savehouse (int id, int heaven, int locationInside, float cameraX, float cameraY, float cameraZ, boolean teleport) {
		this.id = id;
		this.heaven = heaven;
		this.locationInside = locationInside;
		this.camera = new float[] {cameraX, cameraY, cameraZ};
		this.teleport = teleport;
	}

	public int getId () {
		return id;
	}

	public int getHeaven () {
		return heaven;
	}

	public int getLocationInside () {
		return locationInside;
	}

	public float[] getCamera () {
		return camera;
	}
	
	public boolean getTeleport () {
		return teleport;
	}
	
	private static List<Savehouse> houses;
	
	static
	{
		houses = new ArrayList<Savehouse>(18);
		houses.add(new Savehouse(0, 3, 219, 2496.141f, -1706.2134f, 1016.0913f, false));
		houses.add(new Savehouse(1, 5, 222, 1263.5734f, -779.8077f, 1093.3632f, false));
		houses.add(new Savehouse(2, 10, 186, 419.8462f, 2536.691f, 11.1574f, false));
		houses.add(new Savehouse(3, 8, 370, 2366.385f, -1123.2087f, 1051.9702f, true));
		houses.add(new Savehouse(4, 9, 348, 2320.547f, -1014.7947f, 1052.1945f, false));
		houses.add(new Savehouse(5, 6, 356, 2340.0674f, -1064.4211f, 1050.415f, true));
		houses.add(new Savehouse(6, 6, 369, 2187.483f, -1204.8802f, 1050.0815f, true));
		houses.add(new Savehouse(7, 6, 349, 2309.3728f, -1209.2812f, 1050.8184f, true));
		houses.add(new Savehouse(8, 10, 351, 2252.0999f, -1208.5323f, 1050.4799f, false));
		houses.add(new Savehouse(9, 2, 355, 2240.4253f, -1077.5219f, 1050.4371f, true));
		houses.add(new Savehouse(10, 1, 354, 2210.6953f, -1073.9062f, 1051.6207f, true));
		houses.add(new Savehouse(11, 5, 353, 2232.516f, -1109.055f, 1052.635f, false));
		houses.add(new Savehouse(12, 11, 352, 2279.6057f, -1139.4312f, 1051.7806f, true));
		houses.add(new Savehouse(13, 0, -1, -2038.3077f, -2525.1067f, 31.752f, false));
		houses.add(new Savehouse(14, 0, -1, 876.4969f, -32.0775f, 65.1287f, false));
		houses.add(new Savehouse(15, 0, -1, -2020.8798f, 156.3544f, 30.3147f, false));
		houses.add(new Savehouse(16, 0, -1, -693.6253f, 953.1834f, 13.7302f, false));
		houses.add(new Savehouse(17, 0, -1, 2022.2886f, 996.0208f, 11.5915f, false));
		houses = Collections.unmodifiableList(houses);
	}
	
	public static Savehouse getHouse (int id) {
		return houses.get(id);
	}
}
