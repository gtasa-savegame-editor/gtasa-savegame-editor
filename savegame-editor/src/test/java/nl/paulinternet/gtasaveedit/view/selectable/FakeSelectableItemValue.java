package nl.paulinternet.gtasaveedit.view.selectable;

import java.awt.*;

/**
 * Minimal SelectableItemValue test double, storing a single int "parameter"
 * (mirrors how SelectableZone/SelectableTag store one field per call).
 */
class FakeSelectableItemValue implements SelectableItemValue {
    private boolean selected;
    private int value;

    @Override
    public int getValue(int var) {
        return value;
    }

    @Override
    public void setValue(int var, int value) {
        this.value = value;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle();
    }

    @Override
    public void paint(Graphics g) {
    }
}
