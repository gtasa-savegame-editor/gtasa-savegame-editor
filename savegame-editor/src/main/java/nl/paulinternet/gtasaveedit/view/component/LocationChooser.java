package nl.paulinternet.gtasaveedit.view.component;

import nl.paulinternet.gtasaveedit.model.LoadableImage;
import nl.paulinternet.gtasaveedit.event.MethodInvoker;
import nl.paulinternet.gtasaveedit.model.SavegameModel;
import nl.paulinternet.gtasaveedit.view.Images;
import nl.paulinternet.libsavegame.data.Saveplace;
import nl.paulinternet.libsavegame.variables.Variables;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LocationChooser extends ImageComponent implements MouseListener {
    private static final Color overlay = new Color(0.96f, 0.94f, 0.92f, 0.75f);

    private LoadableImage circleWhite, circleGreen;

    public LocationChooser() {
        super(Images.MAP);
        circleWhite = Images.CIRCLE_WHITE;
        circleGreen = Images.CIRCLE_GREEN;
        addMouseListener(this);

        MethodInvoker repaint = new MethodInvoker(this, "repaint");
        SavegameModel.gameLoaded.addHandler(repaint);
        circleWhite.onLoaded().addHandler(repaint);
        circleGreen.onLoaded().addHandler(repaint);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(overlay);
        g.fillRect(0, 0, 620, 620);

        Integer value = Variables.get().savePlace.getValue();

        for (Saveplace place : Saveplace.getPlaces()) {
            LoadableImage loadableImage = value != null && value == place.getId() ? circleGreen : circleWhite;
            Image image = loadableImage.getImage();
            if (image != null) {
                g.drawImage(image, place.getMapX() - 6, place.getMapY() - 6, null);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Saveplace closest = null;
        int minDistance = 1000;

        if (e.getButton() == MouseEvent.BUTTON1) {
            int x = e.getX();
            int y = e.getY();

            for (Saveplace place : Saveplace.getPlaces()) {
                int xDiff = place.getMapX() - x;
                int yDiff = place.getMapY() - y;
                int distanceSquared = xDiff * xDiff + yDiff * yDiff;

                if (distanceSquared < minDistance) {
                    minDistance = distanceSquared;
                    closest = place;
                }
            }

            if (minDistance < 300) {
                Variables.get().savePlace.setValue(closest.getId());
                repaint();
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }
}
