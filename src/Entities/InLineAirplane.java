package Entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class InLineAirplane extends Airplane {
    private int capacityBusiness;

    public InLineAirplane(int numberRows, int numberColumns, String manufacturingDate, String manufacturingCompany, String type, int capacityBusiness){
        super(numberRows, numberColumns, manufacturingDate, manufacturingCompany, type);
        this.capacityBusiness = Math.min(capacityBusiness, this.getCapacity());
        this.setSeats();
    }

    public InLineAirplane copy() {
        String dateManufacturing = new SimpleDateFormat("MM-dd-YYYY").format(super.getManufacturingDate());
        InLineAirplane newObj = new InLineAirplane(super.getNumberRows(), super.getNumberColumns(), dateManufacturing, super.getManufacturingCompany(), super.getType(), capacityBusiness);
        newObj.seats.clear();
        for(Seat seat: this.seats){
            newObj.seats.add(seat.copy());
        }
        return newObj;
    }

    @Override
    public void setSeats() {
        int numberBusinessSeats = this.capacityBusiness;
        this.seats = new ArrayList<Seat>();
        for(int row = 1; row <= this.getNumberRows(); ++row){
            for(char column = 'A'; column < 'A' + this.getNumberColumns(); ++column){
                Seat seat = new Seat(column, row, true, 'E');
                if(numberBusinessSeats > 0){
                    seat.setType('B');
                    --numberBusinessSeats;
                }
                seats.add(seat);
            }
        }
    }

    public int getCapacityBusiness() {
        return capacityBusiness;
    }

    public void setCapacityBusiness(int capacityBusiness) {
        this.capacityBusiness = capacityBusiness;
    }
}
