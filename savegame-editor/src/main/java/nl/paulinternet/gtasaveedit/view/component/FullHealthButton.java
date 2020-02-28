package nl.paulinternet.gtasaveedit.view.component;

import nl.paulinternet.gtasaveedit.view.swing.PButton;
import nl.paulinternet.libsavegame.variables.Variables;

public class FullHealthButton extends PButton {
    private int value;

    public FullHealthButton() {
        Variables.get().maxHealth.setOnTextChange(s -> updateValue());
        onClick().addHandler(this, "setFullHealth");
    }

    public void updateValue() {
        value = Math.round(Variables.get().maxHealth.getValue() / 5.69f);
        setText(String.valueOf(value));
    }

    public void setFullHealth() {
        Variables.get().health.setValue((float) value);
    }
}
