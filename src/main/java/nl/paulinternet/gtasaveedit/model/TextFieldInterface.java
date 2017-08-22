package nl.paulinternet.gtasaveedit.model;

public interface TextFieldInterface
{
	public String getText ();
	public void setText (String text) throws InvalidValueException;
	public String getDefaultText ();
	public String getAllowedCharacters ();
	public int getMaximumLength ();
	public Event onChange ();
	public boolean isEnabled ();
}
