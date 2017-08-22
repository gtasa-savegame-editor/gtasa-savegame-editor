package nl.paulinternet.gtasaveedit.view;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface SelectableItem
{
	public void setSelected (boolean selected);
	public boolean isSelected ();
	public Rectangle getBounds ();
	public void paint (Graphics g);
}
