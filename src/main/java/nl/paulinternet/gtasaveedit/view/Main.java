package nl.paulinternet.gtasaveedit.view;

import javax.swing.SwingUtilities;

public class Main
{
	public static final boolean WINDOWS = System.getProperty("os.name").toLowerCase().startsWith("windows");
	public static final boolean MAC = System.getProperty("os.name").toLowerCase().startsWith("mac");
	
	public static void main (String[] args)
	{
		try {
			// OS X specific
			if (MAC) {
				System.setProperty("apple.laf.useScreenMenuBar", "true");
				System.setProperty("apple.awt.application.name", "GTA SA Savegame Editor");
			}
			
			// Create GUI
			GUICreator guiCreator = new GUICreator();
			SwingUtilities.invokeAndWait(guiCreator);
			SwingUtilities.invokeAndWait(guiCreator);

			// Load images
			Images.loadImages();
		}
		catch (Throwable e) {
			new ExceptionDialog(e).setVisible(true);
		}
	}
}
