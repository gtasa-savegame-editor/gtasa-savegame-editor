package nl.paulinternet.gtasaveedit.view;


import nl.paulinternet.gtasaveedit.model.LoadableImage;

public class MapImage {
    public static final MapImage SAN_ANDREAS = new MapImage(Images.MAP, 0.1f, 310f, -0.1f, 310f);
    public static final MapImage LOS_SANTOS = new MapImage(Images.LOS_SANTOS, 0.2f, -15f, -0.2f, -135f);
    public static final MapImage SAN_FIERRO = new MapImage(Images.SAN_FIERRO, 0.2f, 600f, -0.2f, 340f);
    public static final MapImage LAS_VENTURAS = new MapImage(Images.LAS_VENTURAS, 0.2f, -165f, -0.2f, 605f);

    private LoadableImage image;
    private float multiplyX, multiplyY;
    private float addX, addY;

    public MapImage(LoadableImage image, float multiplyX, float addX, float multiplyY, float addY) {
        this.image = image;
        this.multiplyX = multiplyX;
        this.multiplyY = multiplyY;
        this.addX = addX;
        this.addY = addY;
    }

    public LoadableImage getImage() {
        return image;
    }

    public int getMapX(float x) {
        return Math.round(addX + multiplyX * x);
    }

    public int getMapY(float y) {
        return Math.round(addY + multiplyY * y);
    }
}
