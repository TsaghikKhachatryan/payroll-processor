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

public class TextDataProcessor implements DataProcessor {

    String TRIM_REGEX = ",";

    @Override
    public List<EmployeeRecord> readEmployeeRecord(String filePath) {
        try (var br = new BufferedReader(new FileReader(filePath))) {
            return br.lines()
                    .map(line -> {
                        var employeeDetails = splitAndTrimEmployeeRecord(line);
                        var formatter = DataProcessor.extractFormatterType(employeeDetails);
                        return formatter.format(employeeDetails);
                    })
                    .collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(String.format("Invalid file path: %s", filePath), e);
        } catch (IOException e) {
            throw new RuntimeException(String.format("ERROR reading file: %s", filePath), e);
        }
    }

    @Override
    public List<String> splitAndTrimEmployeeRecord(String employeeDetails) {
        return Arrays.stream(employeeDetails.split(TRIM_REGEX))
                .map(String::trim)
                .collect(Collectors.toUnmodifiableList());
    }
}
