package Entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Document {
    protected String lastName;
    protected String firstName;
    protected String CNP;
    protected Date dateBirth;

    public Document(){
        this.lastName = this.firstName = this.CNP = "";
        this.dateBirth = null;
    }
    public Document(String lastName, String firstName, String CNP, String dateBirth){
        this.lastName = lastName;
        this.firstName = firstName;
        try{
            String patternDate = "MM-dd-yyyy";
            SimpleDateFormat dateFormat = new SimpleDateFormat(patternDate);
            this.dateBirth = dateFormat.parse(dateBirth);
        }
        catch(ParseException exception){
            this.dateBirth = null;
        }
        try{
            String patternCNP = "[1|2|5|6][0-9]{12}";
            if(!CNP.matches(patternCNP)) throw new ParseException("Error - parsing CNP", 0);
            this.CNP = CNP;
        }
        catch(ParseException exception){
            this.CNP = "";
        }
    }

    public abstract Document copy();

    @Override
    public String toString() {
        String dateBirth = new SimpleDateFormat("MM-dd-YYYY").format(this.dateBirth);
        return "Last name: " + this.lastName + "\nFirst name: "
                + this.firstName + "\nCNP: " + this.CNP
                + "\nDate birth: " + dateBirth;
    }

    public void setLastName(String lastName) {this.lastName = lastName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public void setCNP(String CNP) {this.CNP = CNP;}
    public void setDateBirth(Date dateBirth) {this.dateBirth = dateBirth;}

    public String getLastName() {return this.lastName;}
    public String getFirstName() {return this.firstName;}
    public String getCNP() {return this.CNP;}
    public Date getDateBirth() {return this.dateBirth;}

}
