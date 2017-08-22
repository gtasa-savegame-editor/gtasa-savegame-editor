package nl.paulinternet.gtasaveedit.view;

import java.awt.Toolkit;

import javax.swing.UIManager;

import nl.paulinternet.gtasaveedit.model.Settings;

class GUICreator implements Runnable
{
	private boolean secondTime;
	
	public void run () {
		if (!secondTime) {
			// Set eventqueue to display errors
			Toolkit.getDefaultToolkit().getSystemEventQueue().push(new EventQueueProxy());

			// Set look and feel
			try {
				UIManager.setLookAndFeel(Settings.getLookAndFeelClassName());
			}
			catch (Throwable ignored) {}
			
			// Create a window
			Window.instance.setVisible(true);
			
			secondTime = true;
		}
		else {
			Window.instance.createContent();
		}
	}
}
