package nl.paulinternet.gtasaveedit.tool;

public class ErrorMessage extends Exception
{
  /**
   * Constructor
   */
  public ErrorMessage (String message) {
    super(message);
    if (message == null) throw new NullPointerException();
  }
}