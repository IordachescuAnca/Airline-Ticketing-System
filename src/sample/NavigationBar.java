package sample;

import Entities.Airplanes.InLineAirplane;
import Entities.Airplanes.LowCostAirplane;
import Entities.Flight;
import Entities.Services.AirportService;
import Entities.Services.Audit;
import Entities.Services.InLinePlaneService;
import Entities.Services.LowCostPlaneService;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NavigationBar {
    public TextField nameAirplane;
    public TextField company;
    public TextField type;
    public TextField nrRows;
    public TextField nrColumns;
    public TextField businessCapacity;
    public TextField airplaneId;
    public TextField locationFrom;
    public TextField locationTo;
    public TextField dateLocationFrom;
    public TextField dateLocationTo;
    public TextField standardPrice;
    public Button addFlighBtn;
    public Button removeFlight1;
    public TextField FlightIDRemove;
    public TextField AirplaneIDremove;

    public void goToHome(ActionEvent actionEvent) throws IOException {
        Parent mainPage = FXMLLoader.load(getClass().getResource("NavigationBar.fxml"));
        Scene mainPageScene = new Scene(mainPage);
        Stage appStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(mainPageScene);
    }

    public void goToAirplane(ActionEvent actionEvent) throws IOException {
        Parent mainPage = FXMLLoader.load(getClass().getResource("addingAirplanes.fxml"));
        Scene mainPageScene = new Scene(mainPage);
        Stage appStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(mainPageScene);
    }

    public void goToRemoveFlight(ActionEvent actionEvent) throws IOException {
        Parent mainPage = FXMLLoader.load(getClass().getResource("RemoveFlight.fxml"));
        Scene mainPageScene = new Scene(mainPage);
        Stage appStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(mainPageScene);
    }

    public void deleteAccount(ActionEvent actionEvent) {
    }

    public void goToLogOut(ActionEvent actionEvent) throws IOException {
        Audit audit = new Audit();
        audit.writeAction("goToLogOut");
        Parent mainPage = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene mainPageScene = new Scene(mainPage);
        Stage appStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(mainPageScene);
    }

    public void goToAddFlight(ActionEvent actionEvent) throws IOException {
        Parent mainPage = FXMLLoader.load(getClass().getResource("AddFlight.fxml"));
        Scene mainPageScene = new Scene(mainPage);
        Stage appStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(mainPageScene);
    }

    public void addFlightToDB(ActionEvent actionEvent) throws IOException {
        Parent mainPage = FXMLLoader.load(getClass().getResource("AddFlight.fxml"));
        Scene mainPageScene = new Scene(mainPage);
        String airplaneID = this.airplaneId.getText();
        String locationFromData = this.locationFrom.getText();
        String locationToData = this.locationTo.getText();
        String dateLocationFromData = this.dateLocationFrom.getText();
        String dateLocationToData = this.dateLocationTo.getText();
        String priceStandard = this.standardPrice.getText();

        Integer airplaneIDInt = Integer.parseInt(airplaneID);
        Double priceStnd = Double.parseDouble(priceStandard);

        AirportService airportService = new AirportService();
        Boolean existsAirplane = airportService.checkExistanceAirplane(airplaneIDInt);
        if(!existsAirplane){
            System.out.println("There is no such place!");
            Parent mainPage1 = FXMLLoader.load(getClass().getResource("NavigationBar.fxml"));
            Scene mainPageScene1 = new Scene(mainPage1);
            Stage appStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            appStage.setScene(mainPageScene1);
            return;
        }

        DateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm", Locale.FRENCH);
        Date dateFrom = null;
        Date dateTo = null;
        try {
            dateFrom = format.parse(dateLocationFromData);
            dateTo = format.parse(dateLocationToData);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Flight newFlight = new Flight(airplaneIDInt, priceStnd, locationFromData, locationToData, dateFrom, dateTo);
        newFlight.writeData();
        Audit audit = new Audit();
        audit.writeAction("addFlightToDB");
        System.out.println("Add flight");
        Parent mainPage1 = FXMLLoader.load(getClass().getResource("NavigationBar.fxml"));
        Scene mainPageScene1 = new Scene(mainPage1);
        Stage appStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(mainPageScene1);
    }

    public void addingAirplanesToDb(ActionEvent actionEvent) throws IOException {
        Parent mainPage = FXMLLoader.load(getClass().getResource("AddingAirplanes.fxml"));
        Scene mainPageScene = new Scene(mainPage);
        String name = this.nameAirplane.getText();
        String manCompany = this.company.getText();
        String type = this.type.getText();
        String numberRows = this.nrRows.getText();
        String numberColumns = this.nrColumns.getText();

        Integer rows = Integer.parseInt(numberRows);
        Integer columns = Integer.parseInt(numberColumns);

        if(type.equalsIgnoreCase("InLine")){
            String capacityBusiness = this.businessCapacity.getText();
            Integer capBusiness = Integer.parseInt(capacityBusiness);

            InLineAirplane newAirplane = new InLineAirplane(name, manCompany, rows, columns, capBusiness);
            InLinePlaneService inLinePlaneService = new InLinePlaneService();
            inLinePlaneService.addAirplane(newAirplane);
            System.out.println("Add airplane!");
            Parent mainPage1 = FXMLLoader.load(getClass().getResource("NavigationBar.fxml"));
            Scene mainPageScene1 = new Scene(mainPage1);
            Stage appStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            appStage.setScene(mainPageScene1);
        }
        else{
            LowCostAirplane newAirplane = new LowCostAirplane(name, manCompany, rows, columns);
            LowCostPlaneService lowCostAirplane = new LowCostPlaneService();
            lowCostAirplane.addAirplane(newAirplane);
            System.out.println("Add airplane!");
            Audit audit = new Audit();
            audit.writeAction("addAirplanesToDB");
            Parent mainPage1 = FXMLLoader.load(getClass().getResource("NavigationBar.fxml"));
            Scene mainPageScene1 = new Scene(mainPage1);
            Stage appStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            appStage.setScene(mainPageScene1);
        }
    }

    public void removeFlightFromDB(ActionEvent actionEvent) throws IOException {
        Parent mainPage = FXMLLoader.load(getClass().getResource("RemoveFlight.fxml"));
        Scene mainPageScene = new Scene(mainPage);
        String flightID_new = this.FlightIDRemove.getText();
        String airplaneID_new = this.AirplaneIDremove.getText();
        Integer fID = Integer.parseInt(flightID_new);
        Integer aID = Integer.parseInt(airplaneID_new);

        AirportService airportService = new AirportService();
        airportService.deleteFlight(fID, aID);
        Audit audit = new Audit();
        audit.writeAction("removeFlightFromDB");
        Parent mainPage1 = FXMLLoader.load(getClass().getResource("NavigationBar.fxml"));
        Scene mainPageScene1 = new Scene(mainPage1);
        Stage appStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(mainPageScene1);
    }
}
