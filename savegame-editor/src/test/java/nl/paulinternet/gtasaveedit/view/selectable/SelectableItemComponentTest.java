package nl.paulinternet.gtasaveedit.view.selectable;

import nl.paulinternet.gtasaveedit.view.MapImage;
import org.junit.Test;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * SelectableItemComponent (via ImageComponent) used to extend java.awt.Component
 * directly instead of JComponent: a heavyweight AWT component mixed into the Swing
 * tree. repaint() calls on it could be silently dropped instead of routed through
 * Swing's RepaintManager (e.g. "Spray Selected" on Tags updating the data but not
 * visibly refreshing until the tab was switched or the app restarted).
 */
public class SelectableItemComponentTest {
    public static class CountingComponent extends SelectableItemComponent {
        int repaintCount;

        CountingComponent(SelectableItems<?> items) {
            super(MapImage.SAN_ANDREAS, items, SelectableItemComponent.MULTIPLE);
        }

        @Override
        public void repaint() {
            repaintCount++;
        }
    }

    @Test
    public void isALightweightSwingComponent() {
        SelectableItems<FakeSelectableItemValue> items = new SelectableItems<>(new ArrayList<>());
        CountingComponent component = new CountingComponent(items);

        assertTrue(component instanceof JComponent);
        assertTrue(component.isOpaque());
    }

    @Test
    public void dataChangeFromASelectionWideEditTriggersExactlyOneRepaint() {
        List<FakeSelectableItemValue> itemList = new ArrayList<>();
        itemList.add(new FakeSelectableItemValue());
        itemList.add(new FakeSelectableItemValue());
        itemList.add(new FakeSelectableItemValue());
        SelectableItems<FakeSelectableItemValue> items = new SelectableItems<>(itemList);

        // Same construction order as CollectablePageTags: the Variable is wired up
        // before the map component.
        SelectableItemVariable<Integer> var = new SelectableItemVariable<>(items, 0, 0, 255);
        CountingComponent component = new CountingComponent(items);

        // Select multiple items at once, like a shift-drag selection on the map.
        itemList.get(0).setSelected(true);
        itemList.get(1).setSelected(true);
        items.onSelectionChange().report();
        assertEquals(0, component.repaintCount);

        // Simulate clicking "Spray Selected" / "Collect selected" / etc.
        var.setIntValue(255);

        assertEquals(255, itemList.get(0).getValue(0));
        assertEquals(255, itemList.get(1).getValue(0));
        assertEquals(0, itemList.get(2).getValue(0));
        assertEquals(1, component.repaintCount);
    }
}
