package nl.paulinternet.gtasaveedit.view;

import javax.swing.JLabel;

import nl.paulinternet.gtasaveedit.model.savegame.variables.VariableIntegerImpl;

public class IntLabel extends JLabel
{
	private String text;
	private VariableIntegerImpl var;
	
	public IntLabel (String text, VariableIntegerImpl var) {
		this.text = text;
		this.var = var;
		var.onChange().addHandler(this, "updateText");
		updateText();
	}

	public void updateText () {
		setText(text.replace("?", var.getText()));
	}
}
