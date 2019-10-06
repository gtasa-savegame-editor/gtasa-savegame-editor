package nl.paulinternet.gtasaveedit.view.cloth;

import nl.paulinternet.libsavegame.SavegameModel;
import nl.paulinternet.libsavegame.event.ReportableEvent;
import nl.paulinternet.gtasaveedit.view.swing.PCheckBox;
import nl.paulinternet.libsavegame.data.Cloth;

public class ClothCheckBox extends PCheckBox {
    public static final ReportableEvent updateView;

    static {
        updateView = new ReportableEvent();
        SavegameModel.gameLoaded.addHandler(updateView);
    }

    private Cloth cloth;

    public ClothCheckBox(Cloth cloth) {
        this.cloth = cloth;
        updateView.addHandler(this, "updateView");
        onChange().addHandler(this, "updateModel");
    }

    public void updateView() {
        setSelected(cloth.isPurchased());
    }

    public void updateModel() {
        cloth.setPurchased(isSelected());
    }
}
