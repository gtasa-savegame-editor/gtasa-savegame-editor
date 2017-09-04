package nl.paulinternet.gtasaveedit.model.variables;

import nl.paulinternet.gtasaveedit.model.event.Event;
import nl.paulinternet.gtasaveedit.model.event.ReportableEvent;
import nl.paulinternet.gtasaveedit.model.TextFieldInterface;
import nl.paulinternet.gtasaveedit.model.exceptions.InvalidValueException;

public class VariableIntegerImpl implements TextFieldInterface, VariableInteger
{
	private int min, max;
	private int value;
	private boolean hasChanged;
	private boolean enabled;
	protected ReportableEvent onChange;

	public VariableIntegerImpl (int min, int max) {
		this.min = min;
		this.max = max;
		onChange = new ReportableEvent();
		enabled = true;
	}
	
	public VariableIntegerImpl () {
		this(Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	public Integer getIntValue () {
		return value;
	}

	public void setIntValue (Integer value) {
		if (value == null) throw new NullPointerException();
		if (value < min || value > max) throw new InvalidValueException();
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
			setIntValue(Integer.parseInt(text));
		}
		catch (NumberFormatException e) {
			throw new InvalidValueException();
		}
	}

	@Override
	public String getAllowedCharacters () {
		return "-0123456789";
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
		return enabled;
	}
	
	public boolean hasChanged () {
		return hasChanged;
	}
	
	public void resetChangedState () {
		hasChanged = false;
	}
	
	public void setEnabled (boolean enabled) {
		if (this.enabled != enabled) {
			this.enabled = enabled;
			onChange.report();
		}
	}
}
