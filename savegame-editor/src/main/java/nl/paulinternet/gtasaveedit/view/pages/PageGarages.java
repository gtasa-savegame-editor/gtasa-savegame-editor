package nl.paulinternet.gtasaveedit.view.pages;

import nl.paulinternet.gtasaveedit.view.connected.ConnectedComboBox;
import nl.paulinternet.gtasaveedit.view.connected.ConnectedTextField;
import nl.paulinternet.gtasaveedit.view.swing.Alignment;
import nl.paulinternet.gtasaveedit.view.swing.Table;
import nl.paulinternet.libsavegame.data.*;
import nl.paulinternet.libsavegame.variables.Variable;
import nl.paulinternet.libsavegame.variables.Variables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Lukas Fülling (lukas@k40s.net)
 */
public class PageGarages extends Page {

    private static final Logger log = LoggerFactory.getLogger(PageGarages.class);

    /**
     * Template string used for the initial size the dropdowns.
     */
    public static final String PROTOTYPE_DISPLAY_VALUE = "---------------------------";

    public PageGarages() {
        super("Garages");

        Table table = new Table();
        table.setSpacing(10, 10);
        table.setCellWeight(1.0, 0.0);
        table.setCellExpand(1.0f, 0.0f);
        table.setCellAlignment(0.5f, 0.5f);
        addHeaders(table);

        for (int i = 0; i < Garage.TOTAL_COUNT; i++) {
            // TODO: this whole "Garage.TOTAL_COUNT" stuff seems sus...
            addGarage(table, i);
        }

        Alignment alignment = new Alignment(table, 0.0f, 0.0f);
        alignment.setBorder(10);
        setComponent(alignment, true);
    }

