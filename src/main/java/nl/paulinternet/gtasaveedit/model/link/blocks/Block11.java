package nl.paulinternet.gtasaveedit.model.link.blocks;

import nl.paulinternet.gtasaveedit.model.exceptions.FileFormatException;
import nl.paulinternet.gtasaveedit.model.Model;
import nl.paulinternet.gtasaveedit.model.savegame.SavegameData;
import nl.paulinternet.gtasaveedit.model.link.Link;

public class Block11 implements Link
{
	@Override
	public void load (SavegameData io) throws FileFormatException {
		int p=0;
		for (int i=0; i<8; i++) {
			Model.vars.gangWeapon.get(p++).setIntValue(io.readInt(11, i * 0x10 + 0x4));
			Model.vars.gangWeapon.get(p++).setIntValue(io.readInt(11, i * 0x10 + 0x8));
			Model.vars.gangWeapon.get(p++).setIntValue(io.readInt(11, i * 0x10 + 0xc));
		}
	}

	@Override
	public void save (SavegameData io) {
		int p=0;
		for (int i=0; i<8; i++) {
			io.writeInt(11, i * 0x10 + 0x4, Model.vars.gangWeapon.get(p++).getIntValue());
			io.writeInt(11, i * 0x10 + 0x8, Model.vars.gangWeapon.get(p++).getIntValue());
			io.writeInt(11, i * 0x10 + 0xc, Model.vars.gangWeapon.get(p++).getIntValue());
		}
	}
}
