package nl.paulinternet.libsavegame.variables;

import nl.paulinternet.libsavegame.event.Event;

public interface VariableInteger {
    Integer getIntValue();

    void setIntValue(Integer value);

    boolean isEnabled();

    Event onChange();
}
