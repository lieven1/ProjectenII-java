package domain.LesmateriaalModels;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Comparator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import persistentie.GenericDao;
import persistentie.GenericDaoJpa;

/**
 * @author Bono
 */
public final class ThemaBeheerder {
    private final GenericDao<Thema> themaRepo;
    
    private ObservableList<Thema> themaList;
    private SortedList<Thema> sortedList;
    
    private Thema currentThema;
    private PropertyChangeSupport subject;
    
    public ThemaBeheerder() {
        themaRepo = new GenericDaoJpa<>(Thema.class);
        themaList = FXCollections.observableArrayList(themaRepo.findAll());
        sortedList = new SortedList<>(themaList, 
                Comparator.comparing(Thema::getNaam)
        );
    }
    
    public ObservableList<Thema> getThemaList(){
        return sortedList;
    }
    
    // CRUD
    public void create(){
        GenericDaoJpa.startTransaction();
        themaRepo.insert(currentThema);
        GenericDaoJpa.commitTransaction();
        themaList.add(currentThema);
    }
    
    public void modify(){
        GenericDaoJpa.startTransaction();
        themaRepo.update(currentThema);
        GenericDaoJpa.commitTransaction();
    }
    
    public void delete(){
        GenericDaoJpa.startTransaction();
        themaRepo.delete(currentThema);
        GenericDaoJpa.commitTransaction();
        themaList.remove(currentThema);
    }
    
    // PropertyChangeListener
    public void setCurrentThema(Thema currentThema) {
        subject.firePropertyChange("currentThema", this.currentThema, currentThema);
        this.currentThema = currentThema;
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        subject.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        subject.removePropertyChangeListener(pcl);
    }
}
