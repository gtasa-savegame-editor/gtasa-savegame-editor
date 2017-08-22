package nl.paulinternet.gtasaveedit.model.savegame.variables;

import nl.paulinternet.gtasaveedit.model.Event;
import nl.paulinternet.gtasaveedit.model.ReportableEvent;
import nl.paulinternet.gtasaveedit.model.TextFieldInterface;
import nl.paulinternet.gtasaveedit.model.exceptions.InvalidValueException;

public class VariableFloat implements TextFieldInterface
{
	private float value;
	private ReportableEvent onChange;
	private boolean hasChanged;

	public VariableFloat () {
		onChange = new ReportableEvent();
	}

	public float getValue () {
		return value;
	}

	public void setValue (float value) {
		if (value != this.value) {
			this.value = value;
			hasChanged = true;
			onChange.report();
		}
	}

	@Override
	public String getText () {
		return String.valueOf(value);
	}

	@Override
	public void setText (String text) throws InvalidValueException {
		try {
			setValue(Float.parseFloat(text));
		}
		catch (NumberFormatException e) {
			throw new InvalidValueException();
		}
	}

	@Override
	public String getAllowedCharacters () {
		return "-0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ.";
	}

	@Override
	public String getDefaultText () {
		return "0";
	}

	@Override
	public int getMaximumLength () {
		return 0;
	}

	@Override
	public Event onChange () {
		return onChange;
	}

	@Override
	public boolean isEnabled () {
		return true;
	}
	
	public boolean hasChanged () {
		return hasChanged;
	}
	
	public void resetChangedState () {
		hasChanged = false;
	}
}
