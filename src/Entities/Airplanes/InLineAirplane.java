package Entities.Airplanes;

import Entities.Seat;

import java.sql.*;
import java.util.Scanner;

public class InLineAirplane extends Airplane{
    private Integer capacityBusiness;

    public InLineAirplane(String name, String manufacturingCompany, Integer numberRows, Integer numberColumns, Integer capacityBusiness) {
        super(name, manufacturingCompany, numberRows, numberColumns);
        this.capacityBusiness = capacityBusiness;
    }

    public Integer getCapacityBusiness() {
        return capacityBusiness;
    }

    public void setCapacityBusiness(Integer capacityBusiness) {
        this.capacityBusiness = capacityBusiness;
    }

    /*@Override
    public void writeData() {
        super.writeData();
        this.generateSeats(this.airplaneId);
    }*/

    public static InLineAirplane readData(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Name:");
        String name = scanner.nextLine();

        System.out.println("Manufacturing company:");
        String manufacturingCompany = scanner.nextLine();

        System.out.println("Number rows:");
        Integer numberRows = scanner.nextInt();

        System.out.println("Number columns:");
        Integer numberColumns = scanner.nextInt();

        System.out.println("Business capacity");
        Integer businessCapacity = scanner.nextInt();

        return new InLineAirplane(name, manufacturingCompany, numberRows, numberColumns, businessCapacity);
    }

    @Override
    public void generateSeats(Integer airplaneId) {
        String connectionURL = "jdbc:sqlserver://pao-project.database.windows.net:1433;database=PAO-db;user=anca@pao-project;password=qwer1234!@#$;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
        Connection connection;
        Integer contor = 0;
        try{
            connection = DriverManager.getConnection(connectionURL);
            String query = "UPDATE dbo.AIRPLANES set TypeAirplane = ?, BusinessCapacity = ?  where Airplane_id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, "InLine");
            preparedStmt.setInt(2, this.capacityBusiness);
            preparedStmt.setInt(3, this.airplaneId);
            preparedStmt.executeUpdate();

            for (Integer row = 1; row <= numberRows; ++row){
                for(Character column = 'A'; column < 'A' + numberColumns; ++column){
                    Character type = 'E';
                    if(contor <= this.capacityBusiness){
                        contor += 1;
                        type = 'B';
                    }
                    Seat newSeat = new Seat(column, row, true, type, airplaneId.toString());
                    Statement stmt = connection.createStatement();
                    stmt.executeUpdate("INSERT INTO dbo.SEATS (ColumnSeat, RowSeat, Available, TypeSeat, Airplane_id) "
                            + "VALUES ( '" + newSeat.getColumn() + "' , " + newSeat.getRow() + " , " + (newSeat.getAvailable() ? 1: 0) + " , '" + newSeat.getType() + "' , '" + newSeat.getAirplaneID()  +  "' )");

                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}