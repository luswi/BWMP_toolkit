package pl.bwmp.main.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import pl.bwmp.main.model.Project;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


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
    @FXML
    private Button sfaCreatedButton;
    @FXML
    private Button oracleButton;
    @FXML
    private Button spButton;





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
/**
 * status img
 */
// for Sfa Created
        if(sfaCreatedCB.getValue().equals("Yes")||sfaCreatedCB.getValue().equals("SC")) {
            sfaCreatedImg.setImage(new Image("pl/bwmp/main/style/yes.png"));
        } else {
            sfaCreatedImg.setImage(new Image("pl/bwmp/main/style/no.png"));
        }

// for Oracle Status
        if(oracleCB.getValue().equals("Ok")) {
            oracleImg.setImage(new Image("pl/bwmp/main/style/yes.png"));
        } else {
            oracleImg.setImage(null);
        }

// for SFA Sent
        if(sfaSentCB.getValue().equals("Yes")||sfaSentCB.getValue().equals("SC")) {
            sfaSentImg.setImage(new Image("pl/bwmp/main/style/yes.png"));
        } else {
            sfaSentImg.setImage(new Image("pl/bwmp/main/style/no.png"));
        }

// for SFA Rec
        if(sfaRecCB.getValue().equals("Yes")||sfaRecCB.getValue().equals("SC")) {
            sfaRecImg.setImage(new Image("pl/bwmp/main/style/yes.png"));
        } else {
            sfaRecImg.setImage(new Image("pl/bwmp/main/style/no.png"));
        }



/**
 * Buttons
 */

// Sfa Created button (open SFA folder)
        sfaCreatedButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Desktop.getDesktop().open(new File("c:\\"));
                } catch (IOException exception) {
                    exception.printStackTrace();
                }


            }
        });

// Oracle button (open oracle task/invoice SP)
        oracleButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Desktop.getDesktop().browse(new URI("www.onet.pl"));
                } catch (IOException exception) {
                    exception.printStackTrace();
                } catch (URISyntaxException exception) {
                    exception.printStackTrace();
                }


            }
        });

// Share Point button (ballast list)
        spButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Desktop.getDesktop().browse(new URI("www.wp.pl"));
                } catch (IOException exception) {
                    exception.printStackTrace();
                } catch (URISyntaxException exception) {
                    exception.printStackTrace();
                }
            }
        });



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
