package nl.paulinternet.gtasaveedit.view.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import nl.paulinternet.gtasaveedit.model.ReportableEvent;

class ActionReporter implements ActionListener
{
	private ReportableEvent event;
	
	public ActionReporter (ReportableEvent event) {
		this.event = event;
	}

	@Override
	public void actionPerformed (ActionEvent e) {
		event.report();
	}
}
