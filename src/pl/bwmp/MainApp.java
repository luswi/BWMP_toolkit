package pl.bwmp;
/**
 * Created by lukas on 13.05.2017.
 */
import com.sun.jmx.remote.internal.Unmarshal;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.bwmp.main.model.Project;
import pl.bwmp.main.model.ProjectListWrapper;
import pl.bwmp.main.view.ProjectEditController;
import pl.bwmp.main.view.ProjectOverviewController;
import pl.bwmp.main.view.RootLayoutController;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.function.Predicate;
import java.util.prefs.Preferences;

public class MainApp extends Application {
    /**
     * main window and main layout
     */
    private Stage mainWindow;
    private BorderPane mainLayout;


    /**
     * Data as an observable list of Projects.
     */

    public ObservableList<Project> projectData = FXCollections.observableArrayList();


    /**
     * Constructor
     */

    public MainApp() {
        //add some sample data
        //projectData.add(new Project("985557", "SeaCat", "Approved"));
        //projectData.add(new Project("G31500", "Sun", "Open"));
        //projectData.add(new Project("58966", "Wind", "pre-check"));
        //projectData.add(new Project("25789", "Sea", "Open"));
    }

    /**
     * Returns the data as an observable list of Persons.
     *
     * @return
     */

    public ObservableList<Project> getProjectData() {
        return projectData;
    }


    @Override
    public void start(Stage mainWindow) {
        this.mainWindow = mainWindow;
        this.mainWindow.setTitle("BWMP App");

        initMainLayout();
        showProjectOverview();
    }

    /**
     * Initialize main layout
     */
    public void initMainLayout() {
        try {
            // Load root layout from file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("main/view/RootLayout.fxml"));
            mainLayout = (BorderPane) loader.load();
            // Show the scene containing the root layout
            Scene scene = new Scene(mainLayout);
            mainWindow.setResizable(false);
            mainWindow.setScene(scene);
            mainWindow.show();
            // Give the controller access to the main app.
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);
            mainWindow.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
        // Try to load last opened project file.
        File file = getProjectFilePath();
        if (file != null){
            loadProjectDataFromFile(file);
        }
    }

    /**
     * Show the Project overview inside the root layout
     */
    public void showProjectOverview() {
        try {
            // Load project overview
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("main/view/ProjectOverview.fxml"));
            AnchorPane projectOverview = (AnchorPane) loader.load();
            // Set project overview into the center of root layout
            mainLayout.setCenter(projectOverview);
            // Give the controller access to the main app.
            ProjectOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean showProjectEdit(Project project) {
        try {
            //load fxml file and create a new stage for the popup dialog
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("main/view/ProjectEdit.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            //Create project edit stage
            Stage projectEditStage = new Stage();
            projectEditStage.setTitle("Edit project");
            projectEditStage.setResizable(false);
            projectEditStage.initModality(Modality.WINDOW_MODAL);
            projectEditStage.initOwner(mainWindow);
            Scene scene = new Scene(page);
            projectEditStage.setScene(scene);
            //set the project into the controller.
            ProjectEditController controller = loader.getController();
            controller.setProjectEditStage(projectEditStage);
            controller.setProject(project);

            // show edit and wait untill the user closes it
            projectEditStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Returns the person file preference, i.e. the file that was last opened.
     * The preference is read from the OS specific registry. If no such
     * preference can be found, null is returned.
     */
    public File getProjectFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    /**
     * Sets the file path of the currently loaded file. The path is persisted in
     * the OS specific registry.
     */
    public void setProjectFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            //update the stage title.
            mainWindow.setTitle("ProjectApp - " + file.getName());
        } else {
            prefs.remove("filePath");
            //update the stage title.
            mainWindow.setTitle("ProjectApp");
        }
    }


    /**
     * Returns the main stage
     */
    public Stage getMainWindow() {
        return mainWindow;
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Loads person data from the specified file. The current person data will
     * be replaced.
     */
    public void loadProjectDataFromFile(File file) {
        try {

            JAXBContext context = JAXBContext.newInstance(ProjectListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            //Reading XML from the file and unmarshalling.
            ProjectListWrapper wrapper = (ProjectListWrapper) um.unmarshal(file);

            projectData.clear();
            projectData.addAll(wrapper.getProjects());



            //Save the file path to the registry.
            setProjectFilePath(file);

        } catch (Exception e) { //catches ANY exception
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not load data from file:\n" + file.getPath());

            alert.showAndWait();
        }


    }
    /**
     * Saves the current person data to the specified file.
     *
     */

    public void saveProjectDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(ProjectListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            //Wrapping our project data.
            ProjectListWrapper wrapper = new ProjectListWrapper();
            wrapper.setProjects(projectData);

            //Marshalling and saving XML to the file.
            m.marshal(wrapper, file);

            //save the file path to the registry.
            setProjectFilePath(file);


        } catch (Exception e){ //catch any exception.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data");
            alert.setContentText("Could not save data to file:\n" + file.getPath());
            alert.showAndWait();
        }
    }

}
