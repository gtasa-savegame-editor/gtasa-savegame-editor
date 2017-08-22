package nl.paulinternet.gtasaveedit.model.link;

import nl.paulinternet.gtasaveedit.model.exceptions.FileFormatException;
import nl.paulinternet.gtasaveedit.model.savegame.SavegameData;

public interface Link
{
	public void load (SavegameData io) throws FileFormatException;
	public void save (SavegameData io);
}
