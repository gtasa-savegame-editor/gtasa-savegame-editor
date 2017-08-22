package nl.paulinternet.gtasaveedit.model.link;

import static nl.paulinternet.gtasaveedit.model.Model.vars;

public class Block04 extends LinkArray
{
	public Block04 () {
		setLinks(new Link[] {
			new LinkBoolean(vars.loseStuffWasted, 4, 0x4, 1),
			new LinkBoolean(vars.loseStuffBusted, 4, 0x5, 1)
		});
	}
}
