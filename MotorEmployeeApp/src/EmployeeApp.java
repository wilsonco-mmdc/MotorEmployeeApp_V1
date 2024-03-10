import javax.swing.*;

import java.awt.*;
import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class EmployeeApp extends JFrame {
    public EmployeeApp() {
        setTitle("Employee App");
        setSize(700, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton openEmployeeRecordsButton = new JButton("Open Employee Records");
        openEmployeeRecordsButton.addActionListener(e -> {
            try {
                new EmployeeReadGUI();
            } catch (CsvValidationException | IOException ex) {
                ex.printStackTrace();
            }
        });

        JPanel panel = new JPanel();
        panel.add(openEmployeeRecordsButton);

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EmployeeApp::new);
    }
}