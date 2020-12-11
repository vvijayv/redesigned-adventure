package com.fk.poc;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XMLTransformer {

    private XmlMapper xmlMapper;
    private TemplateEngine templateEngine;

    public XMLTransformer() {
        SimpleModule module = new SimpleModule().addDeserializer(Object.class,
                Issue205FixedUntypedObjectDeserializer.getInstance());
        xmlMapper = (XmlMapper) new XmlMapper().registerModule(module);
        templateEngine = new HandlebarTemplateEngine();
    }

    public String parse(String input, String template, Context additionalContext) throws IOException {
        Object data = xmlMapper.readValue(input, Object.class);
        if (additionalContext != null) {
            additionalContext.addContexts(((Map<?, ?>) data));
            return transform(additionalContext.getContexts(), template);
        }
        return transform(data, template);
    }

    private String transform(Object data, String template) throws IOException {
        return templateEngine.apply(data, template);
    }

    public String parseAndTransform(String input, String templateFile, Context additionalContext) throws IOException {
        return parse(input, templateFile, additionalContext);
    }

}
