package Views.Gebruiker;

import controllers.GebruikerController;
import domain.GebruikerModels.AGebruiker;
import Views.Start.ApplicatieFrameController;
import Views.Panel;
import java.util.List;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GebruikerBeheerPanelController extends HBox implements Panel {
    private ApplicatieFrameController frame;
    private GebruikerBeheerListPanel listPanel;
    private GebruikerBeheerGegevensPanel gegevensPanel;
    VBox gegevensPanelcontainer;
    
    private GebruikerController gc;
    
    public GebruikerBeheerPanelController(ApplicatieFrameController frame, GebruikerController gc){
        this.gc = gc;
        this.frame = frame;
        
        // Panels
        listPanel = new GebruikerBeheerListPanel(this);
        gegevensPanel = new GebruikerBeheerGegevensPanel(this);
        resizeWidth(frame.getWidth());
        this.getChildren().addAll(listPanel, gegevensPanel);
    }
    
    public List<AGebruiker> getGebruikerList(){
        return gc.getGebruikerList();
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
