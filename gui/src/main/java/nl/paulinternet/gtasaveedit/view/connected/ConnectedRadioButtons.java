package nl.paulinternet.gtasaveedit.view.connected;

import nl.paulinternet.libsavegame.event.Event;
import nl.paulinternet.libsavegame.event.EventHandler;
import nl.paulinternet.libsavegame.variables.VariableInteger;
import nl.paulinternet.libsavegame.Util;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ConnectedRadioButtons {
    private class Handler implements EventHandler {
        @Override
        public void handleEvent(Event e) {
            Integer value = var.getIntValue();
            boolean enabled = var.isEnabled();

            group.clearSelection();
            for (Button b : buttons) {
                b.updateView(value, enabled);
            }
        }
    }

    private class Button extends JRadioButton implements ActionListener {
        private int value;
        private boolean disabled;

        public Button(int value, String text) {
            super(text);
            this.value = value;
            addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!disabled && isSelected()) {
                var.setIntValue(value);
            }
        }

        public void updateView(Integer value, boolean enabled) {
            disabled = true;
            if (value != null && value == this.value) setSelected(true);
            setEnabled(enabled);
            disabled = false;
        }
    }

    private VariableInteger var;
    private List<Button> buttons;
    private ButtonGroup group;

    public ConnectedRadioButtons(VariableInteger var) {
        this.var = var;
        buttons = new ArrayList<Button>();
        group = new ButtonGroup();
        var.onChange().addHandler(new Handler());
    }

    public JRadioButton create(int value) {
        return create(value, Util.EMPTYSTRING);
    }

    public JRadioButton create(int value, String text) {
        Button button = new Button(value, text);
        buttons.add(button);
        group.add(button);
        button.updateView(var.getIntValue(), var.isEnabled());
        return button;
    }
}
