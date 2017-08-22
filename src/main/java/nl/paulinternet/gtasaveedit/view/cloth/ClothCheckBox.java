package nl.paulinternet.gtasaveedit.view.cloth;

import nl.paulinternet.gtasaveedit.model.Cloth;
import nl.paulinternet.gtasaveedit.model.Model;
import nl.paulinternet.gtasaveedit.model.ReportableEvent;
import nl.paulinternet.gtasaveedit.view.swing.PCheckBox;

public class ClothCheckBox extends PCheckBox
{
	public static final ReportableEvent updateView;
	
	static {
		updateView = new ReportableEvent();
		Model.gameLoaded.addHandler(updateView);
	}
	
	private Cloth cloth;
	
	public ClothCheckBox (Cloth cloth) {
		this.cloth = cloth;
		updateView.addHandler(this, "updateView");
		onChange().addHandler(this, "updateModel");
	}
	
	public void updateView () {
		setSelected(cloth.isPurchased());
	}
	
	public void updateModel () {
		cloth.setPurchased(isSelected());
	}
}
