package sample;

import Entities.Services.AirportService;
import Entities.Services.Audit;
import Entities.Services.ClientTableService;
import Entities.Users.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SignUpClient {

    @FXML
    public Button signUpClient;

    @FXML
    public TextField lastName;
    @FXML
    public TextField email;
    @FXML
    public TextField phoneNumber;
    @FXML
    public TextField dateBirth;
    @FXML
    public PasswordField password;
    @FXML
    public TextField firstName;
    @FXML
    public Label labelClient;

    public void backtoLogInClient(MouseEvent mouseEvent) throws IOException {
        Parent mainPage = FXMLLoader.load(getClass().getResource("LogInClient.fxml"));
        Scene mainPageScene = new Scene(mainPage);
        Stage appStage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        appStage.setScene(mainPageScene);
    }

    @FXML
    public void signUpClient(ActionEvent actionEvent) throws IOException, InterruptedException {
        signUpClient.setOnAction(null);
        Parent mainPage = FXMLLoader.load(getClass().getResource("SignUpClient.fxml"));
        Scene mainPageScene = new Scene(mainPage);

        String firstName = this.firstName.getText();
        String lastName = this.lastName.getText();
        String email = this.email.getText();
        String phoneNumber = this.phoneNumber.getText();
        String dateBirth = this.dateBirth.getText();
        String userName = firstName + lastName;
        String password = this.password.getText();


        if (firstName.equals("") || lastName.equals("") || email.equals("") || phoneNumber.equals("") || dateBirth.equals("") || password.equals("")){
            System.out.println("The fields must not be empty!");
            Parent actualPage = FXMLLoader.load(getClass().getResource("SignUpClient.fxml"));
            Scene actualPageScene = new Scene(actualPage);
            Stage appStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            appStage.setScene(actualPageScene);
            return;
        }

        InternetAddress internetAddress = null;
        try{
            internetAddress = new InternetAddress(email);
        } catch (AddressException e) {
            System.out.println("Incorrect email!");
            Parent actualPage = FXMLLoader.load(getClass().getResource("SignUpClient.fxml"));
            Scene actualPageScene = new Scene(actualPage);
            Stage appStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            appStage.setScene(actualPageScene);
            return;
        }

        String patternDate = "MM-dd-yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(patternDate);
        Date dateBirthformat = null;
        try{
            dateBirthformat = dateFormat.parse(dateBirth);
        }
        catch(ParseException exception){
            System.out.println("Invalid birth date!");
            Parent actualPage = FXMLLoader.load(getClass().getResource("SignUpClient.fxml"));
            Scene actualPageScene = new Scene(actualPage);
            Stage appStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            appStage.setScene(actualPageScene);
            return;
        }

        Client newClient = new Client(firstName, lastName, userName, password, internetAddress, phoneNumber, dateBirthformat);

        ClientTableService clientTableService = new ClientTableService();
        clientTableService.addClient(newClient);
        System.out.println("Created a new account! - Client");
        Audit audit = new Audit();
        audit.writeAction("signUpClient");
        Parent actualPage = FXMLLoader.load(getClass().getResource("LogInClient.fxml"));
        Scene actualPageScene = new Scene(actualPage);
        Stage appStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(actualPageScene);
    }
}
