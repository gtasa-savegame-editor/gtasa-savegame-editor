package nl.paulinternet.libsavegame.exceptions;

public class ErrorMessageException extends Exception
{
	private String title;
	
	public ErrorMessageException (String title, String message) {
		super(message);
		this.title = title;
	}

	public ErrorMessageException (String title, String message, Throwable cause) {
		super(message, cause);
		this.title = title;
	}
	
	public String getTitle () {
		return title;
	}
}
