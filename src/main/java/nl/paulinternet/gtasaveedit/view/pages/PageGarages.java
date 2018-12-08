package nl.paulinternet.gtasaveedit.view.pages;

import nl.paulinternet.gtasaveedit.model.Model;
import nl.paulinternet.gtasaveedit.model.savegame.data.*;
import nl.paulinternet.gtasaveedit.model.variables.VariableIntegerImpl;
import nl.paulinternet.gtasaveedit.view.connected.ConnectedComboBox;
import nl.paulinternet.gtasaveedit.view.connected.ConnectedTextField;
import nl.paulinternet.gtasaveedit.view.swing.Alignment;
import nl.paulinternet.gtasaveedit.view.swing.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lukas Fülling (lukas@k40s.net)
 */
public class PageGarages extends Page {

    private static final Logger log = LoggerFactory.getLogger(PageGarages.class);

    public PageGarages() {
        super("Garages");

        Table table = new Table();
        table.setSpacing(10, 10);
        table.setCellWeight(1.0, 0.0);
        table.setCellExpand(0.0f, 0.0f);
        table.setCellAlignment(0.5f, 0.5f);
        addHeaders(table);

        for (int i = 0; i < Garage.TOTAL_COUNT; i++) {
            addGarage(table, i);
        }

        Alignment alignment = new Alignment(table, 0.0f, 0.0f);
        alignment.setBorder(10);
        setComponent(alignment, true);
    }

    private void addGarage(Table table, int i) {
        Garage garage = null;
        try {
            garage = Garage.getGarages().get(i);
        } catch (IndexOutOfBoundsException e) {
            noop();
        }
        if (garage != null) {
            if (garage.getId() == 0) {
                log.error("WARN: no garageId set for garage at position '" + i + "' (" + garage.getDescription() + ")");
            } else {
                JLabel nameLabel = new JLabel("<html><body><p style=\"font-weight: 800;\">" + garage.getName() + "</p><p style=\"font-size: 9px;\">" + garage.getDescription() + "</p></body></html>");
                CarBox carBox = new CarBox(Model.vars.garageCars.get(i).getCarId());
                RadioBox radioBox = new RadioBox(Model.vars.garageCars.get(i).getRadioId());
                PaintJobBox paintJobBox = new PaintJobBox(Model.vars.garageCars.get(i).getPaintJob());
                Color1Box color1Box = new Color1Box(Model.vars.garageCars.get(i).getColor1());
                Color2Box color2Box = new Color2Box(Model.vars.garageCars.get(i).getColor2());
                ConnectedTextField nitroTextField = new ConnectedTextField(Model.vars.garageCars.get(i).getNitro());

                List<ModsBox> modsBoxes = new ArrayList<>();

                for (int j = 0; j < Garage.Car.MOD_COUNT; j++) {
                    ModsBox modsBox = new ModsBox(Model.vars.garageCars.get(i).getMods().get(j));
                    modsBoxes.add(j, modsBox);
                }

                nameLabel.setMinimumSize(new Dimension(128, 64));

                setPropertyEnabledState(carBox, radioBox, paintJobBox, color1Box, color2Box, nitroTextField, modsBoxes);
                carBox.addActionListener(a -> setPropertyEnabledState(carBox, radioBox, paintJobBox, color1Box, color2Box, nitroTextField, modsBoxes));

                table.add(nameLabel, 0, i + 1);
                table.add(carBox, 1, i + 1);
                table.add(radioBox, 2, i + 1);
                table.add(paintJobBox, 3, i + 1);
                table.add(color1Box, 4, i + 1);
                table.add(color2Box, 5, i + 1);
                table.add(nitroTextField, 6, i + 1);

                for (int j = 0; j < Garage.Car.MOD_COUNT; j++) {
                    table.add(modsBoxes.get(j), 7 + j, i + 1);
                }

            }
        }
    }

    private void setPropertyEnabledState(CarBox carBox, RadioBox radioBox,
                                         PaintJobBox paintJobBox, Color1Box color1Box,
                                         Color2Box color2Box, ConnectedTextField nitroTextField,
                                         List<ModsBox> modsBoxes) {
        radioBox.setEnabled(carBox.getSelectedIndex() != 0);
        paintJobBox.setEnabled(carBox.getSelectedIndex() != 0);
        color1Box.setEnabled(carBox.getSelectedIndex() != 0);
        color2Box.setEnabled(carBox.getSelectedIndex() != 0);
        nitroTextField.setEnabled(carBox.getSelectedIndex() != 0);
        modsBoxes.forEach(b -> b.setEnabled(carBox.getSelectedIndex() != 0));
    }

    private void addHeaders(Table table) {
        table.add(new JLabel(fatText("Garage Name")), 0, 0);
        table.add(new JLabel(fatText("Car")), 1, 0);
        table.add(new JLabel(fatText("Radio")), 2, 0);
        table.add(new JLabel(fatText("Paintjob")), 3, 0);
        table.add(new JLabel(fatText("Color 1")), 4, 0);
        table.add(new JLabel(fatText("Color 2")), 5, 0);
        table.add(new JLabel(fatText("Nitro")), 6, 0);
        for (int i = 0; i < Garage.Car.MOD_COUNT; i++) {
            table.add(new JLabel(fatText("Mod " + String.valueOf(i + 1))), 7 + i, 0);
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
            VehicleColor.getColors().forEach(c -> addItem(c.getId(), c.getName()));
        }
    }

    private static class Color2Box extends ConnectedComboBox {
        Color2Box(VariableIntegerImpl var) {
            super(var);
            VehicleColor.getColors().forEach(c -> addItem(c.getId(), c.getName()));
        }
    }

    private static class ModsBox extends ConnectedComboBox {
        ModsBox(VariableIntegerImpl var) {
            super(var);
            VehicleMod.getMods().forEach(m -> addItem(m.getId(), m.getName() + " (" + m.getType() + ")"));
        }
    }

    private static class PaintJobBox extends ConnectedComboBox {
        PaintJobBox(VariableIntegerImpl var) {
            super(var);
            VehiclePaintJob.getPaintJobs().forEach(pj -> addItem(pj.getId(), pj.getName()));
        }
    }

    private void noop() {
    }
}
