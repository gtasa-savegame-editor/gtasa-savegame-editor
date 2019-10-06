package nl.paulinternet.gtasaveedit.view.component;

import nl.paulinternet.gtasaveedit.model.LoadableImage;

import javax.swing.*;
import java.awt.*;

public class ImageComponent extends Component implements Runnable {
    private LoadableImage loadableImage;

    public ImageComponent(LoadableImage image) {
        this.loadableImage = image;
        setMinimumSize(new Dimension(image.getWidth(), image.getHeight()));
        image.onLoaded().addHandler(this, "repaintLater");
    }

    @Override
    public void paint(Graphics g) {
        Image image = loadableImage.getImage();
        if (image != null) {
            g.drawImage(image, 0, 0, null);
        }
    }

    public void repaintLater() {
        SwingUtilities.invokeLater(this);
    }

    @Override
    public void run() {
        repaint();
    }
}
