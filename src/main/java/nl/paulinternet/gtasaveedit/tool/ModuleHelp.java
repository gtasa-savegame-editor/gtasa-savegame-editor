package nl.paulinternet.gtasaveedit.tool;

public class ModuleHelp implements Module
{
	public String getName () {
		return "help";
	}

	public String getDescription () {
		return "Shows a list of available commands";
	}

	public void execute (String[] args) throws Exception {
		for (Module module : Main.modules) {
			// Create output string
			String output = module.getName();
			while (output.length() < 20) output += " ";
			output += "- ";
			output += module.getDescription();

			// Output
			System.out.println(output);
		}
	}
}