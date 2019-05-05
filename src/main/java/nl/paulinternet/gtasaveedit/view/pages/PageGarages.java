package nl.paulinternet.gtasaveedit.view.pages;

import nl.paulinternet.gtasaveedit.model.Model;
import nl.paulinternet.gtasaveedit.model.event.Event;
import nl.paulinternet.gtasaveedit.model.event.EventHandler;
import nl.paulinternet.gtasaveedit.model.event.ReportableEvent;
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import java.util.function.Predicate;

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
        table.setCellExpand(1.0f, 0.0f);
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
                log.warn("No garageId set for garage at position '" + i + "' (" + garage.getDescription() + "), skipping...");
            } else {
                JLabel nameLabel = new JLabel("<html><body><p style=\"font-weight: 800;\">" + garage.getName() + "</p><p style=\"font-size: 9px;\">" + garage.getDescription() + "</p></body></html>");

                CarBox carBox = new CarBox(Model.vars.garageCars.get(i).getCarId());
                RadioBox radioBox = new RadioBox(Model.vars.garageCars.get(i).getRadioId());
                PaintJobBox paintJobBox = new PaintJobBox(Model.vars.garageCars.get(i).getPaintJob());
                ColorBox colorBox = new ColorBox(Model.vars.garageCars.get(i).getColor1(),
                        Model.vars.garageCars.get(i).getColor2(), i);
                ConnectedTextField nitroTextField = new ConnectedTextField(Model.vars.garageCars.get(i).getNitro());

                List<ModsBox> modsBoxes = new ArrayList<>();

                for (int j = 0; j < Garage.Car.MOD_COUNT; j++) {
                    ModsBox modsBox = new ModsBox(Model.vars.garageCars.get(i).getMods().get(j), i);
                    modsBoxes.add(j, modsBox);
                }

                nameLabel.setMinimumSize(new Dimension(128, 64));

                setPropertyEnabledState(carBox, radioBox, paintJobBox, colorBox, nitroTextField, modsBoxes);
                carBox.addActionListener(a -> setPropertyEnabledState(carBox, radioBox, paintJobBox, colorBox, nitroTextField, modsBoxes));

                table.add(nameLabel, 0, i + 2);
                table.add(carBox, 1, i + 2);
                table.add(radioBox, 2, i + 2);
                table.add(paintJobBox, 3, i + 2);
                table.add(colorBox, 4, i + 2);
                table.add(nitroTextField, 5, i + 2);

                JButton clearModsBtn = new JButton("Clear Mods");
                clearModsBtn.setToolTipText("Clicking this button will set all mods to 'None'.");
                clearModsBtn.addActionListener(e -> {
                    for (int j = 0; j < Garage.Car.MOD_COUNT; j++) {
                        Model.vars.garageCars.get(i).getMods().get(j).setIntValue(65535);
                    }
                });
                table.add(clearModsBtn, 6, i + 2);

                for (int j = 0; j < Garage.Car.MOD_COUNT; j++) {
                    table.add(modsBoxes.get(j), 7 + j, i + 2);
                }

            }
        }
    }

    private void setPropertyEnabledState(CarBox carBox, RadioBox radioBox,
                                         PaintJobBox paintJobBox, ColorBox colorBox,
                                         ConnectedTextField nitroTextField,
                                         List<ModsBox> modsBoxes) {
        radioBox.setEnabled(carBox.getSelectedIndex() != 0);
        paintJobBox.setEnabled(carBox.getSelectedIndex() != 0);
        colorBox.setEnabled(carBox.getSelectedIndex() != 0);
        nitroTextField.setEnabled(carBox.getSelectedIndex() != 0);
        modsBoxes.forEach(b -> b.setEnabled(carBox.getSelectedIndex() != 0));
    }

    private void addHeaders(Table table) {
        table.add(new JLabel("<html><body><span style=\"font-weight: 800;\">Warning:</span> This feature is very likely to cause crashes. Don't overwrite important saves when changing things here.</body></html>"), 0, 0, 8, 1);
        table.add(new JLabel(fatText("Garage Name")), 0, 1);
        table.add(new JLabel(fatText("Car")), 1, 1);
        table.add(new JLabel(fatText("Radio")), 2, 1);
        table.add(new JLabel(fatText("Paintjob")), 3, 1);
        table.add(new JLabel(fatText("Color")), 4, 1);
        table.add(new JLabel(fatText("Nitro")), 5, 1);
        table.add(new JLabel(fatText("Clear Mods")), 6, 1);
        for (int i = 0; i < Garage.Car.MOD_COUNT; i++) {
            table.add(new JLabel(fatText("Mod " + (i + 1))), 7 + i, 1);
        }
    }

    private String fatText(String text) {
        return "<html><body><span style=\"font-weight: 800;\">" + text + "</span></body></html>";
    }

    private static class CarBox extends ConnectedComboBox {
        CarBox(VariableIntegerImpl var) {
            super(var);
            setPrototypeDisplayValue("--------------"); // this determines dropdown width
            VehicleType.getTypes().forEach(t -> addItem(t.getId(), t.getName() + " (" + t.getType() + ")"));
        }
    }

    private static class RadioBox extends ConnectedComboBox {
        RadioBox(VariableIntegerImpl var) {
            super(var);
            setPrototypeDisplayValue("--------------"); // this determines dropdown width
            RadioStation.getStations().forEach(s -> addItem(s.getId(), s.getName()));
        }
    }

    private static class ColorBox extends ConnectedComboBox {
        public ColorBox(VariableIntegerImpl color1, VariableIntegerImpl color2, int garageCount) {
            super(new VariableIntegerImpl(0, 7));
            setPrototypeDisplayValue("--------------"); // this determines dropdown width
            updateView(color1, color2, garageCount);
            Model.vars.garageCars.get(garageCount).getCarId().onChange().addHandler(e -> updateView(color1, color2, garageCount));
        }

        private void updateView(VariableIntegerImpl color1, VariableIntegerImpl color2, int garageCount) {
            removeAllItems();
            Garage.Car car = Model.vars.garageCars.get(garageCount);
            VehicleType type = VehicleType.getType(car.getCarId().getIntValue());
            if (type != null) {
                ArrayList<VehicleColor.ColorPair> validColors = type.getValidColors();

                log.debug("Vehicle '" + type.getName() + "' has " + validColors.size() + " valid colors");

                var.onChange().removeAllHandlers();
                var.onChange().addHandler(e -> {
                    VehicleColor.ColorPair selectedColor = validColors.get(var.getIntValue());
                    color1.setIntValue(selectedColor.getFirstColor());
                    color2.setIntValue(selectedColor.getSecondColor());
                });

                for (int i = 0; i < validColors.size(); i++) {
                    addItem(i, VehicleColor.getColor(validColors.get(i).getFirstColor()).getName() + "/" +
                            VehicleColor.getColor(validColors.get(i).getFirstColor()).getName());
                }
            }
            updateUI();
        }
    }

    private static class ModsBox extends ConnectedComboBox {
        ModsBox(VariableIntegerImpl var, int garageCount) {
            super(var);
            setPrototypeDisplayValue("--------------"); // this determines dropdown width
            updateView(garageCount);
            Model.vars.garageCars.get(garageCount).getCarId().onChange().addHandler(e -> updateView(garageCount));
        }

        private void updateView(int garageCount) {
            removeAllItems(); // remove current contents
            VehicleMod.getMods().stream() // stream mods
                    .sorted(Comparator.comparingInt(VehicleMod::getId)) // sort by id
                    .forEach(m -> addItem(m.getId(), // add [INVALID] if vehicle can not use that mod
                            ((Arrays.asList(VehicleType.getType(Model.vars.garageCars.get(garageCount)
                                    .getCarId().getIntValue()).getValidMods()).contains(m.getDffName()))
                                    ? "" : "[INVALID] ") +
                                    m.getName() + " (" + m.getType() + ")")); // add all to dropdown
        }
    }

    private static class PaintJobBox extends ConnectedComboBox {
        PaintJobBox(VariableIntegerImpl var) {
            super(var);
            setPrototypeDisplayValue("--------------"); // this determines dropdown width
            VehiclePaintJob.getPaintJobs().forEach(pj -> addItem(pj.getId(), pj.getName()));
        }
    }

    private void noop() {
    }
}
