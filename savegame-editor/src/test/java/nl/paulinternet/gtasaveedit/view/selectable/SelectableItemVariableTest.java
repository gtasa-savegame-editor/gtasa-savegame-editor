package nl.paulinternet.gtasaveedit.view.selectable;

import nl.paulinternet.libsavegame.variables.Variable;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * SelectableItemVariable used to declare its own "value" field that shadowed the
 * inherited Variable<T>.value field instead of overriding it. Any caller using the
 * generic getValue()/setValue(T) API (ConnectedCheckbox, ConnectedRadioButtons,
 * PopcycleComboBox's display) silently read/wrote a field that was never connected
 * to the actual item data, while getText()/setText() (overridden) worked fine.
 */
public class SelectableItemVariableTest {
    private List<FakeSelectableItemValue> itemList;
    private SelectableItems<FakeSelectableItemValue> items;

    @Before
    public void setUp() {
        itemList = new ArrayList<>();
        itemList.add(new FakeSelectableItemValue());
        itemList.add(new FakeSelectableItemValue());
        items = new SelectableItems<>(itemList);
    }

    @Test
    public void setIntValueIsReflectedByGetValue() {
        itemList.get(0).setSelected(true);
        items.onSelectionChange().report();

        SelectableItemVariable<Integer> var = new SelectableItemVariable<>(items, 0, 0, 255);
        var.setIntValue(42);

        assertEquals(42, itemList.get(0).getValue(0));
        assertEquals(Integer.valueOf(42), var.getValue());
        assertEquals("42", var.getText());
    }

    @Test
    public void forBooleanWritesThroughToItemData() {
        itemList.get(0).setSelected(true);
        items.onSelectionChange().report();

        Variable<Boolean> var = SelectableItemVariable.forBoolean(items, 0);

        var.setValue(true);
        assertEquals(1, itemList.get(0).getValue(0));

        var.setValue(false);
        assertEquals(0, itemList.get(0).getValue(0));
    }

    @Test
    public void selectionChangeNotifiesOnChangeListenersLikeConnectedTextFieldRelyOn() {
        // Regression test for https://github.com/gtasa-savegame-editor/gtasa-savegame-editor/issues/97
        // and /issues/121: SelectableItemVariable declared its own "onChange" ReportableEvent field
        // that shadowed Variable<T>'s inherited "onChange" listener list. Updater fired the shadowed
        // (unused) field on every selection/data change, so ConnectedTextField's
        // model.addOnChangeListener(...) - the only thing that keeps the widget in sync with the
        // model - was never actually invoked. Zone tab fields stayed disabled/blank no matter what
        // was selected on the map.
        SelectableItemVariable<Integer> var = new SelectableItemVariable<>(items, 0, 0, 255);
        List<Integer> notifications = new ArrayList<>();
        var.addOnChangeListener(notifications::add);

        assertFalse(var.isEnabled());
        assertEquals("", var.getText());

        itemList.get(0).setValue(0, 42);
        itemList.get(0).setSelected(true);
        items.onSelectionChange().report();

        assertEquals(1, notifications.size());
        assertTrue(var.isEnabled());
        assertEquals("42", var.getText());

        itemList.get(0).setSelected(false);
        items.onSelectionChange().report();

        assertEquals(2, notifications.size());
        assertFalse(var.isEnabled());
        assertEquals("", var.getText());
    }

    @Test
    public void forBooleanGetValueReturnsARealBooleanNotAClassCastException() {
        // Regression test: this exact "Boolean value = var.getValue();" pattern is what
        // ConnectedCheckbox does. Before the fix, getValue() returned the disconnected
        // Integer field, and javac's erasure-inserted checkcast to Boolean at this call
        // site threw a ClassCastException at runtime.
        itemList.get(0).setSelected(true);
        items.onSelectionChange().report();

        Variable<Boolean> var = SelectableItemVariable.forBoolean(items, 0);
        Boolean value = var.getValue();

        assertFalse(value);

        var.setValue(true);
        boolean unboxed = var.getValue();
        assertTrue(unboxed);
    }
}
