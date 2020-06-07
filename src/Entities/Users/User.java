package Entities.Users;

import javax.mail.internet.InternetAddress;
import java.util.Date;

public abstract class User {
    protected String firstName;
    protected String lastName;
    protected String username;
    protected String password;
    protected InternetAddress email;
    protected String phoneNumber;
    protected Date dateBirth;

    public User(String firstName, String lastName, String username, String password, InternetAddress email, String phoneNumber, Date dateBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateBirth = dateBirth;
    }

    @Override
    public String toString() {
        return "Name: " + this.firstName + " " + this.lastName + "\nEmail: " +
                this.email + "\nPhone number: " + this.phoneNumber + "\nDate birth: " + this.dateBirth.toString();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public InternetAddress getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(InternetAddress email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }
}
