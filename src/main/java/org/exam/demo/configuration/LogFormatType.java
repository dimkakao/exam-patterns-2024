package org.exam.demo.configuration;

import lombok.Getter;

@Getter
public enum LogFormatType {
    JSON("json"),
    XML("xml"),
    TEXT("text"),
    DATABASE("database");

    private final String value;

    LogFormatType(String value) {
        this.value = value;
    }
}