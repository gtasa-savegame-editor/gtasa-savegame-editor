package nl.paulinternet.gtasaveedit.cli.module;

import nl.paulinternet.gtasaveedit.cli.Main;

public class ModuleHelp implements Module {
    public String getName() {
        return "help";
    }

    public String getDescription() {
        return "Shows a list of available commands";
    }

    public void execute(String[] args) throws Exception {
        for (Module module : Main.modules) {
            // Create output string
            StringBuilder output = new StringBuilder(module.getName());
            while (output.length() < 20) output.append(" ");
            output.append("- ");
            output.append(module.getDescription());

            // Output
            System.out.println(output);
        }
    }
}