package Entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.lang.*;
import java.util.regex.Pattern;

public abstract class User {
    protected String username;
    protected String password;
    protected InternetAddress email;
    protected String phoneNumber;
    protected IdentityCard IC;

    public User(){}
    public User(IdentityCard IC, String username, String password, String email, String phoneNumber){
        this.IC = IC;
        this.username = username;
        try{
            String patternPassword = ("[A-Z][a-z]{4,}");
            if(!password.matches(patternPassword)) throw new ParseException("Error - parsing Password", 0);
            this.password = password;
        }
        catch(ParseException exception){
            this.phoneNumber = "";
        }
        try{
            this.email = new InternetAddress(email);
            this.email.validate();
        }
        catch(AddressException exception){
            this.email = null;
        }
        try{
            String patternPhone = "[+][0-9]{1,3} [0-9]{7,12}";
            if(!phoneNumber.matches(patternPhone)) throw new ParseException("Error - parsing Phone Number", 0);
            this.phoneNumber = phoneNumber;
        }
        catch(ParseException exception){
            this.phoneNumber = "";
        }
    }

    @Override
    public String toString() {
        return this.IC + "\nUsername: " + this.username + "\nEmail: " + this.email
                + "\nPhone number: " + this.phoneNumber;
    }

    public void setUsername(String username) {this.username = username;}
    public void setPassword(String password) { this.password = password;}
    public void setEmail(InternetAddress email) {this.email = email;}
    public void setPhoneNumber(String phoneNumber){this.phoneNumber = phoneNumber;}
    public void setIC(IdentityCard IC) {this.IC = IC;}

    public IdentityCard getIC() {return this.IC;}
    public String getUsername() {return this.username;}
    public String getPassword() {return this.password;}
    public InternetAddress getEmail() {return this.email;}
    public String getPhoneNumber() {return this.phoneNumber;}

}
