import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

public class CSVFile extends JFrame implements ActionListener {
    private Container c;
    private JLabel title;
    private JLabel username;
    private JTextField tusername;
    private JLabel password;
    private JPasswordField tpassword;
    private JButton sub;
    private JButton leaveAppBtn;
    private JLabel res;
    private String[] employeeData;
    private int sickLeaveCredits;
    private int vacationLeaveCredits;

    public CSVFile() {
        setTitle("Login Form");
        setBounds(300, 90, 600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        title = new JLabel("Login Form");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setBounds(200, 30, 300, 30);
        c.add(title);

        username = new JLabel("Username");
        username.setFont(new Font("Arial", Font.PLAIN, 20));
        username.setBounds(100, 150, 100, 20);
        c.add(username);

        tusername = new JTextField();
        tusername.setFont(new Font("Arial", Font.PLAIN, 15));
        tusername.setBounds(200, 150, 190, 20);
        c.add(tusername);

        password = new JLabel("Password");
        password.setFont(new Font("Arial", Font.PLAIN, 20));
        password.setBounds(100, 220, 100, 20);
        c.add(password);

        tpassword = new JPasswordField();
        tpassword.setFont(new Font("Arial", Font.PLAIN, 15));
        tpassword.setBounds(200, 220, 190, 20);
        c.add(tpassword);

        sub = new JButton("Submit");
        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setBounds(150, 300, 100, 20);
        sub.addActionListener(this);
        c.add(sub);

        leaveAppBtn = new JButton("Apply for Leave");
        leaveAppBtn.setFont(new Font("Arial", Font.PLAIN, 15));
        leaveAppBtn.setBounds(300, 300, 150, 20);
        leaveAppBtn.addActionListener(this);
        leaveAppBtn.setEnabled(false); 
        c.add(leaveAppBtn);

        res = new JLabel("");
        res.setFont(new Font("Arial", Font.PLAIN, 20));
        res.setBounds(200, 350, 500, 25);
        c.add(res);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String usernameInput = tusername.getText();
        String passwordInput = String.valueOf(tpassword.getPassword());

        if (e.getSource() == sub) {
            authenticateUser(usernameInput, passwordInput);
        } else if (e.getSource() == leaveAppBtn) {
            showLeaveApplicationDialog();
        }
    }

    private void authenticateUser(String usernameInput, String passwordInput) {
        try {
            CSVReader reader = new CSVReader(new FileReader("Employee_Records.csv"));
            String[] nextLine;
            boolean isAuthenticated = false;

            while ((nextLine = reader.readNext()) != null) {
                if (nextLine.length >= 16 && nextLine[14].equals(usernameInput) && nextLine[15].equals(passwordInput)) {
                    isAuthenticated = true;
                    res.setText("Login Successful");
                    employeeData = nextLine;
                    sickLeaveCredits = Integer.parseInt(nextLine[6]); 
                    vacationLeaveCredits = Integer.parseInt(nextLine[7]); 
                    leaveAppBtn.setEnabled(true);
                    break;
                }
            }
            if (!isAuthenticated) {
                res.setText("Login Failed");
            }
            reader.close();
        } catch (IOException | CsvValidationException ex) {
            res.setText("Error occurred while reading the file.");
        }
    }

    private void showLeaveApplicationDialog() {
        JFrame leaveFrame = new JFrame("Leave Application");
        leaveFrame.setSize(800, 300);
        leaveFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        leaveFrame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        JPanel centerPanel = new JPanel(new GridLayout(3, 2));
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel availableLeaveLabel = new JLabel("Available Leave Credits:");
        JTextField availableLeaveField = new JTextField("Sick Leave Credits: " + sickLeaveCredits + ", Vacation Leave Credits: " + vacationLeaveCredits);
        availableLeaveField.setEditable(false);

        JLabel daysLabel = new JLabel("Enter number of days:");
        JTextField daysField = new JTextField();

        JLabel leaveTypeLabel = new JLabel("Select Leave Type:");
        String[] leaveTypes = {"Sick Leave", "Vacation Leave"};
        JComboBox<String> leaveTypeComboBox = new JComboBox<>(leaveTypes);

        centerPanel.add(availableLeaveLabel);
        centerPanel.add(availableLeaveField);
        centerPanel.add(daysLabel);
        centerPanel.add(daysField);
        centerPanel.add(leaveTypeLabel);
        centerPanel.add(leaveTypeComboBox);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
					handleLeaveApplication(daysField.getText(), (String) leaveTypeComboBox.getSelectedItem());
				} catch (CsvValidationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                leaveFrame.dispose();
            }
        });

        bottomPanel.add(submitButton);

        leaveFrame.add(topPanel, BorderLayout.NORTH);
        leaveFrame.add(centerPanel, BorderLayout.CENTER);
        leaveFrame.add(bottomPanel, BorderLayout.SOUTH);

        leaveFrame.setVisible(true);
    }

    private void handleLeaveApplication(String days, String leaveType) throws CsvValidationException {
        try {
            int daysRequested = Integer.parseInt(days);
            int leaveCredits;

            if (leaveType.equals("Sick Leave")) {
                leaveCredits = sickLeaveCredits;
            } else {
                leaveCredits = vacationLeaveCredits;
            }

            if (daysRequested <= leaveCredits) {
                leaveCredits = leaveCredits-daysRequested;

                if (leaveType.equals("Sick Leave")) {
                    sickLeaveCredits = leaveCredits;
                } else {
                    vacationLeaveCredits = leaveCredits;
                }

                updateEmployeeRecordInCSV(sickLeaveCredits, vacationLeaveCredits);
                JOptionPane.showMessageDialog(null, "Leave application submitted successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Insufficient leave credits.");
            }
        } catch (NumberFormatException | IOException ex) {
            JOptionPane.showMessageDialog(null, "Error processing leave application.");
        }
    }
    private void updateEmployeeRecordInCSV(int sickLeaveCredits, int vacationLeaveCredits) throws IOException, CsvValidationException {
        String inputFile = "Employee_Records.csv";
        String tempFile = "temp.csv";

        CSVReader reader = null;
        CSVWriter writer = null;

        try {
            reader = new CSVReader(new FileReader(inputFile));
            writer = new CSVWriter(new FileWriter(tempFile), ',', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END); 

            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine.length >= 16 && nextLine[14].equals(employeeData[14]) && nextLine[15].equals(employeeData[15])) {
                    // Update sick and vacation leave credits
                    nextLine[6] = Integer.toString(sickLeaveCredits);
                    nextLine[7] = Integer.toString(vacationLeaveCredits);
                }
                writer.writeNext(nextLine);
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (writer != null) {
                writer.close();
            }
        }
        File oldFile = new File(inputFile);
        File newFile = new File(tempFile);
        if (oldFile.delete()) {
            if (newFile.renameTo(oldFile)) {
                System.out.println("File updated successfully.");
            } else {
                System.out.println("Failed to rename temporary file.");
            }
        } else {
            System.out.println("Failed to delete original file.");
        }
    }

    public static void main(String[] args) {
        new CSVFile();
    }
}