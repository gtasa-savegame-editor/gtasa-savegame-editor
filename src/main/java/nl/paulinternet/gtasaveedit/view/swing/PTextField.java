package nl.paulinternet.gtasaveedit.view.swing;

import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import nl.paulinternet.gtasaveedit.model.Event;
import nl.paulinternet.gtasaveedit.model.ReportableEvent;

public class PTextField extends JTextField implements DocumentListener, FocusListener
{
	private ReportableEvent onChange;
	private ReportableEvent onFocusLost;
	
	public PTextField () {}
	
	public PTextField (int columns) {
		super(columns);
	}
	
	public PTextField (Document document, String text, int columns) {
		super(document, text, columns);
	}

	public Event onChange () {
		if (onChange == null) {
			onChange = new ReportableEvent();
			getDocument().addDocumentListener(this);
		}
		return onChange;
	}
	
	public Event onFocusLost () {
		if (onFocusLost == null) {
			onFocusLost = new ReportableEvent();
			addFocusListener(this);
		}
		return onFocusLost;
	}
	
	@Override
	public Dimension getMinimumSize () {
		Dimension size = super.getMinimumSize();
		size.width = getPreferredSize().width;
		return size;
	}

	@Override
	public void changedUpdate (DocumentEvent e) {}

	@Override
	public void insertUpdate (DocumentEvent e) {
		onChange.report();
	}

	@Override
	public void removeUpdate (DocumentEvent e) {
		onChange.report();
	}

	@Override
	public void focusGained (FocusEvent e) {}

	@Override
	public void focusLost (FocusEvent e) {
		onFocusLost.report();
	}
}
