package nl.paulinternet.gtasaveedit.model.savegame.blocks;

import nl.paulinternet.gtasaveedit.model.exceptions.FileFormatException;
import nl.paulinternet.gtasaveedit.model.savegame.data.Jump;
import nl.paulinternet.gtasaveedit.model.Model;
import nl.paulinternet.gtasaveedit.model.savegame.SavegameData;
import nl.paulinternet.gtasaveedit.model.link.Link;

public class Block24 implements Link
{
	@Override
	public void load (SavegameData io) throws FileFormatException {
		// Get number of jumps
		int total = io.readInt(24, 0);
		
		// Check block size
		if (io.getBlock(24).getLength() != 4 + total * 0x44) throw new FileFormatException();
		
		// Load jumps
		Jump[] jumps = new Jump[total];
		
		for (int i=0; i<70; i++) {
			int start = 0x4 + 0x44 * i;
			jumps[i] = new Jump();
			jumps[i].x = 0.5f * (io.readFloat(24, start) + io.readFloat(24, start + 0xc));
			jumps[i].y = 0.5f * (io.readFloat(24, start + 0x4) + io.readFloat(24, start + 0x10));
			jumps[i].reward = io.readInt(24, start + 0x3c);
			jumps[i].completed = io.readBoolean(24, start + 0x40);
			jumps[i].found = io.readBoolean(24, start + 0x41);
		}
		
		Model.vars.jumps = jumps;
	}

	@Override
	public void save (SavegameData io) {
		Jump[] jumps = Model.vars.jumps;
		
		for (int i=0; i<jumps.length; i++) {
			int start = 0x4 + 0x44 * i;
			io.writeInt(24, start + 0x3c, jumps[i].reward);
			io.writeBoolean(24, start + 0x40, jumps[i].completed);
			io.writeBoolean(24, start + 0x41, jumps[i].found);
		}
	}
}
