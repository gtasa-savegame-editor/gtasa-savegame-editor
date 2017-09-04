package nl.paulinternet.gtasaveedit.model.savegame.blocks;

import static nl.paulinternet.gtasaveedit.model.Model.vars;

import java.util.ArrayList;

import nl.paulinternet.gtasaveedit.model.exceptions.FileFormatException;
import nl.paulinternet.gtasaveedit.model.Model;
import nl.paulinternet.gtasaveedit.model.savegame.data.Pickup;
import nl.paulinternet.gtasaveedit.model.savegame.SavegameData;
import nl.paulinternet.gtasaveedit.model.link.Link;

public class Block06 implements Link
{
	@Override
	public void load (SavegameData io) throws FileFormatException {
		vars.oysters = new ArrayList<Pickup>();
		vars.snapshots = new ArrayList<Pickup>();
		vars.horseshoes = new ArrayList<Pickup>();
		
		for (int i=0; i<620; i++) {
			int start = 0x20 * i;
			int object = io.readShort(6, start + 0x18);
			
			if (object == 953 || object == 1253 || object == 954) {
				int avail = io.readByte(6, start + 0x1d);
				if (avail == 0) {
					int x = io.readShort(6, start + 0x10);
					int y = io.readShort(6, start + 0x12);
					Pickup pickup = new Pickup(i, x, y);
					
					switch (object) {
						case 953: vars.oysters.add(pickup); break;
						case 1253: vars.snapshots.add(pickup); break;
						case 954: vars.horseshoes.add(pickup); break;
					}
				}
			}
		}
		
		Model.vars.helpReplaceWeapon.setIntValue(io.readByte(6, 0x4d82));
	}

	@Override
	public void save (SavegameData io) {
		io.writeByte(6, 0x4d82, Model.vars.helpReplaceWeapon.getIntValue());
	}
}
