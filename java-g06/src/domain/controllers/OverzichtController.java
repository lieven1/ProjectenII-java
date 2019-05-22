/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.controllers;

import domain.Overzicht.Lesmoment;
import domain.Overzicht.LesmomentLeden;
import domain.beheerders.OverzichtBeheerder;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author boris
 */
public class OverzichtController {

    private OverzichtBeheerder beheerder;

    OverzichtController() {
        this.beheerder = new OverzichtBeheerder();
    }

    public void veranderFilter(LocalDate van, LocalDate tot) {
        beheerder.veranderFilter(van, tot);
    }

    public ObservableList<Lesmoment> getLesomentObservableList() {
        return beheerder.getLesomentObservableList();
    }

    public List<Lesmoment> getLesomentList() {
        return beheerder.getLesomentList();
    }

    public List<LesmomentLeden> getLesmomentLeden() {
        return beheerder.getLesmomentLeden();
    }

    public Lesmoment getCurrentLesmoment() {
        return beheerder.getCurrentLesmoment();
    }

    public void setCurrentLesmoment(Lesmoment currentLesmoment) {
        beheerder.setCurrentLesmoment(currentLesmoment);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        beheerder.addPropertyChangeListener(pcl);
    }

}
