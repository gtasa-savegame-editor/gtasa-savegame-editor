package nl.paulinternet.gtasaveedit.view.selectable;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface SelectableItem
{
	void setSelected(boolean selected);
	boolean isSelected();
	Rectangle getBounds();
	void paint(Graphics g);
}
