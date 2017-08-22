package nl.paulinternet.gtasaveedit.model.link;

import static nl.paulinternet.gtasaveedit.model.Model.vars;

public class Block23 extends LinkArray
{
	public Block23 () {
		setLinks(new Link[] {
			new LinkBoolean(vars.gangWars, 23, 0x4, 1)
		});
	}
}
