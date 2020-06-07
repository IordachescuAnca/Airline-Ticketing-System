package sample;

import Entities.Services.AirportService;
import Entities.Services.EmployeeTableService;
import Entities.Users.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

public class SignUpEmployee {
    @FXML
    public TextField email;
    @FXML
    public TextField lastName;
    @FXML
    public TextField firstName;
    @FXML
    public PasswordField password;
    @FXML
    public TextField phoneNumber;
    @FXML
    public TextField dateHire;
    @FXML
    public TextField jobTitle;
    @FXML
    public TextField department;
    @FXML
    public TextField dateBirth;

    public void goToLogInEmployee(MouseEvent mouseEvent) throws IOException {
        Parent mainPage = FXMLLoader.load(getClass().getResource("LogInEmployee.fxml"));
        Scene mainPageScene = new Scene(mainPage);
        Stage appStage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        appStage.setScene(mainPageScene);
    }

    public void signUpEmployeeAccount(ActionEvent actionEvent) throws IOException {
        Parent mainPage = FXMLLoader.load(getClass().getResource("SignUpEmployee.fxml"));
        Scene mainPageScene = new Scene(mainPage);

        String firstName = this.firstName.getText();
        String lastName = this.lastName.getText();
        String email = this.email.getText();
        String phoneNumber = this.phoneNumber.getText();
        String dateBirth = this.dateBirth.getText();
        String userName = firstName + lastName;
        String password = this.password.getText();
        String dateHire = this.dateHire.getText();
        String jobTitle = this.jobTitle.getText();
        String department = this.department.getText();

        if (dateHire.equals("") || jobTitle.equals("") || department.equals("") || firstName.equals("") || lastName.equals("") || email.equals("") || phoneNumber.equals("") || dateBirth.equals("") || password.equals("")){
            System.out.println("The fields must not be empty!");
            Parent actualPage = FXMLLoader.load(getClass().getResource("SignUpEmployee.fxml"));
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
        }

        Date dateHireformat = null;
        try{
            dateHireformat = dateFormat.parse(dateHire);
        }
        catch(ParseException exception){
            System.out.println("Invalid hire date!");
        }

        Employee newEmployee = new Employee(firstName, lastName, userName, password, internetAddress, phoneNumber, dateBirthformat, dateHireformat, jobTitle, department);
        EmployeeTableService employeeTableService = new EmployeeTableService();
        employeeTableService.addEmployee(newEmployee);
        System.out.println("Created a new account! - Employee");
        Parent actualPage = FXMLLoader.load(getClass().getResource("LogInEmployee.fxml"));
        Scene actualPageScene = new Scene(actualPage);
        Stage appStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(actualPageScene);
    }

}
