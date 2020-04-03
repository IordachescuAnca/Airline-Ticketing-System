package Entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ticket extends Document{
    private double price;
    private String from;
    private String to;
    private Date dateFrom;
    private char col;
    private int row;
    private char typeSeat;

    public Ticket(String lastName, String firstName, String CNP, String dateBirth, double price, String from, String to, String dateFrom, char col, int row, char typeSeat) {
        super(lastName, firstName, CNP, dateBirth);
        this.price = price;
        this.from = from;
        this.to = to;
        String patternDate = "MM-dd-yyyy HH:mm";
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat(patternDate);
            this.dateFrom = dateFormat.parse(dateFrom);
        }
        catch(ParseException exception){
            this.dateFrom = null;
        }
        this.col = col;
        this.row = row;
        this.typeSeat = typeSeat;
    }

    @Override
    public Ticket copy() {
        String dateBirth = new SimpleDateFormat("MM-dd-YYYY").format(this.dateBirth);
        String dateFrom = new SimpleDateFormat("MM-dd-YYYY").format(this.dateFrom);
        return new Ticket(lastName, firstName, CNP, dateBirth, price, from, to, dateFrom, col, row, typeSeat);
    }

    @Override
    public String toString() {
        return super.toString() + "\nPrice: " + this.price + "\nFrom: " + this.from
                + "\nTo: " + this.to + "\nDate FROM: " + this.dateFrom + "\nRow: " + this.row
                + "\nCol: " + this.col;
    }

    public void setPrice(double price) {this.price = price;}
    public void setFrom(String from) {this.from = from;}
    public void setTo(String to) {this.to = to;}
    public void setDateFrom(Date dateFrom) {this.dateFrom = dateFrom;}
    public void setRow(int row) {this.row = row;}
    public void setCol(char col){this.col = col;}

    public double getPrice() {return this.price;}
    public String getFrom() {return this.from;}
    public char getType() {return this.typeSeat;}
    public Date getDateFrom() {return dateFrom;}
    public int getRow() {return this.row;}
    public char getCol(){return this.col;}
}
