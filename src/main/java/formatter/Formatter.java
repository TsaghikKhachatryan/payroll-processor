package formatter;

import data.employee.EmployeeRecord;
import java.util.List;

public interface Formatter {
    EmployeeRecord format(List<String> employeeDetails);
}
