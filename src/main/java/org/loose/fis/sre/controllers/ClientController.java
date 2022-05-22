package org.loose.fis.sre.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.loose.fis.sre.model.Pet;
import org.loose.fis.sre.model.Announcement;
import org.loose.fis.sre.services.AnnouncementService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class ClientController {
    @FXML
    private TableView<Pet> ID;
    @FXML
    private TableColumn<Pet, String> Name;
    @FXML
    private TableColumn<Pet, String> Category;
    @FXML
    private TableColumn<Pet, String> Type;
    @FXML
    private TableColumn<Pet, String> Info;
    @FXML
    private TableColumn<Pet, String> ImagePath;

    private Parent root;
    private Stage stage;
    @FXML
    private Button SignOutBuyer;
    @FXML
    private Button AvailableProducts, Buy, HomePage, History, Review;
    private ObservableList<Pet> Pete = FXCollections.observableArrayList(
            new Pet("1", "furry"));

    private ArrayList<Pet> list = new ArrayList<>();


//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        Name.setCellValueFactory(new PropertyValueFactory<Pet, String>("name"));
//        Category.setCellValueFactory(new PropertyValueFactory<Pet, String>("category"));
//        Type.setCellValueFactory(new PropertyValueFactory<Pet, String>("type"));
//        Info.setCellValueFactory(new PropertyValueFactory<Pet, String>("info"));
////        TableDe.setCellValueFactory(new PropertyValueFactory<Pet, String>("descr"));
////        Table.setItems(Pete);
//    }


    public void gotoPages(ActionEvent event) throws Exception {
        if (event.getSource() == AvailableProducts) {
            this.root = (Parent) FXMLLoader.load(getClass().getClassLoader().getResource("AvailableProducts.fxml"));
            this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(this.root);
            this.stage.setTitle("Products Available");
            this.stage.setScene(scene);
            this.stage.show();
        }
        if (event.getSource() == HomePage) {
            this.root = (Parent) FXMLLoader.load(getClass().getClassLoader().getResource("BuyerHomePage.fxml"));
            this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(this.root);
            this.stage.setTitle("Home Page");
            this.stage.setScene(scene);
            this.stage.show();
        }
        if (event.getSource() == Buy) {
            this.root = (Parent) FXMLLoader.load(getClass().getClassLoader().getResource("Buy.fxml"));
            this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(this.root);
            this.stage.setTitle("Buy Products");
            this.stage.setScene(scene);
            this.stage.show();
        }
        if (event.getSource() == History) {
            this.root = (Parent) FXMLLoader.load(getClass().getClassLoader().getResource("HistoryBuyer.fxml"));
            this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(this.root);
            this.stage.setTitle("History");
            this.stage.setScene(scene);
            this.stage.show();
        }
        if (event.getSource() == Review) {
            this.root = (Parent) FXMLLoader.load(getClass().getClassLoader().getResource("BuyerReview.fxml"));
            this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(this.root);
            this.stage.setTitle("Review");
            this.stage.setScene(scene);
            this.stage.show();
        }
    }

    public static void display() {
        Stage window = new Stage();
        Parent root;
        try {
            root = FXMLLoader.load(ClientController.class.getClassLoader().getResource("BuyerHomePage.fxml"));
            Scene scene = new Scene(root);
            window.setTitle("Home Page");
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
