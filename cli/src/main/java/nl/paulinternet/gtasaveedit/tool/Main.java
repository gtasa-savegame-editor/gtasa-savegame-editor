package nl.paulinternet.gtasaveedit.tool;

import nl.paulinternet.gtasaveedit.tool.module.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static final Module[] modules = new Module[]{
            new ModuleHelp(),
            new ModuleCopy(),
            new ModuleList(),
            new ModuleOpenWorkDir(),
            new ModuleOpenSavegameDir(),
            new ModuleExit()
    };

    public static void main(String args[]) throws Exception {
        // Get the input
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        // Loop
        while (true) {
            // Read a line
            System.out.print("> ");
            String line = in.readLine();
            if (line == null) break;

            // Split the line
            String[] command = line.split(" ");

            // Find the module
            Module module = null;
            for (Module m : modules) {
                if (command[0].equals(m.getName())) module = m;
            }

            // Execute
            if (module != null) {
                try {
                    module.execute(command);
                } catch (ErrorMessage e) {
                    System.out.println(e.getMessage());
                } catch (Throwable e) {
                    System.out.println("Module " + command[0] + " caused an exception:");
                    e.printStackTrace();
                    Thread.sleep(100);
                }
            } else {
                System.out.println("Unknown command");
            }

            // Newline
            System.out.println();
        }
    }
}