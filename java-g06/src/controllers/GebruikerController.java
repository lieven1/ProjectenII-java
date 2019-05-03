package controllers;

import domain.GebruikerModels.AGebruiker;
import domain.GebruikerModels.GebruikerBeheerder;
import domain.GebruikerModels.TypeGebruiker;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class GebruikerController {
    private final GebruikerBeheerder beheerder;
    private final ObservableList<AGebruiker> gebruikerList;
    private ObservableList<AGebruiker> filteredList;
    
    public GebruikerController(){
        beheerder = new GebruikerBeheerder();
        gebruikerList = FXCollections.observableArrayList(beheerder.getAll());
        filteredList = FXCollections.observableArrayList(gebruikerList);
    }
    
    public ObservableList<AGebruiker> getObservableList(){
        return gebruikerList;
    }
    
    public ObservableList<AGebruiker> getFilteredList(String gebruikersnaam, String naam, String voornaam, boolean beheerder, boolean lid, boolean proefgebruiker){
        filteredList = FXCollections.observableArrayList(gebruikerList);
            if(!lid)                
                filteredList.removeAll(filteredList.stream().filter(g -> g.getType().equals(TypeGebruiker.Lid)).collect(Collectors.toList()));
            if(!proefgebruiker)                
                filteredList.removeAll(filteredList.stream().filter(g -> g.getType().equals(TypeGebruiker.Proefgebruiker)).collect(Collectors.toList()));
            if(!beheerder)                
                filteredList.removeAll(filteredList.stream().filter(g -> g.getType().equals(TypeGebruiker.Beheerder)).collect(Collectors.toList()));
            if(gebruikersnaam != null && !gebruikersnaam.isBlank())
                filteredList.removeAll(filteredList.stream().filter(g -> !g.getGebruikersnaam().toUpperCase().contains(gebruikersnaam.toUpperCase())).collect(Collectors.toList()));
            if(naam != null && !naam.isBlank())
                filteredList.removeAll(filteredList.stream().filter(g -> !g.getNaam().toUpperCase().contains(naam.toUpperCase())).collect(Collectors.toList()));
            if(voornaam != null && !voornaam.isBlank())
                filteredList.removeAll(filteredList.stream().filter(g -> !g.getVoornaam().toUpperCase().contains(voornaam.toUpperCase())).collect(Collectors.toList()));
        return filteredList;
    }
    
    public void create(AGebruiker gebruiker){
        beheerder.create(gebruiker);
        gebruikerList.add(gebruiker);
        filteredList.add(gebruiker);
    }
    
    public void modify(AGebruiker oldGebruiker, AGebruiker newGebruiker){
        beheerder.modify(oldGebruiker, newGebruiker);
        gebruikerList.set(gebruikerList.indexOf(oldGebruiker), newGebruiker);
        if(filteredList.contains(oldGebruiker))
            filteredList.set(filteredList.indexOf(oldGebruiker), newGebruiker);
    }
    
    public void delete(AGebruiker gebruiker){
        beheerder.delete(gebruiker);
        gebruikerList.remove(gebruiker);
        if(filteredList.contains(gebruiker))
            filteredList.remove(gebruiker);
    }
    
    public void addObserver(ListChangeListener<AGebruiker> listener){
        gebruikerList.addListener(listener);
    }
}
