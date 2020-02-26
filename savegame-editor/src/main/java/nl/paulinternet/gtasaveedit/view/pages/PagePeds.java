package nl.paulinternet.gtasaveedit.view.pages;

import nl.paulinternet.gtasaveedit.event.MethodInvoker;
import nl.paulinternet.gtasaveedit.model.SavegameModel;
import nl.paulinternet.gtasaveedit.event.EventHandler;
import nl.paulinternet.gtasaveedit.view.swing.*;
import nl.paulinternet.libsavegame.SavegameVars;

import javax.swing.*;

public class PagePeds extends Page {
    private static final String[] PED_GROUPS = new String[]{
            "Player 1", "Player 2", "Player network", "Player unused", "Male civilian", "Female civilian", "Cop",
            "Ballas", "Grove Street", "Los Santos Vagos", "San Fierro Rifa", "Da Nang Boys", "Italian Mafia", "Triads",
            "Varrio Los Aztecas", "Gang 9 (unused)", "Gang 10 (unused)", "Dealer", "Medic", "Fireman", "Criminal", "Bum", "Prostitute", "Special",
            "Mission 1", "Mission 2", "Mission 3", "Mission 4", "Mission 5", "Mission 6", "Mission 7", "Mission 8"
    };

    private PCheckBox[][] checkboxes;
    private PRadioButton[] radioButtons;
    private PRadioButton buttonView1, buttonView2;

    public PagePeds() {
        super("Pedestrians");

        // Event handlers
        EventHandler update = new MethodInvoker(this, "update");
        EventHandler updateModel = new MethodInvoker(this, "updateModel");
        SavegameModel.gameLoaded.addHandler(update);

        // Create checkboxes
        checkboxes = new PCheckBox[4][32];
        for (int type = 0; type < 4; type++) {
            for (int to = 0; to < 32; to++) {
                checkboxes[type][to] = new PCheckBox();
                checkboxes[type][to].onChange().addHandler(updateModel);
            }
        }

        // Create view radiobuttons
        buttonView1 = new PRadioButton("Show respect/like/etc. from the selected person type to others");
        buttonView1.setSelected(true);
        buttonView1.onClick().addHandler(update);
        buttonView2 = new PRadioButton("Show respect/like/etc. from other person types to the selected one");
        buttonView2.onClick().addHandler(update);

        ButtonGroup groupView = new ButtonGroup();
        groupView.add(buttonView1);
        groupView.add(buttonView2);

        // Create radiobuttons
        ButtonGroup group = new ButtonGroup();

        radioButtons = new PRadioButton[32];
        for (int i = 0; i < 32; i++) {
            radioButtons[i] = new PRadioButton(PED_GROUPS[i]);
            radioButtons[i].onClick().addHandler(update);
            group.add(radioButtons[i]);
        }
        radioButtons[0].setSelected(true);

        // Create tables
        Table table1 = createTable(0);
        Table table2 = createTable(16);

        // Create xbox
        XBox xbox = new XBox();
        xbox.add(table1);
        xbox.addSpace(10, 1);
        xbox.add(table2);
        xbox.addSpace(0, 1);

        // Create ybox
        YBox ybox = new YBox();
        ybox.add(new JLabel("<html>You can here set who likes or hates who.<br />Not all person types are used by the game.</html>"));
        ybox.addSpace(10);
        ybox.add(buttonView1);
        ybox.add(buttonView2);
        ybox.addSpace(10);
        ybox.add(xbox);
        ybox.setBorder(10);
        setComponent(ybox, true);
    }

    private Table createTable(int start) {
        Table table = new Table();
        table.setSpacing(10, 0);

        // Add headers
        table.add(new JLabel("Respect"), 1, 0);
        table.add(new JLabel("Like"), 2, 0);
        table.add(new JLabel("Dislike"), 3, 0);
        table.add(new JLabel("Hate"), 4, 0);

        // Add ped groups
        for (int to = start; to < start + 16; to++) {
            table.add(radioButtons[to], 0, to - start + 1);
        }

        // Add checkboxes
        for (int type = 0; type < 4; type++) {
            for (int to = start; to < start + 16; to++) {
                table.add(checkboxes[type][to], type + 1, to - start + 1);
            }
        }

        // Return
        return table;
    }

    public void update() {
        boolean swap = buttonView2.isSelected();

        int selected;
        for (selected = 0; selected < 32; selected++) {
            if (radioButtons[selected].isSelected()) break;
        }

        for (int type = 0; type < 4; type++) {
            for (int other = 0; other < 32; other++) {
                if (swap)
                    checkboxes[type][other].setSelected(SavegameVars.vars.pedAcq[type][other][selected]);
                else
                    checkboxes[type][other].setSelected(SavegameVars.vars.pedAcq[type][selected][other]);
            }
        }
    }

    public void updateModel() {
        boolean swap = buttonView2.isSelected();

        int selected;
        for (selected = 0; selected < 32; selected++) {
            if (radioButtons[selected].isSelected()) break;
        }

        for (int type = 0; type < 4; type++) {
            for (int other = 0; other < 32; other++) {
                if (swap)
                    SavegameVars.vars.pedAcq[type][other][selected] = checkboxes[type][other].isSelected();
                else
                    SavegameVars.vars.pedAcq[type][selected][other] = checkboxes[type][other].isSelected();
            }
        }
    }
}
