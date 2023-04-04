import java.time.YearMonth;

public class Runner {

    private static final String CSV_FILE_PATH = "src/main/resources/employeeRecord.csv";
    private static final String TXT_FILE_PATH = "src/main/resources/employeeRecord.txt";


    public static void main(String[] args) {

        PayrollProcessor payrollProcessor = new PayrollProcessor();
        payrollProcessor.processEmployeeRecord(CSV_FILE_PATH);
//        System.out.println(payrollProcessor.getTotalNumberOfEmployees());
//        System.out.println(payrollProcessor.getEmployeesExitedInMonth("10"));
//        System.out.println(payrollProcessor.getEmployeesJoinedInMonth("10"));
//        System.out.println(payrollProcessor.getMonthlySalaryReport());
        System.out.println(payrollProcessor.getEmployeeWiseFinancialReport());
        YearMonth thisYearMonth = YearMonth.of(2017, 8);

    }
}
