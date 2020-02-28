package nl.paulinternet.gtasaveedit.view.pages.collectables;

import nl.paulinternet.gtasaveedit.model.SavegameModel;
import nl.paulinternet.gtasaveedit.view.MapImage;
import nl.paulinternet.gtasaveedit.view.component.IntLabel;
import nl.paulinternet.gtasaveedit.view.component.PickupCollectButton;
import nl.paulinternet.gtasaveedit.view.pages.Page;
import nl.paulinternet.gtasaveedit.view.selectable.SelectableItemComponent;
import nl.paulinternet.gtasaveedit.view.selectable.SelectableItems;
import nl.paulinternet.gtasaveedit.view.selectable.SelectablePickup;
import nl.paulinternet.gtasaveedit.view.selectable.SelectionSizeLabel;
import nl.paulinternet.gtasaveedit.view.swing.Alignment;
import nl.paulinternet.gtasaveedit.view.swing.XBox;
import nl.paulinternet.gtasaveedit.view.swing.YBox;
import nl.paulinternet.libsavegame.data.Pickup;
import nl.paulinternet.libsavegame.variables.Variables;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CollectablePageSnapshots extends Page {
    private SelectableItems<SelectablePickup> items;

    public CollectablePageSnapshots() {
        super("Snapshots");

        List<SelectablePickup> snapshots = new ArrayList<SelectablePickup>();

        items = new SelectableItems<SelectablePickup>(snapshots);
        SelectableItemComponent select = new SelectableItemComponent(MapImage.SAN_FIERRO, items, SelectableItemComponent.MULTIPLE);

        XBox xbox = new XBox();
        xbox.add(new IntLabel("? / 50 snapshots taken", Variables.get().snapshotsCollected));
        xbox.addSpace(10, 1);
        xbox.add(new SelectionSizeLabel(items, "snapshots"));

        YBox ybox = new YBox();
        ybox.add(new JLabel("<html>Click or drag the mouse to select snapshots.<br />Hold shift or alt to respectively grow or shrink the selection.<br>Only the snapshots that you didn't take are displayed."));
        ybox.addSpace(10);
        ybox.add(new PickupCollectButton("Take selected snapshots", items, Variables.get().snapshotsCollected), 0, 0.5f, 0.0f);
        ybox.addSpace(5);
        ybox.add(select);
        ybox.addSpace(5);
        ybox.add(xbox);

        Alignment alignment = new Alignment(ybox, 0.0f, 0.0f);
        alignment.setBorder(10);
        setComponent(alignment, true);

        SavegameModel.gameLoaded.addHandler(this, "onGameLoaded");
    }

    public void onGameLoaded() {
        List<SelectablePickup> snapshots = items.getItems();

        snapshots.clear();
        for (Pickup shot : Variables.get().snapshots) {
            snapshots.add(new SelectablePickup(shot, MapImage.SAN_FIERRO));
        }

        items.onSelectionChange().report();
        items.onDataChange().report();
    }
}
