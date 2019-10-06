package nl.paulinternet.gtasaveedit.view.pages;

import nl.paulinternet.gtasaveedit.view.component.LocationChooser;
import nl.paulinternet.gtasaveedit.view.swing.YBox;

import javax.swing.*;

public class PageLocation extends Page {
    public PageLocation() {
        super("Location");

        String message = "<html>You can select here in which savehouse you start.<br />" +
                "It is possible to select a savehouse in an area where you are not allowed to be, " +
                "then you'll get a four star wanted level.</html>";

        YBox ybox = new YBox();
        ybox.add(new JLabel(message));
        ybox.addSpace(10);
        ybox.add(new LocationChooser(), 0, 0.0f, 0.0f);
        ybox.setBorder(10);
        setComponent(ybox, true);
    }
}
