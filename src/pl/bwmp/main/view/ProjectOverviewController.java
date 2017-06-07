package pl.bwmp.main.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pl.bwmp.MainApp;
import pl.bwmp.main.model.Project;


import java.util.Optional;



/**
* Created by lukas on 14.05.2017.
*/

public class ProjectOverviewController {
    @FXML
    private TableView<Project> projectTable;
    @FXML
    private TableColumn<Project, String> shipIdColumn;
    @FXML
    private TableColumn<Project, String> shipNameColumn;
    @FXML
    private TableColumn<Project, String> shipStatusColumn;
    @FXML
    private Label shipIdLabel;
    @FXML
    private Label shipNameLabel;
    @FXML
    private Label LcsLabel;
    @FXML
    private Label seqLabel;
    @FXML
    private Label flowLabel;
    @FXML
    private Label sfaCreatedLabel;
    @FXML
    private Label oracleLabel;
    @FXML
    private Label sfaSentLabel;
    @FXML
    private Label sfaRecLabel;
    @FXML
    private Label shipStatusLabel;
    @FXML
    private Label certLabel;
    @FXML
    private Label sharePointLabel;
    @FXML
    private Label hUsedLabel;
    @FXML
    private Label hLeftLabel;
    @FXML
    private Label hMaxLabel;
    @FXML
    private Label invoiceLabel;
    @FXML
    private Label poLabel;
    @FXML
    private Label statusLabel;

    @FXML
    private ImageView sfaCreatedImg;
    @FXML
    private ImageView sfaSentImg;
    @FXML
    private ImageView sfaRecImg;
    @FXML
    private ImageView oracleImg;



    //Reference to main application
    private MainApp mainApp;

/**
* The constructor is called before the initialize() method.
*/

    public ProjectOverviewController(){

    }

/**
* Initializes the controller class. This method is automatically called
* after the fxml file has been loaded.
*/

    @FXML
    private void initialize() {
        //initialize the project table with the 3 columns
        shipIdColumn.setCellValueFactory(cellData->cellData.getValue().shipIdProperty());
        shipNameColumn.setCellValueFactory(cellData->cellData.getValue().shipNameProperty());
        shipStatusColumn.setCellValueFactory(cellData->cellData.getValue().shipStatusProperty());

        // Clear project details.
        showProjectDetails(null);

        // Listen for selection changes and show the project details when changed.
        projectTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showProjectDetails(newValue));
    }
/**
* Is called by the main application to give a reference back to itself.
*/

public void setMainApp(MainApp mainApp){
    this.mainApp = mainApp;

    //Add observable list data to the table
    projectTable.setItems(mainApp.getProjectData());
    }

/**
 * Fills all text fields to show details about the project.
 * If the specified project is null, all text fields are cleared.
 *
 */

private void showProjectDetails(Project project){
    if(project !=null) {

        //fill the labels with info from the project object.
        shipIdLabel.setText(project.getShipId());
        shipNameLabel.setText(project.getShipName());
        LcsLabel.setText(project.getLCS());
        seqLabel.setText(project.getSeq());
        flowLabel.setText(project.getFlow());
        sfaCreatedLabel.setText(project.getSfaCreated());
        oracleLabel.setText(project.getOracle());
        sfaSentLabel.setText(project.getSfaSent());
        sfaRecLabel.setText(project.getSfaRec());
        shipStatusLabel.setText(project.getShipStatus());
        certLabel.setText(project.getCert());
        sharePointLabel.setText(project.getSharePoint());
        hUsedLabel.setText(Double.toString(project.getHused())); // int to string converter
        hLeftLabel.setText(Double.toString(project.getHmax() - project.getHused()));
        //hMaxLabel.setText(Integer.toString(project.getHmax()));
        hMaxLabel.setText(Double.toString(project.getHmax()));
        invoiceLabel.setText(project.getInvoice());
        poLabel.setText(project.getPo());
        statusLabel.setText(project.getStatus());

        if(sfaCreatedLabel.getText().contentEquals("Yes")||sfaCreatedLabel.getText().contentEquals("SC")) {
            sfaCreatedImg.setImage(new Image("pl/bwmp/main/style/yes.png"));
        } else {
            sfaCreatedImg.setImage(new Image("pl/bwmp/main/style/no.png"));
        }
        if(sfaSentLabel.getText().contentEquals("Yes")||sfaSentLabel.getText().contentEquals("SC")){
            sfaSentImg.setImage((new Image("pl/bwmp/main/style/yes.png")));
        } else {
            sfaSentImg.setImage(new Image("pl/bwmp/main/style/no.png"));
        }
        if(sfaRecLabel.getText().contentEquals("Yes")||sfaRecLabel.getText().contentEquals("SC")){
            sfaRecImg.setImage(new Image("pl/bwmp/main/style/yes.png"));
        } else {
            sfaRecImg.setImage(new Image("pl/bwmp/main/style/no.png"));
        }
        if(oracleLabel.getText().contentEquals("Task created")||oracleLabel.getText().contentEquals("Ok")){
            oracleImg.setImage(new Image("pl/bwmp/main/style/yes.png"));
        } else {
            oracleImg.setImage(new Image("pl/bwmp/main/style/no.png"));
        }







    } else {
        // Project is null, remove all the text.
        shipIdLabel.setText("");
        shipNameLabel.setText("");
        LcsLabel.setText("");
        seqLabel.setText("");
        flowLabel.setText("");
        sfaCreatedLabel.setText("");
        oracleLabel.setText("");
        sfaSentLabel.setText("");
        sfaRecLabel.setText("");
        shipStatusLabel.setText("");
        certLabel.setText("");
        sharePointLabel.setText("");
        hUsedLabel.setText("");
        hLeftLabel.setText("");
        hMaxLabel.setText("");
        invoiceLabel.setText("");
        poLabel.setText("");
        statusLabel.setText("");



    }
}
/**
 * new project
 */
    @FXML
    private void handleNewProject(){
        Project tempProject = new Project();
        boolean okClicked = mainApp.showProjectEdit(tempProject);
        if (okClicked){
            mainApp.getProjectData().add(tempProject);
        }
    }

    /**
     * for edit
     */
    @FXML
    private void handleEditProject(){
        Project selectedProject = projectTable.getSelectionModel().getSelectedItem();
        if (selectedProject != null){
            boolean okClicked = mainApp.showProjectEdit(selectedProject);
            if(okClicked){
                showProjectDetails(selectedProject);
            }
        } else {
            //nothing selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getMainWindow());
            alert.setTitle("Nothing selected");
            alert.setHeaderText("No project selected!");
            alert.setContentText("please select project from the table");
            alert.showAndWait();
        }
    }
    /**
     * deletion
     */
    @FXML
    private void handleDeleteProject(){
        int selectProjectIndex = projectTable.getSelectionModel().getSelectedIndex();
        if (selectProjectIndex >= 0){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation...");
            alert.setHeaderText("Want to delete?");
            alert.setContentText("Are you sure to remove entire project Case ?");
            Optional<ButtonType> results = alert.showAndWait();
            if (results.get() == ButtonType.OK){
                projectTable.getItems().remove(selectProjectIndex);
            } else {

            }


        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getMainWindow());
            alert.setTitle("Nothing selected");
            alert.setHeaderText("No selected project!");
            alert.setContentText("Please select project in the table.");
            alert.showAndWait();
        }

    }

}
