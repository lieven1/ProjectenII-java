package gui.Gebruiker;

import controllers.GebruikerController;
import domain.GebruikerModels.AGebruiker;
import domain.GebruikerModels.TypeGebruiker;
import gui.Panel;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GebruikerBeheerPanelController extends HBox implements Panel {
    private GebruikerBeheerListPanel listPanel;
    private GebruikerForm gegevensPanel;
    VBox gegevensPanelcontainer;
    
    private GebruikerController gc;
    
    public GebruikerBeheerPanelController(GebruikerController gc){
        this.gc = gc;
        
        // Panels
        listPanel = new GebruikerBeheerListPanel(this, gc);
        gegevensPanel = new FormLidController(this, gc);
        this.getChildren().addAll(listPanel, gegevensPanel);
    }
    
    public void createForm(TypeGebruiker type){
        switch(type){
            case Lid:
                gegevensPanel = new FormLidController(this, gc);
                this.getChildren().set(1, gegevensPanel);
                break;
            case Proefgebruiker:
                gegevensPanel = new FormProefgebruikerController(this, gc);
                this.getChildren().set(1, gegevensPanel);
                break;
        }
    }
    
    public void loadGebruiker(TypeGebruiker type, AGebruiker gebruiker){
        createForm(type);
        gegevensPanel.loadGebruiker(gebruiker);
    }
    
    @Override
    public void resizeWidth(double width) {
        gegevensPanel.setPrefWidth(width-300);
    }

    @Override
    public void resizeHeight(double height) {
        gegevensPanel.setPrefHeight(2*height);
    }
}
