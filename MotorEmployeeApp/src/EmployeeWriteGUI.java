import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import first.Employee;

public class EmployeeWriteGUI {

    private JFrame frame;
    private JTextField textFieldName;
    private JTextField textFieldId;
    private JTextField textFieldDOB;
    private JTextField textFieldAddress;
    private JTextField textFieldContactNumber;
    private JTextField textFieldPosition;
    private JTextField textFieldSlCredits;
    private JTextField textFieldVlCredits;
    private JTextField textFieldSalary;
    private JTextField textFieldUsername;
    private JTextField textFieldPassword;

    private List<Employee> employees = new ArrayList<>();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    EmployeeWriteGUI window = new EmployeeWriteGUI();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public EmployeeWriteGUI() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(34, 41, 61, 16);
        frame.getContentPane().add(lblName);

        textFieldName = new JTextField();
        textFieldName.setBounds(150, 38, 130, 26);
        frame.getContentPane().add(textFieldName);
        textFieldName.setColumns(10);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(34, 82, 61, 16);
        frame.getContentPane().add(lblId);

        textFieldId = new JTextField();
        textFieldId.setColumns(10);
        textFieldId.setBounds(150, 79, 130, 26);
        frame.getContentPane().add(textFieldId);

        JLabel lblDOB = new JLabel("DOB:");
        lblDOB.setBounds(34, 123, 61, 16);
        frame.getContentPane().add(lblDOB);

        textFieldDOB = new JTextField();
        textFieldDOB.setColumns(10);
        textFieldDOB.setBounds(150, 120, 130, 26);
        frame.getContentPane().add(textFieldDOB);

        JLabel lblAddress = new JLabel("Address:");
        lblAddress.setBounds(34, 164, 61, 16);
        frame.getContentPane().add(lblAddress);

        textFieldAddress = new JTextField();
        textFieldAddress.setColumns(10);
        textFieldAddress.setBounds(150, 161, 130, 26);
        frame.getContentPane().add(textFieldAddress);

        JLabel lblContactNumber = new JLabel("Contact Number:");
        lblContactNumber.setBounds(34, 205, 120, 16);
        frame.getContentPane().add(lblContactNumber);

        textFieldContactNumber = new JTextField();
        textFieldContactNumber.setColumns(10);
        textFieldContactNumber.setBounds(150, 202, 130, 26);
        frame.getContentPane().add(textFieldContactNumber);

        JLabel lblPosition = new JLabel("Position:");
        lblPosition.setBounds(34, 246, 61, 16);
        frame.getContentPane().add(lblPosition);

        textFieldPosition = new JTextField();
        textFieldPosition.setColumns(10);
        textFieldPosition.setBounds(150, 243, 130, 26);
        frame.getContentPane().add(textFieldPosition);

        JLabel lblSlCredits = new JLabel("SL Credits:");
        lblSlCredits.setBounds(34, 287, 61, 16);
        frame.getContentPane().add(lblSlCredits);

        textFieldSlCredits = new JTextField();
        textFieldSlCredits.setColumns(10);
        textFieldSlCredits.setBounds(150, 284, 130, 26);
        frame.getContentPane().add(textFieldSlCredits);

        JLabel lblVlCredits = new JLabel("VL Credits:");
        lblVlCredits.setBounds(34, 328, 61, 16);
        frame.getContentPane().add(lblVlCredits);

        textFieldVlCredits = new JTextField();
        textFieldVlCredits.setColumns(10);
        textFieldVlCredits.setBounds(150, 325, 130, 26);
        frame.getContentPane().add(textFieldVlCredits);
        
        JLabel lblSalary = new JLabel("Salary:");
        lblSalary.setBounds(34, 369, 61, 16);
        frame.getContentPane().add(lblSalary);
        
        textFieldSalary = new JTextField();
        textFieldSalary.setColumns(10);
        textFieldSalary.setBounds(150, 366, 130, 26);
        frame.getContentPane().add(textFieldSalary);
        
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(34, 410, 61, 16);
        frame.getContentPane().add(lblUsername);
        
        textFieldUsername = new JTextField();
        textFieldUsername.setColumns(10);
        textFieldUsername.setBounds(150, 407, 130, 26);
        frame.getContentPane().add(textFieldUsername);
        
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(34, 451, 61, 16);
        frame.getContentPane().add(lblPassword);
        
        textFieldPassword = new JTextField();
        textFieldPassword.setColumns(10);
        textFieldPassword.setBounds(150, 448, 130, 26);
        frame.getContentPane().add(textFieldPassword);

