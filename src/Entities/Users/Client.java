package Entities.Users;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Client extends User{

    public Client(String firstName, String lastName, String username, String password, InternetAddress email, String phoneNumber, Date dateBirth) {
        super(firstName, lastName, username, password, email, phoneNumber, dateBirth);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public static Client readData() {
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
        String patternDate = "MM-dd-yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(patternDate);
        Date dateBirthformat = null;
        try{
            dateBirthformat = dateFormat.parse(dateBirth);
        }
        catch(ParseException exception){
            System.out.println("Invalid birth date!");
        }


        return new Client(firstName, lastName, username, password, internetAddress, phoneNumber, dateBirthformat);
    }

}