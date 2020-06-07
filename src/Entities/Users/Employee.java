package Entities.Users;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Employee extends User{
    private Date hireDate;
    private String jobTitle;
    private String department;

    public Employee(String firstName, String lastName, String username, String password, InternetAddress email, String phoneNumber, Date dateBirth, Date hireDate, String jobTitle, String department) {
        super(firstName, lastName, username, password, email, phoneNumber, dateBirth);
        this.hireDate = hireDate;
        this.jobTitle = jobTitle;
        this.department = department;
    }

    @Override
    public String toString() {
        return super.toString() + "\nHire date: " + this.hireDate.toString() + "\nJob title: " + jobTitle
                + "\nDepartment: " + this.department;
    }

    public static Employee readData() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("First name:");
        String firstName = scanner.nextLine();

        System.out.println("Last name:");
        String lastName = scanner.nextLine();

        System.out.println("Username:");
        String username = scanner.nextLine();

        System.out.println("Password:");
        String password = scanner.nextLine();

        System.out.println("Email:");
        String email = scanner.nextLine();

        InternetAddress internetAddress = null;
        try{
            internetAddress = new InternetAddress(email);
        } catch (AddressException e) {
            System.out.println("Invalid email!");
        }

        System.out.println("Phone number:");
        String phoneNumber = scanner.nextLine();

        System.out.println("Date birth:");
        String dateBirth = scanner.nextLine();

        System.out.println("Date hire:");
        String dateHire = scanner.nextLine();

        String patternDate = "MM-dd-yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(patternDate);
        Date dateBirthformat = null;
        try{
            dateBirthformat = dateFormat.parse(dateBirth);
        }
        catch(ParseException exception){
            System.out.println("Invalid birth date!");
        }

        Date dateHireformat = null;
        try{
            dateHireformat = dateFormat.parse(dateHire);
        }
        catch(ParseException exception){
            System.out.println("Invalid hire date!");
        }

        System.out.println("Job title:");
        String jobTitle = scanner.nextLine();

        System.out.println("Department:");
        String department = scanner.nextLine();

        return new Employee(firstName, lastName, username, password, internetAddress, phoneNumber, dateBirthformat, dateHireformat, jobTitle, department);
    }

    public Date getHireDate() {
        return hireDate;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getDepartment() {
        return department;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}