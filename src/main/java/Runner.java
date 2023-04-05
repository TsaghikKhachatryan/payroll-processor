import java.io.File;
import java.time.Month;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.Objects;

public class Runner {

    private static final String INPUT_PACKAGE_PATH = "src/main/resources/input";

    public static void main(String[] args) {
        var payrollProcessor = new PayrollProcessor();
        var input = new File(INPUT_PACKAGE_PATH);
        var date = YearMonth.of(2023, Month.NOVEMBER);
        var monthlyAmountReleaseDate = YearMonth.of(2020, Month.OCTOBER);

        processInputFolder(payrollProcessor, input);

        printTotalAmountOfEmployees(payrollProcessor);

        printEmployeesJoinedInMonth(payrollProcessor, date);

        printEmployeesExitInMonth(payrollProcessor, date);

        printMonthlySalaryReport(payrollProcessor, date);

        printMonthlyAmountReleasedReport(payrollProcessor, monthlyAmountReleaseDate);

        printEmployeeWiseFinancialReport(payrollProcessor);

        printYearlyFinancialReport(payrollProcessor, date);
    }

    private static void processInputFolder(PayrollProcessor payrollProcessor, File input) {
        Arrays.stream(Objects.requireNonNull(input.listFiles()))
                .forEach(file -> payrollProcessor.processEmployeeRecord(file.getPath()));
    }

    private static void printYearlyFinancialReport(PayrollProcessor payrollProcessor, YearMonth date) {
        var yearlyFinancialReport = payrollProcessor.getYearlyFinancialReport(date.getYear());
        System.out.println("Yearly financial report: ");
        yearlyFinancialReport.forEach(System.out::println);
        printNewLine();
    }

    private static void printEmployeeWiseFinancialReport(PayrollProcessor payrollProcessor) {
        var employeeWiseFinancialReport = payrollProcessor.getEmployeeWiseFinancialReport();
        System.out.println("Employee Wise Financial Report: ");
        employeeWiseFinancialReport.entrySet().stream()
                .forEach(entry -> System.out.println("employee Id: " + entry.getKey() + " : " + entry.getValue()));
        printNewLine();
    }

    private static void printMonthlySalaryReport(PayrollProcessor payrollProcessor, YearMonth date) {
        var monthlySalaryReport = payrollProcessor.getMonthlySalaryReport(date);
        System.out.println("Monthly Salary Report: " + monthlySalaryReport);
        printNewLine();
    }

    private static void printMonthlyAmountReleasedReport(PayrollProcessor payrollProcessor, YearMonth monthlyAmountReleaseDate) {
        var monthlyAmountReleasedReport = payrollProcessor.getMonthlyAmountReleasedReport(monthlyAmountReleaseDate);
        System.out.println("Monthly Amount Released Report: " + monthlyAmountReleasedReport);
        printNewLine();
    }

    private static void printTotalAmountOfEmployees(PayrollProcessor payrollProcessor) {
        var totalNumberOfEmployees = payrollProcessor.getTotalNumberOfEmployees();
        System.out.println("Total Number Of Employees: " + totalNumberOfEmployees + "\n");
    }

    private static void printEmployeesJoinedInMonth(PayrollProcessor payrollProcessor, YearMonth date) {
        var employeesJoinedInMonth = payrollProcessor.getEmployeesJoinedInMonth(date);
        System.out.println("Employees Joined In Month: ");
        employeesJoinedInMonth.forEach(System.out::println);
        printNewLine();
    }

    private static void printEmployeesExitInMonth(PayrollProcessor payrollProcessor, YearMonth date) {
        var employeesExitedInMonth = payrollProcessor.getEmployeesExitedInMonth(date);
        System.out.println("Employees Exit In Month: ");
        employeesExitedInMonth.forEach(System.out::println);
        printNewLine();
    }

    private static void printNewLine() {
        System.out.println();
    }
}
