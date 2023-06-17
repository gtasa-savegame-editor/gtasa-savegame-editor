package nl.paulinternet.gtasaveedit.view.selectable;

import nl.paulinternet.gtasaveedit.event.Event;
import nl.paulinternet.gtasaveedit.event.EventHandler;
import nl.paulinternet.gtasaveedit.event.ReportableEvent;
import nl.paulinternet.libsavegame.TextFieldInterface;
import nl.paulinternet.libsavegame.exceptions.InvalidValueException;
import nl.paulinternet.libsavegame.variables.Variable;

import java.util.Objects;

public class SelectableItemVariable<T> extends Variable<T> implements TextFieldInterface {

    private final ReportableEvent onChange;
    private final ReportableEvent onDataChange;
    private final Iterable<? extends SelectableItemValue> items;
    private final int parameter;
    private boolean disabled;
    private Integer value;
    private final int min, max;

    public SelectableItemVariable(SelectableItems<? extends SelectableItemValue> items, int parameter, int min, int max) {
        this.items = items.getSelectedItems();
        this.parameter = parameter;
        onChange = new ReportableEvent();
        onDataChange = items.onDataChange();
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

    public void setIntValue(Integer value) {
        if (value == null) throw new NullPointerException();
        if (value < min || value > max) throw new InvalidValueException("Value is outside of min or max values!");

        if (this.value == null || !this.value.equals(value)) {
            this.value = value;
            for (SelectableItemValue item : items) {
                item.setValue(parameter, value);
            }
            onChange.report();
            onDataChange.report();
        }
    }

    @Override
    public void setText(String text) throws InvalidValueException {
        if (!text.isEmpty()) {
            try {
                setIntValue(Integer.parseInt(text));
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
    public String getText() {
        return String.valueOf(value);
    }

    @Override
    public boolean isEnabled() {
        return !disabled;
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
                onChange.report();
            }
        }
    }
}
