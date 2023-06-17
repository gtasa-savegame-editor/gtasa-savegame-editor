package nl.paulinternet.gtasaveedit.view.selectable;

import nl.paulinternet.gtasaveedit.model.SavegameModel;
import nl.paulinternet.gtasaveedit.event.ReportableEvent;

import java.util.List;

public class SelectableItems<T extends SelectableItem> {
    private final List<T> items;
    private final SelectedItems<T> selectedItems;
    private final ReportableEvent onSelectionChange;
    private final ReportableEvent onDataChange;

    public SelectableItems(List<T> items) {
        this.items = items;
        selectedItems = new SelectedItems<T>(items);
        onSelectionChange = new ReportableEvent();
        onDataChange = new ReportableEvent();
        SavegameModel.gameLoaded.addHandler(onDataChange);
    }

    public List<T> getItems() {
        return items;
    }

    public SelectedItems<T> getSelectedItems() {
        return selectedItems;
    }

    /**
     * Gets the selection change event.
     * The event is fired by the class SelectableItemComponent.
     *
     * @return an instance of ReportableEvent
     */
    public ReportableEvent onSelectionChange() {
        return onSelectionChange;
    }

    /**
     * Gets the event that is fired when data might have changed.
     *
     * @return an instance of ReportableEvent
     */
    public ReportableEvent onDataChange() {
        return onDataChange;
    }
}
