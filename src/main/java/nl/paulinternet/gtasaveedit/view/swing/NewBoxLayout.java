package nl.paulinternet.gtasaveedit.view.swing;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager2;
import java.util.ArrayList;
import java.util.List;

public class NewBoxLayout implements LayoutManager2
{
	private static final Constraints DEFAULT_CONSTRAINTS = new Constraints(0, 0.0f, 1.0f);
	
	public static class Constraints
	{
		private int expand;
		private float otherAlign;
		private float otherExpand;
		
		public Constraints (int expand, float otherAlign, float otherExpand) {
			this.expand = expand;
			this.otherAlign = otherAlign;
			this.otherExpand = otherExpand;
		}
		
		public Constraints (int expand) {
			this.expand = expand;
			this.otherExpand = 1.0f;
		}
	}
	
	private static class Item
	{
		private Component comp;
		private int expand;
		private float otherAlign;
		private float otherExpand;
		
		public Item (Component comp, Constraints c) {
			this.comp = comp;
			expand = c.expand;
			otherAlign = c.otherAlign;
			otherExpand = c.otherExpand;
		}
	}
	
	private List<Item> items;
	private int axis;
	
	public NewBoxLayout (int axis) {
		items = new ArrayList<Item>();
		this.axis = axis;
	}

	@Override
	public void addLayoutComponent (Component comp, Object constraints) {
		items.add(new Item(comp, constraints == null ? DEFAULT_CONSTRAINTS : (Constraints) constraints));
	}
	
	@Override
	public void addLayoutComponent (String name, Component comp) {
		addLayoutComponent(comp, null);
	}

	@Override
	public float getLayoutAlignmentX (Container target) {
		return 0;
	}

	@Override
	public float getLayoutAlignmentY (Container target) {
		return 0;
	}

	@Override
	public void invalidateLayout (Container target) {
	}

	@Override
	public Dimension maximumLayoutSize (Container target) {
		return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
	}

	@Override
	public void layoutContainer (Container parent) {
		// Get size and minimum size
		Insets insets = parent.getInsets();
		NewDimension minimumSize = new NewDimension(parent.getMinimumSize());
		NewDimension size = new NewDimension(parent.getSize());

		// Get the total expanding
		int spaceLeft = size.getValue(axis) - minimumSize.getValue(axis);
		int expandLeft = 0;
		for (Item item : items) {
			if (item.comp.isVisible()) expandLeft += item.expand;
		}
		
		// Subtract insets from size
		size.subtract(insets);
		
		// Paint
		NewDimension location = new NewDimension(insets.left, insets.top);
		
		for (Item item : items) {
			if (item.comp.isVisible()) {
				// First set the item size to the components minimum size
				NewDimension itemSize = new NewDimension(item.comp.getMinimumSize());
				
				// Add size in the first axis
				int extraPixels = Math.round((float) item.expand / (float) expandLeft * (float) spaceLeft);
				itemSize.addValue(axis, extraPixels);
				spaceLeft -= extraPixels;
				expandLeft -= item.expand;
				
				// Add size in the other axis
				itemSize.addValue(1-axis, Math.round(item.otherExpand * (float) (size.getValue(1-axis) - itemSize.getValue(1-axis))));
				
				// Add location in the other axis
				NewDimension itemLocation = new NewDimension(location);
				itemLocation.addValue(1-axis, Math.round(item.otherAlign * (float) (size.getValue(1-axis) - itemSize.getValue(1-axis))));
	
				// Set bounds
				item.comp.setBounds(itemLocation.width, itemLocation.height, itemSize.width, itemSize.height);
	
				// Update location
				location.addValue(axis, itemSize);
			}
		}
	}

	@Override
	public NewDimension minimumLayoutSize (Container parent) {
		// Create dimension object
		NewDimension size = new NewDimension();

		// Walk through elements
		for (Item item : items) {
			if (item.comp.isVisible()) {
				NewDimension itemSize = new NewDimension(item.comp.getMinimumSize());
				size.addValue(axis, itemSize);
				int otherSize = itemSize.getValue(1 - axis);
				if (otherSize > size.getValue(1 - axis)) size.setValue(1 - axis, otherSize);
			}
		}
		
		// Add space for border
		size.add(parent.getInsets());
		
		// Return
		return size;
	}

	@Override
	public Dimension preferredLayoutSize (Container parent) {
		return minimumLayoutSize(parent);
	}

	@Override
	public void removeLayoutComponent (Component comp) {
		for (int i=0; i<items.size(); i++) {
			if (items.get(i).comp.equals(comp)) {
				items.remove(i--);
			}
		}
	}
}
