/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.beheerders;

import domain.LesmateriaalModels.Lesmateriaal;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author boris
 */
public class RaadplegingBeheerder {

    private Lesmateriaal currentLesmateriaal;

    private final PropertyChangeSupport subject;

    public RaadplegingBeheerder() {
        subject = new PropertyChangeSupport(this);
    }

    // PropertyChangeListener
    public void setCurrentLesmateriaal(Lesmateriaal currentLesmateriaal) {
        subject.firePropertyChange("currentLesmateriaal", this.currentLesmateriaal, currentLesmateriaal);
        this.currentLesmateriaal = currentLesmateriaal;
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        subject.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        subject.removePropertyChangeListener(pcl);
    }
}
