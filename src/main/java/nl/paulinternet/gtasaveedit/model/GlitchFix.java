package nl.paulinternet.gtasaveedit.model;

import nl.paulinternet.gtasaveedit.model.savegame.Savegame;
import nl.paulinternet.gtasaveedit.model.savegame.SavegameData;

import java.io.DataInputStream;

public class GlitchFix
{
	public static void fixTrafficGlitch () {
		SavegameData data = Savegame.getData();
		int time = data.readInt(0, 0x94);
		
		// Weather timer
		data.writeInt(0, 0x80, 0);
		
		// Global timer
		data.writeInt(0, 0x94, 0);
		
		// Thread local timers and wakeup times
		int afterVars = 4 + data.readInt(1, 0);
		int threadCount = data.readInt(1, afterVars + 0x902);
		
		for (int i=0; i<threadCount; i++) {
			int threadStart = afterVars + 0x906 + i * 0x106;
			
			int newTime = data.readInt(1, threadStart + 0xbe) - time;
			data.writeInt(1, threadStart + 0xbe, newTime < 0 ? 0 : newTime);
			
			newTime = data.readInt(1, threadStart + 0xc2) - time;
			data.writeInt(1, threadStart + 0xc2, newTime < 0 ? 0 : newTime);
			
			newTime = data.readInt(1, threadStart + 0xce) - time;
			data.writeInt(1, threadStart + 0xce, newTime < 0 ? 0 : newTime);
		}
		
		// Pickups
		for (int i=0; i<620; i++) {
			int timePos = i * 0x20 + 0xc;
			int newTime = data.readInt(6, timePos) - time;
			data.writeInt(6, timePos, newTime < 0 ? 0 : newTime);
		}
		
		// Car generator times
		int vehicleCount = data.readInt(12, 0);
		
		for (int i=0; i<vehicleCount; i++) {
			int timePos = 0x6 + i * 0x22 + 0x16;
			int newTime = data.readInt(12, timePos) - time;
			data.writeInt(12, timePos, newTime < 0 ? 0 : newTime);
		}
		
		// Police trigger zones
		int policeCount = data.readInt(17, 0);
		
		for (int i=0; i<policeCount; i++) {
			int timePos = 0x4 + i * 0x20;
			int newTime = data.readInt(12, timePos) - time;
			data.writeInt(17, timePos, newTime < 0 ? 0 : newTime);
		}
		
		// Some global variables that need to be reset, see http://www.gtaforums.com/index.php?showtopic=526745
		data.writeInt(1, 4 + 4*184, 0);
		data.writeInt(1, 4 + 4*672, 0);
		data.writeInt(1, 4 + 4*674, 0);
		data.writeInt(1, 4 + 4*5283, 0);
		data.writeInt(1, 4 + 4*10045, 0);
	}
	
	public static void moveSaveDisk () {
		SavegameData data = Savegame.getData();
		data.writeFloat(1, 886 * 4 + 8, 1291.8f);
		data.writeFloat(1, 904 * 4 + 8, -797.8284f);
		data.writeFloat(1, 922 * 4 + 8, 1089.5f);
		data.writeFloat(1, 940 * 4 + 8, 1286.8f);
		data.writeFloat(1, 958 * 4 + 8, -797.69f);
		data.writeFloat(1, 976 * 4 + 8, 1089.1f);
		data.writeFloat(1, 994 * 4 + 8, 90.0f);
	}
	
	public static void fixGymGlitch () {
		SavegameData data = Savegame.getData();
		data.writeInt(1, 0x5388, -1);
		data.writeInt(1, 0x538c, -1);
		data.writeInt(1, 0x5390, -1);
		data.writeInt(1, 0x5394, -1);
	}
	
	private static byte[] zoneinfo1, zoneinfo2;
	
	public static void fixZoneGlitch () {
		// Load data
		if (zoneinfo1 == null) {
			try {
				DataInputStream in = new DataInputStream(GlitchFix.class.getResourceAsStream("zoneinfo1.dat"));
				zoneinfo1 = new byte[12134];
				in.readFully(zoneinfo1);
				in.close();
				
				in = new DataInputStream(GlitchFix.class.getResourceAsStream("zoneinfo2.dat"));
				zoneinfo2 = new byte[224];
				in.readFully(zoneinfo2);
				in.close();
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
		SavegameData data = Savegame.getData();
		
		// Overwrite data
		data.getBlock(10).overwrite(zoneinfo1, 0x4);
		data.getBlock(10).overwrite(zoneinfo2, 0x4884);
		
		// Count zones
		int discovered = 0;
		
		for (int i=0; i<100; i++) {
			if (data.readBoolean(10, 0x4964 + i)) discovered++;
		}
		
		data.writeInt(10, 0x49c8, discovered);
	}
}
