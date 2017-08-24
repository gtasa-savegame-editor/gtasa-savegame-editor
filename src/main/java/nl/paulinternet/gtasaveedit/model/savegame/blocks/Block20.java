package nl.paulinternet.gtasaveedit.model.savegame.blocks;

import nl.paulinternet.gtasaveedit.model.Model;
import nl.paulinternet.gtasaveedit.model.savegame.SavegameData;
import nl.paulinternet.gtasaveedit.model.link.Link;

public class Block20 implements Link
{
	@Override
	public void load (SavegameData io) {
		for (int i=0; i<100; i++) {
			Model.vars.tags[i] = io.readByte(20, i + 4);
		}
	}

	@Override
	public void save (SavegameData io) {
		for (int i=0; i<100; i++) {
			io.writeByte(20, i + 4, Model.vars.tags[i]);
		}
	}

}
