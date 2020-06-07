package Entities.Services;

import Entities.Airplanes.InLineAirplane;
import Entities.Seat;

import java.sql.*;
import java.util.Scanner;

public class InLinePlaneService {
    private String connectionURL = "jdbc:sqlserver://pao-project.database.windows.net:1433;database=PAO-db;user=anca@pao-project;password=qwer1234!@#$;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    private Connection connection;
    private static Scanner scanner = new Scanner(System.in);

    public InLinePlaneService() {
        try{
            connection = DriverManager.getConnection(connectionURL);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addAirplane(InLineAirplane newAirplane){
        String name = newAirplane.getName();
        String manufacturingCompany = newAirplane.getManufacturingCompany();
        Integer numberRows = newAirplane.getNumberRows();
        Integer numberColumns = newAirplane.getNumberColumns();

        try{
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO dbo.AIRPLANES (NamePlane, ManufacturingCompany, NumberRows, NumberColumns) "
                    + "VALUES ( '" + name+ "' , '" + manufacturingCompany + "' , " + numberRows
                    + " , " + numberColumns  + " )", Statement.RETURN_GENERATED_KEYS);
            Integer affectedRows = stmt.executeUpdate();
            Integer airplaneId = null;
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    airplaneId = generatedKeys.getInt(1);
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

            Integer capacityBusiness = newAirplane.getCapacityBusiness();
            String query = "UPDATE dbo.AIRPLANES set TypeAirplane = ?, BusinessCapacity = ?  where Airplane_id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, "InLine");
            preparedStmt.setInt(2, capacityBusiness);
            preparedStmt.setInt(3, airplaneId);
            preparedStmt.executeUpdate();
            Integer contor = 0;

            for (Integer row = 1; row <= numberRows; ++row){
                for(Character column = 'A'; column < 'A' + numberColumns; ++column){
                    Character type = 'E';
                    if(contor <= capacityBusiness){
                        contor += 1;
                        type = 'B';
                    }
                    Seat newSeat = new Seat(column, row, true, type, airplaneId.toString());
                    Statement stmt1 = connection.createStatement();
                    stmt1.executeUpdate("INSERT INTO dbo.SEATS (ColumnSeat, RowSeat, Available, TypeSeat, Airplane_id) "
                            + "VALUES ( '" + newSeat.getColumn() + "' , " + newSeat.getRow() + " , " + (newSeat.getAvailable() ? 1: 0) + " , '" + newSeat.getType() + "' , '" + newSeat.getAirplaneID()  +  "' )");

                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteInLineAirplane(Integer id){
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

    public void updateInLineAirplane(Integer id, String name){
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
            ResultSet rs = stmt.executeQuery("SELECT * FROM dbo.AIRPLANES TypeAirplane = 'InLine'");
            while (rs.next()){
                System.out.println("Name: " + rs.getString("NamePlane"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
