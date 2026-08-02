package nl.paulinternet.gtasaveedit.view.component;

import nl.paulinternet.gtasaveedit.view.Images;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ImageComponentTest {
    @Test
    public void isALightweightSwingComponentSizedToTheImage() {
        ImageComponent component = new ImageComponent(Images.WARNING);

        assertTrue(component instanceof JComponent);
        assertTrue(component.isOpaque());
        assertEquals(Images.WARNING.getWidth(), component.getPreferredSize().width);
        assertEquals(Images.WARNING.getHeight(), component.getPreferredSize().height);
    }
}
