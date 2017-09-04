package nl.paulinternet.gtasaveedit.view.selectable;

import nl.paulinternet.gtasaveedit.model.event.Event;
import nl.paulinternet.gtasaveedit.model.event.EventHandler;
import nl.paulinternet.gtasaveedit.model.exceptions.InvalidValueException;
import nl.paulinternet.gtasaveedit.model.event.ReportableEvent;
import nl.paulinternet.gtasaveedit.model.TextFieldInterface;
import nl.paulinternet.gtasaveedit.model.variables.VariableBoolean;
import nl.paulinternet.gtasaveedit.model.variables.VariableInteger;

public class SelectableItemVariable implements TextFieldInterface, VariableBoolean, VariableInteger
{
	private class Updater implements EventHandler
	{
		@Override
		public void handleEvent (Event e) {
			// Search
			boolean valueFound = false;
			boolean foundDifferent = false;
			int lastValue = 0;
			
			for (SelectableItemValue item : items) {
				int value = item.getValue(parameter);
				if (!valueFound) {
					valueFound = true;
					lastValue = value;
				}
				else {
					if (lastValue != value) {
						foundDifferent = true;
						break;
					}
				}
			}
			
			// Get new values
			Integer newValue = valueFound && !foundDifferent ? lastValue : null;
			boolean newDisabled = !valueFound;
			
			// Change variables and report change event
			if ((value == null ? newValue != null : !value.equals(newValue)) || newDisabled != disabled) {
				value = newValue;
				disabled = newDisabled;
				onChange.report();
			}
		}
	}
	
	private ReportableEvent onChange;
	private ReportableEvent onDataChange;
	private Iterable<? extends SelectableItemValue> items;
	private int parameter;
	private boolean disabled;
	private Integer value;
	private int min, max;
	
	public SelectableItemVariable (SelectableItems<? extends SelectableItemValue> items, int parameter, int min, int max) {
		this.items = items.getSelectedItems();
		this.parameter = parameter;
		onChange = new ReportableEvent();
		onDataChange = items.onDataChange();
		this.min = min;
		this.max = max;
		
		Updater updater = new Updater();
		items.onSelectionChange().addHandler(updater);
		items.onDataChange().addHandler(updater);
		updater.handleEvent(null);
	}
	
	public SelectableItemVariable (SelectableItems<? extends SelectableItemValue> items, int parameter) {
		this(items, parameter, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	public Integer getIntValue () {
		return value;
	}
	
	public void setIntValue (Integer value) {
		if (value == null) throw new NullPointerException();
		if (value < min || value > max) throw new InvalidValueException();
		
		if (this.value == null || this.value != value) {
			this.value = value;
			for (SelectableItemValue item : items) {
				item.setValue(parameter, value);
			}
			onChange.report();
			onDataChange.report();
		}
	}

	@Override
	public void setText (String text) throws InvalidValueException {
		if (!text.isEmpty()) {
			try {
				setIntValue(Integer.parseInt(text));
			}
			catch (NumberFormatException e) {
				throw new InvalidValueException();
			}
		}
	}

	@Override
	public String getAllowedCharacters () {
		return "-0123456789";
	}

	@Override
	public String getDefaultText () {
		return "";
	}

	@Override
	public int getMaximumLength () {
		return 0;
	}

	@Override
	public String getText () {
		return String.valueOf(value);
	}

	@Override
	public Event onChange () {
		return onChange;
	}

	@Override
	public Boolean getBooleanValue () {
		return value == null ? null : value != 0;
	}

	@Override
	public void setBooleanValue (boolean value) {
		setIntValue(value ? 1 : 0);		
	}

	@Override
	public boolean isEnabled () {
		return !disabled;
	}
}
