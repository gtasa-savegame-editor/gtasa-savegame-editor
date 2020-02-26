package nl.paulinternet.libsavegame.variables;

import nl.paulinternet.libsavegame.CallbackHandler;
import nl.paulinternet.libsavegame.TextFieldInterface;
import nl.paulinternet.libsavegame.exceptions.InvalidValueException;

import java.util.Objects;

public class Variable<E> implements TextFieldInterface {
    private E value;
    private boolean enabled;
    protected CallbackHandler<E> onChange;
    private boolean hasChanged;
    private int maximumLength = 255;

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
            if (onChange != null) {
                onChange.handle(value);
            }
        }
    }

    public boolean hasChanged() {
        return hasChanged;
    }

    public void resetChangedState() {
        hasChanged = false;
    }

    public void setOnChange(CallbackHandler<E> onChange) {
        this.onChange = onChange;
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
    @Deprecated
    public String getAllowedCharacters() {
        return "-0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ.";
    }

    @Override
    public int getMaximumLength() {
        return maximumLength;
    }

    @Override
    @Deprecated
    public void setOnTextChange(CallbackHandler<String> onChange) {

    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setMaximumLength(int maximumLength) {
        this.maximumLength = maximumLength;
    }
}
