package com.fk.poc;

import java.io.IOException;
import java.util.Objects;

import com.github.jknack.handlebars.Options;

public class HandlebarsEqualsHelper {
    public CharSequence equals(final Object obj1, final Options options) throws IOException {
        Object obj2 = options.param(0);
        return Objects.equals(obj1, obj2) ? options.fn() : options.inverse();
    }
}
