package pl.bwmp.main.view;

import com.sun.deploy.uitoolkit.impl.fx.HostServicesFactory;
import com.sun.javafx.application.HostServicesDelegate;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import pl.bwmp.main.model.Project;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;


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
    @FXML
    private ComboBox seqCB;
    @FXML
    private ComboBox flowCB;
    @FXML
    private ComboBox sfaCreatedCB;
    @FXML
    private ComboBox oracleCB;
    @FXML
    private ComboBox sfaSentCB;
    @FXML
    private ComboBox sfaRecCB;
    @FXML
    private ImageView sfaCreatedImg;
    @FXML
    private ImageView oracleImg;
    @FXML
    private ImageView sfaSentImg;
    @FXML
    private ImageView sfaRecImg;




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
        seqCB.setValue(project.getSeq());
        flowCB.setValue(project.getFlow());
        sfaCreatedCB.setValue(project.getSfaCreated());
        oracleCB.setValue(project.getOracle());
        sfaSentCB.setValue(project.getSfaSent());
        sfaRecCB.setValue(project.getSfaRec());

        if(sfaCreatedCB.getValue().equals("Yes")||sfaCreatedCB.getValue().equals("SC")) {
            sfaCreatedImg.setImage(new Image("pl/bwmp/main/style/yes.png"));
        } else {
            sfaCreatedImg.setImage(new Image("pl/bwmp/main/style/no.png"));
        }
        if(oracleCB.getValue().equals("Task created")||oracleCB.getValue().equals("OK")) {
            oracleImg.setImage(new Image("pl/bwmp/main/style/yes.png"));
        } else {
            oracleImg.setImage(new Image("pl/bwmp/main/style/no.png"));
        }
        if(sfaSentCB.getValue().equals("Yes")||sfaSentCB.getValue().equals("SC")){
            sfaSentImg.setImage(new Image("pl/bwmp/main/style/yes.png"));
        } else {
            sfaSentImg.setImage(new Image("pl/bwmp/main/style/no.png"));
        }
        if(sfaRecCB.getValue().equals("Yes")||sfaRecCB.getValue().equals("SC")){
            sfaRecImg.setImage(new Image("pl/bwmp/main/style/Yes.png"));
        } else {
            sfaRecImg.setImage(new Image("pl/bwmp/main/style/no.png"));
        }



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
            project.setSeq((String) seqCB.getValue());
            project.setFlow((String) flowCB.getValue());
            project.setSfaCreated((String) sfaCreatedCB.getValue());
            project.setOracle((String) oracleCB.getValue());
            project.setSfaSent((String) sfaSentCB.getValue());
            project.setSfaRec((String) sfaRecCB.getValue());

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
