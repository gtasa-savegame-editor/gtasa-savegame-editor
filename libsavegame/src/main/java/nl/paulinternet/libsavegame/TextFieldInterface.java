package nl.paulinternet.libsavegame;

import nl.paulinternet.libsavegame.exceptions.InvalidValueException;

public interface TextFieldInterface {
    String getText();

    void setText(String text) throws InvalidValueException;

    String getDefaultText();

    String getAllowedCharacters();

    boolean isEnabled();
}
