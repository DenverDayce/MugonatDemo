import java.io.*;
import java.util.*;

// Handles employee operations (add, remove, view, save)
public class EmployeeManager {
    private ArrayList<Employee> employees = new ArrayList<>();
    private final String FILE_PATH = "employees.txt";

    public EmployeeManager() {
        loadEmployees();
    }

    // Load employees from file on startup
    private void loadEmployees() {
        File file = new File(FILE_PATH);
        if (!file.exists()) { // Check if file exists
            return; // No file to load, start with an empty list
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Example line: MUG-001 | Denver | Engineering | $80000.0 | OT: 10
                if (line.trim().isEmpty())
                    continue; // Skip empty lines

                String[] parts = line.split(" \\| ");
                if (parts.length == 5) {
                    Employee emp = new Employee(parts[0].trim(), parts[1].trim(), parts[2].trim(),
                            // More robust parsing for salary
                            Double.parseDouble(parts[3].trim().replace("$", "")),
                            // More robust parsing for OT, gets only the digits
                            Integer.parseInt(parts[4].replaceAll("[^0-9]", "")));
                    employees.add(emp);
                } else {
                    System.out.println("Warning: Skipping malformed line in employees.txt: " + line);
                }
            }
            System.out.println("Successfully loaded " + employees.size() + " employee(s) from " + FILE_PATH);
        } catch (IOException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Error loading employee data: " + e.getMessage() + ". Starting with a clean slate.");
            employees.clear(); // Clear partially loaded data on error
        }
    }

    // Add a new employee
    public void addEmployee(Employee emp) {
        employees.add(emp);
        System.out.println("Employee added successfully!");
    }

    // Find an employee by ID. Returns an Optional.
    public Optional<Employee> findEmployeeById(String id) {
        return employees.stream()
                .filter(e -> e.getId().equalsIgnoreCase(id))
                .findFirst();
    }

    // Remove an employee by ID
    public void removeEmployee(String id) {
        boolean removed = employees.removeIf(e -> e.getId().equalsIgnoreCase(id));
        if (removed) {
            System.out.println("Employee with ID '" + id + "' removed successfully!");
        } else {
            System.out.println("Error: Employee with ID '" + id + "' not found.");
        }
    }

    // View all employees
    public void viewEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employee records found.");
        } else {
            System.out.println("\n--- Mugonat Demo Employee List ---");
            for (Employee e : employees) {
                // We can add the calculated total salary to the view without changing the file
                // format
                System.out.printf("%s | Total: $%.2f%n", e.toString(), e.calculateTotalSalary());
            }
        }
    }

    // Save employees to file
    public void saveEmployees() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Employee emp : employees) {
                writer.write(emp.toString());
                writer.newLine();
            }
            System.out.println("Employee data saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving employee data: " + e.getMessage());
        }
    }
}
