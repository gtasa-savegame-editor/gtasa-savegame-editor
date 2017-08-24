package nl.paulinternet.gtasaveedit.model.savegame.blocks;

import nl.paulinternet.gtasaveedit.model.link.Link;
import nl.paulinternet.gtasaveedit.model.link.LinkArray;
import nl.paulinternet.gtasaveedit.model.link.LinkBoolean;

import static nl.paulinternet.gtasaveedit.model.Model.vars;

public class Block23 extends LinkArray
{
	public Block23 () {
		setLinks(new Link[] {
			new LinkBoolean(vars.gangWars, 23, 0x4, 1)
		});
	}
}
