package nl.paulinternet.gtasaveedit.view.swing;

import nl.paulinternet.libsavegame.event.Event;
import nl.paulinternet.libsavegame.event.ReportableEvent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PCheckBox extends JCheckBox implements ActionListener {
    private ReportableEvent onChange;

    public PCheckBox() {
    }

    public PCheckBox(String text) {
        super(text);
    }

    public Event onChange() {
        if (onChange == null) {
            onChange = new ReportableEvent();
            addActionListener(this);
        }
        return onChange;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        onChange.report();
    }
}
