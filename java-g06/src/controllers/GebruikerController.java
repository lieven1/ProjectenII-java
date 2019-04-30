package controllers;

import domain.GebruikerModels.AGebruiker;
import domain.GebruikerModels.GebruikerBeheerder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GebruikerController {
    private final GebruikerBeheerder beheerder;
    private final ObservableList<AGebruiker> gebruikerList;
    
    public GebruikerController(){
        beheerder = new GebruikerBeheerder();
        gebruikerList = FXCollections.observableArrayList(beheerder.getAll());
    }
    
    public ObservableList<AGebruiker> getObservableList(){
        return gebruikerList;
    }
    
    public void create(AGebruiker gebruiker){
        beheerder.create(gebruiker);
        gebruikerList.add(gebruiker);
    }
    
    public void modify(AGebruiker oldGebruiker, AGebruiker newGebruiker){
        beheerder.modify(newGebruiker);
        gebruikerList.set(gebruikerList.indexOf(oldGebruiker), newGebruiker);
    }
    
    public void delete(AGebruiker gebruiker){
        beheerder.delete(gebruiker);
        gebruikerList.remove(gebruiker);
    }
}
