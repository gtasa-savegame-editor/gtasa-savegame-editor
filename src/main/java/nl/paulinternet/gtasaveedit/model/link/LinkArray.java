package nl.paulinternet.gtasaveedit.model.link;

import nl.paulinternet.gtasaveedit.model.exceptions.FileFormatException;
import nl.paulinternet.gtasaveedit.model.savegame.SavegameData;

public class LinkArray implements Link
{
	private Link[] links;

	public void setLinks (Link[] links) {
		this.links = links;
	}
	
	@Override
	public void load (SavegameData io) throws FileFormatException {
		for (Link link : links) link.load(io);
	}

	@Override
	public void save (SavegameData io) {
		for (Link link : links) link.save(io);
	}
}
