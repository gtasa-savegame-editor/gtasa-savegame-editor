package nl.paulinternet.gtasaveedit.view.cloth;

import nl.paulinternet.libsavegame.SavegameModel;
import nl.paulinternet.libsavegame.variables.Variable;
import nl.paulinternet.gtasaveedit.view.swing.PRadioButton;
import nl.paulinternet.libsavegame.data.Cloth;

public class ClothButton extends PRadioButton {
    private Cloth cloth;
    private Variable<Cloth> var;

    public ClothButton(Cloth cloth, Variable<Cloth> var) {
        super(cloth.getName());
        this.cloth = cloth;
        this.var = var;

        onClick().addHandler(this, "updateModel");
        SavegameModel.gameLoaded.addHandler(this, "updateView");
    }

    public void updateModel() {
        var.setValue(cloth);
    }

    public void updateView() {
        setSelected(var.getValue() == cloth);
    }
}
