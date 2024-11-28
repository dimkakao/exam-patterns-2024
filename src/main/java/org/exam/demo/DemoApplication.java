package org.exam.demo;

import org.exam.demo.configuration.LogFormatType;
import org.exam.demo.configuration.LogPropertiesConfig;
import org.exam.demo.configuration.LogStrategyFactory;
import org.exam.demo.strategies.LogStrategy;

import java.util.Scanner;

public class DemoApplication {

    private static final String RESOURCES_DIRECTORY = "src/main/resources/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select log format:");
        System.out.println("1. JSON");
        System.out.println("2. XML");
        System.out.println("3. Text");
        System.out.println("4. Database");

        int choice = scanner.nextInt();
        scanner.nextLine();

        LogPropertiesConfig config = new LogPropertiesConfig();

        switch (choice) {
            case 1:
                System.out.print("Enter file name for JSON log (e.g., logs.json): ");
                String jsonFileName = scanner.nextLine();
                config.setFormat(LogFormatType.JSON);
                config.setFilePath(RESOURCES_DIRECTORY + jsonFileName);
                break;

            case 2:
                System.out.print("Enter file name for XML log (e.g., logs.xml): ");
                String xmlFileName = scanner.nextLine();
                config.setFormat(LogFormatType.XML);
                config.setFilePath(RESOURCES_DIRECTORY + xmlFileName);
                break;

            case 3:
                System.out.print("Enter file name for Text log (e.g., logs.txt): ");
                String textFileName = scanner.nextLine();
                config.setFormat(LogFormatType.TEXT);
                config.setFilePath(RESOURCES_DIRECTORY + textFileName);
                break;

            case 4:
                System.out.print("Enter JDBC URL for PostgreSQL log (e.g., jdbc:postgresql://localhost:5432/pattern_exam) ");
                String jdbcUrl = scanner.nextLine();
                System.out.print("Enter username for Database log: ");
                String username = scanner.nextLine();
                System.out.print("Enter password for Database log: ");
                String password = scanner.nextLine();
                System.out.print("Enter table name for Database log: ");
                String tableName = scanner.nextLine();
                config.setFormat(LogFormatType.DATABASE);
                config.setJdbcUrl(jdbcUrl);
                config.setUsername(username);
                config.setPassword(password);
                config.setTableName(tableName);
                break;

            default:
                System.out.println("Invalid choice, exiting...");
                return;
        }

        LogStrategy logStrategy = LogStrategyFactory.getLogStrategy(config);

        Logger logger = Logger.getInstance(logStrategy);

        logger.info("Application started");

        System.out.print("Enter a message to log: ");
        String message = scanner.nextLine();
        logger.info(message);

        System.out.println("Log message has been written to the selected log format.");
    }
}
