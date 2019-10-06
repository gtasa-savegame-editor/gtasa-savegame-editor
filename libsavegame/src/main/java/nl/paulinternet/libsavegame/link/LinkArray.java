package nl.paulinternet.libsavegame.link;

import nl.paulinternet.libsavegame.SavegameData;
import nl.paulinternet.libsavegame.exceptions.FileFormatException;

public class LinkArray implements Link
{
	protected Link[] links;

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
