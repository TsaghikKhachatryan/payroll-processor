import java.time.Month;
import java.time.YearMonth;
import java.util.logging.Logger;

public class Runner {

    private static Logger logger = Logger.getLogger(Runner.class.getName());

    private static final String CSV_FILE_PATH = "src/main/resources/employeeRecord.csv";
    private static final String TXT_FILE_PATH = "src/main/resources/employeeRecord.txt";


    public static void main(String[] args) {
        var date = YearMonth.of(2023, Month.NOVEMBER);
        var payrollProcessor = new PayrollProcessor();

        payrollProcessor.processEmployeeRecord(CSV_FILE_PATH);

        payrollProcessor.getTotalNumberOfEmployees();
        payrollProcessor.getEmployeesJoinedInMonth(date);
        payrollProcessor.getEmployeesExitedInMonth(date);
        payrollProcessor.getMonthlySalaryReport(date);
        payrollProcessor.getEmployeeWiseFinancialReport();
        payrollProcessor.getMonthlyAmountReleasedReport(date);
        payrollProcessor.getYearlyFinancialReport(date.getYear());

    }
}
