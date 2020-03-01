package nl.paulinternet.gtasaveedit.view;

import nl.paulinternet.gtasaveedit.view.window.ExceptionDialog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.MouseEvent;

public class EventQueueProxy extends EventQueue {
    @Override
    protected void dispatchEvent(AWTEvent event) {
        try {
            super.dispatchEvent(event);
        } catch (Throwable t) {
            if (event instanceof MouseEvent && t.getStackTrace()[0].getMethodName().equals("tabForCoordinate")) {
                return; // Hovering over a tab while clicking a menu item
                // (and tabs are updating) triggers this, no visible side effects
                //FIXME if you know how to fix this, do.
            }
            new ExceptionDialog(t).setVisible(true);
        }
    }
}
