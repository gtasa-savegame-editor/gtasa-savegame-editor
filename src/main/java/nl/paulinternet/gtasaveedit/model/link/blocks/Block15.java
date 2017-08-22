package nl.paulinternet.gtasaveedit.model.link.blocks;

import nl.paulinternet.gtasaveedit.model.Model;
import nl.paulinternet.gtasaveedit.model.variables.Variables;
import nl.paulinternet.gtasaveedit.model.link.Link;
import nl.paulinternet.gtasaveedit.model.link.LinkArray;
import nl.paulinternet.gtasaveedit.model.link.LinkBoolean;
import nl.paulinternet.gtasaveedit.model.link.LinkInt;

public class Block15 extends LinkArray
{
	public Block15 () {
		Variables vars = Model.vars;
		
		setLinks(new Link[] {
			new LinkInt(vars.money, 15, 0x4),
			new LinkInt(vars.moneyOnScreen, 15, 0x10),
			new LinkBoolean(vars.infiniteRun, 15, 0x20, 1),
			new LinkBoolean(vars.fastReload, 15, 0x21, 1),
			new LinkBoolean(vars.fireProof, 15, 0x22, 1),
			new LinkBoolean(vars.freeBustedOnce, 15, 0x25, 1),
			new LinkBoolean(vars.freeWastedOnce, 15, 0x26, 1),
			new LinkBoolean(vars.enableDriveby, 15, 0x27, 1),
		});
	}
}
