package nl.paulinternet.gtasaveedit.view.component;

import nl.paulinternet.libsavegame.SavegameModel;
import nl.paulinternet.gtasaveedit.view.swing.PButton;

public class FullHealthButton extends PButton {
    private int value;

    public FullHealthButton() {
        SavegameModel.vars.maxHealth.onChange().addHandler(this, "updateValue");
        onClick().addHandler(this, "setFullHealth");
    }

    public void updateValue() {
        value = Math.round(SavegameModel.vars.maxHealth.getValue() / 5.69f);
        setText(String.valueOf(value));
    }

    public void setFullHealth() {
        SavegameModel.vars.health.setValue((float) value);
    }
}
