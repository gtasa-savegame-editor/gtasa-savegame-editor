package nl.paulinternet.gtasaveedit.event;

import java.lang.reflect.Method;

public class MethodInvoker implements EventHandler {
    private Object object;
    private Object[] args;
    private Method method;

    public MethodInvoker(Object object, String method, Object... args) {
        // Search the method
        try {
            Class<?>[] type = new Class<?>[args.length];
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof Integer) type[i] = int.class;
                else if (args[i] instanceof Boolean) type[i] = boolean.class;
                else type[i] = args[i].getClass();
            }

            this.object = object;
            this.method = object.getClass().getMethod(method, type);
            this.args = args;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void handleEvent(Event ev) {
        try {
            method.invoke(object, args);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
