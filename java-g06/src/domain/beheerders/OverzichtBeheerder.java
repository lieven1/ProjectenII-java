/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.beheerders;

import domain.Overzicht.Lesmoment;
import domain.Overzicht.LesmomentLeden;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import persistentie.GenericDao;
import persistentie.GenericDaoJpa;

/**
 *
 * @author boris
 */
public class OverzichtBeheerder {

    private final GenericDao<Lesmoment> lesmomentRepo;
    private final GenericDao<LesmomentLeden> lesmomentLedenRepo;

    private ObservableList<Lesmoment> lesmomentList;
    private FilteredList<Lesmoment> filteredList;
    private SortedList<Lesmoment> sortedList;

    private List<LesmomentLeden> lesmomentLedenList;

    private Lesmoment currentLesmoment;

    private PropertyChangeSupport subject;

    public OverzichtBeheerder() {
        //repo's
        lesmomentRepo = new GenericDaoJpa<>(Lesmoment.class);
        lesmomentLedenRepo = new GenericDaoJpa<>(LesmomentLeden.class);
        //lesmoment lists
        lesmomentList = FXCollections.observableArrayList(lesmomentRepo.findAll());
        filteredList = new FilteredList<>(lesmomentList);
        sortedList = new SortedList<>(filteredList,
                Comparator.comparing(Lesmoment::getStartTijd)
        );
        //lesmomentleden lists
        lesmomentLedenList = lesmomentLedenRepo.findAll();

        subject = new PropertyChangeSupport(this);
    }

    public ObservableList<Lesmoment> getLesomentObservableList() {
        return sortedList;
    }

    public List<Lesmoment> getLesomentList() {
        return lesmomentRepo.findAll();
    }

    public List<LesmomentLeden> getLesmomentLeden() {
        return lesmomentLedenRepo.findAll();
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        subject.addPropertyChangeListener(pcl);
    }

    public void veranderFilter(LocalDate van, LocalDate tot) {
        filteredList.setPredicate(g -> {
            boolean vanLeeg = van == null;
            boolean totLeeg = tot == null;

            if (vanLeeg && totLeeg) {
                return true;
            }

            boolean naamFilter = vanLeeg ? true
                    : (g.getStartTijd().compareTo(convertToDateViaInstant(van)) > 0);
            boolean voornaamFilter = totLeeg ? true
                    : (g.getEindTijd().compareTo(convertToDateViaInstant(tot)) < 0);

            return (naamFilter && voornaamFilter);
        });
    }

    public Lesmoment getCurrentLesmoment() {
        return currentLesmoment;
    }

    public void setCurrentLesmoment(Lesmoment currentLesmoment) {
        subject.firePropertyChange("currentLesmoment", this.currentLesmoment, currentLesmoment);
        this.currentLesmoment = currentLesmoment;
    }

    public Date convertToDateViaInstant(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }
}
