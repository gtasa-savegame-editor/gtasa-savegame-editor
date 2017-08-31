package nl.paulinternet.gtasaveedit.view;

import nl.paulinternet.gtasaveedit.view.window.ExceptionDialog;

import java.awt.AWTEvent;
import java.awt.EventQueue;

public class EventQueueProxy extends EventQueue
{
	@Override
	protected void dispatchEvent (AWTEvent event) {
		try {
			try {
				super.dispatchEvent(event);
			}
			catch (ArrayIndexOutOfBoundsException e) {
				// Ignore exception caused by bug http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6857057
				if (!e.getStackTrace()[0].getClassName().equals("sun.font.FontDesignMetrics")) throw e;
			}
		}
		catch (Throwable t) {
			new ExceptionDialog(t).setVisible(true);
		}
	}
}
