package Entities.Services;

import Entities.Users.Employee;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class EmployeeTableService {
    private String connectionURL = "jdbc:sqlserver://pao-project.database.windows.net:1433;database=PAO-db;user=anca@pao-project;password=qwer1234!@#$;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    private Connection connection;
    private static Scanner scanner = new Scanner(System.in);

    public EmployeeTableService() {
        try{
            connection = DriverManager.getConnection(connectionURL);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addEmployee(Employee newEmployee){
        String firstName = newEmployee.getFirstName();
        String lastName = newEmployee.getLastName();
        String username = newEmployee.getUsername();
        String password = newEmployee.getPassword();
        String email = newEmployee.getEmail().toString();
        String phoneNumber = newEmployee.getPhoneNumber();
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        String strDateBirth = dateFormat.format(newEmployee.getDateBirth());
        String strDateHire = dateFormat.format(newEmployee.getHireDate());
        String jobTitle = newEmployee.getJobTitle();
        String department = newEmployee.getDepartment();

        try{
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("INSERT INTO dbo.EMPLOYEES (FirstName, LastName, UserName, Pass, Email, PhoneNumber, DateBirth, DateHire, JobTitle, Department) " + "VALUES ( '" + firstName + "' , '" + lastName + "' , '" + username + "' , '" + password + "' , '" + email + "' , '" + phoneNumber + "' , '" + strDateBirth + "' , '" + strDateHire + "' , '" + jobTitle + "' , '" + department + "' )");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void displayEmployees(){
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT FirstName, LastName, JobTitle FROM dbo.EMPLOYEES");
            while (rs.next()){
                System.out.println("First name: " + rs.getString("FirstName"));
                System.out.println("Last name: " + rs.getString("LastName"));
                System.out.println("Job title: " + rs.getString("JobTitle"));
                System.out.println();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deteleAccount(String typeUser, String username){
        try {
            Statement stmt = connection.createStatement();
            String query = null;
            if(typeUser.equals("Employee")) {
                query = "DELETE FROM dbo.EMPLOYEES WHERE UserName = ?";
            }else{
                query = "DELETE FROM dbo.CLIENTS WHERE UserName = ?";
            }
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, username);
            preparedStmt.executeUpdate();
            System.out.println("Delete done successfully!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void changePassword(String typeUser, String username){

        System.out.println("Enter the new password:");
        String newPassword = scanner.nextLine();

        System.out.println("Confirm the new password");
        String confirmedNewPassword = scanner.nextLine();

        if(newPassword.equals(confirmedNewPassword) && newPassword.length() > 0){
            try {
                Statement stmt = connection.createStatement();
                String query = null;
                if(typeUser.equals("Employee")) {
                    query = "UPDATE dbo.EMPLOYEES SET Pass = ? where UserName = ?";
                }else{
                    query = "UPDATE dbo.CLIENTS SET Pass = ? where UserName = ?";
                }
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setString(1, newPassword);
                preparedStmt.setString(2, username);
                preparedStmt.executeUpdate();
                System.out.println("Update done successfully!");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        else{
            System.out.println("Invalid password!");
        }

    }
}
