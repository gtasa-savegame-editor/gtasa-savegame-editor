package nl.paulinternet.gtasaveedit.view.selectable;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class SelectedItems<E extends SelectableItem> implements Iterable<E>
{
	private class ItemIterator implements Iterator<E>
	{
		private int pos;
		
		@Override
		public boolean hasNext () {
			for (int pos = this.pos; pos<items.size(); pos++) {
				if (items.get(pos).isSelected()) return true;
			}
			return false;
		}

		@Override
		public E next () {
			while (pos < items.size()) {
				E item = items.get(pos++);
				if (item.isSelected()) return item;
			}
			throw new NoSuchElementException();
		}

		@Override
		public void remove () {
			throw new UnsupportedOperationException();
		}
	}
	
	private List<E> items;

	public SelectedItems (List<E> items) {
		this.items = items;
	}

	@Override
	public Iterator<E> iterator () {
		return new ItemIterator();
	}
	
	public int getSize () {
		int size = 0;
		for (E item : items) {
			if (item.isSelected()) size++;
		}
		return size;
	}
}
