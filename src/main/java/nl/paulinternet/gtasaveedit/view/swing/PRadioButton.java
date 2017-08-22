package nl.paulinternet.gtasaveedit.view.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

import nl.paulinternet.gtasaveedit.model.Event;
import nl.paulinternet.gtasaveedit.model.ReportableEvent;

public class PRadioButton extends JRadioButton implements ActionListener
{
	private ReportableEvent onClick;
	
	public PRadioButton () {}
	
	public PRadioButton (String title) {
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
