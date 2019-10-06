package nl.paulinternet.gtasaveedit.view.component;

import nl.paulinternet.gtasaveedit.view.selectable.SelectableItemVariable;
import nl.paulinternet.gtasaveedit.view.swing.PComboBox;

import java.util.ArrayList;
import java.util.List;

public class PopcycleComboBox extends PComboBox {
    private SelectableItemVariable var;
    private List<Integer> values;
    private boolean disabled;

    public PopcycleComboBox(SelectableItemVariable var) {
        this.var = var;
        values = new ArrayList<Integer>();

        // Add items
        addItem(0x10, "Airport");
        addItem(0x13, "Airport runway");
        addItem(0x08, "Beach");
        addItem(0x00, "Business");
        addItem(0x03, "Countryside");
        addItem(0x01, "Desert");
        addItem(0x02, "Entertainment");
        addItem(0x0c, "Entertainment busy");
        addItem(0x07, "Gangland");
        addItem(0x11, "Golf club");
        addItem(0x0b, "Industry");
        addItem(0x12, "Out of town factory");
        addItem(0x0a, "Park");
        addItem(0x06, "Residential poor");
        addItem(0x05, "Residential average");
        addItem(0x04, "Residential rich");
        addItem(0x0f, "Residential rich secluded");
        addItem(0x09, "Shopping");
        addItem(0x0d, "Shopping busy");
        addItem(0x0e, "Shopping posh");

        // Observe
        var.onChange().addHandler(this, "updateView");
        onChange().addHandler(this, "updateModel");
        updateView();
    }

    public void updateView() {
        if (disabled) return;
        disabled = true;

        setEnabled(var.isEnabled());

        Integer value = var.getIntValue();
        if (value != null) {
            int i;
            for (i = 0; i < values.size(); i++) {
                if (value == values.get(i)) {
                    setSelectedIndex(i);
                    break;
                }
            }
            if (i == values.size()) setSelectedIndex(-1);
        } else {
            setSelectedIndex(-1);
        }

        disabled = false;
    }

    @SuppressWarnings("unused") // used in event
    public void updateModel() {
        if (disabled) return;
        disabled = true;
        var.setIntValue(values.get(getSelectedIndex()));
        disabled = false;
    }

    private void addItem(int value, String name) {
        super.addItem(name);
        values.add(value);
    }
}
