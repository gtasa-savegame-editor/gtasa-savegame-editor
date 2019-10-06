package nl.paulinternet.gtasaveedit.view.component;

import nl.paulinternet.libsavegame.variables.VariableIntegerImpl;

import javax.swing.*;

public class IntLabel extends JLabel {
    private String text;
    private VariableIntegerImpl var;

    public IntLabel(String text, VariableIntegerImpl var) {
        this.text = text;
        this.var = var;
        var.onChange().addHandler(this, "updateText");
        updateText();
    }

    public void updateText() {
        setText(text.replace("?", var.getText()));
    }
}