        JButton btnAdd = new JButton("Add Employee Data");
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					addEmployee();
				} catch (CsvException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        btnAdd.setBounds(34, 492, 180, 29);
        frame.getContentPane().add(btnAdd);

        JButton btnUpdate = new JButton("Update Employee Data");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateEmployee();
            }
        });
        btnUpdate.setBounds(220, 492, 180, 29);
        frame.getContentPane().add(btnUpdate);

        JButton btnDelete = new JButton("Delete Employee Data");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					deleteEmployee();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        btnDelete.setBounds(410, 492, 180, 29);
        frame.getContentPane().add(btnDelete);
        
        
        JButton btnOpenRecords = new JButton("Open Employee Records");
        btnOpenRecords.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String adminPassword = "admin"; 
                String inputPassword = JOptionPane.showInputDialog(frame, "Enter admin password:");
                if (inputPassword != null && inputPassword.equals(adminPassword)) {
                    openEmployeeRecords();
                } else {
                    JOptionPane.showMessageDialog(frame, "Incorrect admin password.");
                }
            }
        });
        btnOpenRecords.setBounds(600, 492, 250, 29);
        frame.getContentPane().add(btnOpenRecords);
    }

    
    
    private void addEmployee() throws CsvException {
        try {
            String name = textFieldName.getText();
            int id = Integer.parseInt(textFieldId.getText());
            String dob = textFieldDOB.getText();
            String address = textFieldAddress.getText();
            String contactNumber = textFieldContactNumber.getText();
            String position = textFieldPosition.getText();
            int slCredits = Integer.parseInt(textFieldSlCredits.getText());
            int vlCredits = Integer.parseInt(textFieldVlCredits.getText());
            int salary = Integer.parseInt(textFieldSalary.getText());
            String username = textFieldUsername.getText();
            String password = textFieldPassword.getText();

            Employee newEmployee = new Employee(name, id, dob, address, contactNumber, position, slCredits, vlCredits, salary, username, password);
            newEmployee.calculateContri();
            employees.add(newEmployee);
  
            saveEmployeeToCSV(newEmployee);

            JOptionPane.showMessageDialog(frame, "Employee added successfully!");
        } catch (NumberFormatException | IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error adding employee data. Please check your input.");
        }
    }

    private void updateEmployee() {
        // Add code for updating employee data here
        JOptionPane.showMessageDialog(frame, "Update Employee Data functionality not implemented yet!");
    }
    
    private void deleteEmployee() throws IOException {
        try {
            String csvFile = "Employee_Records.csv";
            String tempFile = "temp.csv";
            int idToDelete = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter the ID of the employee to delete:"));

            CSVReader reader = new CSVReader(new FileReader(csvFile));
            List<String[]> lines = reader.readAll();
            for (String[]line:lines) {
            	System.out.println(line[0]);
            	System.out.println("-----------");
            	/*System.out.println(line[1]);
            	System.out.println("-----------");
            	System.out.println(line[2]);
            	System.out.println("-----------");
            	System.out.println(line[3]);
            	System.out.println("-----------");
            	System.out.println(line[4]);
            	System.out.println("-----------");
            	*/

            	System.out.println(line[1]);
            }
            reader.close();

            boolean found = false;
            CSVWriter writer = new CSVWriter(new FileWriter(tempFile));
            writer.writeNext(lines.get(0));
            for (int i = 1; i < lines.size(); i++) {
                String[] line = lines.get(i);
                int id = Integer.parseInt(line[1]); 
                if (id == idToDelete) {
                    found = true;
                    continue; 
                }
                writer.writeNext(line);
            }
            writer.close();

            if (found) {
                new File(csvFile).delete();
                new File(tempFile).renameTo(new File(csvFile));
                JOptionPane.showMessageDialog(frame, "Employee with ID " + idToDelete + " deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(frame, "Employee with ID " + idToDelete + " not found!");
            }
        } catch (NumberFormatException | CsvException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error deleting employee data.");
        }
    }
    	
 

    private void saveEmployeeToCSV(Employee newEmployee) throws IOException, CsvException {
        String csvFile = "Employee_Records.csv";

        File file = new File(csvFile);
        if (!file.exists()) {
            try {
                file.createNewFile();
                // Write header to the CSV file
                CSVWriter writer = new CSVWriter(new FileWriter(csvFile));
                String[] header = {"Name", "ID", "DOB", "Address", "Contact Number", "Position",
                        "SL Credits", "VL Credits", "Salary", "Witholding Tax", "SSS",
                        "PhilHealth Contribution", "Pag-IBIG Contribution", "Net Pay",
                        "Username", "Password"};
                writer.writeNext(header);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error creating CSV file.");
                return;
            }
        }
        try {
            // Read existing CSV records
            CSVReader reader = new CSVReader(new FileReader(csvFile));
            List<String[]> lines = reader.readAll();
            reader.close();
            
            lines.remove(0);

            // Add new record
            String[] data = {newEmployee.getName(), String.valueOf(newEmployee.getId()), newEmployee.getDate(),
                    newEmployee.getAddress(), newEmployee.getContactNumber(), newEmployee.getPosition(),
                    String.valueOf(newEmployee.getSlCredits()), String.valueOf(newEmployee.getVlCredits()),
                    String.valueOf(newEmployee.getSalary()), String.valueOf(newEmployee.getWitholdingTax()),
                    String.valueOf(newEmployee.getSss()), String.valueOf(newEmployee.getphilhealthContri()),
                    String.valueOf(newEmployee.getpagibigContri()), String.valueOf(newEmployee.getNetPay()),
                    newEmployee.getUsername(), newEmployee.getPassword()};
            lines.add(data);

            // Sort lines based on employee ID in ascending order using insertion sort
            insertionSort(lines);

            // Write sorted lines to the CSV file
            CSVWriter writer = new CSVWriter(new FileWriter(csvFile));
            writer.writeAll(lines);
            writer.close();
        } catch (FileNotFoundException e) {
            // Handle file not found exception
            e.printStackTrace();
            System.err.println("Error: File not found.");
        } catch (IOException e) {
            // Handle IO exception
            e.printStackTrace();
            System.err.println("Error: IO Exception.");
        }
    }

    // Insertion sort implementation
    private void insertionSort(List<String[]> lines) {
    	
    	
        for (int i = 1; i < lines.size(); i++) {
            String[] key = lines.get(i);
            int j = i - 1;
            while (j >= 0 && Integer.parseInt(lines.get(j)[1]) > Integer.parseInt(key[1])) {
                lines.set(j + 1, lines.get(j));
                j = j - 1;
            }
            lines.set(j + 1, key);
        }
    }

    // Existing code...

    private void openEmployeeRecords() {
        try {
            new EmployeeReadGUI();
        } catch (CsvValidationException | IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error opening employee records.");
        }
    }

    // Existing code...


}


// pending
// 
// log in portal
// deletion  functionality, leave applications, 
// 
