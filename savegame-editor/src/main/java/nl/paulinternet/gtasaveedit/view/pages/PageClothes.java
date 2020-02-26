package nl.paulinternet.gtasaveedit.view.pages;

import nl.paulinternet.gtasaveedit.model.SavegameModel;
import nl.paulinternet.gtasaveedit.view.cloth.ClothCheckBox;
import nl.paulinternet.gtasaveedit.view.swing.PButton;
import nl.paulinternet.gtasaveedit.view.swing.XBox;
import nl.paulinternet.gtasaveedit.view.swing.YBox;
import nl.paulinternet.libsavegame.data.Clothes;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class PageClothes extends Page implements ChangeListener {
    public static final ButtonGroup[] clothButtonGroup, tattooButtonGroup;
    public static boolean tattoo, haircut;

    static {
        clothButtonGroup = new ButtonGroup[9];
        for (int i = 0; i < clothButtonGroup.length; i++) clothButtonGroup[i] = new ButtonGroup();
        tattooButtonGroup = new ButtonGroup[9];
        for (int i = 0; i < tattooButtonGroup.length; i++) tattooButtonGroup[i] = new ButtonGroup();
    }

    private JTabbedPane pane;
    private JPanel container;
    private YBox ybox;
    private JSplitPane splitPane;

    public PageClothes() {
        super("Clothes");

        SavegameModel.gameLoaded.addHandler(this, "onGameLoaded");

        Page[] pages = new Page[]{
                new ClothPage(0),
                new ClothPage(2),
                new ClothPage(3),
                new ClothPage(4),
                new ClothPage(5),
                new ClothPage(6),
                new ClothPage(7),
                new ClothPage(8),
                new ClothPage(1),
                new ClothPage(9)
        };

        pane = new JTabbedPane();
        for (Page page : pages) {
            pane.addTab(page.getTitle(), page.getComponent());
        }
        pane.addChangeListener(this);

        PButton buttonGetAll = new PButton("Get all clothes");
        buttonGetAll.onClick().addHandler(this, "setAllPurchased", true);

        PButton buttonLoseAll = new PButton("Lose all clothes");
        buttonLoseAll.onClick().addHandler(this, "setAllPurchased", false);

        XBox xboxGet = new XBox();
        xboxGet.addSpace(0, 1);
        xboxGet.add(buttonGetAll);
        xboxGet.addSpace(10);
        xboxGet.add(buttonLoseAll);

        ybox = new YBox();
        ybox.add(xboxGet);
        ybox.addSpace(5);
        ybox.add(pane, 1);
        ybox.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(400);
        splitPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        container = new JPanel(new BorderLayout());
        container.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        container.add(ybox);

        setComponent(container, false);
    }

    public void onGameLoaded() {
        for (ButtonGroup b : clothButtonGroup) b.clearSelection();
        for (ButtonGroup b : tattooButtonGroup) b.clearSelection();
    }

    public void setAllPurchased(boolean value) {
        Clothes.setAllPurchased(value);
        ClothCheckBox.updateView.report();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        boolean newTattoo = pane.getSelectedIndex() == 9;
        boolean newHaircut = pane.getSelectedIndex() == 8;

        if (newTattoo != tattoo || newHaircut != haircut) {
            tattoo = newTattoo;
            haircut = newHaircut;
        }
    }
}
