package sample;

import Entities.Services.AirportService;
import Entities.Services.Audit;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class LogInEmployee {

    public TextField usernameEmployee;
    public PasswordField passwordEmployee;

    public void goBack(MouseEvent mouseEvent) throws IOException {
        System.out.println("Employee");
        Parent mainPage = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene mainPageScene = new Scene(mainPage);
        Stage appStage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        appStage.setScene(mainPageScene);
    }

    public void goToSign(ActionEvent actionEvent) throws IOException {
        Parent mainPage = FXMLLoader.load(getClass().getResource("SignUpEmployee.fxml"));
        Scene mainPageScene = new Scene(mainPage);
        Stage appStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(mainPageScene);
    }

    public void goToHomeEmployee(ActionEvent actionEvent) throws IOException {
        Parent mainPage = FXMLLoader.load(getClass().getResource("LogInEmployee.fxml"));
        Scene mainPageScene = new Scene(mainPage);

        String username = this.usernameEmployee.getText();
        String password = this.passwordEmployee.getText();

        if(username.equals("") || password.equals("")){
            System.out.println("The fields must not be empty!");
            Parent actualPage = FXMLLoader.load(getClass().getResource("LogInEmployee.fxml"));
            Scene actualPageScene = new Scene(actualPage);
            Stage appStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            appStage.setScene(actualPageScene);
            return;
        }

        AirportService airportService = new AirportService();
        String type = airportService.logIn(username, password);
        if(type.equals("Employee")){
            System.out.println("Log in into account!");
            Audit audit = new Audit();
            audit.writeAction("logInEmployee");
            String connectionURL = "jdbc:sqlserver://pao-project.database.windows.net:1433;database=PAO-db;user=anca@pao-project;password=qwer1234!@#$;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
            try{
                Connection connection = DriverManager.getConnection(connectionURL);
                Statement stmt = connection.createStatement();
                stmt.executeUpdate("UPDATE dbo.actual " + "SET Username =  '"  + username + "'");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            Parent actualPage = FXMLLoader.load(getClass().getResource("NavigationBar.fxml"));
            Scene actualPageScene = new Scene(actualPage);
            Stage appStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            appStage.setScene(actualPageScene);
            return;
        }
        System.out.println("Incorect password/username!");
        Parent actualPage = FXMLLoader.load(getClass().getResource("LogInEmployee.fxml"));
        Scene actualPageScene = new Scene(actualPage);
        Stage appStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(actualPageScene);
    }
}
