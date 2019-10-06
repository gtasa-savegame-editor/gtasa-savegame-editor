package nl.paulinternet.libsavegame.link;

import nl.paulinternet.libsavegame.SavegameData;
import nl.paulinternet.libsavegame.exceptions.FileFormatException;

public interface Link {
    public void load(SavegameData io) throws FileFormatException;

    public void save(SavegameData io);
}
