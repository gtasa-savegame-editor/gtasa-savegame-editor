package nl.paulinternet.libsavegame.event;

import nl.paulinternet.libsavegame.MethodInvoker;

public abstract class Event {
    public abstract void addHandler(EventHandler h);

    public void addHandler(Object object, String method, Object... args) {
        addHandler(new MethodInvoker(object, method, args));
    }

    public abstract void removeAllHandlers();
}
