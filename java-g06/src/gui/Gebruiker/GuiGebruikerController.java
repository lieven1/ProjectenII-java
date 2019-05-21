package gui.Gebruiker;

import domain.controllers.GebruikerController;
import domain.GebruikerModels.AGebruiker;
import domain.GebruikerModels.TypeGebruiker;
import gui.Panel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.scene.layout.HBox;

public class GuiGebruikerController extends HBox implements Panel, PropertyChangeListener {
    private final GebruikerListPanel listPanel;
    private GebruikerForm gegevensPanel;
    
    private final GebruikerController gc;
    
    public GuiGebruikerController(GebruikerController gc){
        this.gc = gc;
        
        // Panels
        listPanel = new GebruikerListPanel(gc);
        gegevensPanel = new FormLid(gc);
        gc.setCurrentTypeGebruiker(TypeGebruiker.Lid);
        
        gc.addPropertyChangeListener(this);
        this.getChildren().addAll(listPanel, gegevensPanel);
        this.gc.veranderFilter("", "", true, true);
    }
    
    public void createForm(TypeGebruiker type){
        
        switch(type){
            case Lid:
                gegevensPanel = new FormLid(gc);
                this.getChildren().set(1, gegevensPanel);
                break;
            case Proefgebruiker:
                gegevensPanel = new FormProefgebruiker(gc);
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
        listPanel.setPrefWidth(width*0.2);
        gegevensPanel.setPrefWidth(width*0.8);
    }

    @Override
    public void resizeHeight(double height) {
        gegevensPanel.setPrefHeight(height*2.25);
    }
}
