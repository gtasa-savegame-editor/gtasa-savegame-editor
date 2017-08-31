package nl.paulinternet.gtasaveedit.view.pages;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import nl.paulinternet.gtasaveedit.model.Model;
import nl.paulinternet.gtasaveedit.model.Pickup;
import nl.paulinternet.gtasaveedit.view.component.IntLabel;
import nl.paulinternet.gtasaveedit.view.MapImage;
import nl.paulinternet.gtasaveedit.view.component.PickupCollectButton;
import nl.paulinternet.gtasaveedit.view.selectable.SelectableItemComponent;
import nl.paulinternet.gtasaveedit.view.selectable.SelectableItems;
import nl.paulinternet.gtasaveedit.view.selectable.SelectablePickup;
import nl.paulinternet.gtasaveedit.view.selectable.SelectionSizeLabel;
import nl.paulinternet.gtasaveedit.view.swing.Alignment;
import nl.paulinternet.gtasaveedit.view.swing.XBox;
import nl.paulinternet.gtasaveedit.view.swing.YBox;

public class CollectablePageOysters extends Page
{
	private SelectableItems<SelectablePickup> items;

	public CollectablePageOysters () {
		super("Oysters");
		
		List<SelectablePickup> oysters = new ArrayList<SelectablePickup>();
		
		items = new SelectableItems<SelectablePickup>(oysters);
		SelectableItemComponent select = new SelectableItemComponent(MapImage.SAN_ANDREAS, items, SelectableItemComponent.MULTIPLE);
		
		XBox xbox = new XBox();
		xbox.add(new IntLabel("? / 50 oysters collected", Model.vars.oystersCollected));
		xbox.addSpace(10, 1);
		xbox.add(new SelectionSizeLabel(items, "oysters"));
		
		YBox ybox = new YBox();
		ybox.add(new JLabel("<html>Click or drag the mouse to select oysters.<br />Hold shift or alt to respectively grow or shrink the selection.<br>Only the oysters that you didn't collect are displayed."));
		ybox.addSpace(10);
		ybox.add(new PickupCollectButton("Collect selected oysters", items, Model.vars.oystersCollected), 0, 0.5f, 0.0f);
		ybox.addSpace(5);
		ybox.add(select);
		ybox.addSpace(5);
		ybox.add(xbox);
		
		Alignment alignment = new Alignment(ybox, 0.0f, 0.0f);
		alignment.setBorder(10);
		setComponent(alignment, true);
		
		Model.gameLoaded.addHandler(this, "onGameLoaded");
	}
	
	public void onGameLoaded () {
		List<SelectablePickup> oysters = items.getItems();
		
		oysters.clear();
		for (Pickup oyster : Model.vars.oysters) {
			oysters.add(new SelectablePickup(oyster, MapImage.SAN_ANDREAS));
		}
		
		items.onSelectionChange().report();
		items.onDataChange().report();
	}
}
