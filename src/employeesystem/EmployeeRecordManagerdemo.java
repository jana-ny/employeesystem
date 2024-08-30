
package employeesystem;

/**
 *
 * *****************************@author jana Nabiel Alyamani*******************************************
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class EmployeeRecordManagerdemo extends JFrame {
    
    public EmployeeRecordManager manager = new EmployeeRecordManager();;

    private JTextField nameField, idField, startDateField, phoneNumberField, addressField, workHoursField, salaryField;
    private JButton addButton, deleteButton, searchButton,updateSalButton,updateButton,showRecButton;
    public JTextArea displayArea;

    // Define colors
    public static Color gold = new Color(252, 237, 108);
    public static Color navy = new Color(4, 30, 52);
    public static Color whiteSmoke = new Color(245, 245, 245);
    
    final int WINDOW_WIDTH1 = 310; // Window width in pixels
    final int WINDOW_HEIGHT1 = 180; // Window height in pixels

    public EmployeeRecordManagerdemo() {
        
        super("Employee Management System");


        // Create window components
        JLabel nameLabel = new JLabel("Name:");
        JLabel idLabel = new JLabel("ID:");
        JLabel startDateLabel = new JLabel("Start Date:");
        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        JLabel addressLabel = new JLabel("Address:");
        JLabel workHoursLabel = new JLabel("Work Hours:");
        JLabel salaryLabel = new JLabel("Salary:");
        addButton = new JButton("Add");
        deleteButton = new JButton("Delete");
        updateButton = new JButton("Update");
        updateSalButton = new JButton("Update salary");
        searchButton = new JButton("Search");
        showRecButton = new JButton("Show record");

        nameField = new JTextField(20);
        idField = new JTextField(10);
        startDateField = new JTextField(10);
        startDateField.setText("dd-mm-yyyy");
        phoneNumberField = new JTextField(10);
        addressField = new JTextField(20);
        workHoursField = new JTextField(5);
        salaryField = new JTextField(10);
        
        displayArea = new JTextArea(10, 30);
     
        
        //button coloring 
        addButton.setBackground(whiteSmoke);
        addButton.setForeground(navy);

        deleteButton.setBackground(whiteSmoke);
        deleteButton.setForeground(navy);

        updateSalButton.setBackground(whiteSmoke);
        updateSalButton.setForeground(navy);
        
        updateButton.setBackground(whiteSmoke);
        updateButton.setForeground(navy);

        searchButton.setBackground(whiteSmoke);
        searchButton.setForeground(navy);
        
        showRecButton.setBackground(whiteSmoke);
        showRecButton.setForeground(navy);
        
        nameLabel.setForeground(gold);
        idLabel.setForeground(gold);
        startDateLabel.setForeground(gold);
        phoneNumberLabel.setForeground(gold);
        addressLabel.setForeground(gold);
        workHoursLabel.setForeground(gold);
        salaryLabel.setForeground(gold);

        // Add action listeners 
        addButton.addActionListener(new AddButtonListener());
        deleteButton.addActionListener(new DeleteButtonListener());
        updateSalButton.addActionListener(new UpdatesalButtonListener());
        updateButton.addActionListener(new UpdateButtonListener());
        searchButton.addActionListener(new SearchButtonListener());
        showRecButton.addActionListener(new showRecButtonListener());

        
        JPanel inputPanel = new JPanel(new GridLayout(7, 2, 5, 5));
        inputPanel.setBackground(navy);
        
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(idLabel);
        inputPanel.add(idField);
        inputPanel.add(startDateLabel);
        inputPanel.add(startDateField);
        inputPanel.add(phoneNumberLabel);
        inputPanel.add(phoneNumberField);
        inputPanel.add(addressLabel);
        inputPanel.add(addressField);
        inputPanel.add(workHoursLabel);
        inputPanel.add(workHoursField);
        inputPanel.add(salaryLabel);
        inputPanel.add(salaryField);

        JPanel buttonPanel = new JPanel();
        
        buttonPanel.setBackground(navy);
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateSalButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(showRecButton);

        
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);

        // Load employee records from file
        manager.loadFromFile("employee_records.txt");
    }
    
    public void displayWindow(){
        
        JFrame F1= new JFrame();
        F1.setTitle("display window");
        F1.setLayout(new BorderLayout());
        F1.setSize(WINDOW_WIDTH1,WINDOW_HEIGHT1);
        displayArea.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(displayArea);
        JPanel displayPanel = new JPanel();
          
        displayPanel.setBackground(navy);
        displayPanel.add(scrollPane);
        F1.add(displayPanel, BorderLayout.CENTER);
        
        F1.setLocationRelativeTo(null);
        
        F1.pack();
        F1.setVisible(true);
        
}

    // ****************************Add action listeners to buttons*****************************************
    
    private class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            displayWindow();
            addRecord();
            
        }
    }

    private class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            displayWindow();
            deleteRecord();
            
        }
    }

    private class UpdatesalButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            updateSal();
            displayWindow();
            
        }
    }
    private class showRecButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            displayWindow();
            showRecord();
            
        }
    }
    private class UpdateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            updateRecord();
            displayWindow();
            
        }
    }

    private class SearchButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            displayWindow();
            searchRecord();
            
        }
    }

    private class ExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

/*********************INPUT VALIDATION*****************/
    
    private void addRecord() {
    try {
        String name = nameField.getText();
        String idText = idField.getText();
        String startDate = startDateField.getText();
        String phoneNumber = phoneNumberField.getText();
        String address = addressField.getText();
        String workHoursText = workHoursField.getText();
        String salaryText = salaryField.getText();

        if (name.isEmpty() || idText.isEmpty() || startDate.isEmpty() || phoneNumber.isEmpty() || address.isEmpty() || workHoursText.isEmpty() || salaryText.isEmpty()) {
            
            throw new IllegalArgumentException("Please fill in all the fields.");
        }

        int id = Integer.parseInt(idText);
        int workHours = Integer.parseInt(workHoursText);
        double salary = Double.parseDouble(salaryText);

        // Check date format and validity
        if (!isValidDateFormat(startDate)) {
            
            throw new IllegalArgumentException("Invalid date format.");
        }

        // Check phone number length
        if (phoneNumber.length() != 10) {
            
            throw new IllegalArgumentException("Phone number should be 10 digits long.");
        }
        // Check if phone number contains only digits
        for (int i = 0; i < phoneNumber.length(); i++) {
            if (!Character.isDigit(phoneNumber.charAt(i))) {
                
                throw new IllegalArgumentException("Phone number should contain only digits.");
            }
        }

        // Check work hours
        if (workHours < 32) {
            
            throw new IllegalArgumentException("Work hours cannot be less than 32.");
        }

        manager.createRecord(name, id, startDate, phoneNumber, address, workHours, salary);
       
        displayArea.setText("Employee record added:\n" + name + " - ID: " + id);
        manager.saveToFile("employee_records.txt");
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Make sure to enter valid numeric values.", "ERROR", JOptionPane.ERROR_MESSAGE);
    } catch (IllegalArgumentException e) {
        
        displayArea.setText(e.getMessage());
    }
}

