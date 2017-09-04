package nl.paulinternet.gtasaveedit.model.variables;

import nl.paulinternet.gtasaveedit.model.event.Event;

public interface VariableBoolean
{
	Boolean getBooleanValue();
	void setBooleanValue(boolean value);
	boolean isEnabled();
	Event onChange();
}
