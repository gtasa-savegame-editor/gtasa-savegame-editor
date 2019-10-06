package nl.paulinternet.gtasaveedit.view.swing;

import javax.swing.*;
import java.awt.*;

public class FixedRatio extends JPanel {
    private static class Layout implements LayoutManager {
        private Component comp;
        private float ratio;

        public Layout(Component comp, float ratio) {
            this.comp = comp;
            this.ratio = ratio;
        }

        @Override
        public void layoutContainer(Container parent) {
            // Get container size
            Dimension size = parent.getSize();
            Insets insets = parent.getInsets();
            size.width -= insets.left + insets.right;
            size.height -= insets.top + insets.bottom;

            // Layout
            if ((float) size.width / (float) size.height > ratio) {
                int width = Math.round((float) size.height * ratio);
                int x = (size.width - width) / 2;
                comp.setBounds(x, 0, width, size.height);
            } else {
                int height = Math.round((float) size.width / ratio);
                int y = (size.height - height) / 2;
                comp.setBounds(0, y, size.width, height);
            }
        }

        @Override
        public Dimension minimumLayoutSize(Container parent) {
            Dimension minSize = comp.getMinimumSize();

            if ((float) minSize.width / (float) minSize.height > ratio) {
                minSize.height = Math.round((float) minSize.width / ratio);
            } else {
                minSize.width = Math.round((float) minSize.height * ratio);
            }

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
        public void addLayoutComponent(String name, Component comp) {
        }

        @Override
        public void removeLayoutComponent(Component comp) {
        }
    }

    public FixedRatio(Component comp, float ratio) {
        super(new Layout(comp, ratio));
        add(comp);
    }
}
