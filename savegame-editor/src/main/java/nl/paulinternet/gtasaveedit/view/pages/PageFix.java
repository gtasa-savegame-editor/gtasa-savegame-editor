package nl.paulinternet.gtasaveedit.view.pages;

import nl.paulinternet.gtasaveedit.model.GlitchFix;
import nl.paulinternet.gtasaveedit.model.SavegameModel;
import nl.paulinternet.gtasaveedit.view.swing.PButton;
import nl.paulinternet.gtasaveedit.view.swing.Table;
import nl.paulinternet.gtasaveedit.view.swing.YBox;
import nl.paulinternet.libsavegame.variables.Variables;

import javax.swing.*;

public class PageFix extends Page {
    private PButton buttonTraffic, buttonBasketball, buttonSaveDisk, buttonPoolPlayer, buttonGym, buttonZone;
    private JLabel labelTraffic, labelBasketball, labelPoolPlayer, labelGym, labelZone;

    public PageFix() {
        super("Fixes");

        // Buttons
        buttonBasketball = new PButton("Fix basketball glitch");
        buttonBasketball.onClick().addHandler(this, "fixBasketballGlitch");

        buttonSaveDisk = new PButton("Move save disk");
        buttonSaveDisk.onClick().addHandler(this, "moveSaveDisk");

        buttonPoolPlayer = new PButton("Fix pool player glitch");
        buttonPoolPlayer.onClick().addHandler(this, "fixPoolPlayerGlitch");

        buttonTraffic = new PButton("Fix traffic glitch");
        buttonTraffic.onClick().addHandler(this, "fixTrafficGlitch");

        buttonGym = new PButton("Fix gym glitch");
        buttonGym.onClick().addHandler(this, "fixGymGlitch");

        buttonZone = new PButton("Fix zone glitch");
        buttonZone.onClick().addHandler(this, "fixZoneGlitch");

        // Create table
        Table table = new Table();

        // Names
        table.setCellWeight(1.0, 1.0);
        table.add(new JLabel("<html><b>Basketball Glitch"), 0, 1);
        table.setSpacing(20, 10);
        table.add(new JLabel("<html><b>Pool Player Glitch"), 0, 4);
        table.add(new JLabel("<html><b>Traffic Glitch"), 0, 6);
        table.add(new JLabel("<html><b>Gym Glitch"), 0, 8);
        table.add(new JLabel("<html><b>Zone Glitch"), 0, 10);

        // Descriptions
        table.setCellExpand(1.0f, 0.0f);
        table.setSpacing(20, 0);
        table.add(new JLabel("<html>No basketball appears on the basketball courts.<br>" +
                "The cause is saving near an active basketball.<br>You can move the save disk in Madd Dogg's house to prevent this problem."), 0, 2, 1, 2);
        table.add(new JLabel("<html>When saving near an active pool player, the pool player won't appear at pool tables anymore."), 0, 5);
        table.add(new JLabel("<html>Cars travel backwards and morph into each other."), 0, 7);
        table.add(new JLabel("<html>CJ is not allowed to use the gym equipment, the game says \"you have worked out enough today; come back tomorrow\" even though you haven't exercised at all."), 0, 9);
        table.add(new JLabel("<html>Possible effects are that no taxi fares appear anywhere on the map or zone names are invalid."), 0, 11);

        // Present
        table.setCellWeight(0.0, 1.0);
        table.add(new JLabel("<html><b>Present"), 1, 0);
        table.setCellExpand(1.0f, 0.0f);
        table.setCellAlignment(0.0f, 0.5f);
        table.add(labelBasketball = new JLabel(), 1, 2);
        table.add(labelPoolPlayer = new JLabel(), 1, 5);
        table.add(labelTraffic = new JLabel(), 1, 7);
        table.add(labelGym = new JLabel(), 1, 9);
        table.add(labelZone = new JLabel(), 1, 11);

        // Buttons
        table.add(buttonBasketball, 2, 2);
        table.add(buttonSaveDisk, 2, 3);
        table.add(buttonPoolPlayer, 2, 5);
        table.add(buttonTraffic, 2, 7);
        table.add(buttonGym, 2, 9);
        table.add(buttonZone, 2, 11);

        // Create alignment
        YBox ybox = new YBox();
        ybox.add(new JLabel("<html>There are some bugs in GTA San Andreas that can cause things to change in the savegame files.<br>You can fix a few of them here."));
        ybox.addSpace(10);
        ybox.add(table);
        ybox.setBorder(10);
        setComponent(ybox, true);

        // Event
        SavegameModel.gameLoaded.addHandler(this, "onGameLoaded");
    }

    public void fixBasketballGlitch() {
        Variables.get().basketballGlitch.setValue(Boolean.FALSE);
        buttonBasketball.setEnabled(false);
        labelBasketball.setText("No");
    }

    public void moveSaveDisk() {
        GlitchFix.moveSaveDisk();
        buttonSaveDisk.setEnabled(false);
    }

    public void fixPoolPlayerGlitch() {
        Variables.get().poolPlayerGlitch.setValue(Boolean.FALSE);
        buttonPoolPlayer.setEnabled(false);
        labelPoolPlayer.setText("No");
    }

    public void fixTrafficGlitch() {
        GlitchFix.fixTrafficGlitch();
        buttonTraffic.setEnabled(false);
        labelTraffic.setText("No");
    }

    public void fixGymGlitch() {
        GlitchFix.fixGymGlitch();
        buttonGym.setEnabled(false);
        labelGym.setText("No");
    }

    public void fixZoneGlitch() {
        GlitchFix.fixZoneGlitch();
        buttonZone.setEnabled(false);
        labelZone.setText("No");
    }

    public void onGameLoaded() {
        buttonBasketball.setEnabled(Variables.get().basketballGlitch.getValue());
        buttonSaveDisk.setEnabled(true);
        buttonPoolPlayer.setEnabled(Variables.get().poolPlayerGlitch.getValue());
        buttonTraffic.setEnabled(true);
        buttonGym.setEnabled(true);
        buttonZone.setEnabled(Variables.get().zoneGlitch.getValue());

        labelBasketball.setText(Variables.get().basketballGlitch.getValue() ? "Yes" : "No");
        labelPoolPlayer.setText(Variables.get().poolPlayerGlitch.getValue() ? "Yes" : "No");
        labelTraffic.setText("Unknown");
        labelGym.setText("Unknown");
        labelZone.setText(Variables.get().zoneGlitch.getValue() ? "Yes" : "No");
    }
}
