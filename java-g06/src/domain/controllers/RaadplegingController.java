/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.controllers;

import domain.LesmateriaalModels.Lesmateriaal;
import domain.beheerders.RaadplegingBeheerder;
import java.beans.PropertyChangeListener;

/**
 *
 * @author boris
 */
public class RaadplegingController {

    private RaadplegingBeheerder beheerder;

    public RaadplegingController() {
        this.beheerder = new RaadplegingBeheerder();
    }

    public void setCurrentLesmateriaal(Lesmateriaal lesmateriaal) {
        beheerder.setCurrentLesmateriaal(lesmateriaal);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        beheerder.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        beheerder.removePropertyChangeListener(pcl);
    }
}
