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
import javafx.stage.Stage;
import pl.bwmp.main.model.Project;
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
/** Returns the main stage */
 public Stage getMainWindow() {
     return mainWindow;
 }

 public static void main(String[] args){
     launch(args);
 }

}
