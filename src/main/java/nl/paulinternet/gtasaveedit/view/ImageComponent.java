package nl.paulinternet.gtasaveedit.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.SwingUtilities;

public class ImageComponent extends Component implements Runnable
{
	private LoadableImage loadableImage;
	
	public ImageComponent (LoadableImage image) {
		this.loadableImage = image;
		setMinimumSize(new Dimension(image.getWidth(), image.getHeight()));
		image.onLoaded().addHandler(this, "repaintLater");
	}
	
	@Override
	public void paint (Graphics g) {
		Image image = loadableImage.getImage();
		if (image != null) {
			g.drawImage(image, 0, 0, null);
		}
	}
	
	public void repaintLater () {
		SwingUtilities.invokeLater(this);
	}

	@Override
	public void run () {
		repaint();
	}
}
