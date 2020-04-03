package Entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class LowCostAirplane extends Airplane {
    public LowCostAirplane(int numberRows, int numberColumns, String manufacturingDate, String manufacturingCompany, String type){
        super(numberRows, numberColumns, manufacturingDate, manufacturingCompany, type);
        this.setSeats();
    }

    @Override
    public void setSeats() {
        this.seats = new ArrayList<>();
        for(int row = 1; row <= this.getNumberRows(); ++row){
            for(char column = 'A'; column < 'A' + this.getNumberColumns(); ++column){
                Seat seat = new Seat(column, row, true, 'E');
                seats.add(seat);
            }
        }
    }

    public LowCostAirplane copy() {
        String dateManufacturing = new SimpleDateFormat("MM-dd-YYYY").format(super.getManufacturingDate());
        LowCostAirplane newObj =  new LowCostAirplane(super.getNumberRows(), super.getNumberColumns(), dateManufacturing, super.getManufacturingCompany(), super.getType());
        newObj.seats.clear();
        for(Seat seat: this.seats){
            newObj.seats.add(seat.copy());
        }
        return newObj;
    }
}
