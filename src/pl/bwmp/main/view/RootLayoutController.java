package pl.bwmp.main.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import pl.bwmp.MainApp;
import pl.bwmp.main.model.Project;

import java.io.File;

/**
 * The controller for the root layout. The root layout provides the basic
 * application layout containing a menu bar and space where other JavaFX
 * elements can be placed.
 */

public class RootLayoutController {
    // Reference to the main application
    private MainApp mainApp;

    /**
     * Is called by the main application to give a reference back to itself.
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Creates an empty project list.
     */
    @FXML
    private void handleNew() {
        mainApp.getProjectData().clear();
        mainApp.setProjectFilePath(null);
    }
    /**
     * Opens a FileChooser to let the user select an project list to load.
     */
    @FXML
    private void handleOpen(){
        FileChooser fileChooser = new FileChooser();

        //Set extension filter.
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show open file dialog
        File file = fileChooser.showOpenDialog(mainApp.getMainWindow());

        if (file != null) {
            mainApp.loadProjectDataFromFile(file);
        }
    }

    /**
     * Saves the file to the project file that is currently open. If there is no
     * open file, the "save as" dialog is shown.
     */
    @FXML
    private void handleSave(){
        File projectFile = mainApp.getProjectFilePath();
        if (projectFile != null) {
            mainApp.saveProjectDataToFile(projectFile);

        } else {
            handleSaveAs();
        }
    }
    /**
     * Opens a FileChooser to let the user select a file to save to.
     */
    @FXML
    private void handleSaveAs(){
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)","*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showSaveDialog(mainApp.getMainWindow());

        if (file != null){
            // make sure it has the correct extension
            if(!file.getPath().endsWith(".xml")){
                file = new File(file.getPath() + ".xml");
            }
            mainApp.saveProjectDataToFile(file);
        }

    }
    /**
     * Closes the application.
     */
    @FXML
    private void handleExit(){
        System.exit(0);
    }



}


