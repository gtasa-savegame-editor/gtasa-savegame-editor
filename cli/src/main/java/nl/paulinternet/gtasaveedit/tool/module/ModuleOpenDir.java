package nl.paulinternet.gtasaveedit.tool.module;

import nl.paulinternet.gtasaveedit.tool.Dir;

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
		Runtime.getRuntime().exec(new String[] {"explorer", new File(Dir.get().RESEARCH_PATH).getCanonicalPath()});
	}
}