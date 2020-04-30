package FileServices;

import Entities.Employee;
import Entities.IdentityCard;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeFileService {
    private static EmployeeFileService instance = null;
    private final String path = "employees.csv";
    private EmployeeFileService(){}

    public static EmployeeFileService getInstance(){
        if(instance == null){
            return new EmployeeFileService();
        }
        return instance;
    }

    public void writeEmployee(Employee newEmployee){
        FileWriter employeeFile = null;
        try{
            employeeFile = new FileWriter(path, true);
            List<String> details = new ArrayList<>();
            details.add(newEmployee.getUsername());
            details.add(newEmployee.getPassword());
            details.add(newEmployee.getPhoneNumber());
            details.add(newEmployee.getEmail().toString());
            details.add(newEmployee.getIC().getLastName());
            details.add(newEmployee.getIC().getFirstName());
            details.add(newEmployee.getIC().getCNP());
            Date dateBirth = newEmployee.getIC().getDateBirth();
            DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
            String strDate = dateFormat.format(dateBirth);
            details.add(strDate);
            details.add(newEmployee.getIC().getSerie());
            details.add(newEmployee.getIC().getNumber());
            details.add(newEmployee.getIC().getBirthPlace());
            details.add(newEmployee.getIC().getHomeAdress());
            details.add(newEmployee.getIC().getIssuedBy());

            Date dateBegin = newEmployee.getIC().getBeginValidity();
            String strDateBegin = dateFormat.format(dateBegin);
            details.add(strDateBegin);

            Date dateEnd = newEmployee.getIC().getEndValidity();
            String strDateEnd = dateFormat.format(dateEnd);
            details.add(strDateEnd);

            Date dateHire = newEmployee.getHireDate();
            String strDateHire = dateFormat.format(dateHire);
            details.add(strDateHire);

            details.add(newEmployee.getJobTitle());
            details.add(newEmployee.getDepartment());
            employeeFile.append(String.join(",", details));
            employeeFile.append("\n");
        }
        catch(IOException exception){
            System.out.println("Error!");
        }
        finally {
            try {
                assert employeeFile != null;
                employeeFile.flush();
                employeeFile.close();
            }
            catch(IOException exception){
                System.out.println("Could not close the file.");

            }
        }
    }

    public List<Employee> readEmployees(){
        List<Employee> employees = new ArrayList<>();
        BufferedReader employeeFile = null;
        try{
            employeeFile = new BufferedReader(new FileReader(path));
            String line;
            while((line = employeeFile.readLine()) != null){
                String [] data = line.split(",");
                IdentityCard recordedIC = new IdentityCard(data[4], data[5], data[6], data[7], data[8], data[9],data[10], data[11], data[12], data[13], data[14]);
                Employee recordedEmployee = new Employee(recordedIC, data[0], data[1], data[3], data[2], data[15], data[16], data[17]);
                employees.add(recordedEmployee);
            }
        } catch (IOException e) {
            System.out.println("Error!");
        } finally {
            try {
                assert employeeFile != null;
                employeeFile.close();
            }
            catch(IOException exception){
                System.out.println("Could not close the file.");
            }
        }
        return employees;
    }
    public void updateEmployees(List<Employee> employees) {
        try {
            PrintWriter writer = new PrintWriter(path);
            writer.print("");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error!");
        }
        for (Employee actualEmployee : employees) {
            this.writeEmployee(actualEmployee);
        }
    }
}
