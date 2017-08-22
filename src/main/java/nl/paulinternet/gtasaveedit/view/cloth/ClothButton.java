package nl.paulinternet.gtasaveedit.view.cloth;

import nl.paulinternet.gtasaveedit.model.Cloth;
import nl.paulinternet.gtasaveedit.model.Model;
import nl.paulinternet.gtasaveedit.model.Variable;
import nl.paulinternet.gtasaveedit.view.swing.PRadioButton;

public class ClothButton extends PRadioButton
{
	private Cloth cloth;
	private Variable<Cloth> var;
	
	public ClothButton (Cloth cloth, Variable<Cloth> var) {
		super(cloth.getName());
		this.cloth = cloth;
		this.var = var;
		
		onClick().addHandler(this, "updateModel");
		Model.gameLoaded.addHandler(this, "updateView");
	}
	
	public void updateModel () {
		var.setValue(cloth);
	}
	
	public void updateView () {
		setSelected(var.getValue() == cloth);
	}
}
