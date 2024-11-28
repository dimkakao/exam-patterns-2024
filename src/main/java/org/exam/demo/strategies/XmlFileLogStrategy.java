package org.exam.demo.strategies;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class XmlFileLogStrategy implements LogStrategy {
    private final String filePath;
    String formatForMessage =
            """
            <log>
                <message>
                    %s
                </message>
            </log>
            """;

    public XmlFileLogStrategy(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void writeLog(String message) {
        File file = new File(filePath);

        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getName());
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                System.err.println("Error creating new file: " + e.getMessage());
                return;
            }
        }

        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(String.format(formatForMessage, message));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
