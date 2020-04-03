package Entities;
import java.lang.*;

public class Seat {
    private char column;
    private int row;
    private boolean availability;
    private char type;
    public Seat(char column, int row, boolean availability, char type){
        this.column = Character.toUpperCase(column);
        this.row = row;
        this.availability = availability;
        this.type = type;
    }

    public Seat copy(){
        return new Seat(column, row, availability, type);
    }

    @Override
    public String toString() {
        String availabilityStr = this.availability? "yes": "no";
        return "Row: " + this.row + "\nColumn: " + this.column
                + "\nThe seat is available: " + availabilityStr
                + "\nType: " + this.type;
    }

    public void setRow(int row) {this.row = row;}
    public void setColumn(char column) {this.column = Character.toUpperCase(column);}
    public void setAvailable(boolean availability) {this.availability = availability;}
    public void setType(char type) {this.type = type;}

    public int getRow() {return this.row;}
    public char getColumn() {return this.column;}
    public boolean getAvailability() {return this.availability;}
    public char getType() {return type;}

}
