/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import Domain.Activiteit;
import java.util.List;
import persistentie.ActiviteitenRepository;

/**
 *
 * @author Steve
 */
public class ActiviteitenBeheerder {
    
    private ActiviteitenRepository repository;
    
    public ActiviteitenBeheerder(){
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
