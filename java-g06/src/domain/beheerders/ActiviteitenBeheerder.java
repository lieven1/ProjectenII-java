/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.beheerders;

import domain.Activiteit;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Comparator;
import java.util.TimeZone;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import persistentie.GenericDaoJpa;

/**
 *
 * @author Steve
 */
public class ActiviteitenBeheerder {

    private GenericDaoJpa<Activiteit> repository;
    private ObservableList<Activiteit> activiteiten;
    private final FilteredList<Activiteit> filteredList;
    private final SortedList<Activiteit> sortedList;

    private Activiteit currentActiviteit;
    private final PropertyChangeSupport subject;

    public ActiviteitenBeheerder() {
        repository = new GenericDaoJpa<>(Activiteit.class);
        activiteiten = FXCollections.observableArrayList(repository.findAll());
        filteredList = new FilteredList<>(activiteiten, p -> true);
        sortedList = new SortedList<>(filteredList,
                Comparator.comparing(Activiteit::getStartDatum)
                        .thenComparing(Activiteit::getTitel)
        );

        subject = new PropertyChangeSupport(this);
    }

    public void veranderFilter(String titel, String type, LocalDate from, LocalDate until) {
        filteredList.setPredicate(act -> {
            boolean titelLeeg = titel == null || titel.isBlank();
            boolean typeLeeg = type == null;
            boolean fromLeeg = from == null;
            boolean untilLeeg = until == null;

            if (titelLeeg && typeLeeg && fromLeeg && untilLeeg) {
                return true;
            }

            boolean titelFilter = titelLeeg ? true
                    : (act.getTitel().toLowerCase().contains(titel));
            boolean typeFilter = typeLeeg ? true
                    : (act.getType().toLowerCase().contains(type));
            boolean fromFilter = fromLeeg ? true
                    : (calendarToLocalDate(act.getStartDatum()).isAfter(from) || calendarToLocalDate(act.getStartDatum()).isEqual(from));
            boolean untilFilter = untilLeeg ? true
                    : (calendarToLocalDate(act.getEindDatum()).isBefore(until) || calendarToLocalDate(act.getEindDatum()).isEqual(until));

            return titelFilter && typeFilter && fromFilter && untilFilter;
        });
    }

    // CRUD
    public void create(Activiteit activiteit) {
        currentActiviteit = activiteit;
        GenericDaoJpa.startTransaction();
        repository.insert(activiteit);
        GenericDaoJpa.commitTransaction();
        activiteiten.add(activiteit);
    }

    public void modify(Activiteit activiteit) {
        activiteit.setId(currentActiviteit.getId());
        GenericDaoJpa.startTransaction();
        repository.update(activiteit);
        GenericDaoJpa.commitTransaction();
        activiteiten.set(activiteiten.indexOf(currentActiviteit), activiteit);
        activiteiten.notifyAll();
    }

    public void delete() {
        GenericDaoJpa.startTransaction();
        repository.delete(currentActiviteit);
        GenericDaoJpa.commitTransaction();
        activiteiten.remove(currentActiviteit);
        currentActiviteit = null;
    }

    // PropertyChangeListener
    public void setCurrentActiviteit(Activiteit currentActiviteit) {
        subject.firePropertyChange("currentActiviteit", this.currentActiviteit, currentActiviteit);
        this.currentActiviteit = currentActiviteit;
    }

    public ObservableList<Activiteit> getActiviteitenLijst() {
        return sortedList;
    }

    public Activiteit getCurrentActiviteit() {
        return this.currentActiviteit;
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        subject.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        subject.removePropertyChangeListener(pcl);
    }

    private LocalDate calendarToLocalDate(Calendar calendar) {
        if (calendar == null) {
            return null;
        }
        TimeZone tz = calendar.getTimeZone();
        ZoneId zid = tz == null ? ZoneId.systemDefault() : tz.toZoneId();
        return LocalDate.ofInstant(calendar.toInstant(), zid);
    }
}
