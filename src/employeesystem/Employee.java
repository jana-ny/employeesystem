
package employeesystem;

/**
 *
 * *****************************@author jana Nabiel Alyamani*******************************************
 */

// Employee class to represent employee records
class Employee {
    String name;
    int id;
    String startDate;
    String phoneNumber;
    String address;
    int workHours;
    double salary;
    Employee next;

    // Constructor
    public Employee(String name, int id, String startDate, String phoneNumber, String address, int workHours, double salary) {
        this.name = name;
        this.id = id;
        this.startDate = startDate;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.workHours = workHours;
        this.salary = salary;
        this.next = null;
    }
}
