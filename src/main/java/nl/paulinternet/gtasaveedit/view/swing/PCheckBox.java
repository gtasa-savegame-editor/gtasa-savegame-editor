package nl.paulinternet.gtasaveedit.view.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import nl.paulinternet.gtasaveedit.model.event.Event;
import nl.paulinternet.gtasaveedit.model.event.ReportableEvent;

public class PCheckBox extends JCheckBox implements ActionListener
{
	private ReportableEvent onChange;
	
	public PCheckBox () {}
	
	public PCheckBox (String text) {
		super(text);
	}
	
	public Event onChange () {
		if (onChange == null) {
			onChange = new ReportableEvent();
			addActionListener(this);
		}
		return onChange;
	}
	
	@Override
	public void actionPerformed (ActionEvent e) {
		onChange.report();
	}
}
