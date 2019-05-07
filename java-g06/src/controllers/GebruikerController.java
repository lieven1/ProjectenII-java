package controllers;

import domain.GebruikerModels.AGebruiker;
import domain.GebruikerModels.GebruikerBeheerder;
import javafx.collections.ObservableList;

public class GebruikerController {
    private final GebruikerBeheerder beheerder;
    
    public GebruikerController(){
        beheerder = new GebruikerBeheerder();
    }
    
    public ObservableList<AGebruiker> getObservableList(){
        return beheerder.getAll();
    }
    
    public ObservableList<AGebruiker> getFilteredList(String gebruikersnaam, String naam, String voornaam, boolean lid, boolean proefgebruiker){
        return beheerder.getFilteredList(gebruikersnaam, naam, voornaam, lid, proefgebruiker);
    }
    
    public void create(AGebruiker gebruiker){
        beheerder.create(gebruiker);
    }
    
    public void modify(AGebruiker oldGebruiker, AGebruiker newGebruiker){
        beheerder.modify(oldGebruiker, newGebruiker);
    }
    
    public void delete(AGebruiker gebruiker){
        beheerder.delete(gebruiker);
    }
}
