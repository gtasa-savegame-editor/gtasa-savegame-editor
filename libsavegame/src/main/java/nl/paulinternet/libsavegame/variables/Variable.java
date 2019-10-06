package nl.paulinternet.libsavegame.variables;

import nl.paulinternet.libsavegame.event.Event;
import nl.paulinternet.libsavegame.event.ReportableEvent;
import nl.paulinternet.libsavegame.exceptions.InvalidValueException;

import java.util.Objects;

public class Variable<E> {
    private E value;
    private ReportableEvent onChange = new ReportableEvent();
    private boolean hasChanged;

    public Variable() {
    }

    public Variable(E value) {
        this.value = value;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) throws InvalidValueException {
        if (!Objects.equals(this.value, value)) {
            this.value = value;
            hasChanged = true;
            onChange.report();
        }
    }

    public Event onChange() {
        return onChange;
    }

    public boolean hasChanged() {
        return hasChanged;
    }

    public void resetChangedState() {
        hasChanged = false;
    }
}
