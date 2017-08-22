package nl.paulinternet.gtasaveedit.model;

public abstract class Event
{
	public abstract void addHandler (EventHandler h);
	
	public void addHandler (Object object, String method, Object... args) {
		addHandler(new MethodInvoker(object, method, args));
	}
}
