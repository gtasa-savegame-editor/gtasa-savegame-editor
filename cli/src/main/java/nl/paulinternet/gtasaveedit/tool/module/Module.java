package nl.paulinternet.gtasaveedit.tool.module;

public interface Module
{
	String getName();
	String getDescription();
	void execute(String[] args) throws Exception;
}