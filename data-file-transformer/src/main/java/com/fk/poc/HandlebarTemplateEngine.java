package com.fk.poc;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;

public class HandlebarTemplateEngine implements TemplateEngine {

    private Handlebars handlebars;
    private Map<String, Template> templates;

    public HandlebarTemplateEngine() {
        handlebars = new Handlebars();
        handlebars.registerHelpers(new HandlebarsEqualsHelper());
        handlebars.registerHelpers(new HandlebarsDateFormatterHelper());
        handlebars.registerHelpers(new HandlebarsNumberExtractorHelper());
        handlebars.registerHelpers(new HandlebarsLastElementHelper());
        handlebars.registerHelpers(new HandlebarsEscapeStringHelper());
        handlebars.registerHelpers(new HandlebarsReplaceHelper());

        templates = Collections.synchronizedMap(new HashMap<>());
    }

    @Override
    public String apply(Object data, File templateFile) throws IOException {
        Template template = templates.get(templateFile.getAbsolutePath());
        if (template == null) {
            template = handlebars.compile(templateFile.getAbsolutePath());
            templates.put(templateFile.getAbsolutePath(), template);
        }
        return template.apply(data);
    }

    @Override
    public String apply(Object data, String templateData) throws IOException {
        Template template = templates.computeIfAbsent(templateData, (k) -> {
            try {
                return handlebars.compile(templateData);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        if (template == null) {
            template = handlebars.compile(templateData);
            templates.put(templateData, template);
        }
        return template.apply(data);
    }

}
