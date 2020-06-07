package Entities.Services;

import Entities.Users.Client;
import Entities.Users.Employee;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class AirportService {
    private String connectionURL = "jdbc:sqlserver://pao-project.database.windows.net:1433;database=PAO-db;user=anca@pao-project;password=qwer1234!@#$;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    private Connection connection;
    private static Scanner scanner = new Scanner(System.in);

    public AirportService() {
        try{
            connection = DriverManager.getConnection(connectionURL);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String logIn(String username, String password){

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT UserName, Pass FROM dbo.EMPLOYEES");
            while (rs.next()){
                if (rs.getString("UserName").equals(username) && rs.getString("Pass").equals(password)){
                    return "Employee";
                }
            }

            rs = stmt.executeQuery("SELECT UserName, Pass FROM dbo.CLIENTS");
            while (rs.next()){
                if (rs.getString("UserName").equals(username) && rs.getString("Pass").equals(password)){
                    return "Client";
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Boolean checkExistanceAirplane(Integer airplaneId){
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Airplane_id FROM dbo.AIRPLANES WHERE Airplane_id = " + airplaneId);
            if(rs.next()){
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public void deleteFlight(Integer flightID, Integer airplaneID){
        try {
            Statement stmt = connection.createStatement();
            String query = "DELETE FROM dbo.FLIGHTS WHERE Flight_id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, flightID);
            preparedStmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            Statement stmt = connection.createStatement();
            String query = "DELETE FROM dbo.TICKETS WHERE Flight_id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, flightID);
            preparedStmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            Statement stmt = connection.createStatement();
            String query = "UPDATE dbo.SEATS SET Available = 1 WHERE Airplane_id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, airplaneID);
            preparedStmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println("Delete flight done successfully!");
    }

}
