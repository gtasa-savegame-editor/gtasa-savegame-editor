package nl.paulinternet.gtasaveedit.view.selectable;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import nl.paulinternet.gtasaveedit.model.Zone;

public class SelectableZone implements SelectableItemValue
{
	public static final int GANG_PRESENT = 0x10, DISABLE_FOOTCOPS = 0x11; 
	
	private static final Color ZONE_BORDER = new Color(0xdd, 0xdd, 0xdd);
	
	private Zone zone;
	private Rectangle bounds;
	private boolean selected;
	
	public SelectableZone (Zone zone) {
		this.zone = zone;
		bounds = new Rectangle(zone.getX(), zone.getY(), zone.getWidth(), zone.getHeight());
	}

	@Override
	public Rectangle getBounds () {
		return bounds;
	}
	
	@Override
	public void paint (Graphics g) {
		if (selected) {
			g.setColor(Color.GRAY);
			g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
			g.setColor(zone.getColor());
			g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
			g.setColor(ZONE_BORDER);
			g.drawRect(bounds.x, bounds.y, bounds.width - 1, bounds.height - 1);
		}
	}

	@Override
	public int getValue (int var) {
		return zone.getValue(var);
	}
	
	@Override
	public void setValue (int var, int value) {
		zone.setValue(var, value);
	}

	@Override
	public boolean isSelected () {
		return selected;
	}

	@Override
	public void setSelected (boolean selected) {
		this.selected = selected;
	}
}