    private void addGarage(Table table, int i) {
        Garage garage = null;
        try {
            List<Garage> garageList = Garage.getGarages().stream()
                    .filter(Garage::isValid)
                    .collect(Collectors.toUnmodifiableList());
            garage = garageList.get(i);
        } catch (IndexOutOfBoundsException e) {
            noop();
        }
        if (garage != null) {
            if (garage.getId() == 0) {
                log.warn("No garageId set for garage at position '" + i + "' (" + garage.getDescription() + "), skipping...");
            } else {
                JLabel nameLabel = new JLabel("<html><body><p style=\"font-weight: 800;\">" + garage.getName() + "</p><p style=\"font-size: 9px;\">" + garage.getDescription() + "</p></body></html>");

                CarBox carBox = new CarBox(Variables.get().garageCars.get(i).getCarId());
                RadioBox radioBox = new RadioBox(Variables.get().garageCars.get(i).getRadioId());
                PaintJobBox paintJobBox = new PaintJobBox(Variables.get().garageCars.get(i).getPaintJob());
                ColorBox colorBox = new ColorBox(Variables.get().garageCars.get(i).getColor1(),
                        Variables.get().garageCars.get(i).getColor2(), i);
                ConnectedTextField nitroTextField = new ConnectedTextField(Variables.get().garageCars.get(i).getNitro());

                List<ModsBox> modsBoxes = new ArrayList<>();

                for (int j = 0; j < Garage.Car.MOD_COUNT; j++) {
                    ModsBox modsBox = new ModsBox(Variables.get().garageCars.get(i).getMods().get(j), i);
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
                        Variables.get().garageCars.get(i).getMods().get(j).setValue(65535);
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

    private static class CarBox extends ConnectedComboBox<String> {
        CarBox(Variable<Integer> var) {
            super(var);
            setPrototypeDisplayValue(PROTOTYPE_DISPLAY_VALUE); // this determines dropdown width
            VehicleType.getTypes().forEach(t -> addItem(t.getId(), t.getName() + " (" + t.getType() + ")"));
        }
    }

    private static class RadioBox extends ConnectedComboBox<String> {
        RadioBox(Variable<Integer> var) {
            super(var);
            setPrototypeDisplayValue(PROTOTYPE_DISPLAY_VALUE); // this determines dropdown width
            RadioStation.getStations().forEach(s -> addItem(s.getId(), s.getName()));
        }
    }

    private static class ColorBox extends ConnectedComboBox<String> {
        public ColorBox(Variable<Integer> color1, Variable<Integer> color2, int garageCount) {
            super(new Variable<>());
            setPrototypeDisplayValue(PROTOTYPE_DISPLAY_VALUE); // this determines dropdown width
            updateView(color1, color2, garageCount);
            Variables.get().garageCars.get(garageCount).getCarId().addOnChangeListener(i -> updateView(color1, color2, garageCount));
        }

        private void updateView(Variable<Integer> color1, Variable<Integer> color2, int garageCount) {
            removeAllItems();
            Garage.Car car = Variables.get().garageCars.get(garageCount);
            Variable<Integer> carId = car.getCarId();
            if (carId.getValue() != null) {
                VehicleType type = VehicleType.getType(carId.getValue());
                if (type != null) {
                    ArrayList<VehicleColor.ColorPair> validColors = type.getValidColors();

                    log.debug("Vehicle '" + type.getName() + "' has " + validColors.size() + " valid colors");

                    var.addOnChangeListener(i -> {
                        VehicleColor.ColorPair selectedColor = validColors.get(var.getValue());
                        color1.setValue(selectedColor.getFirstColor());
                        color2.setValue(selectedColor.getSecondColor());
                    });

                    for (int i = 0; i < validColors.size(); i++) {
                        addItem(i, VehicleColor.getColor(validColors.get(i).getFirstColor()).getName() + "/" +
                                VehicleColor.getColor(validColors.get(i).getFirstColor()).getName());
                    }
                }
            }
            updateUI();
        }
    }

    private static class ModsBox extends ConnectedComboBox<String> {
        ModsBox(Variable<Integer> var, int garageCount) {
            super(var);
            setPrototypeDisplayValue(PROTOTYPE_DISPLAY_VALUE); // this determines dropdown width
            updateView(garageCount);
            Variables.get().garageCars.get(garageCount).getCarId().addOnChangeListener(i -> updateView(garageCount));
        }

        private void updateView(int garageCount) {
            removeAllItems(); // remove current contents
            Variable<Integer> id = Variables.get().garageCars.get(garageCount).getCarId();
            if (id.getValue() != null) {
                Integer carId = id.getValue();
                List<String> validMods = Arrays.asList(VehicleType.getType(carId).getValidMods());
                VehicleMod.getMods().stream() // stream mods
                        .sorted(Comparator.comparingInt(VehicleMod::getId)) // sort by id
                        .forEach(m -> addItem(m.getId(), buildDisplayName(validMods, m))); // add all to dropdown
            }
        }

        /**
         * Builds the display name for mods in a dropdown.
         * When the vehicle inside the supplied garage does not support the given mod,
         * the String <pre>[INVALID]</pre> is prepended. Since "None" is not recognized
         * as a valid mod by any vehicle, the <pre>[INVALID]</pre> is not prepended in this case.
         *
         * @param validMods a list of valid mods for a given vehicle
         * @param m         the mod that the display name should be build for
         * @return the display name of this mod
         */
        private String buildDisplayName(List<String> validMods, VehicleMod m) {
            String invalidModString = (validMods.contains(m.getDffName())) ? "" : "[INVALID] ";
            String displayName = m.getName() + " (" + m.getType() + ")";
            if (!m.getType().equals("None")) { // prevent [INVALID] when no mod is selected
                displayName = invalidModString + displayName;
            }
            return displayName;
        }
    }

    private static class PaintJobBox extends ConnectedComboBox<String> {
        PaintJobBox(Variable<Integer> var) {
            super(var);
            setPrototypeDisplayValue(PROTOTYPE_DISPLAY_VALUE); // this determines dropdown width
            VehiclePaintJob.getPaintJobs().forEach(pj -> addItem(pj.getId(), pj.getName()));
        }
    }

    private void noop() {
    }
}
