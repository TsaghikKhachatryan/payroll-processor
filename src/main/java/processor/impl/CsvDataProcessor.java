package processor.impl;

import data.employee.EmployeeRecord;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import processor.DataProcessor;

public class CsvDataProcessor implements DataProcessor {
    String TRIM_REGEX = ";";

    @Override
    public List<EmployeeRecord> readEmployeeRecord(String filePath) {
        try (var br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();
            return br.lines()
                    .map(employeeRecord -> {
                        var data = splitAndTrimEmployeeRecord(employeeRecord);
                        var formatter = DataProcessor.extractFormatterType(data);
                        return formatter.format(data);
                    })
                    .collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Invalid file path: " + filePath, e);
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + filePath, e);
        }
    }

    @Override
    public List<String> splitAndTrimEmployeeRecord(String data) {
        return Arrays.stream(data.split(TRIM_REGEX))
                .filter(str -> !str.isEmpty())
                .map(String::trim)
                .collect(Collectors.toUnmodifiableList());
    }
}
