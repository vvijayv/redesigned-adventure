package com.fk.poc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws IOException {
        App app = new App();
        app.parseXml();
        app.parseJson();
    }

    private void parseXml() throws IOException {
        Properties props = new Properties();
        String xmlData = loadFile("2.xml");
        long start = System.nanoTime();
        Context context = Context.newContext().addContext("permalink", "company-1");
        String xmlOutput = TransformerFactory.transform(xmlData, "2.xml", context);
        // String xmlOutput = TransformerFactory.transform(xmlData, "2.xml", context);

        // System.out.println(xmlOutput);
        System.out.println(
                "xml: " + TimeUnit.MILLISECONDS.convert((System.nanoTime() - start), TimeUnit.NANOSECONDS) + "ms");
    }

    private void parseJson() throws IOException {
        String jsonData = loadFile("2.json");
        long start = System.nanoTime();
        String jsonOutput = TransformerFactory.transform(jsonData, "2.json", null);
        // System.out.println(jsonOutput);
        System.out.println(
                "json: " + TimeUnit.MILLISECONDS.convert((System.nanoTime() - start), TimeUnit.NANOSECONDS) + "ms");
    }

    private String loadFile(String res) throws IOException {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(res);
        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        try (BufferedReader reader = new BufferedReader(streamReader)) {
            StringBuilder buffer = new StringBuilder();
            for (String line; (line = reader.readLine()) != null;) {
                buffer.append(line);
            }
            return buffer.toString();
        }
    }
}
