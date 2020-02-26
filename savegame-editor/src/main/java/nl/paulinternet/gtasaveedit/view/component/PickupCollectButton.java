package nl.paulinternet.gtasaveedit.view.component;

import nl.paulinternet.gtasaveedit.view.selectable.SelectableItems;
import nl.paulinternet.gtasaveedit.view.selectable.SelectablePickup;
import nl.paulinternet.gtasaveedit.view.swing.PButton;
import nl.paulinternet.libsavegame.variables.Variable;

import java.util.List;

public class PickupCollectButton extends PButton {
    private SelectableItems<SelectablePickup> pickups;
    private Variable<Integer> total;

    public PickupCollectButton(String title, SelectableItems<SelectablePickup> pickups, Variable<Integer> total) {
        super(title);
        this.pickups = pickups;
        this.total = total;

        pickups.onSelectionChange().addHandler(this, "onSelectionChange");
        onClick().addHandler(this, "pickupSelectedItems");

        onSelectionChange();
    }

    public void onSelectionChange() {
        setEnabled(pickups.getSelectedItems().getSize() != 0);
    }

    public void pickupSelectedItems() {
        List<SelectablePickup> items = pickups.getItems();
        int add = 0;

        for (int i = 0; i < items.size(); i++) {
            SelectablePickup item = items.get(i);
            if (item.isSelected()) {
                item.getPickup().pickup();
                items.remove(i--);
                add++;
            }
        }

        total.setValue(total.getValue() + add);

        pickups.onDataChange().report();
        pickups.onSelectionChange().report();
    }
}
