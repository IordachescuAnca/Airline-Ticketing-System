package Entities;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
public abstract class Airplane {
    private int numberRows;
    private int numberColumns;
    private int capacity;
    private Date manufacturingDate;
    private String manufacturingCompany;
    private String type;
    protected List<Seat> seats;
    public Airplane(int numberRows, int numberColumns, String manufacturingDate, String manufacturingCompany, String type){
        this.numberRows = numberRows;
        this.numberColumns = numberColumns;
        this.capacity = numberRows * numberColumns;
        this.manufacturingCompany = manufacturingCompany;
        this.type = type;
        try{
            String patternDate = "MM-dd-yyyy";
            SimpleDateFormat dateFormat = new SimpleDateFormat(patternDate);
            this.manufacturingDate = dateFormat.parse(manufacturingDate);
        }
        catch(ParseException exception){
            this.manufacturingDate = null;
        }

    }
    public abstract void setSeats();

    public void setDisponibilitySeat(int row, char column, boolean disponibility) throws IndexOutOfBoundsException{
        seats.get(this.numberColumns * (row - 1) + column - 'A').setAvailable(disponibility);
    }
    @Override
    public String toString() {
        String information = "";
        for (Seat seat : seats) {
            information = information + seat.toString() + "\n";
        }
        return information;
    }

    public void setCapacity(int capacity) {this.capacity = capacity;}
    public void setNumberColumns(int numberColumns) {this.numberColumns = numberColumns;}
    public void setNumberRows(int numberRows) {this.numberRows = numberRows;}
    public void setType(String type) {this.type = type;}
    public void setManufacturingDate(Date manufacturingDate) {this.manufacturingDate = manufacturingDate;}
    public void setManufacturingCompany(String manufacturingCompany) {this.manufacturingCompany = manufacturingCompany;}
    public void setSeats(List<Seat> seats) {this.seats = seats;}

    public List<Seat> getSeats() {
        return seats;
    }
    public int getNumberRows() {return this.numberRows;}
    public int getNumberColumns() {return this.numberColumns;}
    public int getCapacity() {return this.capacity;}
    public Date getManufacturingDate() {return this.manufacturingDate;}
    public String getManufacturingCompany() {return this.manufacturingCompany;}
    public String getType() {return this.type;}

    public abstract Airplane copy();
}
