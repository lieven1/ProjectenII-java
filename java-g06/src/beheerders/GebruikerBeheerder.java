package beheerders;

import domain.GebruikerModels.AGebruiker;
import domain.GebruikerModels.Gebruiker;
import domain.GebruikerModels.TypeGebruiker;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Comparator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import persistentie.GenericDao;
import persistentie.GenericDaoJpa;

/**
 * @author Bono
 */
public class GebruikerBeheerder {
    private final GenericDao<AGebruiker> gebruikerRepo;
    
    private final ObservableList<AGebruiker> gebruikerList;
    private final FilteredList<AGebruiker> filteredList;
    private final SortedList<AGebruiker> sortedList;
    
    private AGebruiker currentGebruiker;
    private TypeGebruiker currentTypeGebruiker;
    private final PropertyChangeSupport subject;
    
    public GebruikerBeheerder(){
        gebruikerRepo = new GenericDaoJpa<>(AGebruiker.class);
        gebruikerList = FXCollections.observableArrayList(gebruikerRepo.findAll());
        filteredList = new FilteredList<>(gebruikerList, p -> true);
        sortedList = new SortedList<>(filteredList, 
                Comparator.comparing(AGebruiker::getNaam)
                .thenComparing(AGebruiker::getVoornaam)
                .thenComparing(g -> g.getType().equals(TypeGebruiker.Lid)? ((Gebruiker)g).getGraad() : null)
        );
        
        subject = new PropertyChangeSupport(this);
    }
    
    public ObservableList<AGebruiker> getGebruikerLijst(){
        return sortedList;
    }
    
    public void veranderFilter(String naam, String voornaam, boolean lid, boolean proefgebruiker){
        filteredList.setPredicate(g -> {
            boolean naamLeeg = naam == null || naam.isBlank();
            boolean voornaamLeeg = voornaam == null || voornaam.isBlank();
            
            if(naamLeeg && voornaamLeeg && lid && proefgebruiker){
                return true;
            }
            
            boolean naamFilter = naamLeeg ? true
                    : (g.getNaam().toLowerCase().contains(naam.toLowerCase()));
            boolean voornaamFilter = voornaamLeeg ? true
                    : (g.getVoornaam().toLowerCase().contains(voornaam.toLowerCase()));
            boolean lidFilter = lid ? true 
                    : (g.getType().equals(TypeGebruiker.Lid));
            boolean proefgebruikerFilter = proefgebruiker ? true 
                    : (g.getType().equals(TypeGebruiker.Proefgebruiker));
            
            return (naamFilter && voornaamFilter && lidFilter && proefgebruikerFilter);
        });
    }
    
    // CRUD
    public void create(AGebruiker newGebruiker){
        currentGebruiker = newGebruiker;
        GenericDaoJpa.startTransaction();
        gebruikerRepo.insert(currentGebruiker);
        GenericDaoJpa.commitTransaction();
        gebruikerList.add(currentGebruiker);
    }
    
    public void modify(AGebruiker modifiedGebruiker){
        GenericDaoJpa.startTransaction();
        gebruikerRepo.delete(currentGebruiker);
        GenericDaoJpa.commitTransaction();
        GenericDaoJpa.startTransaction();
        gebruikerRepo.insert(modifiedGebruiker);
        GenericDaoJpa.commitTransaction();
        gebruikerList.set(gebruikerList.indexOf(currentGebruiker), modifiedGebruiker);
        currentGebruiker = modifiedGebruiker;
    }
    
    public void delete(){
        GenericDaoJpa.startTransaction();
        gebruikerRepo.delete(currentGebruiker);
        GenericDaoJpa.commitTransaction();
        gebruikerList.remove(currentGebruiker);
        currentGebruiker = null;
    }
    
    // PropertyChangeListener
    public AGebruiker getCurrentGebruiker() {
        return currentGebruiker;
    }

    public TypeGebruiker getCurrentTypeGebruiker() {
        return currentTypeGebruiker;
    }
    
    public void setCurrentGebruiker(AGebruiker currentGebruiker) {
        subject.firePropertyChange("currentGebruiker", this.currentGebruiker, currentGebruiker);
        this.currentGebruiker = currentGebruiker;
    }
    
    public void setCurrentTypeGebruiker(TypeGebruiker currentTypeGebruiker) {
        subject.firePropertyChange("currentTypeGebruiker", this.currentTypeGebruiker, currentTypeGebruiker);
        this.currentTypeGebruiker = currentTypeGebruiker;
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        subject.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        subject.removePropertyChangeListener(pcl);
    }
}