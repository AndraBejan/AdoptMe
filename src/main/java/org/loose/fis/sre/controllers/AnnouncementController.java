package org.loose.fis.sre.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.loose.fis.sre.model.Announcement;
import org.loose.fis.sre.model.ImageStringTableRow;
import org.loose.fis.sre.model.Pet;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.services.AnnouncementService;
import org.loose.fis.sre.services.UserService;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class AnnouncementController extends Controller{

    @FXML
    private Text exceptionMessage;
    @FXML
    private Announcement announcement;
    @FXML
    private TextField petName;
    @FXML
    private TextField petInfo;
    @FXML
    private ChoiceBox<String> petType = new ChoiceBox<>();

    private final ObservableList<ImageStringTableRow> announcements = FXCollections.observableArrayList();

      @FXML
    private TableView<ImageStringTableRow> announcementsTable;
      @FXML
    private TableColumn<ImageStringTableRow, ImageView> announcementImage;
      @FXML
    private TableColumn<ImageStringTableRow, String> announcementInfo;

    @FXML
    private CheckBox editToggle;
    @FXML
    private Button addPhotoButton;
    @FXML
    private Button updateButton;

    @FXML
    private TextField adInfo;

    @FXML
    private ChoiceBox<String> category = new ChoiceBox<>();

    @FXML
    private Text AddStatus;

    @FXML
    private File image;
    @FXML
    private String imagePath = "src/main/resources/img/pet.png";
    @FXML
    private ImageView imageView;
    @FXML
    private Label title;

    @FXML
    private Button editButton;
    @FXML
    private Button removeButton;

    @FXML
    public void initialize() {
        category.getItems().addAll( "Adoption");
        petType.getItems().addAll("Cat","Dog","Furry", "Snakes");
        if(announcementImage != null && announcementInfo != null && announcementsTable != null){
            announcementImage.setPrefWidth(100);
            announcementImage.setCellValueFactory(new PropertyValueFactory<>("imageView"));
            announcementInfo.setCellValueFactory(new PropertyValueFactory<>("info"));
            if(editButton != null && removeButton != null){
                enableButtons(false);
                announcementsTable.setOnMouseClicked((MouseEvent event) -> {
                    if(event.getButton().equals(MouseButton.PRIMARY) && announcementsTable.getSelectionModel().getSelectedItem() != null){
                        enableButtons(true);
                    }else{
                        enableButtons(false);
                    }
                });
            }
        }
        if(editToggle != null){
            editToggle.selectedProperty().addListener((observable, oldValue, newValue) -> toggleEditAnnouncement(editToggle.isSelected()));
        }
    }

    @FXML
    public void toggleEditAnnouncement(Boolean enable){
        petInfo.setDisable(!enable);
        adInfo.setDisable(!enable);
        addPhotoButton.setDisable(!enable);
        updateButton.setDisable(!enable);
    }

    @FXML
    public void enableButtons(Boolean enable){
        editButton.setDisable(!enable);
        removeButton.setDisable(!enable);
    }

    @FXML
    public void updateMyAnnouncementList() throws MalformedURLException {
        ArrayList<Announcement> cursor= AnnouncementService.getUserAnnouncements(user.getName());

        for (Announcement announcement : cursor) {
            File file = new File((announcement.getPet()).getImagePath());
            String localUrl = file.toURI().toURL().toExternalForm();
            Image profile = new Image(localUrl, false);
            ImageView crtImg = new ImageView();
            crtImg.setImage(profile);
            crtImg.setFitHeight(100);
            crtImg.setFitWidth(100);

            User crtUser = announcement.getUser();
            String info = announcement.getCategory() + " " + announcement.getPet().getType() + "\n\nName: " + announcement.getPet().getName() + "\n\nPosted on " + announcement.getDatePosted().toString() + " by " + crtUser.getName();
            ImageStringTableRow crtAnnouncement = new ImageStringTableRow(crtImg, info, announcement.getID());

            announcements.add(crtAnnouncement);
        }
        announcementsTable.setItems(announcements);
    }

    @FXML
    void handleAddPhotoAction() throws MalformedURLException {
        Stage stage = new Stage();
        stage.setTitle("Add Photo");
        FileChooser filechooser = new FileChooser();
        filechooser.setInitialDirectory(new File("C:\\"));
        filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("jpg files","*.jpg"));
        image = filechooser.showOpenDialog(stage);
        imagePath = image.getAbsolutePath();
        filechooser.setInitialDirectory(image.getParentFile());
        File file = new File(imagePath);
        String localUrl = file.toURI().toURL().toExternalForm();
        Image profile = new Image(localUrl, false);
        imageView.setImage(profile);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        imageView.rotateProperty();
    }


    @FXML
    public void clearImageAction(ActionEvent event) throws MalformedURLException {
        imagePath = "src/main/resources/img/pet.png";
        File file = new File(imagePath);
        String localUrl = file.toURI().toURL().toExternalForm();
        Image petImg = new Image(localUrl, false);
        imageView.setImage(petImg);
        imageView.rotateProperty();
    }

    @FXML
    public void handleAddAnnouncementAction(ActionEvent event) {
        if(petName.getText() == "" || petType.getValue() == null || category.getValue() == null){
            AddStatus.setText("Pet name, pet type and announcement category are required!");
            return;
        }
        Pet crtPet = new Pet(petName.getText(), (String) petType.getValue());
        crtPet.setInfo(petInfo.getText());
        crtPet.setImagePath(imagePath);

        Announcement crtAd = new Announcement(crtPet, user, (String) category.getValue());
        crtAd.setInfo(adInfo.getText());
        AnnouncementService.addAnnouncement(crtPet,user,category.getValue());

        redirectToHome(event, user);
    }

    @FXML
    public void cancelAddAnnouncement(ActionEvent event) throws IOException {
        redirectToHome(event, user);
    }

    @FXML
    public void handleRemoveAnnouncementAction(ActionEvent event) throws MalformedURLException {
        ImageStringTableRow crt = announcementsTable.getSelectionModel().getSelectedItem();
        ArrayList<Announcement> userAds=AnnouncementService.getUserAnnouncements(user.getName());
        Announcement ad = null;
        for(Announcement announcement : userAds){
            if(crt.getID().equals(announcement.getID())){
                ad = announcement;
            }
        }
//        if(ad != null){
//            RequestService.closeAllRequestsRelatedToAnnouncement(ad);
//            AnnouncementService.removeAnnouncement(ad);
//            announcementsTable.getItems().remove(crt);
//        }
    }

    @FXML
    public void redirectToEditAnnouncement(ActionEvent event) throws IOException {
        FXMLLoader loader = redirect(event, "editAnnouncement.fxml", "Edit Announcement");
        AnnouncementController ac = loader.getController();
        ac.setUser(user);
        ac.setAnnouncement(AnnouncementService.getIdAnnouncement(announcementsTable.getSelectionModel().getSelectedItem().getID()));
    }

    @FXML
    public void setAnnouncement(Announcement announcement) throws MalformedURLException {
        this.announcement = announcement;
        this.adInfo.setText(announcement.getDescriere());
        this.petInfo.setText(announcement.getPet().getInfo());
        this.imagePath = announcement.getPet().getImagePath();
        title.setText("Announcement - " + announcement.getPet().getName() + " " + announcement.getCategory());
        File file = new File(imagePath);
        String localUrl = file.toURI().toURL().toExternalForm();
        Image img = new Image(localUrl, false);
        imageView.setImage(img);
    }

    @FXML
    public void handleEditAnnouncement(ActionEvent event)throws MalformedURLException {

        try {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Confirm your identity");
            dialog.setHeaderText("Please enter your current password");

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                if (Objects.equals(UserService.encodePassword(user.getName(),result.get()),user.getPassword())) {
                    announcement.setInfo(adInfo.getText());
                    announcement.getPet().setImagePath(imagePath);
                    announcement.getPet().setInfo(petInfo.getText());

//                    AnnouncementService.updateAnnouncement(announcement);
//                    Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Announcement modified");
//                    a.showAndWait();

                    redirectToHomePage(event);
                } else {
                    Alert a = new Alert(Alert.AlertType.ERROR, "Invalid password!");
                    a.showAndWait();
                }
            } else {
                Alert a = new Alert(Alert.AlertType.WARNING, "Your password is required!");
                a.showAndWait();
            }
        } catch (Exception e) {
            exceptionMessage.setText(e.getMessage());
        }

    }

    @FXML
    public void redirectToHomePage(ActionEvent event){
        redirectToHome(event,user);
    }


}
