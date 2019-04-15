package Applicatie;

import java.io.File;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class StartUp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(new ApplicatieFrameController());
        stage.setTitle("Taijitan");
        scene.getStylesheets().add((new File("src/resources/bootstrap-3.css")).toURI().toURL().toExternalForm());
        stage.setScene(scene);
        stage.setOnShown((WindowEvent t) -> {
            stage.setResizable(false);
            stage.setWidth(1280);
            stage.setHeight(800);
        });
        stage.show();
    }
    
    public static void main(String[] args){
        launch(args);
    }
}
