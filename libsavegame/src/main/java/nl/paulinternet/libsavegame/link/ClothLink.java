package nl.paulinternet.libsavegame.link;

import nl.paulinternet.libsavegame.data.Cloth;
import nl.paulinternet.libsavegame.data.Clothes;
import nl.paulinternet.libsavegame.SavegameData;
import nl.paulinternet.libsavegame.exceptions.FileFormatException;

public class ClothLink implements Link
{
	@Override
	public void load (SavegameData io) throws FileFormatException {
		int add = io.readInt(22, 0) * 8;
		
		// Clothes
		for (Cloth[][] clothArr1 : Clothes.clothes) {
			for (Cloth[] clothArr2 : clothArr1) {
				for (Cloth cloth : clothArr2) {
					int address = cloth.getAddress();
					if (address > 1000) {
						cloth.setPurchased(io.readBoolean(1, address));
					}
					else if (address != 0) {
						cloth.setPurchased(io.readBoolean(22, add + address));
					}
				}
			}
		}
	}

	@Override
	public void save (SavegameData io) {
		int add = io.readInt(22, 0) * 8;
		
		boolean[] shopEnable = new boolean[6];
		
		// Clothes
		for (Cloth[][] clothArr1 : Clothes.clothes) {
			int shop = 0;
			for (Cloth[] clothArr2 : clothArr1) {
				for (Cloth cloth : clothArr2) {
					int address = cloth.getAddress();
					if (address != 0) {
						if (address > 1000) {
							io.writeBoolean(1, address, cloth.isPurchased());
						}
						else {
							io.writeBoolean(22, address + add, cloth.isPurchased());
						}
						
						if (cloth.isPurchased() && shop != 0) {
							shopEnable[shop-1] = true;
						}
					}
	
				}
				shop++;
			}
		}

		// Shops
		for (int i=0; i<6; i++) {
			io.writeBoolean(1, 0x27d8 + 4 * i, shopEnable[i]);
		}
	}
}
