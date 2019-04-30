package gui.Gebruiker;

import controllers.GebruikerController;
import gui.Panel;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GebruikerBeheerPanelController extends HBox implements Panel {
    private GebruikerBeheerListPanel listPanel;
    private GebruikerBeheerGegevensPanel gegevensPanel;
    VBox gegevensPanelcontainer;
    
    private GebruikerController gc;
    
    public GebruikerBeheerPanelController(GebruikerController gc){
        this.gc = gc;
        
        // Panels
        listPanel = new GebruikerBeheerListPanel(gc);
        gegevensPanel = new GebruikerBeheerGegevensPanel(gc);
        this.getChildren().addAll(listPanel, gegevensPanel);
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
