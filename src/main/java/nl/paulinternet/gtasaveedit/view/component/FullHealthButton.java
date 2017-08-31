package nl.paulinternet.gtasaveedit.view.component;

import nl.paulinternet.gtasaveedit.model.Model;
import nl.paulinternet.gtasaveedit.view.swing.PButton;

public class FullHealthButton extends PButton
{
	private int value;
	
	public FullHealthButton () {
		Model.vars.maxHealth.onChange().addHandler(this, "updateValue");
		onClick().addHandler(this, "setFullHealth");
	}

	public void updateValue () {
		value = Math.round(Model.vars.maxHealth.getValue() / 5.69f);
		setText(String.valueOf(value));
	}

	public void setFullHealth () {
		Model.vars.health.setValue((float) value);
	}
}
