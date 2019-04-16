package Applicatie;

import Gebruiker.GebruikerBeheerPanel;
import Gebruiker.GebruikerToevoegenPanel;
import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class ApplicatieStartPanel extends AnchorPane implements Panel {
    private ImageView logo;
    private GridPane menuPanel;
    
    public ApplicatieStartPanel(ApplicatieFrameController frame){
        // Applicatie Logo
        logo = new ImageView();
        try {
            logo.setImage(new Image((new File("src/resources/logo.jpg")).toURI().toURL().toExternalForm()));
        } catch (MalformedURLException ex) {
            System.err.println(ex);
        }
        logo.fitHeightProperty().setValue(233.0);
        this.getChildren().add(logo);
        
        // MenuPanel
        menuPanel = new GridPane();
        menuPanel.setPrefWidth(400);
        menuPanel.setLayoutY(230);
        menuPanel.getColumnConstraints().add(new ColumnConstraints(200));
        
        // Label
        Label lblTitle = new Label("Taijitan");
        lblTitle.setFont(new Font("Arial Black", 96.0));
        lblTitle.setTextFill(Paint.valueOf("#393980"));
        lblTitle.setLayoutY(242);
        lblTitle.setPadding(new Insets(0, 0, 20, 0));
        menuPanel.add(lblTitle, 0, 0, 2, 1);        
        GridPane.setHalignment(lblTitle, HPos.CENTER);

        // ButtonGebruikers
        Button btnGebruikers = new Button("Gebruikers");
        btnGebruikers.getStyleClass().addAll("lg", "primary");
        btnGebruikers.setPrefWidth(150);
        menuPanel.add(btnGebruikers, 0, 1);
        GridPane.setHalignment(btnGebruikers, HPos.CENTER);
        
        // ButtonOverzicht
        Button btnOverzicht = new Button("Overzicht");
        btnOverzicht.getStyleClass().addAll("lg", "primary");
        btnOverzicht.setPrefWidth(150);
        menuPanel.add(btnOverzicht, 1, 1);
        GridPane.setHalignment(btnOverzicht, HPos.CENTER);
        
        // Buttons
        createButton("Gebruikers", 0, 1).setOnAction((ActionEvent t) -> {
            frame.setContentPane(new GebruikerBeheerPanel(frame));
        });
        createButton("Overzicht", 1, 1).setOnAction((ActionEvent t) -> {
            frame.setContentPane(new GebruikerToevoegenPanel(frame));
        });
        
        this.getChildren().add(menuPanel);
        resizeWidth(frame.getWidth());
    }
    
    private Button createButton(String text, int x, int y){
        Button btn = new Button(text);
        btn.getStyleClass().addAll("lg", "primary");
        btn.setPrefWidth(150);
        menuPanel.add(btn, x, y);
        GridPane.setHalignment(btn, HPos.CENTER);
        return btn;
    }

    @Override
    public void resizeWidth(double width) {
        logo.fitWidthProperty().setValue(width);
        menuPanel.setLayoutX(width/2 - 200);
    }

    @Override
    public void resizeHeight(double height) {
        // No resizeHeight actions just yet
    }
}
