package nl.paulinternet.libsavegame.link;

import nl.paulinternet.libsavegame.SavegameData;
import nl.paulinternet.libsavegame.exceptions.FileFormatException;
import nl.paulinternet.libsavegame.variables.Variables;

public interface Link {
    public void load(SavegameData io) throws FileFormatException;

    public void save(SavegameData io);

    Variables vars = Variables.get();
}
