/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.controllers;

import domain.beheerders.ActiviteitenBeheerder;
import domain.Activiteit;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import javafx.collections.ObservableList;

/**
 *
 * @author Steve
 */
public class ActiviteitenController {

    private ActiviteitenBeheerder beheerder;

    public ActiviteitenController() {
        this.beheerder = new ActiviteitenBeheerder();
    }

    public ObservableList<Activiteit> getActiviteiten() {
        return beheerder.getActiviteitenLijst();
    }

    public void create(Activiteit activiteit) {
        beheerder.setCurrentActiviteit(activiteit);
        beheerder.create(activiteit);
    }

    public void modify(Activiteit activiteit) {
        beheerder.modify(activiteit);
    }

    public void delete() {
        beheerder.delete();
    }

    public Activiteit getCurrentActiviteit() {
        return beheerder.getCurrentActiviteit();
    }

    public void veranderFilter(String naam, String type, LocalDate from, LocalDate until) {
        beheerder.veranderFilter(naam, type, from, until);
    }

    public void setCurrentActiviteit(Activiteit activiteit) {
        beheerder.setCurrentActiviteit(activiteit);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        beheerder.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        beheerder.removePropertyChangeListener(pcl);
    }
}
