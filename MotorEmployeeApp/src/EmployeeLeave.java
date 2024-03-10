import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class EmployeeLeave extends JFrame {
    private JTable table;

    public EmployeeLeave() throws CsvValidationException, IOException {
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
        model.addColumn("Withholding tax");
        model.addColumn("SSS");
        model.addColumn("PhilHealth");
        model.addColumn("Pag-ibig");
        model.addColumn("Net Salary");
        model.addColumn("Username");
        model.addColumn("Password");

        String[] line;
        // skip the first line
        reader.readNext();
        while ((line = reader.readNext()) != null) {
            model.addRow(line);
        }
        // closing csvreader
        reader.close();
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Button for leave deduction
        JButton deductLeaveButton = new JButton("Deduct Leave");
        deductLeaveButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) { // Check if a row is selected
                // Assuming SL Credits is in column index 6 and VL Credits is in column index 7
                int slCredits = Integer.parseInt(model.getValueAt(selectedRow, 6).toString());
                int vlCredits = Integer.parseInt(model.getValueAt(selectedRow, 7).toString());

                // Implement your deduction logic here
                // For example, deducting 1 SL Credit and 1 VL Credit
                slCredits -= 1;
                vlCredits -= 1;

                // Update the table with deducted leave credits
                model.setValueAt(slCredits, selectedRow, 6);
                model.setValueAt(vlCredits, selectedRow, 7);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row.");
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(deductLeaveButton);
        add(buttonPanel, BorderLayout.SOUTH);

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