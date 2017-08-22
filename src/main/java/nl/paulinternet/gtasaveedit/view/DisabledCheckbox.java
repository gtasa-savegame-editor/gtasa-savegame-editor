package nl.paulinternet.gtasaveedit.view;

import javax.swing.JCheckBox;

public class DisabledCheckbox extends JCheckBox
{
	public DisabledCheckbox (boolean selected) {
		setEnabled(false);
		setSelected(selected);
	}
}
