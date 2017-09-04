package nl.paulinternet.gtasaveedit.view.selectable;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import nl.paulinternet.gtasaveedit.model.savegame.data.Jump;

public class SelectableJump implements SelectableItemValue
{
	public static final int REWARD = 0, COMPLETED = 1;
	
	private static final Color COLOR_FOUND = new Color(0.5f, 0.75f, 0.5f);
	
	private Rectangle bounds;
	private boolean selected;
	private Jump jump;
	
	public SelectableJump (Jump jump) {
		this.jump = jump;
		
		int mapX = 310 + Math.round(0.1f * jump.x);
		int mapY = 310 - Math.round(0.1f * jump.y);
		bounds = new Rectangle(mapX - 3, mapY - 3, 7, 7);
	}

	@Override
	public Rectangle getBounds () {
		return bounds;
	}
		
	@Override
	public void paint (Graphics g) {
		g.setColor(selected ? Color.RED : Color.BLACK);
		g.drawRect(bounds.x, bounds.y, 7, 7);
		g.setColor(jump.completed ? Color.GREEN : (jump.found ? COLOR_FOUND : Color.LIGHT_GRAY));
		g.fillRect(bounds.x + 1, bounds.y + 1, 6, 6);
	}
	
	@Override
	public int getValue (int var) {
		switch (var) {
			case REWARD: return jump.reward;
			case COMPLETED: return jump.completed ? 2 : (jump.found ? 1 : 0);
			default: throw new IllegalArgumentException();
		}
	}
	
	@Override
	public void setValue (int var, int value) {
		switch (var) {
			case REWARD: jump.reward = value; break;
			case COMPLETED: jump.found = value > 0; jump.completed = value > 1; break;
			default: throw new IllegalArgumentException();
		}
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
