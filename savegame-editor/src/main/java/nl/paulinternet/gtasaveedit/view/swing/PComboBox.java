package nl.paulinternet.gtasaveedit.view.swing;

import nl.paulinternet.gtasaveedit.event.Event;
import nl.paulinternet.gtasaveedit.event.ReportableEvent;

import javax.swing.*;
import java.awt.*;

/**
 * @param <T> the type of the elements of this combo box
 */
public class PComboBox<T> extends JComboBox<T> {
    private ReportableEvent onChange;

    public PComboBox() {
    }

    public Event onChange() {
        if (onChange == null) {
            onChange = new ReportableEvent();
            addActionListener(new ActionReporter(onChange));
        }
        return onChange;
    }

    @Override
    public Dimension getMinimumSize() {
        if ("Windows".equals(UIManager.getLookAndFeel().getID())) {
            Dimension size = super.getMinimumSize();
            size.width += 15;
            return size;
        } else {
            return super.getMinimumSize();
        }
    }
}
