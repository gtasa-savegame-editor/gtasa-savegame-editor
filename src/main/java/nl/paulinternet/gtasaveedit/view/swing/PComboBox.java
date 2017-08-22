package nl.paulinternet.gtasaveedit.view.swing;

import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.UIManager;

import nl.paulinternet.gtasaveedit.model.Event;
import nl.paulinternet.gtasaveedit.model.ReportableEvent;

public class PComboBox extends JComboBox
{
	private ReportableEvent onChange;	
	
	public PComboBox () {}
	
	public Event onChange () {
		if (onChange == null) {
			onChange = new ReportableEvent();
			addActionListener(new ActionReporter(onChange));
		}
		return onChange;
	}
	
	@Override
	public Dimension getMinimumSize () {
		if (UIManager.getLookAndFeel().getID().equals("Windows")) {
			Dimension size = super.getMinimumSize();
			size.width += 15;
			return size;
		}
		else {
			return super.getMinimumSize();
		}
	}
}
