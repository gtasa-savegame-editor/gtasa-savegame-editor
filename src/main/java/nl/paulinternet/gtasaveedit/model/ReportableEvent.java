package nl.paulinternet.gtasaveedit.model;

import java.util.ArrayList;
import java.util.List;

public class ReportableEvent extends Event implements EventHandler
{
	private List<EventHandler> handlers;

	public ReportableEvent () {
		handlers = new ArrayList<EventHandler>();
	}
	
	public void addHandler (EventHandler h) {
		handlers.add(h);
	}

	public void report () {
		for (EventHandler h : handlers) {
			h.handleEvent(this);
		}
	}

	@Override
	public void handleEvent (Event e) {
		report();
	}
}
