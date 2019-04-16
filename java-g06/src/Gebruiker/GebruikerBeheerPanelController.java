package Gebruiker;

import Applicatie.ApplicatieFrameController;
import Applicatie.Panel;
import javafx.scene.layout.Pane;

public class GebruikerBeheerPanelController extends Pane implements Panel {
    private GebruikerBeheerListPanel listPanel;
    
    public GebruikerBeheerPanelController(ApplicatieFrameController frame){
        listPanel = new GebruikerBeheerListPanel(this);
        this.getChildren().add(listPanel);
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
