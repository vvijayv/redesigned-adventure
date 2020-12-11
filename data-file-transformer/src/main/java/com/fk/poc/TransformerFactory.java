package com.fk.poc;

import java.io.IOException;

public class TransformerFactory {

    private TransformerFactory() {
    }

    private static final XMLTransformer XML_TRANSFORMER = new XMLTransformer();
    private static final JSONTransformer JSON_TRANSFORMER = new JSONTransformer();

    public static String transform(String input, String template, Context additionalContext) throws IOException {
        if (input == null || input.isEmpty()) {
            return null;
        }
        if (input.startsWith("<")) {
            return XML_TRANSFORMER.parseAndTransform(input, template, additionalContext);
        } else if (input.startsWith("{")) {
            return JSON_TRANSFORMER.parseAndTransform(input, template, additionalContext);
        }
        return null;
    }
}
