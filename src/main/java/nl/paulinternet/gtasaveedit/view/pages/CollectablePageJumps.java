package nl.paulinternet.gtasaveedit.view.pages;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import nl.paulinternet.gtasaveedit.model.Jump;
import nl.paulinternet.gtasaveedit.model.Model;
import nl.paulinternet.gtasaveedit.view.connected.ConnectedRadioButtons;
import nl.paulinternet.gtasaveedit.view.connected.ConnectedTextField;
import nl.paulinternet.gtasaveedit.view.MapImage;
import nl.paulinternet.gtasaveedit.view.selectable.SelectableItemComponent;
import nl.paulinternet.gtasaveedit.view.selectable.SelectableItemVariable;
import nl.paulinternet.gtasaveedit.view.selectable.SelectableItems;
import nl.paulinternet.gtasaveedit.view.selectable.SelectableJump;
import nl.paulinternet.gtasaveedit.view.selectable.SelectionSizeLabel;
import nl.paulinternet.gtasaveedit.view.swing.Alignment;
import nl.paulinternet.gtasaveedit.view.swing.XBox;
import nl.paulinternet.gtasaveedit.view.swing.YBox;

public class CollectablePageJumps extends Page
{
	public static class LabelFound extends JLabel
	{
		public LabelFound (SelectableItems<?> items) {
			items.onDataChange().addHandler(this, "updateText");
		}

		public void updateText () {
			int found = 0, completed = 0;
			
			for (Jump jump : Model.vars.jumps) {
				if (jump.found) found++;
				if (jump.completed) completed++;
			}
			
			setText(found + " jumps found, " + completed + " jumps completed");
		}
	}
	
	private List<SelectableJump> jumps;
	private SelectableItems<SelectableJump> items;
	
	public CollectablePageJumps () {
		super("Jumps");
		
		jumps = new ArrayList<SelectableJump>();
		items = new SelectableItems<SelectableJump>(jumps);
		SelectableItemComponent select = new SelectableItemComponent(MapImage.SAN_ANDREAS, items, SelectableItemComponent.MULTIPLE);
		
		ConnectedRadioButtons buttons = new ConnectedRadioButtons(new SelectableItemVariable(items, SelectableJump.COMPLETED));
		YBox yboxButtons = new YBox();
		yboxButtons.add(buttons.create(0, "Not found"));
		yboxButtons.add(buttons.create(1, "Found"));
		yboxButtons.add(buttons.create(2, "Completed"));
		
		XBox boxReward = new XBox();
		boxReward.add(new JLabel("Reward: $"));
		boxReward.addSpace(5);
		boxReward.add(new ConnectedTextField(new SelectableItemVariable(items, SelectableJump.REWARD)));

		XBox xbox = new XBox();
		xbox.add(yboxButtons, 1);
		xbox.addSpace(10);
		xbox.add(boxReward, 1, 1.0f, 0.0f);
		
		XBox xboxBottom = new XBox();
		xboxBottom.add(new LabelFound(items));
		xboxBottom.addSpace(10, 1);
		xboxBottom.add(new SelectionSizeLabel(items, "jumps"));
		
		YBox ybox = new YBox();
		ybox.add(new JLabel("<html>Click or drag the mouse to select jumps.<br />Hold shift or alt to respectively grow or shrink the selection."));
		ybox.addSpace(10);
		ybox.add(xbox);
		ybox.addSpace(5);
		ybox.add(select, 0, 0.0f, 0.0f);
		ybox.addSpace(5);
		ybox.add(xboxBottom);
		
		Alignment alignment = new Alignment(ybox, 0.0f, 0.0f);
		alignment.setBorder(10);
		setComponent(alignment, true);
		
		Model.gameLoaded.addHandler(this, "onGameLoad");
	}

	public void onGameLoad () {
		jumps.clear();
		for (Jump jump : Model.vars.jumps) {
			jumps.add(new SelectableJump(jump));
		}
		items.onSelectionChange().report();
	}
}
