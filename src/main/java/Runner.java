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

        Arrays.stream(Objects.requireNonNull(input.listFiles()))
                .forEach(file -> payrollProcessor.processEmployeeRecord(file.getPath()));

        payrollProcessor.getTotalNumberOfEmployees();
        payrollProcessor.getEmployeesJoinedInMonth(date);
        payrollProcessor.getEmployeesExitedInMonth(date);
        payrollProcessor.getMonthlySalaryReport(date);
        payrollProcessor.getEmployeeWiseFinancialReport();
        payrollProcessor.getMonthlyAmountReleasedReport(date);
        payrollProcessor.getYearlyFinancialReport(date.getYear());

    }
}
