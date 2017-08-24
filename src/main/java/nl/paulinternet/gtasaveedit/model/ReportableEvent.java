package nl.paulinternet.gtasaveedit.model;

import java.util.ArrayList;
import java.util.List;

public class ReportableEvent extends Event implements EventHandler
{
	private List<EventHandler> handlers;

	public ReportableEvent () {
		handlers = new ArrayList<>();
	}
	
	public void addHandler (EventHandler h) {
		handlers.add(h);
	}

	public void report () {
		handlers.forEach(h -> h.handleEvent(this));
	}

	@Override
	public void handleEvent (Event e) {
		report();
	}
}
