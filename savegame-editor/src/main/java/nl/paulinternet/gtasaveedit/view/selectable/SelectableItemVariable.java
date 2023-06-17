package nl.paulinternet.gtasaveedit.view.selectable;

import nl.paulinternet.gtasaveedit.event.Event;
import nl.paulinternet.gtasaveedit.event.EventHandler;
import nl.paulinternet.libsavegame.CallbackHandler;
import nl.paulinternet.libsavegame.TextFieldInterface;
import nl.paulinternet.libsavegame.exceptions.InvalidValueException;
import nl.paulinternet.libsavegame.variables.Variable;

import java.util.Objects;

public class SelectableItemVariable<T> extends Variable<T> implements TextFieldInterface {

    private CallbackHandler<Integer> onChange;
    private CallbackHandler<Integer> onDataChange;
    private final Iterable<? extends SelectableItemValue> items;
    private final int parameter;
    private boolean disabled;
    private Integer value;
    private final int min, max;

    public SelectableItemVariable(SelectableItems<? extends SelectableItemValue> items, int parameter, int min, int max) {
        this.items = items.getSelectedItems();
        this.parameter = parameter;
        this.min = min;
        this.max = max;

        Updater updater = new Updater();
        items.onSelectionChange().addHandler(updater);
        items.onDataChange().addHandler(updater);
        updater.handleEvent(null);
    }

    public SelectableItemVariable(SelectableItems<? extends SelectableItemValue> items, int parameter) {
        this(items, parameter, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public void setSelectedValue(Integer value) {
        if (value == null) throw new NullPointerException();
        if (value < min || value > max) throw new InvalidValueException("Value is outside of min or max values!");

        if (this.value == null || !this.value.equals(value)) {
            this.value = value;
            for (SelectableItemValue item : items) {
                item.setValue(parameter, value);
            }
            if (onChange != null) {
                onChange.handle(value);
            }
            if (onDataChange != null) {
                onDataChange.handle(value);
            }
        }
    }

    @Override
    public void setText(String text) throws InvalidValueException {
        if (!text.isEmpty()) {
            try {
                setSelectedValue(Integer.valueOf(text, 10));
            } catch (NumberFormatException e) {
                throw new InvalidValueException("Unable to parse input!", e);
            }
        }
    }

    @Override
    public String getAllowedCharacters() {
        return "-0123456789";
    }

    @Override
    public int getMaxLength() {
        return 0;
    }

    @Override
    public String getText() {
        return String.valueOf(value);
    }

    @Override
    public boolean isEnabled() {
        return !disabled;
    }

    public void setOnDataChange(CallbackHandler<Integer> onDataChange) {
        this.onDataChange = onDataChange;
    }

    private class Updater implements EventHandler {
        @Override
        public void handleEvent(Event e) {
            // Search
            boolean valueFound = false;
            boolean foundDifferent = false;
            int lastValue = 0;

            for (SelectableItemValue item : items) {
                int value = item.getValue(parameter);
                if (!valueFound) {
                    valueFound = true;
                    lastValue = value;
                } else {
                    if (lastValue != value) {
                        foundDifferent = true;
                        break;
                    }
                }
            }

            // Get new values
            Integer newValue = valueFound && !foundDifferent ? lastValue : null;
            boolean newDisabled = !valueFound;

            // Change variables and report change event
            if ((!Objects.equals(value, newValue)) || newDisabled != disabled) {
                value = newValue;
                disabled = newDisabled;
                if (onChange != null) {
                    onChange.handle(value);
                }
            }
        }
    }
}
