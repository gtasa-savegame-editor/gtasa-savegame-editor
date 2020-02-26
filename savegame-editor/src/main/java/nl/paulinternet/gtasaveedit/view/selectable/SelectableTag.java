package nl.paulinternet.gtasaveedit.view.selectable;

import nl.paulinternet.gtasaveedit.model.SavegameModel;
import nl.paulinternet.gtasaveedit.view.MapImage;
import nl.paulinternet.libsavegame.SavegameVars;

import java.awt.*;

import static nl.paulinternet.libsavegame.SavegameVars.vars;

public class SelectableTag implements SelectableItemValue {
    private Rectangle bounds;
    private boolean selected;
    private int id;

    public SelectableTag(int id, float x, float y) {
        int mapX = MapImage.LOS_SANTOS.getMapX(x);
        int mapY = MapImage.LOS_SANTOS.getMapY(y);
        bounds = new Rectangle(mapX - 3, mapY - 3, 7, 7);
        this.id = id;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(selected ? Color.RED : Color.BLACK);
        g.drawRect(bounds.x, bounds.y, 7, 7);
        g.setColor(vars.tags[id] >= 0xe5 ? Color.GREEN : Color.LIGHT_GRAY);
        g.fillRect(bounds.x + 1, bounds.y + 1, 6, 6);
    }

    @Override
    public Rectangle getBounds() {
        return bounds;
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
    public int getValue(int var) {
        return SavegameVars.vars.tags[id];
    }

    @Override
    public void setValue(int var, int value) {
        SavegameVars.vars.tags[id] = value;
    }
}
