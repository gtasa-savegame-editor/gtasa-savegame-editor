package nl.paulinternet.libsavegame.variables;

import nl.paulinternet.libsavegame.event.Event;

public interface VariableBoolean {
    Boolean getBooleanValue();

    void setBooleanValue(boolean value);

    boolean isEnabled();

    Event onChange();
}
