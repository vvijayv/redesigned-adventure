package com.fk.poc;

import java.io.File;
import java.io.IOException;

public interface TemplateEngine {
    public String apply(Object data, File templateFile) throws IOException;

    public String apply(Object data, String template) throws IOException;
}
