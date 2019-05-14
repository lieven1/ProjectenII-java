package domain.beheerders;

import domain.GebruikerModels.Gradatie;
import domain.LesmateriaalModels.Lesmateriaal;
import domain.LesmateriaalModels.Thema;
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
public final class LesmateriaalBeheerder {
    private final GenericDao<Lesmateriaal> lesmateriaalRepo;
    
    private final ObservableList<Lesmateriaal> lesmateriaalList;
    private final FilteredList<Lesmateriaal> filteredList;
    private final SortedList<Lesmateriaal> sortedList;
    
    private Lesmateriaal currentLesmateriaal;
    private final PropertyChangeSupport subject;
    
    public LesmateriaalBeheerder(){
        lesmateriaalRepo = new GenericDaoJpa<>(Lesmateriaal.class);
        lesmateriaalList = FXCollections.observableArrayList(lesmateriaalRepo.findAll());
        filteredList = new FilteredList<>(lesmateriaalList, p -> true);
        sortedList = new SortedList<>(filteredList, 
                Comparator.comparing(Lesmateriaal::getGraad)
                .thenComparing(Lesmateriaal::getNaam)
        );
        
        subject = new PropertyChangeSupport(this);
    }
    
    public ObservableList<Lesmateriaal> getLesmateriaalLijst(){
        return sortedList;
    }
    
    public void veranderFilter(String naam, Gradatie graad, Thema thema){
        filteredList.setPredicate(lesm -> {
            boolean naamLeeg = naam == null || naam.isBlank();
            boolean graadLeeg = graad == null;
            boolean themaLeeg = thema == null;
            
            if(naamLeeg && graadLeeg && themaLeeg){
                return true;
            }
            
            boolean naamFilter = naamLeeg ? true
                    : (lesm.getNaam().toLowerCase().contains(naam));
            boolean graadFilter = graadLeeg ? true
                    : (lesm.getGraad().equals(graad));
            boolean themaFilter = themaLeeg ? true
                    : (lesm.getThema().equals(thema));
            
            return naamFilter && graadFilter && themaFilter;
        });
    }
    
    // CRUD
    public void create(Lesmateriaal lesmateriaal){
        currentLesmateriaal = lesmateriaal;
        GenericDaoJpa.startTransaction();
        lesmateriaalRepo.insert(lesmateriaal);
        GenericDaoJpa.commitTransaction();
        lesmateriaalList.add(lesmateriaal);
    }
    
    public void modify(){
        GenericDaoJpa.startTransaction();
        lesmateriaalRepo.update(currentLesmateriaal);
        GenericDaoJpa.commitTransaction();
        lesmateriaalList.set(lesmateriaalList.indexOf(currentLesmateriaal), currentLesmateriaal);
    }
    
    public void delete(){
        GenericDaoJpa.startTransaction();
        lesmateriaalRepo.delete(currentLesmateriaal);
        GenericDaoJpa.commitTransaction();
        lesmateriaalList.remove(currentLesmateriaal);
        currentLesmateriaal = null;
    }
    
    // PropertyChangeListener
    public void setCurrentLesmateriaal(Lesmateriaal currentLesmateriaal) {
        subject.firePropertyChange("currentLesmateriaal", this.currentLesmateriaal, currentLesmateriaal);
        this.currentLesmateriaal = currentLesmateriaal;
    }
    
    public Lesmateriaal getCurrentLesmateriaal() {
        return this.currentLesmateriaal;
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        subject.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        subject.removePropertyChangeListener(pcl);
    }
}
