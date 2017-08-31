package nl.paulinternet.gtasaveedit.view.pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;

import javax.swing.JLabel;

import nl.paulinternet.gtasaveedit.model.Model;
import nl.paulinternet.gtasaveedit.model.ReportableEvent;
import nl.paulinternet.gtasaveedit.model.Zone;
import nl.paulinternet.gtasaveedit.model.savegame.data.Zones;
import nl.paulinternet.gtasaveedit.view.connected.ConnectedCheckbox;
import nl.paulinternet.gtasaveedit.view.connected.ConnectedTextField;
import nl.paulinternet.gtasaveedit.view.MapImage;
import nl.paulinternet.gtasaveedit.view.component.PopcycleComboBox;
import nl.paulinternet.gtasaveedit.view.component.RectangleComponent;
import nl.paulinternet.gtasaveedit.view.selectable.SelectableItemComponent;
import nl.paulinternet.gtasaveedit.view.selectable.SelectableItemVariable;
import nl.paulinternet.gtasaveedit.view.selectable.SelectableItems;
import nl.paulinternet.gtasaveedit.view.selectable.SelectableZone;
import nl.paulinternet.gtasaveedit.view.selectable.SelectionSizeLabel;
import nl.paulinternet.gtasaveedit.view.swing.Alignment;
import nl.paulinternet.gtasaveedit.view.swing.Table;
import nl.paulinternet.gtasaveedit.view.swing.YBox;

public class PageZones extends Page implements ActionListener
{
	public static final ReportableEvent update = new ReportableEvent();
	public static final ReportableEvent selectPopCycle = new ReportableEvent();
	
	static {
		Model.gameLoaded.addHandler(update);
	}
	
	private JCheckBox buttonMapZone;
	private List<SelectableZone> items;
	private SelectableItems<SelectableZone> zones;
	private SelectableItemComponent map;

	public PageZones () {
		super("Zones");
		
		items = new ArrayList<>();
		for (Zone zone : Zones.getZones()) {
			items.add(new SelectableZone(zone));
		}
		
		zones = new SelectableItems<>(items);

		Table table = new Table();
		table.setSpacing(5, 3);
		table.setCellAlignment(1.0f, 0.5f);
		table.setCellExpand(0.0f, 0.0f);
		table.add(new JLabel("Ballas:"), 0, 0);
		table.add(new JLabel("Grove Street:"), 0, 1);
		table.add(new JLabel("Los Santos Vagos:"), 0, 2);
		table.add(new JLabel("San Fierro Rifa:"), 0, 3);
		table.add(new JLabel("Type:"), 0, 4);
				
		table.setSpacing(40, 3);
		table.add(new JLabel("Da Nang Boys:"), 3, 0);
		table.add(new JLabel("Italian Mafia:"), 3, 1);
		table.add(new JLabel("Triads:"), 3, 2);
		table.add(new JLabel("Varrio Los Aztecas:"), 3, 3);
		table.add(new JLabel("Unused Gang 1:"), 3, 4);
		table.add(new JLabel("Unused Gang 2:"), 3, 5);
		table.add(new JLabel("Dealers:"), 3, 6);
		table.add(new JLabel("Pedestrians:"), 3, 7);
		table.setSpacing(5, 3);

		SelectableItemVariable popCycle = new SelectableItemVariable(zones, Zone.POPCYCLE, 0, 0x1f);
		
		table.setCellExpand(1.0f, 0.0f);
		table.add(new ConnectedTextField(new SelectableItemVariable(zones, 0, 0, 255)), 1, 0);
		table.add(new ConnectedTextField(new SelectableItemVariable(zones, 1, 0, 255)), 1, 1);
		table.add(new ConnectedTextField(new SelectableItemVariable(zones, 2, 0, 255)), 1, 2);
		table.add(new ConnectedTextField(new SelectableItemVariable(zones, 3, 0, 255)), 1, 3);
		table.add(new PopcycleComboBox(popCycle), 1, 4);
		table.add(new ConnectedCheckbox(new SelectableItemVariable(zones, Zone.DISABLE_FOOTCOPS, 0, 255), "No cops on foot"), 1, 5, 2, 1);
				
		table.add(new ConnectedTextField(new SelectableItemVariable(zones, 4, 0, 255)), 4, 0);
		table.add(new ConnectedTextField(new SelectableItemVariable(zones, 5, 0, 255)), 4, 1);
		table.add(new ConnectedTextField(new SelectableItemVariable(zones, 6, 0, 255)), 4, 2);
		table.add(new ConnectedTextField(new SelectableItemVariable(zones, 7, 0, 255)), 4, 3);
		table.add(new ConnectedTextField(new SelectableItemVariable(zones, 8, 0, 255)), 4, 4);
		table.add(new ConnectedTextField(new SelectableItemVariable(zones, 9, 0, 255)), 4, 5);
		table.add(new ConnectedTextField(new SelectableItemVariable(zones, Zone.DEALER, 0, 255)), 4, 6);
		table.add(new ConnectedTextField(new SelectableItemVariable(zones, Zone.PED, 0, 255)), 4, 7);

		table.setCellExpand(0.0f, 0.0f);
		table.setCellAlignment(0.0f, 0.5f);
		for (int i=0; i<4; i++) table.add(new RectangleComponent(Zone.gangColor[i]), 2, i);
		for (int i=4; i<10; i++) table.add(new RectangleComponent(Zone.gangColor[i]), 5, i-4);
	
		String message = "<html>Click or drag the mouse to select zones.<br />";
		message += "Hold shift or alt to respectively grow or shrink the selection.<br />";
		message += "The gang density is usually between 0 and 40, but higher values are possible.<br />";
		message += "Note that it is dependent on the zone type whether gang members will show up.</html>";
		
		map = new SelectableItemComponent(MapImage.SAN_ANDREAS, zones, SelectableItemComponent.SMALLEST_AREA, 0.65f);

		buttonMapZone = new JCheckBox("Zone that covers the whole map selected");
		buttonMapZone.addActionListener(this);
		zones.onSelectionChange().addHandler(this, "updateMapZoneButton");
		
		YBox ybox = new YBox();
		ybox.add(new JLabel(message));
		ybox.addSpace(10);
		ybox.add(table, 0, 0.0f, 0.0f);
		ybox.addSpace(10);
		ybox.add(buttonMapZone);
		ybox.addSpace(5);
		ybox.add(map, 0, 0.0f, 0.0f);
		ybox.addSpace(5);
		ybox.add(new SelectionSizeLabel(zones, "zones"), 0, 1.0f, 0.0f);
		
		Alignment alignment = new Alignment(ybox, 0.0f, 0.0f);
		alignment.setBorder(10);
		setComponent(alignment, true);
	}

	@Override
	public void actionPerformed (ActionEvent ae) {
		items.get(0).setSelected(buttonMapZone.isSelected());
		zones.onSelectionChange().report();
	}
	
	public void updateMapZoneButton () {
		buttonMapZone.setSelected(items.get(0).isSelected());
	}
}
