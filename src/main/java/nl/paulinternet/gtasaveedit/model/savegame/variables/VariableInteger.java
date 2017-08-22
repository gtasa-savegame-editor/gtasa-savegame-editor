package nl.paulinternet.gtasaveedit.model.savegame.variables;

import nl.paulinternet.gtasaveedit.model.Event;

public interface VariableInteger
{
	Integer getIntValue();
	void setIntValue(Integer value);
	boolean isEnabled();
	Event onChange();
}
