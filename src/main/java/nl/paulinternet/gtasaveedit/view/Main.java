package nl.paulinternet.gtasaveedit.view;

import com.apple.eawt.Application;
import nl.paulinternet.gtasaveedit.view.pages.PageAbout;

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

			Application.getApplication().setAboutHandler(aboutEvent -> {
				PageAbout aboutPage = new PageAbout();
				aboutPage.setVisible(true);
			});
			
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
