package nl.paulinternet.gtasaveedit.view.swing;

import java.awt.Dimension;
import java.awt.Insets;

public class NewDimension extends Dimension
{
	public NewDimension () {}
	
	public NewDimension (int width, int height) {
		super(width, height);
	}
	
	public NewDimension (Dimension other) {
		super(other);
	}
	
	public int getValue (int axis) {
		if (axis == 0) return width;
		if (axis == 1) return height;
		throw new IllegalArgumentException();
	}
	
	public void setValue (int axis, int value) {
		if (axis == 0) width = value;
		else if (axis == 1) height = value;
		else throw new IllegalArgumentException();
	}

	public void setValue (int axis, Dimension other) {
		if (axis == 0) width = other.width;
		else if (axis == 1) height = other.height;
		else throw new IllegalArgumentException();
	}
	
	public void addValue (int axis, int value) {
		if (axis == 0) width += value;
		else if (axis == 1) height += value;
		else throw new IllegalArgumentException();
	}
	
	public void addValue (int axis, Dimension other) {
		if (axis == 0) width += other.width;
		else if (axis == 1) height += other.height;
		else throw new IllegalArgumentException();
	}
	
	public void add (Insets insets) {
		width += insets.left + insets.right;
		height += insets.top + insets.bottom;
	}
	
	public void subtract (Insets insets) {
		width -= insets.left + insets.right;
		height -= insets.top + insets.bottom;
	}
}
