package Entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Flight {
    private String id;
    private Airplane airplane;
    private double price;
    private String from;
    private String to;
    private Date dateFrom;
    private Date dateTo;
    private List<Ticket> clientTickets;

    public Flight(String id, double price, String from, String to, String dateFrom, String dateTo, Airplane airplane) {
        this.id = id;
        this.airplane = airplane.copy();
        this.price = price;
        this.from = from;
        this.to = to;

        String patternDate = "MM-dd-yyyy";
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat(patternDate);
            this.dateFrom = dateFormat.parse(dateFrom);
        }
        catch(ParseException exception){
            this.dateFrom = null;
        }
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat(patternDate);
            this.dateTo = dateFormat.parse(dateTo);
        }
        catch(ParseException exception){
            this.dateTo = null;
        }
        this.clientTickets = new ArrayList<Ticket>();
    }

    public Flight copy(){
        String dateF = new SimpleDateFormat("MM-dd-YYYY").format(dateFrom);
        String dateT = new SimpleDateFormat("MM-dd-YYYY").format(dateTo);
        return new Flight(id, price, from, to, dateF, dateT, airplane);
    }

    public void addTicket(Ticket newTicket){
        this.clientTickets.add(newTicket);
        this.airplane.setDisponibilitySeat(newTicket.getRow(), newTicket.getCol(), false);
    }

    public void removeTicket(Ticket oldTicket){
        this.clientTickets.remove(oldTicket);
        this.airplane.setDisponibilitySeat(oldTicket.getRow(), oldTicket.getCol(), true);
    }

    public void displayUnavailableSeats(){
        for(Seat seat: this.airplane.getSeats()){
            if(!seat.getAvailability()){
                System.out.println(seat.toString());
            }
        }
    }

    public void setId(String id) {this.id = id;}
    public void setPrice(double price) {this.price = price;}
    public void setFrom(String from) {this.from = from;}
    public void setTo(String to) {this.to = to;}
    public void setDateFrom(Date dateFrom) {this.dateFrom = dateFrom;}
    public void setDateTo(Date dateTo) {this.dateTo = dateTo;}
    public void setAirplane(Airplane airplane) {this.airplane = airplane;}

    public void setClientTickets(List<Ticket> clientTickets) {this.clientTickets = clientTickets;}

    public String getId() {return this.id;}
    public Airplane getAirplane() {return airplane;}
    public double getPrice() {return this.price;}
    public String getFrom() {return this.from;}
    public String getTo() {return this.to;}
    public Date getDateFrom() {return this.dateFrom;}
    public Date getDateTo() {return this.dateTo;}
    public List<Ticket> getClientTickets() {return clientTickets;}

    @Override
    public String toString() {
        String information = "Flight ID: " + this.id + "\nAirplane: " + this.airplane.getType()
                + "\nCapacity: " + this.airplane.getCapacity() + "\n";
        information += this.airplane.toString();
        return information;
    }
}
