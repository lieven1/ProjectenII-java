package controllers;

import domain.GebruikerModels.AGebruiker;
import domain.GebruikerModels.GebruikerBeheerder;
import domain.GebruikerModels.TypeGebruiker;
import java.beans.PropertyChangeListener;
import javafx.collections.ObservableList;

public class GebruikerController {
    private final GebruikerBeheerder beheerder;
    
    public GebruikerController(){
        beheerder = new GebruikerBeheerder();
    }
    
    public ObservableList<AGebruiker> getGebruikerLijst(){
        return beheerder.getGebruikerLijst();
    }
    
    public void veranderFilter(String naam, String voornaam, boolean lid, boolean proefgebruiker){
        beheerder.veranderFilter(naam, voornaam, lid, proefgebruiker);
    }
    
    // CRUD
    public void create(AGebruiker gebruiker){
        beheerder.create(gebruiker);
    }
    
    public void modify(AGebruiker gebruiker){
        beheerder.modify(gebruiker);
    }
    
    public void delete(){
        beheerder.delete();
    }
    
    // PropertyChangeListeners
    public AGebruiker getCurrentGebruiker() {
        return beheerder.getCurrentGebruiker();
    }

    public TypeGebruiker getCurrentTypeGebruiker() {
        return beheerder.getCurrentTypeGebruiker();
    }
    
    public void setCurrentGebruiker(AGebruiker currentGebruiker) {
        beheerder.setCurrentGebruiker(currentGebruiker);
    }
    
    public void setCurrentTypeGebruiker(TypeGebruiker currentTypeGebruiker) {
        beheerder.setCurrentTypeGebruiker(currentTypeGebruiker);
    }
    
    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        beheerder.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        beheerder.removePropertyChangeListener(pcl);
    }
}
