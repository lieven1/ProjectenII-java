package Applicatie;

import Gebruiker.GebruikerBeheerPanelController;
import java.io.File;
import java.net.MalformedURLException;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
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
        
        // Buttons
        createButton("Gebruikers", 0, 1).setOnAction((ActionEvent t) -> {
            frame.setContentPane(new GebruikerBeheerPanelController(frame));
        });
        createButton("Overzicht", 1, 1).setOnAction((ActionEvent t) -> {
            frame.setContentPane(new GebruikerBeheerPanelController(frame));
        });
        
        this.getChildren().addAll(logo, menuPanel);
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
