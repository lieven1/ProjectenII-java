package gui.Gebruiker;

import controllers.GebruikerController;
import domain.GebruikerModels.AGebruiker;
import domain.GebruikerModels.TypeGebruiker;
import gui.Panel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.scene.layout.HBox;

public class GebruikerBeheerPanelController extends HBox implements Panel, PropertyChangeListener {
    private GebruikerBeheerListPanel listPanel;
    private GebruikerForm gegevensPanel;
    
    private GebruikerController gc;
    
    public GebruikerBeheerPanelController(GebruikerController gc){
        this.gc = gc;
        
        // Panels
        listPanel = new GebruikerBeheerListPanel(gc);
        gegevensPanel = new FormLidController(gc);
        gc.setCurrentTypeGebruiker(TypeGebruiker.Lid);
        
        gc.addPropertyChangeListener(this);
        this.getChildren().addAll(listPanel, gegevensPanel);
    }
    
    public void createForm(TypeGebruiker type){
        switch(type){
            case Lid:
                gegevensPanel = new FormLidController(gc);
                this.getChildren().set(1, gegevensPanel);
                break;
            case Proefgebruiker:
                gegevensPanel = new FormProefgebruikerController(gc);
                this.getChildren().set(1, gegevensPanel);
                break;
        }
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch(evt.getPropertyName()){
            case "currentGebruiker":
                if(evt.getNewValue() != null)
                    gegevensPanel.loadGebruiker((AGebruiker)evt.getNewValue());
                break;
            case "currentTypeGebruiker":
                createForm((TypeGebruiker)evt.getNewValue());
                if(gc.getCurrentGebruiker() != null)
                    gegevensPanel.loadGebruiker(gc.getCurrentGebruiker());
                break;
        }
    }
    
    @Override
    public void resizeWidth(double width) {
        listPanel.setPrefWidth(width*0.3);
        gegevensPanel.setPrefWidth(width*0.7);
    }

    @Override
    public void resizeHeight(double height) {
        gegevensPanel.setPrefHeight(2*height);
    }
}
