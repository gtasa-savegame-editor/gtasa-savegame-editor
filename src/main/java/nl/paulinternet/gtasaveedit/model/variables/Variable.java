package nl.paulinternet.gtasaveedit.model.variables;

import nl.paulinternet.gtasaveedit.model.event.Event;
import nl.paulinternet.gtasaveedit.model.event.ReportableEvent;
import nl.paulinternet.gtasaveedit.model.exceptions.InvalidValueException;

public class Variable<E>
{
	private E value;
	private ReportableEvent onChange = new ReportableEvent();
	private boolean hasChanged;
	
	public Variable () {}
	
	public Variable (E value) {
		this.value = value;
	}

	public E getValue () {
		return value;
	}
	
	public void setValue (E value) throws InvalidValueException {
		if (this.value == null ? value != null : !this.value.equals(value)) {
			this.value = value;
			hasChanged = true;
			onChange.report();
		}
	}
	
	public Event onChange () {
		return onChange;
	}
	
	public boolean hasChanged () {
		return hasChanged;
	}
	
	public void resetChangedState () {
		hasChanged = false;
	}
}
