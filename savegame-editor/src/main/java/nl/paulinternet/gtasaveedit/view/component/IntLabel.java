package nl.paulinternet.gtasaveedit.view.component;

import nl.paulinternet.libsavegame.variables.Variable;

import javax.swing.*;

public class IntLabel extends JLabel {
    private String text;
    private Variable<Integer> var;

    public IntLabel(String text, Variable<Integer> var) {
        this.text = text;
        this.var = var;
        updateText();
        var.addOnChangeListener(i -> updateText());
    }

    public void updateText() {
        setText(text.replace("?", String.valueOf(var.getValue())));
    }
}
