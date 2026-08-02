package nl.paulinternet.gtasaveedit.view.component;

import javax.swing.*;
import java.awt.*;

public class RectangleComponent extends JComponent {
    private static final Dimension size = new Dimension(10, 10);

    private Color color;

    public RectangleComponent(Color color) {
        this.color = color;
    }

    @Override
    public Dimension getMinimumSize() {
        return size;
    }

    @Override
    public Dimension getPreferredSize() {
        return size;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Dimension compSize = getSize();
        int x = (compSize.width - size.width) / 2;
        int y = (compSize.height - size.height) / 2;

        g.setColor(Color.BLACK);
        g.drawRect(x, y, size.width - 1, size.height - 1);
        g.setColor(color);
        g.fillRect(x + 1, y + 1, size.width - 2, size.height - 2);
    }
}
