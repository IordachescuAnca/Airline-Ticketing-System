package Entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IdentityCard extends Document {
    private String serie;
    private String number;
    private String birthPlace;
    private String homeAddress;
    private String issuedBy;
    private Date beginValidity;
    private Date endValidity;

    public IdentityCard(){
        super();
        this.serie = this.birthPlace = this.homeAddress = this.issuedBy = "";
        this.beginValidity = this.endValidity = null;
    }

    public IdentityCard(String lastName, String firstName, String CNP, String dateBirth, String serie, String number, String birthPlace, String homeAdress,
                        String issuedBy, String beginValidity, String endValidity) {
        super(lastName, firstName, CNP, dateBirth);
        this.serie = serie;
        this.number = number;
        this.birthPlace = birthPlace;
        this.homeAddress = homeAdress;
        this.issuedBy = issuedBy;

        String patternDate = "MM-dd-yyyy";
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat(patternDate);
            this.beginValidity = dateFormat.parse(beginValidity);
        }
        catch(ParseException exception){
            this.beginValidity = null;
        }
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat(patternDate);
            this.endValidity = dateFormat.parse(endValidity);
        }
        catch(ParseException exception) {
            this.endValidity = null;
        }
    }

    @Override
    public String toString() {
        String dateBegin = new SimpleDateFormat("MM-dd-YYYY").format(this.beginValidity);
        String dateEnd = new SimpleDateFormat("MM-dd-YYYY").format(this.endValidity);
        return super.toString() + "\nSerie: " + this.serie + "\nNumber: " + this.number
                + "\nBirth place: " + this.birthPlace + "\nHome address: " + this.homeAddress
                + "\nThe IC is issued by: " + this.issuedBy + "\nDate begin validity: " + dateBegin
                + "\nDate end validity: " + dateEnd;
    }

    @Override
    public IdentityCard copy() {
        String dateBirth = new SimpleDateFormat("MM-dd-YYYY").format(this.dateBirth);
        String beginVal = new SimpleDateFormat("MM-dd-YYYY").format(this.beginValidity);
        String endVal = new SimpleDateFormat("MM-dd-YYYY").format(this.endValidity);
        return new IdentityCard(lastName, firstName, CNP, dateBirth, serie, number, birthPlace, homeAddress, issuedBy, beginVal, endVal);
    }

    public void setSerie(String serie) {this.serie = serie;}
    public void setNumber(String number) {this.number = number;}
    public void setBirthPlace(String birthPlace) {this.birthPlace = birthPlace;}
    public void setHomeAddress(String homeAdress) {this.homeAddress = homeAdress;}
    public void setIssuedBy(String issuedBy) {this.issuedBy = issuedBy;}
    public void setBeginValidity(Date beginValidity) {this.beginValidity = beginValidity;}
    public void setEndValidity(Date endValidity) {this.endValidity = endValidity;}


    public String getSerie() {return this.serie;}
    public String getNumber() {return this.number;}
    public String getBirthPlace() {return this.birthPlace;}
    public String getHomeAdress() {return this.homeAddress;}
    public String getIssuedBy() {return this.issuedBy;}
    public Date getBeginValidity() {return this.beginValidity;}
    public Date getEndValidity() {return this.endValidity;}
}
