import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Optional;

// Entry point for Mugonat Demo Employee System
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=====================================");
        System.out.println("  Mugonat Demo - Employee Management ");
        System.out.println("  Developer: Denver Mazhindu          ");
        System.out.println("  For: Mugonat Systems Interview Demo ");
        System.out.println("=====================================");
        EmployeeManager manager = new EmployeeManager(); // Loads data on instantiation

        while (true) {
            System.out.println("\n1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Remove Employee");
            System.out.println("4. Update Employee");
            System.out.println("5. Save & Exit");
            System.out.print("\nChoose an option: ");

            int choice = -1;
            try {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine(); // clear the invalid input
                continue; // Skip the rest of the loop and show the menu again
            }
            sc.nextLine(); // consume the rest of the line

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Department: ");
                    String dept = sc.nextLine();

                    // Robust input for numbers
                    double salary = getDoubleInput(sc, "Enter Basic Salary: ");
                    int ot = getIntInput(sc, "Enter Overtime Hours: ");

                    Employee emp = new Employee(id, name, dept, salary, ot);
                    manager.addEmployee(emp);
                    break;

                case 2:
                    manager.viewEmployees();
                    break;

                case 3:
                    System.out.print("Enter ID to remove: ");
                    String removeId = sc.nextLine().trim();
                    manager.removeEmployee(removeId);
                    break;

                case 4:
                    System.out.print("Enter ID of employee to update: ");
                    String updateId = sc.nextLine().trim();
                    handleUpdateEmployee(sc, manager, updateId);
                    break;

                case 5:
                    manager.saveEmployees();
                    System.out.println("Goodbye Mugonat Systems ");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Try again!");
            }
        }
    }

    // Handles the logic for updating an employee
    private static void handleUpdateEmployee(Scanner sc, EmployeeManager manager, String id) {
        Optional<Employee> employeeOptional = manager.findEmployeeById(id);

        if (employeeOptional.isEmpty()) {
            System.out.println("Error: Employee with ID '" + id + "' not found.");
            return;
        }

        Employee emp = employeeOptional.get();
        System.out.println("Found Employee: " + emp);

        while (true) {
            System.out.println("\nWhat do you want to update?");
            System.out.println("1. Name");
            System.out.println("2. Department");
            System.out.println("3. Basic Salary");
            System.out.println("4. Overtime Hours");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = getIntInput(sc, "");

            switch (choice) {
                case 1:
                    System.out.print("Enter new Name: ");
                    emp.setName(sc.nextLine());
                    System.out.println("Name updated.");
                    break;
                case 2:
                    System.out.print("Enter new Department: ");
                    emp.setDepartment(sc.nextLine());
                    System.out.println("Department updated.");
                    break;
                case 3:
                    emp.setBasicSalary(getDoubleInput(sc, "Enter new Basic Salary: "));
                    System.out.println("Salary updated.");
                    break;
                case 4:
                    emp.setOvertimeHours(getIntInput(sc, "Enter new Overtime Hours: "));
                    System.out.println("Overtime updated.");
                    break;
                case 5:
                    return; // Exit the update loop
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Helper method for robustly getting a double from the user
    private static double getDoubleInput(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = sc.nextDouble();
                sc.nextLine(); // consume newline
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.nextLine(); // clear the invalid input
            }
        }
    }

    // Helper method for robustly getting an integer from the user
    private static int getIntInput(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }
}
