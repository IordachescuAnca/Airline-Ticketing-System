package Entities.Services;

import Entities.Airplanes.InLineAirplane;
import Entities.Airplanes.LowCostAirplane;
import Entities.Seat;

import java.sql.*;
import java.util.Scanner;

public class LowCostPlaneService {
    private String connectionURL = "jdbc:sqlserver://pao-project.database.windows.net:1433;database=PAO-db;user=anca@pao-project;password=qwer1234!@#$;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    private Connection connection;
    private static Scanner scanner = new Scanner(System.in);

    public LowCostPlaneService() {
        try{
            connection = DriverManager.getConnection(connectionURL);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addAirplane(LowCostAirplane newAirplane) {
        String name = newAirplane.getName();
        String manufacturingCompany = newAirplane.getManufacturingCompany();
        Integer numberRows = newAirplane.getNumberRows();
        Integer numberColumns = newAirplane.getNumberColumns();

        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO dbo.AIRPLANES (NamePlane, ManufacturingCompany, NumberRows, NumberColumns) "
                    + "VALUES ( '" + name + "' , '" + manufacturingCompany + "' , " + numberRows
                    + " , " + numberColumns + " )", Statement.RETURN_GENERATED_KEYS);
            Integer affectedRows = stmt.executeUpdate();
            Integer airplaneId = null;
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    airplaneId = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

            String query = "UPDATE dbo.AIRPLANES set TypeAirplane = ? where Airplane_id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString   (1, "LowCost");
            preparedStmt.setInt(2, airplaneId);
            preparedStmt.executeUpdate();


            for (Integer row = 1; row <= numberRows; ++row){
                for(Character column = 'A'; column < 'A' + numberColumns; ++column){
                    Seat newSeat = new Seat(column, row, true, 'E', airplaneId.toString());
                    Statement stmt1 = connection.createStatement();
                    stmt1.executeUpdate("INSERT INTO dbo.SEATS (ColumnSeat, RowSeat, Available, TypeSeat, Airplane_id) "
                            + "VALUES ( '" + newSeat.getColumn() + "' , " + newSeat.getRow() + " , " + (newSeat.getAvailable() ? 1: 0) + " , '" + newSeat.getType() + "' , '" + newSeat.getAirplaneID()  +  "' )");

                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



    public void deleteLowCostAirplane(Integer id){
        try {
            Statement stmt = connection.createStatement();
            String query = "DELETE FROM dbo.AIRPLANES WHERE Airplane_id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, id);
            preparedStmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateLowCostAirplane(Integer id, String name){
        try {
            Statement stmt = connection.createStatement();
            String query = "UPDATE dbo.AIRPLANES SET = ? WHERE Airplane_id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, name);
            preparedStmt.setInt(2, id);
            preparedStmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void displayFlights(){
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM dbo.AIRPLANES TypeAirplane = 'LowCost'");
            while (rs.next()){
                System.out.println("Name: " + rs.getString("NamePlane"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
