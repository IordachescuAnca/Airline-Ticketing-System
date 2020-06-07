package Entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Ticket {
    private Integer flightID;
    private Integer clientID;
    private Integer seatID;
    private Double price;

    public Ticket(Integer flightID, Integer clientID, Integer seatID, Double price) {
        this.flightID = flightID;
        this.clientID = clientID;
        this.seatID = seatID;
        this.price = price;
    }

    public void writeData(){
        String connectionURL = "jdbc:sqlserver://pao-project.database.windows.net:1433;database=PAO-db;user=anca@pao-project;password=qwer1234!@#$;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
        Connection connection;
        try{
            connection = DriverManager.getConnection(connectionURL);
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("INSERT INTO dbo.TICKETS (Flight_id, Client_id, Seat_id, Price) "
                    + "VALUES ( " + this.flightID + " , " + this.clientID + " , " + this.seatID + " , " + price + " )");


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Integer getFlightID() {
        return flightID;
    }

    public Integer getClientID() {
        return clientID;
    }

    public Integer getSeatID() {
        return seatID;
    }

    public Double getPrice() {
        return price;
    }

    public void setFlightID(Integer flightID) {
        this.flightID = flightID;
    }

    public void setClientID(Integer clientID) {
        this.clientID = clientID;
    }

    public void setSeatID(Integer seatID) {
        this.seatID = seatID;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
