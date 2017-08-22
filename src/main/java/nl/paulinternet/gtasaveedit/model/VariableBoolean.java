package nl.paulinternet.gtasaveedit.model;

public interface VariableBoolean
{
	public Boolean getBooleanValue ();
	public void setBooleanValue (boolean value);
	public boolean isEnabled ();
	public Event onChange ();
}
