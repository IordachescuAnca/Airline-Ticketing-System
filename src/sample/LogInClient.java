package sample;

import Entities.Services.AirportService;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LogInClient {
    public void goBack(MouseEvent mouseEvent) throws IOException {
        System.out.println("Client");
        Parent mainPage = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene mainPageScene = new Scene(mainPage);
        Stage appStage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        appStage.setScene(mainPageScene);
    }

    public void goSignUpClient(ActionEvent actionEvent) throws IOException {
        Parent mainPage = FXMLLoader.load(getClass().getResource("SignUpClient.fxml"));
        Scene mainPageScene = new Scene(mainPage);
        Stage appStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(mainPageScene);
    }

    public void goToHomeClient(MouseEvent mouseEvent) {
        AirportService airportService = new AirportService();
    }
}
