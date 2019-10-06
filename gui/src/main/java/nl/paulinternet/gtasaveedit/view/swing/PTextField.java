package nl.paulinternet.gtasaveedit.view.swing;

import nl.paulinternet.libsavegame.event.Event;
import nl.paulinternet.libsavegame.event.ReportableEvent;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class PTextField extends JTextField implements DocumentListener, FocusListener {
    private ReportableEvent onChange;
    private ReportableEvent onFocusLost;

    public PTextField() {
    }

    public PTextField(int columns) {
        super(columns);
    }

    public PTextField(Document document, String text, int columns) {
        super(document, text, columns);
    }

    public Event onChange() {
        if (onChange == null) {
            onChange = new ReportableEvent();
            getDocument().addDocumentListener(this);
        }
        return onChange;
    }

    public Event onFocusLost() {
        if (onFocusLost == null) {
            onFocusLost = new ReportableEvent();
            addFocusListener(this);
        }
        return onFocusLost;
    }

    @Override
    public Dimension getMinimumSize() {
        Dimension size = super.getMinimumSize();
        size.width = getPreferredSize().width;
        return size;
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        onChange.report();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        onChange.report();
    }

    @Override
    public void focusGained(FocusEvent e) {
    }

    @Override
    public void focusLost(FocusEvent e) {
        onFocusLost.report();
    }
}
