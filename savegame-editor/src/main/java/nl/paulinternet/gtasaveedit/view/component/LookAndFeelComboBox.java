package nl.paulinternet.gtasaveedit.view.component;

import com.bulenkov.darcula.DarculaLaf;
import nl.paulinternet.gtasaveedit.model.Model;
import nl.paulinternet.gtasaveedit.view.swing.PComboBox;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LookAndFeelComboBox extends PComboBox<LookAndFeelComboBox.Item> {

    private List<Item> items;

    public LookAndFeelComboBox() {
        items = new ArrayList<>();

        // Load Available System Themes
        LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
        Arrays.asList(looks).forEach(look -> {
            Item item = new Item(look.getName(), look.getClassName());
            items.add(item);
            addItem(item);
        });

        // Add darcula theme
        Item darcula = new Item("Darcula", DarculaLaf.class.getName());
        items.add(darcula);
        addItem(darcula);

        // Select Default
        copyFromModel();

        // Register Events
        onChange().addHandler(this, "copyToModel");
        Model.editSettings.lookAndFeelClassName.addOnChangeListener(s -> copyFromModel());
    }

    /**
     * Sets the current value to the default/current active theme.
     */
    private void selectDefaultValue() {
        String currentThemeName = Model.editSettings.lookAndFeelClassName.getValue();
        String activeThemeName = UIManager.getLookAndFeel().getClass().getName();
        if (!activeThemeName.equals(currentThemeName)) {
            items.forEach(i -> {
                if (activeThemeName.equals(i.className)) {
                    setSelectedItem(i);
                }
            });
        }
    }

    @SuppressWarnings("WeakerAccess") // used in handler
    public void copyFromModel() {
        String value = Model.editSettings.lookAndFeelClassName.getValue();

        if (value == null || value.isEmpty()) {
            selectDefaultValue();
        } else {
            items.forEach(i -> {
                if (i.className.equals(value)) {
                    setSelectedItem(i);
                }
            });
        }
    }

    @SuppressWarnings("unused") // used in handler
    public void copyToModel() {
        int selectedIndex = getSelectedIndex();
        Item item = items.get(selectedIndex);
        Model.editSettings.lookAndFeelClassName.setValue(item.className);
    }

    public static class Item {
        private final String name;
        private final String className;

        Item(String name, String className) {
            this.name = name;
            this.className = className;
        }

        public String toString() {
            return isDefault() ? name + " (Default)" : name;
        }

        private boolean isDefault() {
            return UIManager.getSystemLookAndFeelClassName().equals(className);
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Item)) {
                return false;
            } else {
                return name.equals(((Item) obj).name) &&
                        className.equals(((Item) obj).className);
            }
        }
    }
}
