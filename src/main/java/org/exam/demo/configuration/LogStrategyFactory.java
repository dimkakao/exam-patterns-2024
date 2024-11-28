package org.exam.demo.configuration;


import org.exam.demo.strategies.*;

public class LogStrategyFactory {

    public static LogStrategy getLogStrategy(LogPropertiesConfig config) {
        return switch (config.getFormat().toLowerCase()) {
            case "json" -> new JsonLogStrategy(config.getFilePath());
            case "xml" -> new XmlFileLogStrategy(config.getFilePath());
            case "text" -> new TextFileLogStrategy(config.getFilePath());
            case "database" -> new DatabaseLogStrategy(
                    config.getJdbcUrl(),
                    config.getUsername(),
                    config.getPassword(),
                    config.getTableName()
            );
            default -> throw new IllegalArgumentException("Unknown log format: " + config.getFormat());
        };
    }
}
