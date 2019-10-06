package nl.paulinternet.libsavegame.variables;

import nl.paulinternet.libsavegame.TextFieldInterface;
import nl.paulinternet.libsavegame.exceptions.InvalidValueException;

public class VariableString extends Variable<String> implements TextFieldInterface {
    @Override
    public String getAllowedCharacters() {
        return null;
    }

    @Override
    public String getDefaultText() {
        return "";
    }

    @Override
    public int getMaximumLength() {
        return 0;
    }

    @Override
    public String getText() {
        return getValue();
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void setText(String text) throws InvalidValueException {
        setValue(text);
    }
}
