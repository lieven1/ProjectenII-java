package Gebruiker;

import Applicatie.ApplicatieFrameController;
import Applicatie.Panel;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class GebruikerToevoegenPanel extends Pane implements Panel {
    public GebruikerToevoegenPanel(ApplicatieFrameController frame){
        Label lblTitle = new Label("GebruikertoevoegenPanel");
        lblTitle.setFont(new Font("Arial Black", 46.0));
        lblTitle.setTextFill(Paint.valueOf("#393980"));
        lblTitle.setLayoutX(440);
        lblTitle.setLayoutY(242);
        this.getChildren().add(lblTitle);
    }
    
    @Override
    public void resizeWidth(double width) {
        // No resizeWidth actions just yet
    }

    @Override
    public void resizeHeight(double height) {
        // No resizeHeight actions just yet
    }
}
