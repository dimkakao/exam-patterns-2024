package org.exam.demo;

import lombok.RequiredArgsConstructor;
import org.exam.demo.configuration.LogLevel;
import org.exam.demo.strategies.LogStrategy;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class Logger {
    private static Logger instance;
    private final LogStrategy logStrategy;

    public static synchronized Logger getInstance(LogStrategy logStrategy) {
        if (instance == null) {
            instance = new Logger(logStrategy);
        }
        return instance;
    }

    public void log(LogLevel level, String message) {
        String logMessage = String.format("[%s] [%s]: %s", LocalDateTime.now(), level, message);
        logStrategy.writeLog(logMessage);
    }

    public void debug(String message) {
        log(LogLevel.DEBUG, message);
    }

    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    public void warning(String message) {
        log(LogLevel.WARNING, message);
    }

    public void error(String message) {
        log(LogLevel.ERROR, message);
    }

    public void critical(String message) {
        log(LogLevel.CRITICAL, message);
    }

}