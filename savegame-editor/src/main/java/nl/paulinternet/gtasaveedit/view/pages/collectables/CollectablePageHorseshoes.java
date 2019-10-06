package nl.paulinternet.gtasaveedit.view.pages.collectables;

import nl.paulinternet.libsavegame.SavegameModel;
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

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CollectablePageHorseshoes extends Page {
    private SelectableItems<SelectablePickup> items;

    public CollectablePageHorseshoes() {
        super("Horseshoes");

        List<SelectablePickup> horseshoes = new ArrayList<SelectablePickup>();

        items = new SelectableItems<SelectablePickup>(horseshoes);
        SelectableItemComponent select = new SelectableItemComponent(MapImage.LAS_VENTURAS, items, SelectableItemComponent.MULTIPLE);

        XBox xbox = new XBox();
        xbox.add(new IntLabel("? / 50 horseshoes collected", SavegameModel.vars.horseshoesCollected));
        xbox.addSpace(10, 1);
        xbox.add(new SelectionSizeLabel(items, "horseshoes"));

        String message = "<html>Click or drag the mouse to select horseshoes.<br>";
        message += "Hold shift or alt to respectively grow or shrink the selection.<br />";
        message += "Only the horseshoes that you didn't collect are displayed.";

        YBox ybox = new YBox();
        ybox.add(new JLabel(message));
        ybox.addSpace(10);
        ybox.add(new PickupCollectButton("Collect selected horseshoes", items, SavegameModel.vars.horseshoesCollected), 0, 0.5f, 0.0f);
        ybox.addSpace(5);
        ybox.add(select);
        ybox.addSpace(5);
        ybox.add(xbox);

        Alignment alignment = new Alignment(ybox, 0.0f, 0.0f);
        alignment.setBorder(10);
        setComponent(alignment, true);

        SavegameModel.gameLoaded.addHandler(this, "onGameLoad");
    }

    public void onGameLoad() {
        List<SelectablePickup> horseshoes = items.getItems();

        horseshoes.clear();
        for (Pickup shoe : SavegameModel.vars.horseshoes) {
            horseshoes.add(new SelectablePickup(shoe, MapImage.LAS_VENTURAS));
        }

        items.onSelectionChange().report();
        items.onDataChange().report();
    }
}
