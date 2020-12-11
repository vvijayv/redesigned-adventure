package com.fk.poc;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONTransformer {

    private ObjectMapper mapper;
    private TemplateEngine templateEngine;

    public JSONTransformer() {
        mapper = new ObjectMapper();
        templateEngine = new HandlebarTemplateEngine();
    }

    private String parse(String input, String template, Context additionalContext) throws IOException {
        Map<?, ?> data = parseJSONContent(input);
        if (additionalContext != null) {
            additionalContext.addContexts(data);
            return transform(additionalContext.getContexts(), template);
        }
        return transform(data, template);
    }

    private String transform(Map<?, ?> data, String template) throws IOException {
        return templateEngine.apply(data, template);
    }

    private Map<?, ?> parseJSONContent(String data) throws IOException {
        return mapper.readValue(data, Map.class);
    }

    public String parseAndTransform(String input, String template, Context additionalContext) throws IOException {
        return parse(input, template, additionalContext);
    }

}
