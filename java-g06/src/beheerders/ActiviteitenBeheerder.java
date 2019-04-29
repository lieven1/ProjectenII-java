/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beheerders;

import controllers.ActiviteitenController;
import domain.Activiteit;
import java.util.List;
import javafx.beans.value.ObservableValue;
import persistentie.ActiviteitenRepository;

/**
 *
 * @author Steve
 */
public class ActiviteitenBeheerder {
    
    private ActiviteitenController controller;
    private ActiviteitenRepository repository;
    
    public ActiviteitenBeheerder(){
        controller = new ActiviteitenController(this);
    }
    
    public List<Activiteit> getAllActiviteiten(){
        return repository.getAll();
    }

    public void createActiviteit(Activiteit act) {
        repository.add(act);
    }

    public void modifyActiviteit(Activiteit oldValue, Activiteit newValue) {
        repository.modify(oldValue, newValue);
    }

    public void remove(Activiteit act) {
        repository.remove(act);
    }

    
}
