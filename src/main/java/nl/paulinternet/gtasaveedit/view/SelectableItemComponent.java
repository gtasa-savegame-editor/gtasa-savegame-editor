package nl.paulinternet.gtasaveedit.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.List;

import nl.paulinternet.gtasaveedit.model.Event;
import nl.paulinternet.gtasaveedit.model.EventHandler;
import nl.paulinternet.gtasaveedit.model.ReportableEvent;

public class SelectableItemComponent extends ImageComponent implements MouseListener, MouseMotionListener
{
	private class ImageDrawer implements EventHandler
	{
		private int[] array = new int[620];
		
		@Override
		public void handleEvent (Event e) {
			Graphics2D g = image.createGraphics();
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, 620, 620);
			
			for (SelectableItem item : items) {
				item.paint(g);
			}

			int opacityMask = (Math.round(itemOpacity * 255.0f) << 24) | 0xffffff;
			for (int y=0; y<620; y++) {
				image.getRGB(0, y, 620, 1, array, 0, 1);
				for (int x=0; x<620; x++) {
					array[x] = (array[x] & 0xffffff) == 0xffffff ? 0 : array[x] & opacityMask;
				}
				image.setRGB(0, y, 620, 1, array, 0, 1);
			}
			
			repaint();
		}
	}
	
	public static final int MULTIPLE = 0, SMALLEST_AREA = 1;
	
	private static final Color overlay = new Color(0.96f, 0.94f, 0.92f, 0.75f);

	private List<? extends SelectableItem> items;
	private boolean dragging;
	private int startX, startY;
	private int xMin, xMax, yMin, yMax;
	private int imageWidth, imageHeight;
	private int clickSelection;
	private ReportableEvent selectionChanged;
	private float itemOpacity;
	private BufferedImage image;
	
	public SelectableItemComponent (MapImage map, SelectableItems<?> items, int clickSelection, float itemOpacity) {
		super(map.getImage());
		LoadableImage image = map.getImage();
		
		imageWidth = image.getWidth();
		imageHeight = image.getHeight();
		this.items = items.getItems();
		this.clickSelection = clickSelection;
		this.itemOpacity = itemOpacity;
		selectionChanged = items.onSelectionChange();
		
		if (itemOpacity != 1.0f) {
			this.image = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
			ImageDrawer drawer = new ImageDrawer();
			items.onDataChange().addHandler(drawer);
			items.onSelectionChange().addHandler(drawer);
		}
		else {
			items.onDataChange().addHandler(this, "repaint");
		}
		
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public SelectableItemComponent (MapImage map, SelectableItems<?> items, int clickSelection) {
		this(map, items, clickSelection, 1.0f);
	}
	
	@Override
	public void paint (Graphics g) {
		super.paint(g);
		
		g.setColor(overlay);
		g.fillRect(0, 0, imageWidth, imageHeight);
		
		if (image != null) {
			g.drawImage(image, 0, 0, null);
		}
		else {
			for (SelectableItem item : items) {
				item.paint(g);
			}
		}
		
		if (dragging) {
			g.setColor(Color.BLACK);
			g.drawRect(xMin, yMin, xMax - xMin, yMax - yMin);
			g.drawRect(xMin + 1, yMin + 1, xMax - xMin - 2, yMax - yMin - 2);
		}
	}

	@Override
	public void mousePressed (MouseEvent e) {
		dragging = true;
		startX = e.getX();
		startY = e.getY();
		mouseDragged(e);
	}

	@Override
	public void mouseDragged (MouseEvent e) {
		int endX = Math.min(Math.max(e.getX(), 0), imageWidth - 1);
		int endY = Math.min(Math.max(e.getY(), 0), imageHeight - 1);
		xMin = Math.min(startX, endX);
		xMax = Math.max(startX, endX);
		yMin = Math.min(startY, endY);
		yMax = Math.max(startY, endY);
		repaint();
	}
	
	@Override
	public void mouseReleased (MouseEvent e) {
		boolean shift = e.isShiftDown() && !e.isAltDown();
		boolean alt = e.isAltDown() && !e.isShiftDown();

		if (xMin != xMax || yMin != yMax) { // mouse dragged
			// Selected area
			Rectangle bounds = new Rectangle(xMin, yMin, xMax - xMin, yMax - yMin);

			// Add, remove or replace selection
			if (shift) {
				for (SelectableItem item : items) {
					if (bounds.contains(item.getBounds())) item.setSelected(true);
				}
			}
			else if (alt) {
				for (SelectableItem item : items) {
					if (bounds.contains(item.getBounds())) item.setSelected(false);
				}
			}
			else {
				for (SelectableItem item : items) {
					item.setSelected(bounds.contains(item.getBounds()));
				}
			}
		}
		else { // mouse clicked
			int x = xMin;
			int y = yMin;

			if (clickSelection == MULTIPLE) {
				// Add, remove or replace selection
				if (shift) {
					for (SelectableItem item : items) {
						if (item.getBounds().contains(x, y)) item.setSelected(true);
					}
				}
				else if (alt) {
					for (SelectableItem item : items) {
						if (item.getBounds().contains(x, y)) item.setSelected(false);
					}
				}
				else {
					for (SelectableItem item : items) {
						item.setSelected(item.getBounds().contains(x, y));
					}
				}
			}
			else if (clickSelection == SMALLEST_AREA) {
				// Find smallest area (possibly no area at all) that contains the (x,y) of the mouse click
				SelectableItem selectedItem = null;
				int selectedArea = 0;

				for (SelectableItem item : items) {
					Rectangle bounds = item.getBounds();
					if (bounds.contains(x, y) && (!alt || item.isSelected()) && (!shift || !item.isSelected())) {
						int area = bounds.width * bounds.height;
						if (selectedItem == null || area < selectedArea) {
							selectedItem = item;
							selectedArea = area;
						}
					}
				}

				// Add, remove or replace selection
				if (shift) {
					if (selectedItem != null) selectedItem.setSelected(true);
				}
				else if (alt) {
					if (selectedItem != null) selectedItem.setSelected(false);
				}
				else {
					for (SelectableItem item : items) item.setSelected(item == selectedItem);
				}
			}
		}

		dragging = false;
		repaint();
		selectionChanged.report();
	}

	@Override
	public void mouseMoved (MouseEvent e) {}
	
	@Override
	public void mouseClicked (MouseEvent e) {}

	@Override
	public void mouseEntered (MouseEvent e) {}

	@Override
	public void mouseExited (MouseEvent e) {}
}
