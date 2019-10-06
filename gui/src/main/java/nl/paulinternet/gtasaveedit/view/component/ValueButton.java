package nl.paulinternet.gtasaveedit.view.component;

import nl.paulinternet.libsavegame.TextFieldInterface;
import nl.paulinternet.libsavegame.event.Event;
import nl.paulinternet.libsavegame.event.EventHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ValueButton extends JButton implements ActionListener, EventHandler {
    private TextFieldInterface field;
    private String value;

    public ValueButton(TextFieldInterface field, String value, String text) {
        super(text);
        this.field = field;
        this.value = value;
        addActionListener(this);
        field.onChange().addHandler(this);
        handleEvent((Event) null);
    }

    public ValueButton(TextFieldInterface field, String value) {
        this(field, value, value);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        field.setText(value);
    }

    @Override
    public void handleEvent(Event e) {
        setEnabled(field.isEnabled());
    }
}
