package nl.paulinternet.gtasaveedit.tool;

import java.io.File;

public class ModuleOpenDir implements Module
{
	public String getName () {
		return "opendir";
	}

	public String getDescription () {
		return "Opens the research directory (Windows only)";
	}

	public void execute (String[] args) throws Exception {
		Runtime.getRuntime().exec(new String[] {"explorer", new File(Dir.RESEARCH_PATH).getCanonicalPath()});
	}
}