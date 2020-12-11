package com.fk.poc;

import java.io.IOException;

import com.github.jknack.handlebars.Options;

import org.apache.commons.text.StringEscapeUtils;

public class HandlebarsEscapeStringHelper {
    public CharSequence escape(final String string, final Options options) throws IOException {
        return StringEscapeUtils.escapeJava(StringEscapeUtils.escapeHtml4(string));
    }
}
