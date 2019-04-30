/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domain.Activiteit;
import java.util.List;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author Steve
 */
public class ActiviteitenRepository {
    
    private final GenericDao<Activiteit> dao;

    public ActiviteitenRepository() {
        this.dao = new GenericDaoJpa(Activiteit.class);
    }
    
    public List<Activiteit> getAll(){
        return dao.findAll();
    }

    public void add(Activiteit act) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void modify(Activiteit oldValue, Activiteit newValue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void remove(Activiteit act) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
