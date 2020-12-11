package com.fk.poc;

import java.io.IOException;
import java.util.Objects;
import java.util.Set;

import com.github.jknack.handlebars.Options;

public class HandlebarsLastElementHelper {
    public CharSequence isLast(final Object obj1, final Options options) throws IOException {
        System.out.println(obj1);
        Object obj2 = options.context.propertySet();
        if (!(obj2 instanceof Set)) {
            return options.inverse();
        }
        return Objects.equals(obj1, obj2) ? options.fn() : options.inverse();
    }
}
