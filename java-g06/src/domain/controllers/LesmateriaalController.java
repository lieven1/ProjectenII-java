package domain.controllers;

import domain.GebruikerModels.Gradatie;
import domain.LesmateriaalModels.Lesmateriaal;
import domain.LesmateriaalModels.Thema;
import domain.beheerders.LesmateriaalBeheerder;
import domain.beheerders.ThemaBeheerder;
import java.beans.PropertyChangeListener;
import javafx.collections.ObservableList;

public class LesmateriaalController {
    private final LesmateriaalBeheerder beheerder;
    private final ThemaBeheerder themaBeheerder;
    
    public LesmateriaalController(){
        beheerder = new LesmateriaalBeheerder();
        themaBeheerder = new ThemaBeheerder();
    }
    
    public ObservableList<Lesmateriaal> getLesmateriaalLijst(){
        return beheerder.getLesmateriaalLijst();
    }
    
    public ObservableList<Thema> getThemaLijst(){
        return themaBeheerder.getThemaList();
    }
    
    public void veranderFilter(String naam, Gradatie graad, Thema thema){
        beheerder.veranderFilter(naam, graad, thema);
    }
    
    // CRUD
    public void create(Lesmateriaal lesmateriaal){
        beheerder.create(lesmateriaal);
    }
    
    public void modify(){
        beheerder.modify();
    }
    
    public void delete(){
        beheerder.delete();
    }
    
    // PropertyChangeListeners
    public Lesmateriaal getCurrentLesmateriaal() {
        return beheerder.getCurrentLesmateriaal();
    }
    
    public void setCurrentLesmateriaal(Lesmateriaal currentLesmateriaal) {
        beheerder.setCurrentLesmateriaal(currentLesmateriaal);
    }
    
    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        beheerder.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        beheerder.removePropertyChangeListener(pcl);
    }
}
