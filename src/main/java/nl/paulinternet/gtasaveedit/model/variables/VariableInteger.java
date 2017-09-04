package nl.paulinternet.gtasaveedit.model.variables;

import nl.paulinternet.gtasaveedit.model.event.Event;

public interface VariableInteger
{
	Integer getIntValue();
	void setIntValue(Integer value);
	boolean isEnabled();
	Event onChange();
}
