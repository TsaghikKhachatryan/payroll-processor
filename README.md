# Payroll Processing System

This is a Payroll Processing System (PPC) that manages the payroll of various companies ranging from small scale to large scale. The program accepts employees' data from clients in either plain text (.txt) or csv (.csv) formats to manage the employee life cycle starting from the date of onboarding to the date of exit.

# Installation and Usage

This application requires Java 11 to run. Users can supply the application with input data via text or csv file. The program runs without any errors. Follow the below steps to use the program:

1. Clone the repository to your local machine.

GitHub repository link: https://github.com/TsaghikKhachatryan/payroll-processor.git

2. Navigate to the cloned directory.

3. Build the program using any IDE.

4. Supply the input data in either plain text or csv format by providing INPUT_PACKAGE_PATH.
* currently, INPUT_PACKAGE_PATH refers to "src/main/resources/input" where test data is kept.

6. Run the main method of Runner class.

# Questions answered by the program
The program answers the following questions as output:

1. Total number of employees in an organization.
2. Month wise following details
   1. Total number of employees joined the organization with employee details like emp id,
   designation, name, surname.
   2. Total number of employees exit organization with employee details like name, surname.
3. Monthly salary report in following format
   1. Month, Total Salary, Total employees
4. Employee wise financial report in the following format
   1. Employee Id, Name, Surname, Total amount paid
5. Monthly amount released in following format
   1. Month, Total Amount (Salary + Bonus + REIMBURSEMENT), Total employees
6. Yearly financial report in the following format
   1. Event, Emp Id, Event Date, Event value