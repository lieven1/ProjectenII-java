package Gebruiker;

import Gebruiker.Models.AGebruiker;
import Applicatie.ApplicatieFrameController;
import static Applicatie.ApplicatieFrameController.GEBRUIKERCONTROLLER;
import Applicatie.Panel;
import java.util.List;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GebruikerBeheerPanelController extends HBox implements Panel {
    private ApplicatieFrameController frame;
    private GebruikerBeheerListPanel listPanel;
    private GebruikerBeheerGegevensPanel gegevensPanel;
    private List<AGebruiker> _gebruikerList;
    VBox gegevensPanelcontainer;
    
    public GebruikerBeheerPanelController(ApplicatieFrameController frame){
        this.frame = frame;
        _gebruikerList = GEBRUIKERCONTROLLER.getGebruikerList();
        
        // Panels
        listPanel = new GebruikerBeheerListPanel(this);
        gegevensPanel = new GebruikerBeheerGegevensPanel(this);
        resizeWidth(frame.getWidth());
        this.getChildren().addAll(listPanel, gegevensPanel);
    }
    
    public List<AGebruiker> getGebruikerList(){
        return this._gebruikerList;
    }
    
    public void beheerGebruiker(AGebruiker gebruiker){
        gegevensPanel.beheerGebruiker(gebruiker);
    }
    
    @Override
    public void resizeWidth(double width) {
        gegevensPanel.setPrefWidth(width-300);
    }

    @Override
    public void resizeHeight(double height) {
        // No resizeHeight actions just yet
    }
}
