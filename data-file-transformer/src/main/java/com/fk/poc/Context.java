package com.fk.poc;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Context {

    private Map<Object, Object> contextData = new HashMap<>();

    public Context() {

    }

    public static Context newContext() {
        return new Context();
    }

    public Context addContext(Object name, Object value) {
        contextData.put(name, value);
        return this;
    }

    public Context addContexts(Map<?, ?> contexts) {
        contextData.putAll(contexts);
        return this;
    }

    public Map<Object, Object> getContexts() {
        return Collections.unmodifiableMap(contextData);
    }

}
