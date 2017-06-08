package pl.bwmp;
/**
 * Created by lukas on 13.05.2017.
 */
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.bwmp.main.model.Project;
import pl.bwmp.main.view.ProjectEditController;
import pl.bwmp.main.view.ProjectOverviewController;
import java.io.IOException;

public class MainApp extends Application {
/** main window and main layout */
   private Stage mainWindow;
   private BorderPane mainLayout;


/**
* Data as an observable list of Projects.
*/

    private ObservableList<Project> projectData = FXCollections.observableArrayList();

/**
* Constructor
*/

    public MainApp(){
        //add some sample data
        projectData.add(new Project("985557","SeaCat","Approved"));
        projectData.add(new Project("G31500","Sun","Open"));
        projectData.add(new Project("58966","Wind","pre-check"));
        projectData.add(new Project("25789","Sea","Open"));
    }
/**
* Returns the data as an observable list of Persons.
* @return
*/

    public ObservableList<Project> getProjectData(){
        return projectData;
    }




   @Override
    public void start(Stage mainWindow){
       this.mainWindow = mainWindow;
       this.mainWindow.setTitle("BWMP App");

       initMainLayout();
       showProjectOverview();
   }
/** Initialize main layout */
 public void initMainLayout(){
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

     } catch (IOException e){
         e.printStackTrace();
     }
 }
/** Show the Project overview inside the root layout */
 public void showProjectOverview(){
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

     } catch (IOException e){
         e.printStackTrace();
     }
 }


 public boolean showProjectEdit(Project project){
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
     } catch (IOException e){
         e.printStackTrace();
         return false;
     }

 }


/** Returns the main stage */
 public Stage getMainWindow() {
     return mainWindow;
 }

 public static void main(String[] args){
     launch(args);
 }

}
