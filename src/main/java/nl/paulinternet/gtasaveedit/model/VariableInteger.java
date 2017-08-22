package nl.paulinternet.gtasaveedit.model;

public interface VariableInteger
{
	public Integer getIntValue ();
	public void setIntValue (Integer value);
	public boolean isEnabled ();
	public Event onChange ();
}
