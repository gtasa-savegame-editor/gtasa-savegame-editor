package nl.paulinternet.gtasaveedit.model;

import nl.paulinternet.gtasaveedit.model.exceptions.InvalidValueException;

public interface TextFieldInterface
{
	String getText();
	void setText(String text) throws InvalidValueException;
	String getDefaultText();
	String getAllowedCharacters();
	int getMaximumLength();
	Event onChange();
	boolean isEnabled();
}
