package nl.paulinternet.libsavegame.link;

import nl.paulinternet.libsavegame.blocks.*;

public class SavegameLink extends LinkArray
{
	public SavegameLink () {
		setLinks(new Link[] {
			new Block00(),
			new Block01(),
			new Block02(),
			new Block03(),
			new Block04(),
			new Block06(),
			new Block10(),
			new Block11(),
			new Block15(),
			new Block16(),
			new Block19(),
			new Block20(),
			new Block21(),
			new Block23(),
			new Block24(),
			new ClothLink(), // Purchased clothes, block 01 and 22
			new SaveplaceLink()
		});
	}
}
