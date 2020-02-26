package nl.paulinternet.gtasaveedit.view.swing;

import nl.paulinternet.gtasaveedit.event.ReportableEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ActionReporter implements ActionListener {
    private ReportableEvent event;

    public ActionReporter(ReportableEvent event) {
        this.event = event;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        event.report();
    }
}
