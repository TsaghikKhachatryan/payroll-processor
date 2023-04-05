import java.io.File;
import java.time.Month;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Logger;

public class Runner {

    private static final String INPUT_PACKAGE_PATH = "src/main/resources/input";
    private static Logger logger = Logger.getLogger(Runner.class.getName());

    public static void main(String[] args) {
        var payrollProcessor = new PayrollProcessor();
        var input = new File(INPUT_PACKAGE_PATH);
        var date = YearMonth.of(2023, Month.NOVEMBER);
        var monthlyAmountReleaseDate = YearMonth.of(2020, Month.OCTOBER);

        Arrays.stream(Objects.requireNonNull(input.listFiles()))
                .forEach(file -> payrollProcessor.processEmployeeRecord(file.getPath()));

        payrollProcessor.getEmployeeRecords().stream().forEach(System.out::println);

        var totalNumberOfEmployees = payrollProcessor.getTotalNumberOfEmployees();
        var employeesJoinedInMonth = payrollProcessor.getEmployeesJoinedInMonth(date);
        var employeesExitedInMonth = payrollProcessor.getEmployeesExitedInMonth(date);
        var monthlySalaryReport = payrollProcessor.getMonthlySalaryReport(date);
        var employeeWiseFinancialReport = payrollProcessor.getEmployeeWiseFinancialReport();
        var monthlyAmountReleasedReport = payrollProcessor.getMonthlyAmountReleasedReport(monthlyAmountReleaseDate);
        var yearlyFinancialReport = payrollProcessor.getYearlyFinancialReport(date.getYear());

    }
}
