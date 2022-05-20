package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.NotComplete;
import org.loose.fis.sre.exceptions.InvalidPassword;
import org.loose.fis.sre.exceptions.NameAlreadyExistsException;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;

public class RegistrationController {

    @FXML
    private Text registrationMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField passwordField2;
    @FXML
    private TextField NameField;
    @FXML
    private TextField PhoneNumField;
    @FXML
    private TextField EmailField;
    @FXML
    private ChoiceBox role;

    @FXML
    public void initialize() {
        role.getItems().addAll("Client", "Owner");
    }

    @FXML
    public void handleRegisterAction() {
        try {
            UserService.addUser(NameField.getText(), passwordField.getText(),passwordField2.getText(), PhoneNumField.getText(), EmailField.getText(), (String) role.getValue() );
            registrationMessage.setText("Account created successfully!");
        } catch (NameAlreadyExistsException e) {
            registrationMessage.setText(e.getMessage());
        }
        catch (InvalidPassword p) {
            registrationMessage.setText(p.getMessage());
        }
        catch (NotComplete n) {
            registrationMessage.setText(n.getMessage());
        }

    }
    @FXML
    private Hyperlink loginPressed;
    @FXML
    public void handleLoginMenu(javafx.event.ActionEvent event) throws IOException {
        Stage stage = (Stage) loginPressed.getScene().getWindow();
        stage.close();

        Parent register = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        Scene scene = new Scene(register);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }
}
