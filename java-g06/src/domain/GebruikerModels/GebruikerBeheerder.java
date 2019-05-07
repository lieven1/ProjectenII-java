package domain.GebruikerModels;

import java.util.Comparator;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistentie.GenericDao;
import persistentie.GenericDaoJpa;

public class GebruikerBeheerder {
    private final GenericDao<AGebruiker> gebruikerRepo;
    private final ObservableList<AGebruiker> gebruikerList;
    private ObservableList<AGebruiker> filteredList;
    
    public GebruikerBeheerder(){
        gebruikerRepo = new GenericDaoJpa<>(AGebruiker.class);
        gebruikerList = FXCollections.observableArrayList(gebruikerRepo.findAll());
        filteredList = FXCollections.observableArrayList(gebruikerList);
    }
    
    public ObservableList<AGebruiker> getAll(){
        Comparator<AGebruiker> naamComparator = Comparator.comparing(AGebruiker::getNaam).thenComparing(AGebruiker::getVoornaam);
        gebruikerList.sort(naamComparator);
        return gebruikerList;
    }
    
    public ObservableList<AGebruiker> getFilteredList(String gebruikersnaam, String naam, String voornaam, boolean lid, boolean proefgebruiker){
        filteredList = FXCollections.observableArrayList(this.getAll());
            if(!lid)                
                filteredList.removeAll(filteredList.stream().filter(g -> g.getType().equals(TypeGebruiker.Lid)).collect(Collectors.toList()));
            if(!proefgebruiker)                
                filteredList.removeAll(filteredList.stream().filter(g -> g.getType().equals(TypeGebruiker.Proefgebruiker)).collect(Collectors.toList()));
            if(gebruikersnaam != null && !gebruikersnaam.isBlank())
                filteredList.removeAll(filteredList.stream().filter(g -> !g.getGebruikersnaam().toUpperCase().contains(gebruikersnaam.toUpperCase())).collect(Collectors.toList()));
            if(naam != null && !naam.isBlank())
                filteredList.removeAll(filteredList.stream().filter(g -> !g.getNaam().toUpperCase().contains(naam.toUpperCase())).collect(Collectors.toList()));
            if(voornaam != null && !voornaam.isBlank())
                filteredList.removeAll(filteredList.stream().filter(g -> !g.getVoornaam().toUpperCase().contains(voornaam.toUpperCase())).collect(Collectors.toList()));
        return filteredList;
    }
    
    public void create(AGebruiker gebruiker){
        GenericDaoJpa.startTransaction();
        gebruikerRepo.insert(gebruiker);
        GenericDaoJpa.commitTransaction();
        gebruikerList.add(gebruiker);
        filteredList.add(gebruiker);
    }
    
    public void modify(AGebruiker oldGeb, AGebruiker gebruiker){
        GenericDaoJpa.startTransaction();
        gebruikerRepo.delete(oldGeb);
        GenericDaoJpa.commitTransaction();
        GenericDaoJpa.startTransaction();
        gebruikerRepo.insert(gebruiker);
        GenericDaoJpa.commitTransaction();
        gebruikerList.set(gebruikerList.indexOf(oldGeb), gebruiker);
        if(filteredList.contains(oldGeb))
            filteredList.set(filteredList.indexOf(oldGeb), gebruiker);
    }
    
    public void delete(AGebruiker gebruiker){
        GenericDaoJpa.startTransaction();
        gebruikerRepo.delete(gebruiker);
        GenericDaoJpa.commitTransaction();
    }
}