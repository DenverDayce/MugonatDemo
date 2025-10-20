// Mugonat Demo Employee Management System
// Developer: Denver Mazhindu
// Purpose: Demonstration project for Mugonat Systems interview
// Date: October 2025

public class Employee {
    private String id;
    private String name;
    private String department;
    private double basicSalary;
    private int overtimeHours;

    public Employee(String id, String name, String department, double basicSalary, int overtimeHours) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.basicSalary = basicSalary;
        this.overtimeHours = overtimeHours;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public int getOvertimeHours() {
        return overtimeHours;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public void setOvertimeHours(int overtimeHours) {
        this.overtimeHours = overtimeHours;
    }

    public double calculateTotalSalary() {
        double overtimePay = overtimeHours * 5.0; // each overtime hour = $5
        return basicSalary + overtimePay;
    }

    @Override
    public String toString() {
        // Format: ID | Name | Department | $Salary | OT: Hours
        return id + " | " + name + " | " + department + " | $" + basicSalary + " | OT: " + overtimeHours;
    }
}
