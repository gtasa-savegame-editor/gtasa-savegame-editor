package nl.paulinternet.gtasaveedit.view.pages;

import nl.paulinternet.gtasaveedit.model.Model;
import nl.paulinternet.gtasaveedit.model.savegame.data.Garage;
import nl.paulinternet.gtasaveedit.model.savegame.data.VehicleColor;
import nl.paulinternet.gtasaveedit.model.savegame.data.VehicleType;
import nl.paulinternet.gtasaveedit.model.variables.VariableIntegerImpl;
import nl.paulinternet.gtasaveedit.view.connected.ConnectedComboBox;
import nl.paulinternet.gtasaveedit.view.swing.Alignment;
import nl.paulinternet.gtasaveedit.view.swing.Table;

import javax.swing.*;

public class PageGarages extends Page {

    public PageGarages() {
        super("Garages");

        String[] garages = new String[Garage.TOTAL_COUNT];

        Table table = new Table();
        table.setSpacing(10, 3);
        table.add(new JLabel("Garage Name"), 0, 0);
        table.add(new JLabel("Car"), 1, 0);
        table.add(new JLabel("Radio"), 2, 0);
        table.add(new JLabel("Color 1"), 3, 0);
        table.add(new JLabel("Color 2"), 4, 0);

        for (int i = 0; i < garages.length; i++) {
            try {
                table.add(new JLabel(String.valueOf(Garage.getGarages().get(i).getName())), 0, i + 1);
            } catch (IndexOutOfBoundsException e) {
                table.add(new JLabel(String.valueOf("OutOfBounds/Missing")), 0, i + 1);
            }
            table.add(new PageGarages.CarBox(Model.vars.carId.get(i)), 1, i + 1);
            table.add(new PageGarages.RadioBox(Model.vars.radioId.get(i)), 2, i + 1);
            table.add(new PageGarages.Color1Box(Model.vars.color1Id.get(i)), 3, i + 1);
            table.add(new PageGarages.Color2Box(Model.vars.color2Id.get(i)), 4, i + 1);
        }

        Alignment alignment = new Alignment(table, 0.0f, 0.0f);
        alignment.setBorder(10);
        setComponent(alignment, true);
    }

    private static class CarBox extends ConnectedComboBox {
        CarBox(VariableIntegerImpl var) {
            super(var);
            VehicleType.getTypes().forEach(t -> addItem(t.getId(), t.getName() + "(" + t.getType() + ")"));
        }
    }

    private static class RadioBox extends ConnectedComboBox {
        RadioBox(VariableIntegerImpl var) {
            super(var);
            addItem(0x00, "Radio Off");
            addItem(0x01, "Playback FM");
            addItem(0x02, "K Rose");
            addItem(0x03, "K-DST");
            addItem(0x04, "Bounce FM");
            addItem(0x05, "SF-UR");
            addItem(0x06, "Radio Los Santos");
            addItem(0x07, "Radio X");
            addItem(0x08, "CSR 103.9");
            addItem(0x09, "K-Jah West");
            addItem(0x0A, "Master Sounds 98.3");
            addItem(0x0B, "WCTR");
            addItem(0x0C, "User Track Player");
            addItem(0x0D, "Vehicle has no radio");
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

    /*private static class ModsBox extends ConnectedComboBox {//????????????
        public ModsBox(VariableString var) { //????????????
            super(var);//????????????
            VehicleMod.getMods().forEach(m -> addItem(m.getId(), m.getName() + "(" + m.getType() + ")"));//????????????
        }
    }*/
}
