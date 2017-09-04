package nl.paulinternet.gtasaveedit.model;

import nl.paulinternet.gtasaveedit.model.event.Event;
import nl.paulinternet.gtasaveedit.model.event.ReportableEvent;
import nl.paulinternet.gtasaveedit.view.Images;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class LoadableImage {
    private String fileName;
    private int width, height;
    private volatile Image image;
    private ReportableEvent onLoaded;

    public LoadableImage(String fileName, int width, int height) {
        this.fileName = fileName;
        this.width = width;
        this.height = height;
        onLoaded = new ReportableEvent();
    }

    public void load() {
        if (image == null) {
            try {
                image = ImageIO.read(Images.class.getResourceAsStream("/" + fileName));
                onLoaded.report();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Event onLoaded() {
        return onLoaded;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Image getImage() {
        return image;
    }
}