package nl.paulinternet.libsavegame.variables;

import nl.paulinternet.libsavegame.CallbackHandler;
import nl.paulinternet.libsavegame.TextFieldInterface;
import nl.paulinternet.libsavegame.exceptions.InvalidValueException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Variable<E> implements TextFieldInterface {
    protected E value;
    private boolean enabled = true;
    protected List<CallbackHandler<E>> onChange;
    private boolean hasChanged;

    public Variable() {
    }

    public Variable(E value) {
        this.value = value;
    }

    public E getValue() {
        return value;
    }

    /**
     * Setter for the value that also calls the callbacks.
     * If you override this method, be sure to call
     * {@link #handleChange(E)} in case there was an update!
     *
     * @param value the new value of this variable
     * @throws InvalidValueException if the value is invalid
     */
    public void setValue(E value) throws InvalidValueException {
        if (!Objects.equals(this.value, value) && validate(value)) {
            this.value = value;
            handleChange(value);
        }
    }

    protected boolean validate(E value) {
        return true; // default
    }

    protected void handleChange(E value) {
        hasChanged = true;
        if (onChange != null && !onChange.isEmpty()) {
            onChange.forEach(h -> h.handle(value));
        }
    }

    public boolean hasChanged() {
        return hasChanged;
    }

    public void resetChangedState() {
        hasChanged = false;
    }

    public void addOnChangeListener(CallbackHandler<E> handler) {
        if (this.onChange == null) {
            this.onChange = new ArrayList<>();
        }
        this.onChange.add(handler);
    }

    @Override
    public String getText() {
        return String.valueOf(value);
    }

    @Override
    public void setText(String text) throws InvalidValueException {
        if (value instanceof String) {
            //noinspection unchecked it's checked, check again
            value = (E) text;
        } else if (value instanceof Integer) {
            //noinspection unchecked it's checked, check again
            value = (E) Integer.valueOf(Integer.parseInt(text));
        } else if (value instanceof Float) {
            //noinspection unchecked it's checked, check again
            value = (E) Float.valueOf(Float.parseFloat(text));
        } else {
            throw new RuntimeException("Not implemented!");
        }
    }

    @Override
    public String getDefaultText() {
        return "";
    }

    @Override
    public String getAllowedCharacters() {
        return "-0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ.";
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getMaxLength() {
        return 255; // default value
    }
}
