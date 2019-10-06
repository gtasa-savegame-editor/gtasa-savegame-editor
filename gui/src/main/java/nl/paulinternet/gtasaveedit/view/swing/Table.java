package nl.paulinternet.gtasaveedit.view.swing;

import javax.swing.*;
import java.awt.*;

public class Table extends JPanel {
    private GridBagConstraints c;
    private float alignX, alignY;
    private float expandX, expandY;
    private double weightX, weightY;
    private int spaceX, spaceY;
    private Insets padding;

    public Table() {
        super(new GridBagLayout());
        setAlignmentX(0.0f);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        expandX = 1.0f;
        expandY = 1.0f;
        padding = new Insets(0, 0, 0, 0);
    }

    public void add(Component comp, int x, int y) {
        add(comp, x, y, 1, 1);
    }

    public void add(Component comp, int x, int y, int width, int height) {
        c.gridx = x;
        c.gridy = y;
        c.gridwidth = width;
        c.gridheight = height;
        c.weightx = weightX;
        c.weighty = weightY;
        c.insets = new Insets(padding.top + (y > 0 ? spaceY : 0), padding.left + (x > 0 ? spaceX : 0), padding.bottom, padding.right);
        add(new Alignment(comp, alignX, alignY, expandX, expandY), c);
    }

    public void setCellAlignment(float alignX, float alignY) {
        this.alignX = alignX;
        this.alignY = alignY;
    }

    public void setCellExpand(float expandX, float expandY) {
        this.expandX = expandX;
        this.expandY = expandY;
    }

    public void setCellPadding(int left, int right, int top, int bottom) {
        padding = new Insets(top, left, bottom, right);
    }

    public void setCellWeight(double weightX, double weightY) {
        this.weightX = weightX;
        this.weightY = weightY;
    }

    public void setSpacing(int columnSpacing, int rowSpacing) {
        spaceX = columnSpacing;
        spaceY = rowSpacing;
    }
}
