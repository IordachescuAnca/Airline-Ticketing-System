package Entities;

import Entities.Airplanes.LowCostAirplane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Flight {
    private Integer airplaneId;
    private Double priceStandard;
    private String fromLocation;
    private String toLocation;
    private Date dateFromLocation;
    private Date dateToLocation;

    public Flight( Integer airplaneId, Double priceStandard, String fromLocation, String toLocation, Date dateFromLocation, Date dateToLocation) {
        this.airplaneId = airplaneId;
        this.priceStandard = priceStandard;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.dateFromLocation = dateFromLocation;
        this.dateToLocation = dateToLocation;
    }

    public void writeData(){
        String connectionURL = "jdbc:sqlserver://pao-project.database.windows.net:1433;database=PAO-db;user=anca@pao-project;password=qwer1234!@#$;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
        Connection connection;
        try{
            connection = DriverManager.getConnection(connectionURL);
            Statement stmt = connection.createStatement();
            DateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm", Locale.FRENCH);
            String strFromLocation= format.format(this.dateFromLocation);
            String strToLocation = format.format(this.dateToLocation);
            stmt.executeUpdate("INSERT INTO dbo.FLIGHTS (PriceStandard, fromLocation, toLocation, dateFromLocation, dateToLocation,  Airplane_id) "
                    + "VALUES ( " + this.priceStandard + " , '" + this.fromLocation + "' , '" + this.toLocation + "' , '" + strFromLocation + "' , '" + strToLocation + "' , " + this.airplaneId  +  " )");


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static Flight readData(Integer airplaneId){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter price:");
        Double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Location From:");
        String fromLocation = scanner.nextLine();

        System.out.println("Location To:");
        String toLocation = scanner.nextLine();

        System.out.println("Date location from:");
        String dateLocationFrom = scanner.nextLine();

        System.out.println("Date location to:");
        String dateLocationTo = scanner.nextLine();

        DateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm", Locale.FRENCH);
        Date dateFrom = null;
        Date dateTo = null;
        try {
            dateFrom = format.parse(dateLocationFrom);
            dateTo = format.parse(dateLocationTo);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new Flight(airplaneId, price, fromLocation, toLocation, dateFrom, dateTo);
    }

    public Integer getAirplaneId() {
        return airplaneId;
    }

    public Double getPriceStandard() {
        return priceStandard;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public String getToLocation() {
        return toLocation;
    }

    public Date getDateFromLocation() {
        return dateFromLocation;
    }

    public Date getDateToLocation() {
        return dateToLocation;
    }

    public void setAirplaneId(Integer airplaneId) {
        this.airplaneId = airplaneId;
    }

    public void setPriceStandard(Double priceStandard) {
        this.priceStandard = priceStandard;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public void setDateFromLocation(Date dateFromLocation) {
        this.dateFromLocation = dateFromLocation;
    }

    public void setDateToLocation(Date dateToLocation) {
        this.dateToLocation = dateToLocation;
    }

}