package nl.paulinternet.gtasaveedit.view.pages;

import nl.paulinternet.libsavegame.SavegameModel;
import nl.paulinternet.gtasaveedit.view.connected.ConnectedTextField;
import nl.paulinternet.gtasaveedit.view.swing.Table;
import nl.paulinternet.gtasaveedit.view.swing.YBox;

import javax.swing.*;

public class PageSchools extends Page {
    public PageSchools() {
        super("Schools");

        Table table = new Table();
        table.setSpacing(10, 3);

        // Driving
        String[] driving = new String[]{"The 360", "The 180", "Whip and Terminate", "Pop and Control", "Burn and Lap",
                "Cone Coil", "The '90'", "Wheelie Weave", "Spin and Go", "P.I.T. Maneuver", "Alley Oop",
                "City Slicking"};
        table.add(new JLabel("Driving"), 0, 2);
        for (int i = 0; i < 12; i++) {
            table.add(new JLabel(driving[i]), 1, 2 + i);
            table.add(new ConnectedTextField(SavegameModel.vars.schoolDriving.get(i)), 2, 2 + i);
            table.add(new JLabel("%"), 3, 2 + i);
        }

        table.add(new JSeparator(), 0, 14, 4, 1);

        // Flying
        String[] flying = new String[]{"Takeoff", "Land Plane", "Circle Airstrip", "Cricle Airstrip and Land", "Helicopter Takeoff",
                "Land Helicopter", "Destroy Targets", "Loop the Loop", "Barrel Roll", "Parachute onto Target"};
        table.add(new JLabel("Flying"), 0, 15);
        for (int i = 0; i < 10; i++) {
            table.add(new JLabel(flying[i]), 1, 15 + i);
            table.add(new ConnectedTextField(SavegameModel.vars.schoolFlying.get(i)), 2, 15 + i);
            table.add(new JLabel("%"), 3, 15 + i);
        }

        table.add(new JSeparator(), 0, 25, 4, 1);

        // Boat
        String[] boat = new String[]{"Basic Seamanship", "Plot a Course", "Fresh Slalom", "Flying Fish", "Land, Sea and Air"};
        String[] boatUnit = new String[]{"ms (less than 12000 required)", "ms (less than 40000 required)", "ms (less than 120000 required)",
                "m (more than 55 required)", "ms (less than 180000 required)"};

        table.add(new JLabel("Boat"), 0, 26);
        for (int i = 0; i < 5; i++) {
            table.add(new JLabel(boat[i]), 1, 26 + i);
            table.add(new ConnectedTextField(SavegameModel.vars.schoolBoat.get(i)), 2, 26 + i);
            table.add(new JLabel(boatUnit[i]), 3, 26 + i);
        }

        table.add(new JSeparator(), 0, 31, 4, 1);

        // Bike
        String[] bike = new String[]{"The 360", "The 180", "The Wheelie", "Jump & Stop", "The Stoppie", "Jump & Stoppie"};
        table.add(new JLabel("Bike"), 0, 32);
        for (int i = 0; i < 6; i++) {
            table.add(new JLabel(bike[i]), 1, 32 + i);
            table.add(new ConnectedTextField(SavegameModel.vars.schoolBike.get(i)), 2, 32 + i);
            table.add(new JLabel("%"), 3, 32 + i);
        }

        YBox ybox = new YBox();
        ybox.add(new JLabel("<html>You can here edit all the school values.<br />For the percent values, use 70% for bronze, 85% for silver and 100% for gold.<br />Before you can edit the driving school scores, you have to visit it once.<br />You cannot edit the last exercises of the driving, flying and boat schools if you didn't complete the school.</html>"));
        ybox.addSpace(10);
        ybox.add(table, 0, 0.0f, 0.0f);
        ybox.setBorder(10);
        setComponent(ybox, true);
    }
}
