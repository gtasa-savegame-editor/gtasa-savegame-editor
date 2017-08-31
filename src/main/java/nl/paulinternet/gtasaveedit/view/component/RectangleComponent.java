package nl.paulinternet.gtasaveedit.view.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

public class RectangleComponent extends Component
{
	private static final Dimension size = new Dimension(10, 10);

	private Color color;
	
	public RectangleComponent (Color color) {
		this.color = color;
	}
	
	public Dimension getMinimumSize () {
		return size;
	}
	
	public void paint (Graphics g) {
		Dimension compSize = getSize();
		int x = (compSize.width - size.width) / 2;
		int y = (compSize.height - size.height) / 2;
		
		g.setColor(Color.BLACK);
		g.drawRect(x, y, size.width - 1, size.height - 1);
		g.setColor(color);
		g.fillRect(x + 1, y + 1, size.width - 2, size.height - 2);
	}
}
