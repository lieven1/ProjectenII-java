package Applicatie;

import java.io.File;
import java.net.MalformedURLException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class ApplicatieStartPanel extends AnchorPane {
    public ApplicatieStartPanel(ApplicatieFrameController frame){
        // PaneSettings
        this.maxHeight(-1);
        this.maxWidth(-1);
        this.prefHeight(-1);
        this.prefWidth(-1);
        VBox.setVgrow(this, Priority.ALWAYS);
        
        // Applicatie Logo
        ImageView logo = new ImageView();
        try {
            logo.setImage(new Image((new File("src/resources/logo.jpg")).toURI().toURL().toExternalForm()));
        } catch (MalformedURLException ex) {
            System.err.println(ex);
        }
        logo.fitHeightProperty().setValue(233.0);
        logo.fitWidthProperty().setValue(1280);
        this.getChildren().add(logo);
        
        // Label
        Label lblTitle = new Label("Taijitan");
        lblTitle.setFont(new Font("Arial Black", 96.0));
        lblTitle.setTextFill(Paint.valueOf("#393980"));
        lblTitle.setLayoutX(440);
        lblTitle.setLayoutY(242);
        this.getChildren().add(lblTitle);
        
        // ButtonGebruikers
        Button btnGebruikers = new Button("Gebruikers");
        btnGebruikers.setLayoutX(544);
        btnGebruikers.setLayoutY(418);
        btnGebruikers.getStyleClass().addAll("lg", "primary");
        this.getChildren().add(btnGebruikers);
    }
}
