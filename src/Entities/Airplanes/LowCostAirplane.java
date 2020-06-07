package Entities.Airplanes;

import Entities.Seat;

import java.sql.*;
import java.util.Scanner;

public class LowCostAirplane extends Airplane{
    public LowCostAirplane(String name, String manufacturingCompany, Integer numberRows, Integer numberColumns) {
        super(name, manufacturingCompany, numberRows, numberColumns);
    }

    /*@Override
    public void writeData() {
        super.writeData();
        this.generateSeats(this.airplaneId);
    }*/

    public static LowCostAirplane readData(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Name:");
        String name = scanner.nextLine();

        System.out.println("Manufacturing company:");
        String manufacturingCompany = scanner.nextLine();

        System.out.println("Number rows:");
        Integer numberRows = scanner.nextInt();

        System.out.println("Number columns:");
        Integer numberColumns = scanner.nextInt();


        return new LowCostAirplane(name, manufacturingCompany, numberRows, numberColumns);
    }

    @Override
    public void generateSeats(Integer airplaneId) {
        String connectionURL = "jdbc:sqlserver://pao-project.database.windows.net:1433;database=PAO-db;user=anca@pao-project;password=qwer1234!@#$;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
        Connection connection;
        try{
            connection = DriverManager.getConnection(connectionURL);
            String query = "UPDATE dbo.AIRPLANES set TypeAirplane = ? where Airplane_id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString   (1, "LowCost");
            preparedStmt.setInt(2, this.airplaneId);
            preparedStmt.executeUpdate();


            for (Integer row = 1; row <= numberRows; ++row){
                for(Character column = 'A'; column < 'A' + numberColumns; ++column){
                    Seat newSeat = new Seat(column, row, true, 'E', airplaneId.toString());
                    Statement stmt = connection.createStatement();
                    stmt.executeUpdate("INSERT INTO dbo.SEATS (ColumnSeat, RowSeat, Available, TypeSeat, Airplane_id) "
                            + "VALUES ( '" + newSeat.getColumn() + "' , " + newSeat.getRow() + " , " + (newSeat.getAvailable() ? 1: 0) + " , '" + newSeat.getType() + "' , '" + newSeat.getAirplaneID()  +  "' )");

                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void main(String[]args){
        //LowCostAirplane a = new LowCostAirplane("Airbus A320neo", "Airbus", 9, 4);
        LowCostAirplane a = LowCostAirplane.readData();
        System.out.println(a.getManufacturingCompany());

    }
}