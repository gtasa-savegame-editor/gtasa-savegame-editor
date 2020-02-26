package nl.paulinternet.gtasaveedit.view.component;

import nl.paulinternet.gtasaveedit.view.swing.PButton;
import nl.paulinternet.libsavegame.SavegameVars;

public class FullHealthButton extends PButton {
    private int value;

    public FullHealthButton() {
        SavegameVars.vars.maxHealth.setOnTextChange(s -> updateValue());
        onClick().addHandler(this, "setFullHealth");
    }

    public void updateValue() {
        value = Math.round(SavegameVars.vars.maxHealth.getValue() / 5.69f);
        setText(String.valueOf(value));
    }

    public void setFullHealth() {
        SavegameVars.vars.health.setValue((float) value);
    }
}
