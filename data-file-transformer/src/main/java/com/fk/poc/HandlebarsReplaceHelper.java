package com.fk.poc;

import com.github.jknack.handlebars.Options;

public class HandlebarsReplaceHelper {
    private static final String SYMBOL = "\\?";

    public CharSequence replace(final Object obj1, final Options options) {
        int paramLength = options.params.length;
        Object[] params = new Object[paramLength];
        for (int i = 0; i < paramLength; i++) {
            params[i] = options.param(i);
        }
        return evaluate(params, obj1);
    }

    private CharSequence evaluate(Object[] params, Object obj1) {
        for (Object object : params) {
            String[] args = object.toString().split(SYMBOL);
            if (args[0].equals(obj1)) {
                return args[1].trim();
            }
        }
        return obj1.toString();
    }
}
