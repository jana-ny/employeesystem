
package employeesystem;
import java.io.*;
import java.util.Scanner;

/**
 *
 * *****************************@author jana Nabiel Alyamani*******************************************
 */
class EmployeeRecordManager {
    public Employee head;

    // method that checks if a record exists by the ID given
    private boolean checkRecord(int id) {
        Employee current = head;
        //traverse to check in all the list if the node exists
        while (current != null) {
            if (current.id == id) {
                return true; // Record found
            }
            current = current.next;
        }
        return false; // Record doesn't exist
    }

    
    // method to create a new employee record
    public void createRecord(String name, int id, String startDate, String phoneNumber, String address, int workHours, double salary) {

        if (checkRecord(id)) {
            System.out.println("Employee: " + name + ", " + id + " already exists");
            return;
        }
        if (name.isEmpty() || startDate.isEmpty() || phoneNumber.isEmpty() || address.isEmpty()) {
            throw new IllegalArgumentException("Please fill in all the fields.");
        }
        Employee emp = new Employee(name, id, startDate, phoneNumber, address, workHours, salary);
        if (head == null) {
            head = emp;
        } else {
            Employee current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = emp;
        }
        saveToFile("employee_records.txt");
    }
    
    //method that shows all employees record
    public String showRecord() {
        loadFromFile("employee_records.txt");
        Employee current = head;
        String recordDetails = "";

        while (current != null) {
            recordDetails += "Employee Details:\n"
                    + "Name: " + current.name + "\n"
                    + "ID: " + current.id + "\n"
                    + "Start Date: " + current.startDate + "\n"
                    + "Phone Number: " + current.phoneNumber + "\n"
                    + "Address: " + current.address + "\n"
                    + "Work Hours: " + current.workHours + "\n"
                    + "Salary: " + current.salary + "\n\n";

            current = current.next;
        }

        if (!recordDetails.isEmpty()) {
            return recordDetails;
        }
        recordDetails = "No records found.";
        return recordDetails;
    }

    //a method that updates an employees information
    public String updateRecord(int id, String newName, String newPhoneNumber, String newAddress, String newWorkHours, String newSalary) {
    Employee current = head;

    while (current != null) {
        if (current.id == id) {
            // Update the employee's record
            if (newName != null && !newName.isEmpty()) {
                current.name = newName;
            }
            if (newPhoneNumber != null && !newPhoneNumber.isEmpty()) {
                current.phoneNumber = newPhoneNumber;
            }
            if (newAddress != null && !newAddress.isEmpty()) {
                current.address = newAddress;
            }
            
            if (newWorkHours != null && !newWorkHours.isEmpty()) {
                try {
                    current.workHours = Integer.parseInt(newWorkHours);
                } catch (NumberFormatException e) {
                    return "Invalid work hours format.";
                }
            }
            if (newSalary != null && !newSalary.isEmpty()) {
                try {
                    current.salary = Double.parseDouble(newSalary);
                } catch (NumberFormatException e) {
                    return "Invalid salary format.";
                }
            }
            // Update the record in the file
            saveToFile("employee_records.txt");
            return "Employee record with ID " + id + " has been updated.";
        }
        current = current.next;
    }
    return "Employee with ID " + id + " not found.";
}
  
    // method that search for an employee record by ID and returns it
    public Employee searchRecord(int id) {
        
        Employee current = head;
        while (current != null) {
            if (current.id == id)
                return current;
            current = current.next;
        }
        return null; // Record not found
    }

    //a method that deletes an employee record from their ID
    public int deleteRecord(int id) {
        Employee current = head;
        Employee prev = null;

        while (current != null) {
            if (current.id == id) {
                if (prev == null) {
                    head = current.next;
                } else {
                    prev.next = current.next;
                }
                return 0; // record Deleted 
            }
            prev = current;
            current = current.next;
        }
        return -1; // if Record not found
    }

    // method that updates an employees salary by asking for their extra hours
    public String updateSalary(int id, int extraHours) {
        try {
            if (!checkRecord(id)) {
                return "Employee with ID " + id + " not found!";
            }
            Employee employee = searchRecord(id);
            employee.salary += (extraHours * 0.02 * employee.salary);
            employee.workHours= employee.workHours+extraHours;
            saveToFile("employee_records.txt");

            return "Salary updated for employee with ID " + id;
        } catch (Exception e) {
            return "An error occurred while updating the salary.";
        }
    }

    // Function to save all employee records to a text file
    public void saveToFile(String fileName) {
        try {
            PrintWriter writer = new PrintWriter(fileName);
            Employee current = head;
            while (current != null) {
                writer.println(current.name);
                writer.println(current.id);
                writer.println(current.startDate);
                writer.println(current.phoneNumber);
                writer.println(current.address);
                writer.println(current.workHours);
                writer.println(current.salary);
                current = current.next;
            }
            writer.close();
            System.out.println("Employee records saved to file: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Function to load employee records from a text file
    public void loadFromFile(String fileName) {
        try {
            Scanner scanner = new Scanner(new File(fileName));
            head = null; // to clear record
            while (scanner.hasNextLine()) {
                String name = scanner.nextLine();
                int id = Integer.parseInt(scanner.nextLine());
                String startDate = scanner.nextLine();
                String phoneNumber = scanner.nextLine();
                String address = scanner.nextLine();
                int workHours = Integer.parseInt(scanner.nextLine());
                double salary = Double.parseDouble(scanner.nextLine());

                createRecord(name, id, startDate, phoneNumber, address, workHours, salary);
            }
            scanner.close();
            System.out.println("Employee records loaded from file: " + fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
