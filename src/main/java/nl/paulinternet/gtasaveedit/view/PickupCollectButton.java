package nl.paulinternet.gtasaveedit.view;

import java.util.List;

import nl.paulinternet.gtasaveedit.model.savegame.variables.VariableIntegerImpl;
import nl.paulinternet.gtasaveedit.view.swing.PButton;

public class PickupCollectButton extends PButton
{
	private SelectableItems<SelectablePickup> pickups;
	private VariableIntegerImpl total;
	
	public PickupCollectButton (String title, SelectableItems<SelectablePickup> pickups, VariableIntegerImpl total) {
		super(title);
		this.pickups = pickups;
		this.total = total;
		
		pickups.onSelectionChange().addHandler(this, "onSelectionChange");
		onClick().addHandler(this, "pickupSelectedItems");
		
		onSelectionChange();
	}

	public void onSelectionChange () {
		setEnabled(pickups.getSelectedItems().getSize() != 0);
	}

	public void pickupSelectedItems () {
		List<SelectablePickup> items = pickups.getItems();
		int add = 0;
		
		for (int i=0; i<items.size(); i++) {
			SelectablePickup item = items.get(i);
			if (item.isSelected()) {
				item.getPickup().pickup();
				items.remove(i--);
				add++;
			}
		}
		
		total.setIntValue(total.getIntValue() + add);
		
		pickups.onDataChange().report();
		pickups.onSelectionChange().report();
	}
}
