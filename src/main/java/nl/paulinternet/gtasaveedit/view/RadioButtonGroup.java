package nl.paulinternet.gtasaveedit.view;

import javax.swing.ButtonGroup;

import nl.paulinternet.gtasaveedit.view.swing.PRadioButton;

public class RadioButtonGroup extends ButtonGroup
{
	public PRadioButton create (String name) {
		PRadioButton button = new PRadioButton(name);
		add(button);
		return button;
	}
	
	public PRadioButton create (String name, boolean selected) {
		PRadioButton button = new PRadioButton(name);
		button.setSelected(selected);
		add(button);
		return button;
	}
}
