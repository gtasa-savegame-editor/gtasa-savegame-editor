package nl.paulinternet.gtasaveedit.tool;

public interface Module
{
	public String getName ();
	public String getDescription ();
	public void execute (String[] args) throws Exception;
}