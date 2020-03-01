package nl.paulinternet.gtasaveedit.view.connected;

import nl.paulinternet.gtasaveedit.event.Event;
import nl.paulinternet.gtasaveedit.event.EventHandler;
import nl.paulinternet.gtasaveedit.view.swing.PComboBox;
import nl.paulinternet.libsavegame.variables.Variable;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @param <T> the type of the elements of this combo box
 */
public class ConnectedComboBox<T> extends PComboBox<T> {
    private class Handler implements ItemListener, EventHandler {
        private boolean disabled;

        @Override
        public void handleEvent(Event e) {
            if (!disabled) {
                int index = -1;
                int value = var.getValue();
                for (int i = 0; i < values.size(); i++) {
                    if (value == values.get(i)) {
                        index = i;
                        break;
                    }
                }
                disabled = true;
                setSelectedIndex(index);
                disabled = false;
            }
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (!disabled) {
                int index = getSelectedIndex();
                if (index >= 0 && index < values.size()) {
                    disabled = true;
                    var.setValue(values.get(index));
                    disabled = false;
                }
            }
        }
    }

    protected Variable<Integer> var;
    private List<Integer> values;

    public ConnectedComboBox(Variable<Integer> var) {
        this.var = var;
        values = new ArrayList<>();

        // Observe
        Handler h = new Handler();
        addItemListener(h);

        var.addOnChangeListener(anIndex -> {
            for (int i = 0; i < getItemCount(); i++) {
                if(anIndex.equals(values.get(i))) {
                    setSelectedIndex(i);
                }
            }
        });
    }

    public void addItem(int value, T name) {
        super.addItem(name);
        values.add(value);
    }
}
