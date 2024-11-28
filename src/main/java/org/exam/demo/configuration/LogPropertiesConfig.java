package org.exam.demo.configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LogPropertiesConfig {
    private String format;
    private String filePath;
    private String jdbcUrl;
    private String username;
    private String password;
    private String tableName;

    public void setFormat(LogFormatType format) {
        this.format = format.getValue();
    }
}
