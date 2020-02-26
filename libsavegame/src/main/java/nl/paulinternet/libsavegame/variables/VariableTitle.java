package nl.paulinternet.libsavegame.variables;

import nl.paulinternet.libsavegame.CallbackHandler;
import nl.paulinternet.libsavegame.TextFieldInterface;
import nl.paulinternet.libsavegame.exceptions.InvalidValueException;

public class VariableTitle implements TextFieldInterface {
    private String text;
    private CallbackHandler<String> onChange;
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
    public int getMaximumLength() {
        return 99;
    }

    @Override
    public void setOnTextChange(CallbackHandler<String> onChange) {
        this.onChange = onChange;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) throws InvalidValueException {
        if (text == null) throw new InvalidValueException();
        if (!this.text.equals(text)) {
            this.text = text;
            if(onChange != null) {
                onChange.handle(text);
            }
            hasChanged = true;
        }
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
