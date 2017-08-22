package nl.paulinternet.gtasaveedit.view;

import nl.paulinternet.gtasaveedit.model.savegame.variables.VariableBoolean;
import nl.paulinternet.gtasaveedit.view.swing.PCheckBox;

public class ConnectedCheckbox extends PCheckBox
{
	private boolean disabled;
	private VariableBoolean var;
	private String text;
	
	public ConnectedCheckbox (VariableBoolean var, String text) {
		this.text = text;
		this.var = var;
		
		// Observe
		onChange().addHandler(this, "updateToModel");
		var.onChange().addHandler(this, "updateFromModel");
		updateFromModel();
	}
	
	public void updateFromModel () {
		if (!disabled) {
			disabled = true;
			
			Boolean value = var.getBooleanValue();
			boolean varEnabled = var.isEnabled();
			boolean inbetweenValue;
			
			if (value == null) {
				setSelected(false);
				inbetweenValue = varEnabled;
			}
			else {
				setSelected(var.getBooleanValue());
				inbetweenValue = false;
			}
			
			setText(inbetweenValue ? text + " (different values)" : text);
			setEnabled(varEnabled);

			disabled = false;
		}
	}
	
	public void updateToModel () {
		if (!disabled) {
			disabled = true;
			var.setBooleanValue(isSelected());
			setText(text);
			disabled = false;
		}
	}
}
