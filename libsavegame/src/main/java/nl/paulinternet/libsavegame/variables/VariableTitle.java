package nl.paulinternet.libsavegame.variables;

import nl.paulinternet.libsavegame.exceptions.InvalidValueException;

public class VariableTitle extends Variable<String> {
    private String text;
    private boolean hasChanged;

    public VariableTitle() {
        text = "";
    }

    @Override
    public String getAllowedCharacters() {
        return "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz012345789!\"$%&'[]+,-/.:;<=>?\\* ";
    }

    @Override
    public String getDefaultText() {
        return "";
    }

    @Override
    protected boolean validate(String value) {
        return value.length() <= 99; //TODO take allowed chars into account!
    }

    @Override
    public int getMaxLength() {
        return 99;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) throws InvalidValueException {
        if (text == null || text.isEmpty()) throw new InvalidValueException("Title may not be empty!");
        if (!this.text.equals(text)) {
            this.text = text;
            handleChange(text);
            hasChanged = true;
            this.value = getText();
        }
    }

    @Override
    public String getValue() {
        return getText();
    }

    @Override
    public void setValue(String value) throws InvalidValueException {
        setText(value);
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean hasChanged() {
        return hasChanged;
    }

    public void resetChangedState() {
        hasChanged = false;
    }
}
