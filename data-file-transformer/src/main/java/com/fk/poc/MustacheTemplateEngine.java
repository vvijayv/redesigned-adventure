package com.fk.poc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;

public class MustacheTemplateEngine implements TemplateEngine {

    public MustacheTemplateEngine() {

    }

    @Override
    public String apply(Object data, File templateFile) throws FileNotFoundException {
        Template template = Mustache.compiler().compile(new FileReader(templateFile));
        return template.execute(data);
    }

    @Override
    public String apply(Object data, String templateData) {
        Template template = Mustache.compiler().compile(templateData);
        return template.execute(data);
    }

}
