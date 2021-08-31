package nl.paulinternet.gtasaveedit.view;

import nl.paulinternet.gtasaveedit.model.LoadableImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class Images {
    public static final LoadableImage WARNING = new LoadableImage("warning.png", 16, 16);
    public static final LoadableImage MAP = new LoadableImage("map.png", 620, 620);
    public static final LoadableImage LOS_SANTOS = new LoadableImage("lossantos.png", 585, 465);
    public static final LoadableImage SAN_FIERRO = new LoadableImage("sanfierro.png", 420, 543);
    public static final LoadableImage LAS_VENTURAS = new LoadableImage("lasventuras.png", 440, 505);
    public static final LoadableImage CIRCLE_WHITE = new LoadableImage("circle_white.png", 13, 13);
    public static final LoadableImage CIRCLE_GREEN = new LoadableImage("circle_green.png", 13, 13);

    private Images() {
    }

    public static Image readImage(String name) {
        try {
            return ImageIO.read(Objects.requireNonNull(Images.class.getResourceAsStream("/" + name)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void loadImages() {
        WARNING.load();
        MAP.load();
        LOS_SANTOS.load();
        SAN_FIERRO.load();
        LAS_VENTURAS.load();
        CIRCLE_WHITE.load();
        CIRCLE_GREEN.load();
    }
}
