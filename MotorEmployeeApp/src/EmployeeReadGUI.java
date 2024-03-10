import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class EmployeeReadGUI extends JFrame {
    private JTable table;

    public EmployeeReadGUI() throws CsvValidationException, IOException {
        String csvFile = "Employee_Records.csv";
        CSVReader reader = new CSVReader(new FileReader(csvFile));
        setTitle("MOTOR PH EMPLOYEE RECORDS");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Table creation
        DefaultTableModel model = new DefaultTableModel();
        table = new JTable(model);
        model.addColumn("Employee Name");
        model.addColumn("Id");
        model.addColumn("Date of Birth");
        model.addColumn("Address");
        model.addColumn("Mobile Number");
        model.addColumn("Position");
        model.addColumn("SL Credits");
        model.addColumn("VL Credits");
        model.addColumn("Gross Salary");
        model.addColumn("Witholding tax");
        model.addColumn("SSS");
        model.addColumn("PhilHealth");
        model.addColumn("Pag-ibig");
        model.addColumn("Net Salary");
        model.addColumn("Username");
        model.addColumn("password");

        // Read and sort records
        insertionSort(reader, model);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }

    private void insertionSort(CSVReader reader, DefaultTableModel model) throws IOException, CsvValidationException {
        // Skip the header
        String[] header = reader.readNext();
        String[] line;
        while ((line = reader.readNext()) != null) {
            // Insert the current line into the sorted model
            int id = Integer.parseInt(line[1]);
            int i = 0;
            while (i < model.getRowCount() && Integer.parseInt((String) model.getValueAt(i, 1)) < id) {
                i++;
            }
            model.insertRow(i, line);
        }
        reader.close();
    }

    // Constructor for displaying a single employee record
    public EmployeeReadGUI(String[] employeeRecord) {
        setTitle("MOTOR PH EMPLOYEE RECORDS");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Table creation
        DefaultTableModel model = new DefaultTableModel();
        table = new JTable(model);
        model.addColumn("Employee Name");
        model.addColumn("Id");
        model.addColumn("Date of Birth");
        model.addColumn("Address");
        model.addColumn("Mobile Number");
        model.addColumn("Position");
        model.addColumn("SL Credits");
        model.addColumn("VL Credits");
        model.addColumn("Gross Salary");
        model.addColumn("Withholding tax");
        model.addColumn("SSS");
        model.addColumn("PhilHealth");
        model.addColumn("Pag-ibig");
        model.addColumn("Net Salary");
        model.addColumn("Username");
        model.addColumn("Password");

        model.addRow(employeeRecord);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new EmployeeReadGUI();
            } catch (CsvValidationException | IOException e) {
                e.printStackTrace();
            }
        });
    }
}