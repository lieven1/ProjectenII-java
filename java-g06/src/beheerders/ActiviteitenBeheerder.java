/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beheerders;

import domain.Activiteit;
import domain.Activiteit;
import java.time.LocalDate;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import persistentie.ActiviteitenDao;
import persistentie.GenericDaoJpa;

/**
 *
 * @author Steve
 */
public class ActiviteitenBeheerder {

    private ActiviteitenDao repository;
    private ObservableList<Activiteit> activiteiten;

    public ActiviteitenBeheerder() {
        repository = new ActiviteitenDao();
        activiteiten = FXCollections.observableArrayList();
        activiteiten.addAll(getAllActiviteiten());
        activiteiten.addListener((ListChangeListener<Activiteit>) this::activiteitenListChanged);

    }

    private void activiteitenListChanged(ListChangeListener.Change<? extends Activiteit> change) {
        // nodig?
    }

    public ObservableList<Activiteit> getActiviteiten() {
        return activiteiten;
    }

    private List<Activiteit> getAllActiviteiten() {
        return repository.findAll();
    }

    public void createActiviteit(Activiteit act) {
        repository.insert(act);
    }

    public void modifyActiviteit(Activiteit newValue) {
        repository.update(newValue);
    }

    public void remove(Activiteit act) {
        repository.delete(act);
    }

    public void veranderFilter(String naam, String type, LocalDate from, LocalDate until) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    public void setCurrentActiviteit(Activiteit activiteit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
