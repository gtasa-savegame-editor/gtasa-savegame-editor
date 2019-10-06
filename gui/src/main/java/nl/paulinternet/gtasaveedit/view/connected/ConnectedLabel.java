package nl.paulinternet.gtasaveedit.view.connected;

import nl.paulinternet.libsavegame.variables.VariableInteger;

import javax.swing.*;

public class ConnectedLabel extends JLabel {
    private VariableInteger var;

    public ConnectedLabel(VariableInteger var) {
        this.var = var;
        var.onChange().addHandler(this, "update");
        update();
    }

    public void update() {
        setText(String.valueOf(var.getIntValue()));
    }
}
