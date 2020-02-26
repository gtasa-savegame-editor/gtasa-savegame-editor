package nl.paulinternet.gtasaveedit.view.connected;

import nl.paulinternet.gtasaveedit.view.swing.PCheckBox;
import nl.paulinternet.libsavegame.variables.Variable;

public class ConnectedCheckbox extends PCheckBox {
    private boolean disabled;
    private Variable<Boolean> var;
    private String text;

    public ConnectedCheckbox(Variable<Boolean> var, String text) {
        this.text = text;
        this.var = var;

        // Observe
        onChange().addHandler(this, "updateToModel");
        var.setOnChange(b -> updateFromModel());
        updateFromModel();
    }

    public void updateFromModel() {
        if (!disabled) {
            disabled = true;

            Boolean value = var.getValue();
            boolean varEnabled = var.isEnabled();
            boolean inbetweenValue;

            if (value == null) {
                setSelected(false);
                inbetweenValue = varEnabled;
            } else {
                setSelected(var.getValue());
                inbetweenValue = false;
            }

            setText(inbetweenValue ? text + " (different values)" : text);
            setEnabled(varEnabled);

            disabled = false;
        }
    }

    public void updateToModel() {
        if (!disabled) {
            disabled = true;
            var.setValue(isSelected());
            setText(text);
            disabled = false;
        }
    }
}
