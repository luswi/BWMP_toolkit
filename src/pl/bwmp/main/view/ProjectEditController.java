package pl.bwmp.main.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import pl.bwmp.main.model.Project;
import javafx.scene.control.TextField;

import java.awt.*;

/**
 * Created by lukas on 29.05.2017.
 */
public class ProjectEditController {


    @FXML
    private TextField idEdit;
    //@FXML
    //private ComboBox<T> lcsEdit;


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
        //lcsEdit.setTooltip(project.getLCS());

    }

    public boolean isOkClicked(){
        return okClicked;
    }

    @FXML
    private void handleOK(){
        if(isInputValid()){
            project.setShipId(idEdit.getText());

            okClicked = true;
            projectEditStage.close();
        }
    }

    @FXML
    private void handleCancel(){
        projectEditStage.close();
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
