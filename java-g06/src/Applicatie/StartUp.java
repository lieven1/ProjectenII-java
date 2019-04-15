package Applicatie;

import java.io.File;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
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
            stage.setMinWidth(1024);
            stage.setWidth(1280);
            stage.setMinHeight(768);
            stage.setHeight(800);
        });
        stage.show();
        scene.widthProperty().addListener((ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) -> {
            ((Panel)scene.getRoot()).resizeWidth((double) newSceneWidth);
        });
        scene.heightProperty().addListener((ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) -> {
            ((Panel)scene.getRoot()).resizeHeight((double) newSceneHeight);
        });
    }
    
    public static void main(String[] args){
        launch(args);
    }
}
