package nl.paulinternet.gtasaveedit.model.savegame.variables;

import nl.paulinternet.gtasaveedit.model.Event;

public interface VariableBoolean
{
	Boolean getBooleanValue();
	void setBooleanValue(boolean value);
	boolean isEnabled();
	Event onChange();
}
