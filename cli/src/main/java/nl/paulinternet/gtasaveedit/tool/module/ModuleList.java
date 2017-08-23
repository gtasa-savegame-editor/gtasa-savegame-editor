package nl.paulinternet.gtasaveedit.tool.module;

import nl.paulinternet.gtasaveedit.tool.Dir;

import java.io.File;

public class ModuleList implements Module
{
	@Override
	public void execute (String[] args) throws Exception {
		for (File file : new File(Dir.get().RESEARCH_PATH).listFiles()) {
			if (file.isDirectory() && new File(file, "00.block").exists()) {
				System.out.println(file.getName());
			}
		}
	}

	@Override
	public String getDescription () {
		return "Shows the directories with extracted savegames";
	}

	@Override
	public String getName () {
		return "ls";
	}
}
