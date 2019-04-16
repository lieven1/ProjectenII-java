package Gebruiker;

import Gebruiker.Models.AGebruiker;
import Applicatie.ApplicatieFrameController;
import Applicatie.Panel;
import java.util.List;
import javafx.scene.layout.Pane;

public class GebruikerBeheerPanelController extends Pane implements Panel {
    private static final GebruikerController GEBRUIKERCONTROLLER = new GebruikerController();
    private ApplicatieFrameController frame;
    private GebruikerBeheerListPanel listPanel;
    private GebruikerBeheerGegevensPanel gegevensPanel;
    private List<AGebruiker> _gebruikerList;
    
    public GebruikerBeheerPanelController(ApplicatieFrameController frame){
        this.frame = frame;
        _gebruikerList = GEBRUIKERCONTROLLER.getGebruikerList();
        listPanel = new GebruikerBeheerListPanel(this);
        gegevensPanel = new GebruikerBeheerGegevensPanel(this);
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
        // No resizeWidth actions just yet
    }

    @Override
    public void resizeHeight(double height) {
        // No resizeHeight actions just yet
    }
}
