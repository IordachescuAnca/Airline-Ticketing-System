package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Button employee;

    @FXML
    private Button client;


    @FXML
    void clickClient(ActionEvent actionEvent){
        System.out.println("There");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void clickEmployee(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent mainPage = FXMLLoader.load(getClass().getResource("LogInEmployee.fxml"));
        Scene mainPageScene = new Scene(mainPage);
        Stage appStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(mainPageScene);
    }

    public void clickClient(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent mainPage = FXMLLoader.load(getClass().getResource("LogInClient.fxml"));
        Scene mainPageScene = new Scene(mainPage);
        Stage appStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(mainPageScene);
    }
}