// Method to check if the "dd-mm-yyyy" format applies
    private boolean isValidDateFormat(String date) {
    // Check if the date has the format "dd-mm-yyyy"
    for (int i = 0; i < date.length(); i++) {
        char c = date.charAt(i);
        if ((i == 2 || i == 5) && c != '-') {
            return false;
        } else if (i != 2 && i != 5 && !Character.isDigit(c)) {
            return false;
        }
    }
    return date.length() == 10 && isValidDate(date);
}

    // Method to check the date validity
    private boolean isValidDate(String date) {

        try {
            int[] parts = new int[3];
            parts[0] = Integer.parseInt(date.substring(0, 2)); // Extract day
            parts[1] = Integer.parseInt(date.substring(3, 5)); // Extract month
            parts[2] = Integer.parseInt(date.substring(6, 10)); // Extract year

            int day = parts[0];
            int month = parts[1];
            int year = parts[2];

            // Check if day is less than or equal to 31, month is less than or equal to 12, and year consists of 4 digits
            return day <= 31 && month <= 12 && String.valueOf(year).length() == 4;
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            return false;
        }
    }

    private void deleteRecord() {
        int id = Integer.parseInt(idField.getText());
        manager.deleteRecord(id);
        displayArea.setText("Employee record with ID " + id + " deleted.");
        manager.saveToFile("employee_records.txt");
    }

    private void updateSal() {
        int id = Integer.parseInt(idField.getText());
        Employee employee = manager.searchRecord(id);
        if (employee != null) {
            int extraHours = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter extra work hours:"));
            String result = manager.updateSalary(id, extraHours);
            displayArea.setText(result);
        } else {
            displayArea.setText("Employee with ID " + id + " not found.");
        }
}

    private void updateRecord() {
        int id = Integer.parseInt(idField.getText());
        String newName = nameField.getText();
        String newPhoneNumber = phoneNumberField.getText();
        String newAddress = addressField.getText();
        String newWorkHours = workHoursField.getText();
        String newSalary = salaryField.getText();

        String result = manager.updateRecord(id, newName, newPhoneNumber, newAddress, newWorkHours, newSalary);
        displayArea.setText(result);
}

    private void searchRecord() {
        int id = Integer.parseInt(idField.getText());
        Employee employee = manager.searchRecord(id);
        if (employee != null) {
            displayArea.setText("""
                                Employee Details:
                                Name: """ + employee.name + "\n" +
                    "ID: " + employee.id + "\n" +
                    "Start Date: " + employee.startDate + "\n" +
                    "Phone Number: " + employee.phoneNumber + "\n" +
                    "Address: " + employee.address + "\n" +
                    "Work Hours: " + employee.workHours + "\n" +
                    "Salary: " + employee.salary);
        } else {
            displayArea.setText("Employee with ID " + id + " not found.");
        }
        
    }
    private void showRecord() {
        
        String recordDetails;
        recordDetails = manager.showRecord();
        displayArea.setText(recordDetails);
    }

    public static void main(String[] args) {
        
        EmployeeRecordManager manager = new EmployeeRecordManager();
        
        
        manager.createRecord("Mohammed Ahmed", 1001, "01-01-2024", "1234567890", "murjan, jeddah", 32, 2000.0);
        manager.createRecord("Fatima Ali", 1002, "01-02-2024", "9876543210", "basatin, jeddah", 32, 2500.0);
        manager.createRecord("Youssef Abdullah", 1003, "15-05-2023", "5551112222", "mohammad anas,murjan", 32, 1800.0);
        manager.createRecord("Nora Hussein", 1004, "20-10-2023", "4443335555", "mohammad anas,murjan", 32, 2200.0);
        manager.createRecord("Layla Mahmoud", 1005, "12-03-2024", "9998887777", "omar ali, muhamadia", 32, 2700.0);
        manager.createRecord("Ali khalid", 1006, "03-07-2023", "2223334444", "basatin, jeddah", 32, 1700.0);
        manager.createRecord("Reema alzahrani", 1007, "21-09-2023", "3334446666", "mohammad almalki Street, obhur", 32, 2100.0);
        manager.createRecord("Mohammed alghamdi", 1008, "09-05-2024", "1112223333", "omar ali, obhur", 32, 2300.0);
        manager.createRecord("Maryam aljahdali", 1009, "17-12-2023", "6667778888", "basatin, jeddah", 32, 2400.0);
        manager.createRecord("Abdullah Saleh", 1010, "05-08-2024", "7778889999", "omar ali, naeem", 32, 2500.0);
        manager.createRecord("Nadia alrajhi", 1011, "02-04-2023", "8889990000", "mohammad anas,murjan", 32, 2600.0);
        manager.createRecord("Khaled Ali", 1012, "28-02-2023", "1234567890", "mohammad anas,murjan", 32, 2700.0);
        manager.createRecord("Samar atia", 1013, "15-11-2024", "5554443333", "basatin, jeddah", 32, 2800.0);
        manager.createRecord("Sarah almalki", 1014, "30-06-2023", "4443332222", "omar ali, muhamadia", 32, 2900.0);
        manager.createRecord("Abdulaziz Mohammed", 1015, "10-10-2023", "2223334444", "omar ali, muhamadia", 32, 3000.0);
        manager.createRecord("Dana Alsharqi", 1016, "29-01-2024", "3334445555", "basatin, jeddah", 32, 3100.0);
        manager.createRecord("Jana Alyamani", 1017, "12-08-2023", "1112223333", "omar ali, muhamadia", 32, 3200.0);
        manager.createRecord("Omar Ahmad", 1018, "17-03-2023", "6665554444", "basatin, jeddah", 32, 3300.0);
        manager.createRecord("Fatima AbdulRahman", 1019, "05-04-2024", "9998887777", "mohammad anas,murjan", 32, 3400.0);
        manager.createRecord("jameela muhammad", 1020, "23-09-2023", "7776665555", "basatin, jeddah", 32, 3500.0);

        
        EmployeeRecordManagerdemo managerDemo = new EmployeeRecordManagerdemo();
    }
}