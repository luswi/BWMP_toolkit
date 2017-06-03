package pl.bwmp.main.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import pl.bwmp.main.model.Project;
import javafx.scene.control.TextField;
import java.net.URL;

import java.net.URL;


/**
 * Created by lukas on 29.05.2017.
 */
public class ProjectEditController {


    @FXML
    private TextField idEdit;
    @FXML
    private TextField shipEdit;
    @FXML
    private ComboBox lcsCB;


    private Stage projectEditStage;
    private Project project;
    private boolean okClicked = false;

    @FXML
    private void initialize() {

    }

    public void setProjectEditStage(Stage projectEditStage){
        this.projectEditStage = projectEditStage;
    }

    public void setProject(Project project){
        this.project = project;

        idEdit.setText(project.getShipId());
        shipEdit.setText(project.getShipName());
        lcsCB.setValue(project.getLCS());


    }

    public boolean isOkClicked(){
        return okClicked;
    }

    @FXML
    private void handleOK(){
        if(isInputValid()){
            project.setShipId(idEdit.getText());
            project.setShipName(shipEdit.getText());
            project.setLCS((String) lcsCB.getValue());

            okClicked = true;
            projectEditStage.close();
        }
    }

    @FXML
    private void handleCancel(){
        projectEditStage.close();
    }
    @FXML
    private void handleOracle(){
  //open oracle code

    }


    private boolean isInputValid(){
        String errorMessage = "";

        if(idEdit.getText() == null || idEdit.getText().length() == 0){
            errorMessage +="No valid idShip!\n";
        }
        if (errorMessage.length() == 0){
            return true;
        } else {
            // show error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(projectEditStage);
            alert.setTitle("Invalid data");
            alert.setHeaderText("Please correct data");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }

    }






}
