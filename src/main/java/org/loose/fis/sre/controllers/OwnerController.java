package org.loose.fis.sre.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import org.loose.fis.sre.exceptions.*;
import org.loose.fis.sre.model.Announcement;
import org.loose.fis.sre.model.Pet;
import org.loose.fis.sre.services.AnnouncementService;
import org.loose.fis.sre.model.Announcement;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.services.UserService;
//import javax.sound.midi.Pet;
import java.awt.*;
import java.io.IOException;

public class OwnerController {

        @FXML
        private TableView <Announcement> Table = new TableView<>();
        @FXML
        private TextField PetName;
        @FXML
        private TextField PetCateg;
        @FXML
        private TextArea PetDescr;
        @FXML
        private TextField deleteName;
        private User user;
        private Announcement announcement;

    public void setAnnouncement(Announcement announcement) {
        this.announcement = announcement;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static void setPetOwner(TextField PetOwner) {
            PetOwner = PetOwner;
        }

        @FXML
        private static TextField PetSeller;
        @FXML
        private String PetBuyer;
        @FXML
        private Button SignOutSeller, Home, Sell, Delete, Review, History;
        private Stage stage;
        private Parent root;
        //private Announcement announcement = new Announcement();
//        public void handleAddAction() {
//            Pet i = new Pet();
//            PetClient ="none";
//            if(PetName.getText().equals("") || PetCateg.getText().equals("") || PetDescr.getText().equals("") || PetSeller.getText().equals("") || PetBuyer == "" ){
//                AddException.displayInvalid();
//                return;
//            }
//            else try {
//                announcement.getID(PetName.getText());
//                announcement.getCategory();
//                Pet.set(PetClient);
//                Pet.setOwner(PetSeller.getText());
//                AnnouncementService.addAnnouncement(PetName.getText(), PetCateg.getText(), PetDescr.getText(), PetPrice.getText(), PetBuyer, PetSeller.getText());
//                Table.getItems().add(i);
//                AddException.displayValid();
//            } catch (UsernameAlreadyExistsException e) {
//                AddException.displayInvalid();
//            }
//            PetName.clear();
//
//            PetDescr.clear();
//            PetCateg.clear();
//        }
//        public void handleDeleteAction() {
//            if(deleteName.getText().equals("")){
//                AddException.displayInvalid();
//                return;
//            }
//            else try {
//                PetService.deletePet(deleteName.getText());
//                SuccesDeleteException.displayValid();
//            } catch (UsernameNotExistsException e) {
//                AddException.displayInvalid();
//            }
//        }

        public void gotoPages(ActionEvent event)throws Exception{
            if(event.getSource() == Home){
                this.root = (Parent) FXMLLoader.load(getClass().getClassLoader().getResource("SellerHomePage.fxml"));
                this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(this.root);
                this.stage.setTitle("Home Page");
                this.stage.setScene(scene);
                this.stage.show();
            }
            if(event.getSource() == Sell){
                this.root = (Parent)FXMLLoader.load(getClass().getClassLoader().getResource("ListForSelling.fxml"));
                this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(this.root);
                this.stage.setTitle("Sell");
                this.stage.setScene(scene);
                this.stage.show();
            }
            if(event.getSource() == Delete){
                this.root = (Parent)FXMLLoader.load(getClass().getClassLoader().getResource("DeletePetument.fxml"));
                this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(this.root);
                this.stage.setTitle("Delete");
                this.stage.setScene(scene);
                this.stage.show();
            }
            if(event.getSource() == Review){
                this.root = (Parent)FXMLLoader.load(getClass().getClassLoader().getResource("SellerReviews.fxml"));
                this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(this.root);
                this.stage.setTitle("Reviews");
                this.stage.setScene(scene);
                this.stage.show();
            }
            if(event.getSource() == History){
                this.root = (Parent)FXMLLoader.load(getClass().getClassLoader().getResource("HistorySeller.fxml"));
                this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(this.root);
                this.stage.setTitle("History");
                this.stage.setScene(scene);
                this.stage.show();
            }
        }

        public static void display() {
            Stage window = new Stage();
            Parent root;
            try {
               root = FXMLLoader.load(ClientController.class.getClassLoader().getResource("SellerHomePage.fxml"));
                Scene scene = new Scene(root);
                window.setTitle("Home Page");
                window.setScene(scene);
                window.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}

