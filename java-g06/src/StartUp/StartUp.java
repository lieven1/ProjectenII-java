package StartUp;

import controllers.DomeinController;
import gui.Panel;
import gui.Start.ApplicatieFrameController;
import java.io.File;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class StartUp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        DomeinController dc = new DomeinController();
        
        Scene scene = new Scene(new ApplicatieFrameController(dc));
        stage.setTitle("Taijitan");
        scene.getStylesheets().add((new File("src/resources/bootstrap-3.css")).toURI().toURL().toExternalForm());
        stage.setScene(scene);
        stage.setOnShown((WindowEvent t) -> {
           stage.setMaximized(true);
        });
        
        // ResizeListeners to children
        scene.widthProperty().addListener((ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) -> {
            ((Panel)scene.getRoot()).resizeWidth((double) newSceneWidth);
        });
        scene.heightProperty().addListener((ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) -> {
            ((Panel)scene.getRoot()).resizeHeight((double) newSceneHeight);
        });
        stage.show();
    }
    
    public static void main(String[] args){
        launch(args);
    }
}
