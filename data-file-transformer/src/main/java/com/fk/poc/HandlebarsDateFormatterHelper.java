package com.fk.poc;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.github.jknack.handlebars.Options;

public class HandlebarsDateFormatterHelper {
    public CharSequence dtFormat(final String dateString, final Options options) throws IOException {
        Object dateFormat = options.param(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat.toString());
        return dateFormat != null ? LocalDateTime.parse(dateString.toString(), formatter).toString()
                : dateString.toString();
    }
}
