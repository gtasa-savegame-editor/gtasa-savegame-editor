package nl.paulinternet.gtasaveedit.view;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import nl.paulinternet.gtasaveedit.model.Event;
import nl.paulinternet.gtasaveedit.model.EventHandler;
import nl.paulinternet.gtasaveedit.model.VariableIntegerImpl;
import nl.paulinternet.gtasaveedit.view.swing.PComboBox;

public class ConnectedComboBox extends PComboBox
{
	private class Handler implements ItemListener, EventHandler
	{
		private boolean disabled;
		
		@Override
		public void handleEvent (Event e) {
			if (!disabled) {
				int index = -1;
				int value = var.getIntValue();
				for (int i=0; i<values.size(); i++) {
					if (value == values.get(i)) {
						index = i;
						break;
					}
				}
				disabled = true;
				setSelectedIndex(index);
				disabled = false;
			}
		}
		
		@Override
		public void itemStateChanged (ItemEvent e) {
			if (!disabled) {
				int index = getSelectedIndex();
				if (index >= 0 && index < values.size()) {
					disabled = true;
					var.setIntValue(values.get(index));
					disabled = false;
				}
			}
		}
	}

	private VariableIntegerImpl var;
	private List<Integer> values;
	
	public ConnectedComboBox (VariableIntegerImpl var) {
		this.var = var;
		values = new ArrayList<Integer>();

		// Observe
		Handler h = new Handler();
		addItemListener(h);
		var.onChange().addHandler(h);
	}
	
	public void addItem (int value, String name) {
		super.addItem(name);
		values.add(value);
	}
}
