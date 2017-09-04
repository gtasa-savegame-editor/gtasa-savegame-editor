package nl.paulinternet.gtasaveedit.view.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import nl.paulinternet.gtasaveedit.model.event.Event;
import nl.paulinternet.gtasaveedit.model.event.ReportableEvent;

public class PMenuItem extends JMenuItem implements ActionListener
{
	private ReportableEvent onClick;
	
	public PMenuItem () {}
	
	public PMenuItem (String title) {
		super(title);
	}
	
	public Event onClick () {
		if (onClick == null) {
			onClick = new ReportableEvent();
			addActionListener(this);
		}
		return onClick;
	}
	
	@Override
	public void actionPerformed (ActionEvent e) {
		onClick.report();
	}
}
