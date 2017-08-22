package nl.paulinternet.gtasaveedit.view;

import java.util.List;

import nl.paulinternet.gtasaveedit.model.Model;
import nl.paulinternet.gtasaveedit.model.ReportableEvent;

public class SelectableItems<T extends SelectableItem>
{
	private List<T> items;
	private SelectedItems<T> selectedItems;
	private ReportableEvent onSelectionChange;
	private ReportableEvent onDataChange;
	
	public SelectableItems (List<T> items) {
		this.items = items;
		selectedItems = new SelectedItems<T>(items);
		onSelectionChange = new ReportableEvent();
		onDataChange = new ReportableEvent();
		Model.gameLoaded.addHandler(onDataChange);
	}

	public List<T> getItems () {
		return items;
	}

	public SelectedItems<T> getSelectedItems () {
		return selectedItems;
	}
	
	/**
	 * Gets the selection change event.
	 * The event is fired by the class SelectableItemComponent.
	 * 
	 * @return an instance of ReportableEvent
	 */
	public ReportableEvent onSelectionChange () {
		return onSelectionChange;
	}
	
	/**
	 * Gets the event that is fired when data might have changed.
	 * 
	 * @return an instance of ReportableEvent
	 */
	public ReportableEvent onDataChange () {
		return onDataChange;
	}
}
