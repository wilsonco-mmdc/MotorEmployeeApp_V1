
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import first.Employee;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.*;


public class EmployeeWriteConsole {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		boolean done = false;
		
	   /* private String name;
	    private int employee_id;
	    private Date dob;
	    private String address;
	    private String contact_number;
	    private String position;
	    private int sl_Credits;
	    private int vl_Credits;
	    */
		
		List<Employee> employeeMotors = new ArrayList<>();
    	Scanner scanner = new Scanner(System.in);

        // List to store custom objects that hold student quiz scores
	    while (!done) {
		    System.out.print("enter the employee's name (or type 'done' to finish): ");
	        String check_emp = scanner.nextLine();

			 // Check if the user has finished entering student data
            if (check_emp.equalsIgnoreCase("done")) {
                done = true; // Set the flag to true to exit the loop
                continue; // Skip the rest of the loop and start from the beginning
            }
			Employee temp_emp = new Employee();
			temp_emp.setName(check_emp);
			
			System.out.println("enter the employee's id");
			temp_emp.setId(Integer.parseInt(scanner.nextLine()));

			System.out.println("enter the employee's DOB");
			temp_emp.setDate(scanner.nextLine());

			System.out.println("enter the employee's address");
			temp_emp.setAddress(scanner.nextLine());

			System.out.println("enter the employee's contact number");
			temp_emp.setNumber(scanner.nextLine());

			System.out.println("enter the employee's position");
			temp_emp.setPosition(scanner.nextLine());
			if(temp_emp.getPosition().equalsIgnoreCase("tier 1")) {
				temp_emp.setSalary(20000);
				//System.out.println("1");
			}
			else if(temp_emp.getPosition().equalsIgnoreCase("tier 2")) {
				temp_emp.setSalary(30000);
			}
			else if(temp_emp.getPosition().equalsIgnoreCase("tier 3")) {
				temp_emp.setSalary(40000);
			}
			else if(temp_emp.getPosition().equalsIgnoreCase("tier 4")) {
				temp_emp.setSalary(50000);
			}
			else if(temp_emp.getPosition().equalsIgnoreCase("tier 5")) {
				temp_emp.setSalary(60000);
			}
			else if(temp_emp.getPosition().equalsIgnoreCase("tier 6")) {
				temp_emp.setSalary(70000);
			}
			else if(temp_emp.getPosition().equalsIgnoreCase("tier 7")) {
				temp_emp.setSalary(80000);
			}

			System.out.println("enter the employee's sick leave credits");
			temp_emp.setSlCredits(Integer.parseInt(scanner.nextLine()));
			
			System.out.println("enter the employee's vacation leave credits");
			temp_emp.setVlCredits(Integer.parseInt(scanner.nextLine()));
			
			employeeMotors.add(new Employee(temp_emp.getName(),temp_emp.getId(),temp_emp.getDate(), temp_emp.getAddress(),
					temp_emp.getContactNumber(),temp_emp.getPosition(),temp_emp.getSlCredits(),temp_emp.getVlCredits(),
					temp_emp.getSalary(),temp_emp.getUsername(),temp_emp.getPassword()));

			
		}
		scanner.close();

		 // CSV file name where we will write the data
        String csvFile = "Employee_Records.csv";
        // Create a CSVWriter object to write to the CSV file
        CSVWriter writer = new CSVWriter(new FileWriter(csvFile));

        // Define headers for the CSV file
        String[] headers = {"Employee_name", "ID", "DOB", "Address", "Contact Number", "Position", "SL credits","VL Credits","salary"};
        writer.writeNext(headers); // Write headers to the CSV

        // Iterate over each student to write their data
        for (Employee emp : employeeMotors) {
            String[] data = new String[9]; // Array to hold data for employee 
            data[0] = emp.getName(); 
            data[1] = Integer.toString(emp.getId()); // 
            data[2] = emp.getDate();
            data[3] = emp.getAddress(); 
            data[4] = emp.getContactNumber();  
            data[5] = emp.getPosition();  
            data[6] = Integer.toString(emp.getSlCredits()); 
            data[7] = Integer.toString(emp.getVlCredits()); 
            data[8] = Integer.toString(emp.getSalary());
            writer.writeNext(data);
            }
        writer.close(); // Close the writer to finalize writing and free resources
    }

}
