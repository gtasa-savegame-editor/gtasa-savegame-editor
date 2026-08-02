package nl.paulinternet.gtasaveedit.view.component;

import org.junit.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RectangleComponentTest {
    @Test
    public void isALightweightSwingComponentOfFixedSize() {
        RectangleComponent component = new RectangleComponent(Color.RED);

        assertTrue(component instanceof JComponent);
        assertEquals(new Dimension(10, 10), component.getMinimumSize());
        assertEquals(new Dimension(10, 10), component.getPreferredSize());
    }
}
