/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.Overzicht;

import domain.GebruikerModels.AGebruiker;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.SortedSet;
import java.util.TreeSet;
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

    private ObservableList<Lesmoment> lesmomentList;
    private FilteredList<Lesmoment> filteredList;
    private SortedList<Lesmoment> sortedList;

    private Lesmoment currentLesmoment;

    private PropertyChangeSupport subject;

    public OverzichtBeheerder() {
        this.lesmomentRepo = new GenericDaoJpa<>(Lesmoment.class);
        lesmomentList = FXCollections.observableArrayList(lesmomentRepo.findAll());
        filteredList = new FilteredList<>(lesmomentList);
        sortedList = new SortedList<>(filteredList,
                Comparator.comparing(Lesmoment::getStartTijd)
        );

        subject = new PropertyChangeSupport(this);
    }

    public ObservableList<Lesmoment> getLesomentList() {
        return sortedList;
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        subject.addPropertyChangeListener(pcl);
    }

    public void veranderFilter(LocalDate van, LocalDate tot) {
        filteredList.setPredicate(g -> {
            boolean naamLeeg = van == null;
            boolean voornaamLeeg = tot == null;

            if (naamLeeg && voornaamLeeg) {
                return true;
            }

            boolean naamFilter = naamLeeg ? true
                    : (g.getStartTijd().compareTo(convertToDateViaInstant(van)) > 0);
            boolean voornaamFilter = voornaamLeeg ? true
                    : (g.getEindTijd().compareTo(convertToDateViaInstant(tot)) < 0);

            return (naamFilter && voornaamFilter);
        });
    }

    public Lesmoment getCurrentLesmoment() {
        return currentLesmoment;
    }

    public void setCurrentLesmoment(Lesmoment currentLesmoment) {
        this.currentLesmoment = currentLesmoment;
    }

    public Date convertToDateViaInstant(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }
}
