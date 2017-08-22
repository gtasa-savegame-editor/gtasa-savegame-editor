package nl.paulinternet.gtasaveedit.view.pages;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import nl.paulinternet.gtasaveedit.model.Model;
import nl.paulinternet.gtasaveedit.model.Pickup;
import nl.paulinternet.gtasaveedit.view.IntLabel;
import nl.paulinternet.gtasaveedit.view.MapImage;
import nl.paulinternet.gtasaveedit.view.PickupCollectButton;
import nl.paulinternet.gtasaveedit.view.SelectableItemComponent;
import nl.paulinternet.gtasaveedit.view.SelectableItems;
import nl.paulinternet.gtasaveedit.view.SelectablePickup;
import nl.paulinternet.gtasaveedit.view.SelectionSizeLabel;
import nl.paulinternet.gtasaveedit.view.swing.Alignment;
import nl.paulinternet.gtasaveedit.view.swing.XBox;
import nl.paulinternet.gtasaveedit.view.swing.YBox;

public class CollectablePageSnapshots extends Page
{
	private SelectableItems<SelectablePickup> items;

	public CollectablePageSnapshots () {
		super("Snapshots");
		
		List<SelectablePickup> snapshots = new ArrayList<SelectablePickup>();

		items = new SelectableItems<SelectablePickup>(snapshots);
		SelectableItemComponent select = new SelectableItemComponent(MapImage.SAN_FIERRO, items, SelectableItemComponent.MULTIPLE);
		
		XBox xbox = new XBox();
		xbox.add(new IntLabel("? / 50 snapshots taken", Model.vars.snapshotsCollected));
		xbox.addSpace(10, 1);
		xbox.add(new SelectionSizeLabel(items, "snapshots"));
		
		YBox ybox = new YBox();
		ybox.add(new JLabel("<html>Click or drag the mouse to select snapshots.<br />Hold shift or alt to respectively grow or shrink the selection.<br>Only the snapshots that you didn't take are displayed."));
		ybox.addSpace(10);
		ybox.add(new PickupCollectButton("Take selected snapshots", items, Model.vars.snapshotsCollected), 0, 0.5f, 0.0f);
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
		List<SelectablePickup> snapshots = items.getItems();
		
		snapshots.clear();
		for (Pickup shot : Model.vars.snapshots) {
			snapshots.add(new SelectablePickup(shot, MapImage.SAN_FIERRO));
		}
		
		items.onSelectionChange().report();
		items.onDataChange().report();
	}
}
