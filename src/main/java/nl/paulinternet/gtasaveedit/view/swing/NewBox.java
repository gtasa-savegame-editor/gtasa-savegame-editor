package nl.paulinternet.gtasaveedit.view.swing;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class NewBox extends JPanel
{
	public static final int X = 0, Y = 1;
	
	private int axis;
	
	public NewBox (int axis) {
		super(new NewBoxLayout(axis));
		this.axis = axis;
	}
	
	public Component add (Component comp, int expand, float otherAlign, float otherExpand) {
		add(comp, new NewBoxLayout.Constraints(expand, otherAlign, otherExpand));
		return comp;
	}
	
	public Component add (Component comp, int expand) {
		return add(comp, expand, 0.0f, 1.0f);
	}

	public void addSpace (int pixels) {
		add(Box.createRigidArea(new Dimension(axis == X ? pixels : 0, axis == Y ? pixels : 0)));
	}
	
	public void addSpace (int pixels, int expand) {
		add(Box.createRigidArea(new Dimension(axis == X ? pixels : 0, axis == Y ? pixels : 0)), new NewBoxLayout.Constraints(expand));
	}
	
	public void addSeparator () {
		add(new JSeparator(axis == Y ? SwingConstants.HORIZONTAL : SwingConstants.VERTICAL));
	}
	
	public void addSeparator (int padding) {
		addSpace(padding);
		addSeparator();
		addSpace(padding);
	}
	
	public void setBorder (int size) {
		setBorder(BorderFactory.createEmptyBorder(size, size, size, size));
	}
}
