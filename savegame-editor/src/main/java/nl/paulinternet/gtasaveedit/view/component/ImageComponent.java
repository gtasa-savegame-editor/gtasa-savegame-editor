package nl.paulinternet.gtasaveedit.view.component;

import nl.paulinternet.gtasaveedit.model.LoadableImage;

import javax.swing.*;
import java.awt.*;

public class ImageComponent extends JComponent implements Runnable {
    private LoadableImage loadableImage;

    public ImageComponent(LoadableImage image) {
        this.loadableImage = image;
        Dimension size = new Dimension(image.getWidth(), image.getHeight());
        setMinimumSize(size);
        setPreferredSize(size);
        setOpaque(true);
        image.onLoaded().addHandler(this, "repaintLater");
    }

    @Override
    protected void paintComponent(Graphics g) {
        Image image = loadableImage.getImage();
        if (image != null) {
            g.drawImage(image, 0, 0, null);
        } else {
            g.setColor(getBackground());
            g.fillRect(0, 0, getWidth(), getHeight());
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
