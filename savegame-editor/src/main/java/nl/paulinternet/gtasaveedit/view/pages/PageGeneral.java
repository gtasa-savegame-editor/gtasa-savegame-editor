package nl.paulinternet.gtasaveedit.view.pages;

import nl.paulinternet.gtasaveedit.view.component.DisabledCheckbox;
import nl.paulinternet.gtasaveedit.view.component.ValueButton;
import nl.paulinternet.gtasaveedit.view.connected.ConnectedCheckbox;
import nl.paulinternet.gtasaveedit.view.connected.ConnectedComboBox;
import nl.paulinternet.gtasaveedit.view.connected.ConnectedRadioButtons;
import nl.paulinternet.gtasaveedit.view.connected.ConnectedTextField;
import nl.paulinternet.gtasaveedit.view.swing.Table;
import nl.paulinternet.gtasaveedit.view.swing.XBox;
import nl.paulinternet.gtasaveedit.view.swing.YBox;
import nl.paulinternet.libsavegame.variables.VariableTime;
import nl.paulinternet.libsavegame.variables.Variables;

import javax.swing.*;

public class PageGeneral extends Page {
    public PageGeneral() {
        super("General");

        Variables vars = Variables.get();

        ConnectedComboBox<String> versionBox = new ConnectedComboBox<>(Variables.get().version);
        versionBox.addItem(0x35da8175, "Version 1.00 Unmodified EXE");
        versionBox.addItem(0x65f3e583, "Version 1.00 Modified EXE");
        versionBox.addItem(0x9a6ebe58, "Version 1.01 Unmodified EXE");
        versionBox.addItem(0x9345765e, "Version 1.01 Modified EXE");
        versionBox.addItem(0x38009aae, "Version 1.01 (German, macOS)");
        versionBox.addItem(0xfd148df6, "Version 2.00 Unmodified EXE");
        versionBox.addItem(0x5d31cc22, "Version 2.00 (German)");

        ConnectedRadioButtons buttonsScriptVersion = new ConnectedRadioButtons(Variables.get().scriptVersion);
        ConnectedRadioButtons buttonsCurrentIpl = new ConnectedRadioButtons(Variables.get().currentIplVersion);
        ConnectedRadioButtons buttonsConvertIpl = new ConnectedRadioButtons(Variables.get().convertIplVersion);

        Table versionTable = new Table();
        versionTable.setSpacing(10, 3);
        versionTable.setCellAlignment(1.0f, 0.5f);
        versionTable.setCellExpand(0.0f, 0.0f);
        versionTable.add(new JLabel("Version ID:"), 0, 0);
        versionTable.add(new JLabel("Script version:"), 0, 1);
        versionTable.add(new JLabel("Current IPL version:"), 0, 2);
        versionTable.add(new JLabel("Convert to IPL version:"), 0, 3);
        versionTable.setCellExpand(1.0f, 0.0f);
        versionTable.add(versionBox, 1, 0, 2, 1);
        versionTable.add(buttonsScriptVersion.create(1, "1"), 1, 1);
        versionTable.add(buttonsScriptVersion.create(2, "2"), 2, 1);
        versionTable.add(buttonsCurrentIpl.create(1, "1"), 1, 2);
        versionTable.add(buttonsCurrentIpl.create(2, "2"), 2, 2);
        versionTable.add(buttonsConvertIpl.create(1, "1"), 1, 3);
        versionTable.add(buttonsConvertIpl.create(2, "2"), 2, 3);

        XBox titlePanel = new XBox();
        titlePanel.add(new JLabel("Title:"));
        titlePanel.addSpace(10);
        titlePanel.add(new ConnectedTextField(Variables.get().title), 1);

        Table tableMoney = new Table();
        tableMoney.setSpacing(10, 3);
        tableMoney.setCellAlignment(1.0f, 0.5f);
        tableMoney.setCellExpand(0.0f, 0.0f);
        tableMoney.add(new JLabel("Money:"), 0, 0);
        tableMoney.add(new JLabel("Money on screen:"), 0, 1);
        tableMoney.setCellExpand(1.0f, 0.0f);
        tableMoney.add(new ConnectedTextField(vars.money), 1, 0);
        tableMoney.add(new ConnectedTextField(vars.moneyOnScreen), 1, 1);
        tableMoney.add(new ValueButton(vars.money, "-999999999"), 2, 0);
        tableMoney.add(new ValueButton(vars.money, "0"), 3, 0);
        tableMoney.add(new ValueButton(vars.money, "999999999"), 4, 0);
        tableMoney.add(new ValueButton(vars.moneyOnScreen, "-999999999"), 2, 1);
        tableMoney.add(new ValueButton(vars.moneyOnScreen, "0"), 3, 1);
        tableMoney.add(new ValueButton(vars.moneyOnScreen, "999999999"), 4, 1);

        Table tableTime = new Table();
        tableTime.setSpacing(10, 3);
        tableTime.setCellExpand(0.0f, 0.0f);
        tableTime.setCellAlignment(1.0f, 0.5f);
        tableTime.add(new JLabel("Days passed:"), 0, 0);
        tableTime.add(new JLabel("Day of week:"), 0, 1);
        tableTime.add(new JLabel("Time:"), 0, 2);
        tableTime.add(new JLabel("Minute length:"), 0, 3);

        ConnectedComboBox<String> dayOfWeek = new ConnectedComboBox<>(vars.timeDayOfWeek);
        dayOfWeek.addItem(2, "Monday");
        dayOfWeek.addItem(3, "Tuesday");
        dayOfWeek.addItem(4, "Wednesday");
        dayOfWeek.addItem(5, "Thursday");
        dayOfWeek.addItem(6, "Friday");
        dayOfWeek.addItem(7, "Saturday");
        dayOfWeek.addItem(1, "Sunday");

        tableTime.setCellExpand(1.0f, 0.0f);
        tableTime.add(new ConnectedTextField(vars.daysPassed), 1, 0);
        tableTime.add(dayOfWeek, 1, 1);
        tableTime.add(new ConnectedTextField(new VariableTime()), 1, 2);
        tableTime.add(new ConnectedTextField(vars.minuteLength), 1, 3);
        tableTime.add(new JLabel("milliseconds"), 2, 3);

        ConnectedRadioButtons buttons = new ConnectedRadioButtons(vars.storyLine);

        Table table = new Table();
        table.setSpacing(10, 0);
        table.add(buttons.create(0, "Los Santos"), 0, 1);
        table.add(buttons.create(1, "San Fierro"), 0, 2);
        table.add(buttons.create(2, "Las Venturas"), 0, 3);
        table.add(buttons.create(3, "Back to Los Santos"), 0, 4);
        table.add(buttons.create(4, "Completed"), 0, 5);
        table.setCellExpand(0.0f, 0.0f);
        table.setCellAlignment(0.5f, 0.5f);
        table.add(new JLabel("Access to San Fierro"), 1, 0);
        table.add(new DisabledCheckbox(false), 1, 1);
        table.add(new DisabledCheckbox(true), 1, 2);
        table.add(new DisabledCheckbox(true), 1, 3);
        table.add(new DisabledCheckbox(true), 1, 4);
        table.add(new DisabledCheckbox(true), 1, 5);
        table.add(new JLabel("Access to Las Venturas"), 2, 0);
        table.add(new DisabledCheckbox(false), 2, 1);
        table.add(new DisabledCheckbox(false), 2, 2);
        table.add(new DisabledCheckbox(true), 2, 3);
        table.add(new DisabledCheckbox(true), 2, 4);
        table.add(new DisabledCheckbox(true), 2, 5);

        Table tableStat = new Table();
        tableStat.setSpacing(5, 3);
        tableStat.setCellExpand(0.0f, 0.0f);
        tableStat.setCellAlignment(1.0f, 0.5f);
        tableStat.add(new JLabel("Times cheated:"), 0, 0);
        tableStat.add(new JLabel("Times busted:"), 0, 1);
        tableStat.add(new JLabel("Times wasted:"), 0, 2);
        tableStat.setCellAlignment(0.0f, 0.5f);
        tableStat.add(new ConnectedTextField(vars.timesCheated), 1, 0);
        tableStat.add(new ConnectedTextField(vars.timesBusted), 1, 1);
        tableStat.add(new ConnectedTextField(vars.timesWasted), 1, 2);
        tableStat.add(new ValueButton(vars.timesCheated, "0"), 2, 0);
        tableStat.add(new ValueButton(vars.timesBusted, "0"), 2, 1);
        tableStat.add(new ValueButton(vars.timesWasted, "0"), 2, 2);

        YBox boxCheckbox1 = new YBox();
        boxCheckbox1.add(new ConnectedCheckbox(vars.roadblockSF, "Roadblocks to San Fierro"));
        boxCheckbox1.add(new ConnectedCheckbox(vars.roadblockLV, "Roadblocks to Las Venturas"));
        boxCheckbox1.add(new ConnectedCheckbox(vars.gangWars, "Gang wars"));
        boxCheckbox1.add(new ConnectedCheckbox(vars.riots, "Riots in Los Santos"));
        boxCheckbox1.add(new ConnectedCheckbox(vars.uncensored, "Uncensored (you can shoot heads off and such)"));
        boxCheckbox1.add(new ConnectedCheckbox(vars.hotCoffee, "Hot Coffee (dating with sex, original mod by PatrickW)"));
        boxCheckbox1.add(new ConnectedCheckbox(vars.taxiNitro, "All taxis have nitro"));
        boxCheckbox1.add(new ConnectedCheckbox(vars.prostitutesPay, "Prostitutes pay you"));
        boxCheckbox1.add(new ConnectedCheckbox(vars.twoTimingDate, "Enable Two-Timing Dates"));

        YBox boxCheckbox2 = new YBox();
        boxCheckbox2.add(new ConnectedCheckbox(vars.loseStuffBusted, "Lose weapons when busted"));
        boxCheckbox2.add(new ConnectedCheckbox(vars.loseStuffWasted, "Lose weapons when wasted"));
        boxCheckbox2.add(new ConnectedCheckbox(vars.freeBustedOnce, "Free busted once"));
        boxCheckbox2.add(new ConnectedCheckbox(vars.freeWastedOnce, "Free wasted once"));
        boxCheckbox2.add(new ConnectedCheckbox(vars.infiniteRun, "Infinite run"));
        boxCheckbox2.add(new ConnectedCheckbox(vars.fireProof, "Fireproof"));
        boxCheckbox2.add(new ConnectedCheckbox(vars.enableDriveby, "Enable driveby"));
        boxCheckbox2.add(new ConnectedCheckbox(vars.fastReload, "Fast reload"));

        XBox boxCheckboxes = new XBox();
        boxCheckboxes.add(boxCheckbox1, 1);
        boxCheckboxes.addSpace(10);
        boxCheckboxes.add(boxCheckbox2, 1);

        YBox boxWarning1 = new YBox();
        boxWarning1.add(new ConnectedCheckbox(vars.helpCar, "Driving"));
        boxWarning1.add(new ConnectedCheckbox(vars.helpSwimming, "Swimming"));
        boxWarning1.add(new ConnectedCheckbox(vars.helpGym, "The gym"));

        YBox boxWarning2 = new YBox();
        boxWarning2.add(new ConnectedCheckbox(vars.helpStealVehicle, "Stealing a vehicle"));
        boxWarning2.add(new ConnectedCheckbox(vars.helpBusted, "Busted"));
        boxWarning2.add(new ConnectedCheckbox(vars.helpWasted, "Wasted"));

        XBox xboxReplaceWeapon = new XBox();
        xboxReplaceWeapon.add(new JLabel("Replace weapon: show"));
        xboxReplaceWeapon.addSpace(5);
        xboxReplaceWeapon.add(new ConnectedTextField(vars.helpReplaceWeapon));
        xboxReplaceWeapon.addSpace(5);
        xboxReplaceWeapon.add(new JLabel("times"));

        XBox boxWarnings = new XBox();
        boxWarnings.add(boxWarning1, 1);
        boxWarnings.addSpace(10);
        boxWarnings.add(boxWarning2, 1);

        YBox ybox = new YBox();
        ybox.add(new JLabel("It is recommended that you backup your savegame files now and then."));
        ybox.addSeparator(10);
        ybox.add(versionTable, 0, 0.0f, 0.0f);
        ybox.addSeparator(10);
        ybox.add(titlePanel);
        ybox.addSeparator(10);
        ybox.add(tableMoney, 0, 0.0f, 0.0f);
        ybox.addSeparator(10);
        ybox.add(tableTime, 0, 0.0f, 0.0f);
        ybox.addSeparator(10);
        ybox.add(new JLabel("<html>The following settings indicates your position in the storyline.<br>This option does only a few things, for example it doesn't open shops or skip missions."));
        ybox.addSpace(5);
        ybox.add(table, 0, 0.0f, 0.0f);
        ybox.addSeparator(10);
        ybox.add(boxCheckboxes);
        ybox.addSeparator(10);
        ybox.add(new JLabel("You can here disable the help messages that show up when you do something for the first time in the game:"));
        ybox.addSpace(5);
        ybox.add(boxWarnings);
        ybox.add(xboxReplaceWeapon);
        ybox.addSeparator(10);
        ybox.add(tableStat, 0, 0.0f, 0.0f);
        ybox.setBorder(10);
        setComponent(ybox, true);
    }
}
