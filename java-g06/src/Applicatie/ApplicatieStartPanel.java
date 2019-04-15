package Applicatie;

import Gebruiker.GebruikerBeheerPanel;
import java.io.File;
import java.net.MalformedURLException;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class ApplicatieStartPanel extends AnchorPane implements Panel {
    private ImageView logo;
    private Label lblTitle;
    private Button btnGebruikers;
    
    public ApplicatieStartPanel(ApplicatieFrameController frame){
        // PaneSettings
        this.maxHeight(-1);
        this.maxWidth(-1);
        this.prefHeight(-1);
        this.prefWidth(-1);
        VBox.setVgrow(this, Priority.ALWAYS);
        
        // Applicatie Logo
        logo = new ImageView();
        try {
            logo.setImage(new Image((new File("src/resources/logo.jpg")).toURI().toURL().toExternalForm()));
        } catch (MalformedURLException ex) {
            System.err.println(ex);
        }
        logo.fitHeightProperty().setValue(233.0);
        this.getChildren().add(logo);
        
        
        // Set movable objects (when resized) on a fixed pane so less items need to be moved independently
        // Label
        lblTitle = new Label("Taijitan");
        lblTitle.setFont(new Font("Arial Black", 96.0));
        lblTitle.setTextFill(Paint.valueOf("#393980"));
        lblTitle.setLayoutY(242);
        this.getChildren().add(lblTitle);
        
        // ButtonGebruikers
        btnGebruikers = new Button("Gebruikers");
        btnGebruikers.setLayoutY(418);
        btnGebruikers.getStyleClass().addAll("lg", "primary");
        this.getChildren().add(btnGebruikers);
        resizeWidth(frame.getWidth());
        
        // EventHandlers
        btnGebruikers.setOnAction((ActionEvent t) -> {
            frame.setContentPane(new GebruikerBeheerPanel(frame));
        });
    }

    @Override
    public void resizeWidth(double width) {
        logo.fitWidthProperty().setValue(width);
        lblTitle.layoutXProperty().setValue(width/2 - 160);
        btnGebruikers.layoutXProperty().setValue(width/2 - 56);
    }

    @Override
    public void resizeHeight(double height) {
        // No resizeHeight actions just yet
    }
}
