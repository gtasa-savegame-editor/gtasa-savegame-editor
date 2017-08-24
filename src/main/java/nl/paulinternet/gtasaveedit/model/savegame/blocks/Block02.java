package nl.paulinternet.gtasaveedit.model.savegame.blocks;

import static nl.paulinternet.gtasaveedit.model.Model.vars;
import nl.paulinternet.gtasaveedit.model.Cloth;
import nl.paulinternet.gtasaveedit.model.Clothes;
import nl.paulinternet.gtasaveedit.model.exceptions.FileFormatException;
import nl.paulinternet.gtasaveedit.model.savegame.SavegameData;
import nl.paulinternet.gtasaveedit.model.variables.Variable;
import nl.paulinternet.gtasaveedit.model.link.Link;
import nl.paulinternet.gtasaveedit.model.link.LinkArray;
import nl.paulinternet.gtasaveedit.model.link.LinkFloat;
import nl.paulinternet.gtasaveedit.model.link.LinkInt;

public class Block02 extends LinkArray
{
	private static final int[] CLOTH_TEXTURE = new int[] {0x1d4, 0x1d8, 0x1dc, 0x1e0, 0x208, 0x20c, 0x210, 0x214, 0x218};
	private static final int[] CLOTH_MODEL = new int[] {0x1ac, 0x1b0, 0x1b8, 0x1bc, 0x1c0, 0x1c4, 0x1c8, 0x1cc, 0x1d0};
	
	public Block02 () {
		setLinks(new Link[] {
			new LinkFloat(vars.health, 2, 0x20),
			new LinkFloat(vars.armor, 2, 0x24),
			new LinkInt(vars.weaponStartSlot, 2, 0x224, 1),
		});
	}
	
	@Override
	public void load (SavegameData io) throws FileFormatException {
		super.load(io);
		
		// Load weapons
		for (int i=0; i<13; i++) {
			vars.weaponAmmo.get(i).setIntValue(io.readInt(2, 0x34 + 28 * i));
			
			if (i > 10 && vars.weaponAmmo.get(i).getIntValue() == 0) {
				vars.weaponType.get(i).setIntValue(0);
			}
			else {
				vars.weaponType.get(i).setIntValue(io.readInt(2, 0x28 + 28 * i));
			}
			vars.weaponType.get(i).resetChangedState();
		}

		// Load clothes
		for (int i=0; i<9; i++) {
			int textureId = io.readInt(2, CLOTH_TEXTURE[i]);
			vars.clothes.get(i).setValue(Clothes.getCloth(i, textureId));
			vars.clothes.get(i).resetChangedState();
		}
		
		// Load tattoos
		for (int i=0; i<9; i++) {
			int textureId = io.readInt(2, 0x1e4 + 4 * i);
			vars.tattoos.get(i).setValue(Clothes.getTattoo(i, textureId));
			vars.tattoos.get(i).resetChangedState();
		}
	}
	
	@Override
	public void save (SavegameData io) {
		super.save(io);
		
		// Save weapons
		io.writeInt(2, 0x195, 1, vars.weaponStartSlot.getIntValue());
		
		for (int i=0; i<13; i++) {
			if (vars.weaponType.get(i).hasChanged()) {
				// Set ammo to 1
				if (i > 10 && vars.weaponType.get(i).getIntValue() != 0 && vars.weaponAmmo.get(i).getIntValue() == 0) {
					vars.weaponAmmo.get(i).setIntValue(1);
				}
				
				// Write weapons type
				io.writeInt(2, 0x28 + 28 * i, vars.weaponType.get(i).getIntValue());
				vars.weaponType.get(i).resetChangedState();
			}
			io.writeInt(2, 0x34 + 28 * i, vars.weaponAmmo.get(i).getIntValue());
		}
		
		// Save clothes
		for (int i=0; i<9; i++) {
			Variable<Cloth> var = vars.clothes.get(i);
			if (var.hasChanged()) {
				Cloth cloth = var.getValue();
				io.writeInt(2, CLOTH_MODEL[i], cloth.getModelId());
				io.writeInt(2, CLOTH_TEXTURE[i], cloth.getTextureId());
				var.resetChangedState();
				
			}
		}
		
		// Save tattoos
		for (int i=0; i<9; i++) {
			Variable<Cloth> var = vars.tattoos.get(i);
			if (var.hasChanged()) {
				io.writeInt(2, 0x1e4 + 4 * i, var.getValue().getTextureId());
				var.resetChangedState();
			}
		}
	}
}
