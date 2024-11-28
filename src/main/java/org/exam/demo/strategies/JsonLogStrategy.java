package org.exam.demo.strategies;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class JsonLogStrategy implements LogStrategy {
    private final String filePath;
    private final ObjectMapper objectMapper;
    private final List<JsonLogEntry> logEntries;

    public JsonLogStrategy(String filePath) {
        this.filePath = filePath;
        this.objectMapper = new ObjectMapper();
        this.logEntries = new ArrayList<>();
        loadExistingLogs();
    }

    @Override
    public void writeLog(String message) {
        JsonLogEntry jsonLogEntry = new JsonLogEntry(message);
        logEntries.add(jsonLogEntry);

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

        try {
            objectMapper.writeValue(file, logEntries);
            System.out.println("Log saved to JSON file: " + message);
        } catch (IOException e) {
            System.err.println("Error saving log to JSON file: " + e.getMessage());
        }
    }


    private void loadExistingLogs() {
        File file = new File(filePath);
        if (file.exists()) {
            try {
                logEntries.addAll(objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, JsonLogEntry.class)));
            } catch (IOException e) {
                System.err.println("Error loading existing logs: " + e.getMessage());
            }
        }
    }

    @Getter
    @Setter
    public static class JsonLogEntry {
        private String message;
        private String timestamp;

        public JsonLogEntry() {
        }

        public JsonLogEntry(String message) {
            this.message = message;
            this.timestamp = Instant.now().toString();
        }
    }
}
