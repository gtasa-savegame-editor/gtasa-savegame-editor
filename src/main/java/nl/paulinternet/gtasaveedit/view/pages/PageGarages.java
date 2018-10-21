package nl.paulinternet.gtasaveedit.view.pages;

import nl.paulinternet.gtasaveedit.model.Model;
import nl.paulinternet.gtasaveedit.model.savegame.data.*;
import nl.paulinternet.gtasaveedit.model.variables.VariableIntegerImpl;
import nl.paulinternet.gtasaveedit.view.connected.ConnectedComboBox;
import nl.paulinternet.gtasaveedit.view.swing.Alignment;
import nl.paulinternet.gtasaveedit.view.swing.Table;

import javax.swing.*;

public class PageGarages extends Page {

    public PageGarages() {
        super("Garages");

        Table table = new Table();
        table.setSpacing(10, 10);
        addHeaders(table);

        for (int i = 0; i < Garage.TOTAL_COUNT; i++) {
            Garage garage = null;
            try {
                garage = Garage.getGarages().get(i);
            } catch (IndexOutOfBoundsException e) {
                noop();
            }
            if (garage != null) {
                if (garage.getId() == 0) {
                    System.err.println("WARN: no garageId set for garage at position '" + i + "' (" + garage.getDescription() + ")");
                } else {
                    table.add(new JLabel("<html><body><p style=\"font-weight: 800;\">" + garage.getName() + "</p><p style=\"font-size: 9px;\">" + garage.getDescription() + "</p></body></html>"), 0, i + 1);
                    table.add(new PageGarages.CarBox(Model.vars.garageCars.get(i).getCarId()), 1, i + 1);
                    table.add(new PageGarages.RadioBox(Model.vars.garageCars.get(i).getRadioId()), 2, i + 1);
                    table.add(new PageGarages.Color1Box(Model.vars.garageCars.get(i).getColor1()), 3, i + 1);
                    table.add(new PageGarages.Color2Box(Model.vars.garageCars.get(i).getColor2()), 4, i + 1);
                    for (int j = 0; j < Garage.Car.MOD_COUNT; j++) {
                        table.add(new PageGarages.ModsBox(Model.vars.garageCars.get(i).getMods().get(j)), 5 + j, i + 1);
                    }
                }
            }
        }

        Alignment alignment = new Alignment(table, 0.0f, 0.0f);
        alignment.setBorder(10);
        setComponent(alignment, true);
    }

    private void addHeaders(Table table) {
        table.add(new JLabel(fatText("Garage Name")), 0, 0);
        table.add(new JLabel(fatText("Car")), 1, 0);
        table.add(new JLabel(fatText("Radio")), 2, 0);
        table.add(new JLabel(fatText("Color 1")), 3, 0);
        table.add(new JLabel(fatText("Color 2")), 4, 0);
        for (int i = 0; i < Garage.Car.MOD_COUNT; i++) {
            table.add(new JLabel(fatText("Mod " + String.valueOf(i + 1))), 5 + i, 0);
        }
    }

    private String fatText(String text) {
        return "<html><body><span style=\"font-weight: 800;\">" + text + "</span></body></html>";
    }

    private static class CarBox extends ConnectedComboBox {
        CarBox(VariableIntegerImpl var) {
            super(var);
            VehicleType.getTypes().forEach(t -> addItem(t.getId(), t.getName() + " (" + t.getType() + ")"));
        }
    }

    private static class RadioBox extends ConnectedComboBox {
        RadioBox(VariableIntegerImpl var) {
            super(var);
            RadioStation.getStations().forEach(s -> addItem(s.getId(), s.getName()));
        }
    }

    private static class Color1Box extends ConnectedComboBox {
        Color1Box(VariableIntegerImpl var) {
            super(var);
            VehicleColor.getColors().forEach(c -> addItem(c.getId(), "rgb(" +
                    c.getRgb()[0] + "," +
                    c.getRgb()[1] + "," +
                    c.getRgb()[2] + ")"));
        }
    }

    private static class Color2Box extends ConnectedComboBox {
        Color2Box(VariableIntegerImpl var) {
            super(var);
            VehicleColor.getColors().forEach(c -> addItem(c.getId(), "rgb(" +
                    c.getRgb()[0] + "," +
                    c.getRgb()[1] + "," +
                    c.getRgb()[2] + ")"));
        }
    }

    private static class ModsBox extends ConnectedComboBox {
        public ModsBox(VariableIntegerImpl var) {
            super(var);
            VehicleMod.getMods().forEach(m -> addItem(m.getId(), m.getName() + " (" + m.getType() + ")"));
        }
    }

    private void noop() {
    }
}
