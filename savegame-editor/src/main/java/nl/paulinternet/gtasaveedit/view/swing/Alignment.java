package nl.paulinternet.gtasaveedit.view.swing;

import javax.swing.*;
import java.awt.*;

public class Alignment extends JPanel {
    private static class Layout implements LayoutManager {
        private Component comp;
        private float alignX, alignY;
        private float expandX, expandY;

        @Override
        public void addLayoutComponent(String name, Component comp) {
        }

        @Override
        public void layoutContainer(Container parent) {
            // Get information
            Dimension minSize = comp.getMinimumSize();
            Dimension size = parent.getSize();
            Insets insets = parent.getInsets();

            // Subtract insets from size
            size.width -= insets.left + insets.right;
            size.height -= insets.top + insets.bottom;

            // Calculate size
            int width = minSize.width + (int) (expandX * (float) (size.width - minSize.width) + 0.5f);
            int height = minSize.height + (int) (expandY * (float) (size.height - minSize.height) + 0.5f);

            // Calculate position
            int x = insets.left + (int) (alignX * (float) (size.width - width) + 0.5f);
            int y = insets.top + (int) (alignY * (float) (size.height - height) + 0.5f);

            // Set bounds
            comp.setBounds(x, y, width, height);
        }

        @Override
        public Dimension minimumLayoutSize(Container parent) {
            Dimension minSize = comp.getMinimumSize();
            Insets insets = parent.getInsets();
            minSize.width += insets.left + insets.right;
            minSize.height += insets.top + insets.bottom;
            return minSize;
        }

        @Override
        public Dimension preferredLayoutSize(Container parent) {
            return minimumLayoutSize(parent);
        }

        @Override
        public void removeLayoutComponent(Component comp) {
        }
    }

    public Alignment(Component comp, float alignX, float alignY) {
        this(comp, alignX, alignY, 0.0f, 0.0f);
    }

    public Alignment(Component comp, float alignX, float alignY, float expandX, float expandY) {
        this(comp, alignX, alignY, expandX, expandY, new Layout());
    }

    private Alignment(Component comp, float alignX, float alignY, float expandX, float expandY, Layout layout) {
        super(layout);
        layout.comp = comp;
        layout.alignX = alignX;
        layout.alignY = alignY;
        layout.expandX = expandX;
        layout.expandY = expandY;
        add(comp);
    }

    public void setBorder(int size) {
        setBorder(BorderFactory.createEmptyBorder(size, size, size, size));
    }
}
