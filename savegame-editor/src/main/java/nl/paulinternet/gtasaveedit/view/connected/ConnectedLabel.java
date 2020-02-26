package nl.paulinternet.gtasaveedit.view.connected;

import nl.paulinternet.libsavegame.variables.Variable;

import javax.swing.*;

public class ConnectedLabel extends JLabel {

    public ConnectedLabel(Variable<Integer> var) {
        var.setOnChange(this::update);
        update(var.getValue());
    }

    public void update(Integer value) {
        setText(String.valueOf(value));
    }
}
