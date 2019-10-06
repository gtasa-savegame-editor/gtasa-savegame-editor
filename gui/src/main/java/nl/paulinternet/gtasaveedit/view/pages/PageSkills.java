package nl.paulinternet.gtasaveedit.view.pages;

import nl.paulinternet.libsavegame.SavegameModel;
import nl.paulinternet.libsavegame.TextFieldInterface;
import nl.paulinternet.libsavegame.variables.Variables;
import nl.paulinternet.gtasaveedit.view.component.FullHealthButton;
import nl.paulinternet.gtasaveedit.view.component.ValueButton;
import nl.paulinternet.gtasaveedit.view.connected.ConnectedTextField;
import nl.paulinternet.gtasaveedit.view.swing.Alignment;
import nl.paulinternet.gtasaveedit.view.swing.Table;

import javax.swing.*;

public class PageSkills extends Page {
    private Table table;
    private int row;

    private <E> void addRow(String label, TextFieldInterface var, int min, int max, boolean infinity) {
        table.setCellExpand(0.0f, 0.0f);
        table.setCellAlignment(1.0f, 0.5f);
        table.add(new JLabel(label + ":", SwingConstants.RIGHT), 0, row);
        table.setCellExpand(1.0f, 1.0f);

        table.add(new ConnectedTextField(var), 1, row);
        table.add(new ValueButton(var, String.valueOf(min)), 2, row);

        if (var == SavegameModel.vars.health) {
            table.add(new FullHealthButton(), 3, row);
        } else {
            table.add(new ValueButton(var, String.valueOf(max)), 3, row);
        }

        if (infinity) {
            table.add(new ValueButton(var, "Infinity"), 4, row);
        }

        row++;
    }

    private void addSeparator() {
        table.setCellPadding(0, 0, 5, 5);
        table.add(new JSeparator(), 0, row++, 5, 1);
        table.setCellPadding(0, 0, 0, 0);
    }

    public PageSkills() {
        super("Body & Skills");

        // Create a panel with GridBagLayout
        table = new Table();
        table.setSpacing(5, 3);

        // Get variables
        Variables vars = SavegameModel.vars;

        // Add rows
        addRow("Health", vars.health, 0, 176, true);
        addRow("Max health", vars.maxHealth, 569, 1000, false);
        addRow("Armor", vars.armor, 0, 150, true);
        addSeparator();
        addRow("Fat", vars.fat, 0, 1000, false);
        addRow("Stamina", vars.stamina, 0, 1000, false);
        addRow("Muscle", vars.muscle, 0, 1000, false);
        addRow("Lung capacity", vars.lungCapacity, 0, 1000, false);
        addSeparator();
        addRow("Cycling skill", vars.cyclingSkill, 0, 1000, false);
        addRow("Bike skill", vars.bikeSkill, 0, 1000, false);
        addRow("Driving skill", vars.drivingSkill, 0, 1000, false);
        addRow("Flying skill", vars.flyingSkill, 0, 1000, false);
        addSeparator();
        addRow("Respect", vars.respect, 0, 1000, false);
        addRow("Sex appeal", vars.sexAppeal, 0, 2000, false);
        addRow("Gambling skill", vars.gamblingSkill, 0, 1000, false);

        // Create outer pannel
        Alignment alignment = new Alignment(table, 0.0f, 0.0f);
        alignment.setBorder(10);
        setComponent(alignment, true);
    }
}
