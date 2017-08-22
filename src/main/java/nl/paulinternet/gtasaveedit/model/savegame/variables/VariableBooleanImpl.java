package nl.paulinternet.gtasaveedit.model.savegame.variables;

import nl.paulinternet.gtasaveedit.model.Event;
import nl.paulinternet.gtasaveedit.model.ReportableEvent;

public class VariableBooleanImpl implements VariableBoolean
{
	private ReportableEvent onChange;
	private boolean enabled;
	private boolean value;
	private boolean hasChanged;
	
	public VariableBooleanImpl () {
		enabled = true;
		onChange = new ReportableEvent();
	}

	public Boolean getBooleanValue () {
		return value;
	}
	
	public void setBooleanValue (boolean value) {
		if (this.value != value) {
			this.value = value;
			hasChanged = true;
			onChange.report();
		}
	}

	public Event onChange () {
		return onChange;
	}

	@Override
	public boolean isEnabled () {
		return enabled;
	}
	
	public void setEnabled (boolean enabled) {
		if (this.enabled != enabled) {
			this.enabled = enabled;
			hasChanged = true;
			onChange.report();
		}
	}
	
	public boolean hasChanged () {
		return hasChanged;
	}
	
	public void resetChangedState () {
		hasChanged = false;
	}
}
