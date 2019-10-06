package nl.paulinternet.gtasaveedit.view.connected;

import nl.paulinternet.libsavegame.TextFieldInterface;
import nl.paulinternet.libsavegame.exceptions.InvalidValueException;
import nl.paulinternet.gtasaveedit.view.swing.PTextField;

import java.awt.*;

public class ConnectedTextField extends PTextField {
    private static final Color COLOR_ERROR = new Color(255, 192, 192);

    private TextFieldInterface model;
    private boolean error;
    private boolean disabled;

    public ConnectedTextField(TextFieldInterface model, int columns) {
        super(new ConnectedTextFieldDocument(model), model.getText(), columns);
        this.model = model;

        // Observe
        model.onChange().addHandler(this, "updateFromModel");
        onFocusLost().addHandler(this, "updateFromModel");
        onChange().addHandler(this, "updateToModel");

        updateFromModel();
    }

    public ConnectedTextField(TextFieldInterface model) {
        this(model, 10);
    }

    public Color getBackground() {
        return error ? COLOR_ERROR : super.getBackground();
    }

    public void updateFromModel() {
        if (disabled) return;
        disabled = true;

        setText(model.getText());
        setEnabled(model.isEnabled());
        error = false;

        disabled = false;
    }

    public void updateToModel() {
        if (disabled) return;
        disabled = true;

        try {
            model.setText(getText());
            error = false;
        } catch (InvalidValueException e) {
            if (getText().isEmpty()) {
                model.setText(model.getDefaultText());
                error = false;
            } else {
                error = true;
            }
        }

        disabled = false;
    }
}
