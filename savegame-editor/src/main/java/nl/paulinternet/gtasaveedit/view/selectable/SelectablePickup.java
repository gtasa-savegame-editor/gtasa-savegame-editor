package nl.paulinternet.gtasaveedit.view.selectable;

import nl.paulinternet.gtasaveedit.view.MapImage;
import nl.paulinternet.libsavegame.data.Pickup;

import java.awt.*;

public class SelectablePickup implements SelectableItem {
    private Pickup pickup;
    private Rectangle bounds;
    private boolean selected;

    public SelectablePickup(Pickup pickup, MapImage map) {
        this.pickup = pickup;

        int mapX = map.getMapX(0.125f * (float) pickup.getX());
        int mapY = map.getMapY(0.125f * (float) pickup.getY());
        bounds = new Rectangle(mapX - 3, mapY - 3, 7, 7);
    }

    @Override
    public Rectangle getBounds() {
        return bounds;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(selected ? Color.RED : Color.BLACK);
        g.drawRect(bounds.x, bounds.y, 7, 7);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(bounds.x + 1, bounds.y + 1, 6, 6);
    }

    public Pickup getPickup() {
        return pickup;
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
