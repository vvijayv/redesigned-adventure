package com.fk.poc;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.jknack.handlebars.Options;

public class HandlebarsNumberExtractorHelper {
    public CharSequence numExtract(final String string, final Options options) throws IOException {
        Pattern p = Pattern.compile("([0-9]{1,})([a-zA-Z]{1,}[a-zA-Z0-9]{0,})");
        Matcher matcher = p.matcher(string);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return string;
    }
}
