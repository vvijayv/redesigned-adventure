package com.fk.poc;

import java.io.OutputStreamWriter;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class InputstreamPipe {
    public static void main(String[] args) {
        try (PipedOutputStream out = new PipedOutputStream()) {
            try (PipedInputStream in = new PipedInputStream()) {
                in.connect(out);
                OutputStreamWriter writer = new OutputStreamWriter(out);
                writer.append("line1\n");
                writer.append("line2\n");
                writer.flush();
                int available = in.available();
                System.out.println(available);
                int data = in.read();
                StringBuilder content = new StringBuilder();
                int counter = 0;
                while (data != -1) {
                    content.append((char) data);
                    counter++;
                    if (counter == available) {
                        break;
                    }
                    data = in.read();
                }
                System.out.println(content);
            } catch (Exception e) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
