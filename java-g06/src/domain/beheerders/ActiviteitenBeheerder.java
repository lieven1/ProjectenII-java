/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.beheerders;

import domain.Activiteit;
import domain.DateConverter;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import persistentie.GenericDaoJpa;
import domain.ActiviteitBegeleider;
import domain.ActiviteitDeelnemer;
import domain.GebruikerModels.AGebruiker;
import domain.GebruikerModels.Gebruiker;
import domain.GebruikerModels.TypeGebruiker;
import java.util.ArrayList;
import java.util.stream.Collectors;
import persistentie.GenericDao;

/**
 *
 * @author Steve
 */
public class ActiviteitenBeheerder {

    private GenericDaoJpa<Activiteit> repository;
    private ObservableList<Activiteit> activiteiten;
    private final FilteredList<Activiteit> filteredList;
    private final SortedList<Activiteit> sortedList;

    private final GenericDao<ActiviteitBegeleider> activiteitBegeleiderRepo;
    private final GenericDao<ActiviteitDeelnemer> activiteitDeelnemerRepo;

    private GenericDaoJpa<AGebruiker> gebruikerRepository;

    private ObservableList<AGebruiker> alleGebruikers;
    private final FilteredList<AGebruiker> alleGebruikersFilteredList;
    private final SortedList<AGebruiker> alleGebruikersSortedList;

    private ObservableList<AGebruiker> specifiekeGebruikers;
    private SortedList<AGebruiker> specifiekeGebruikersSortedList;

    private List<AGebruiker> deelnemersOmToeTeVoegen;
    private List<AGebruiker> begeleidersOmToeTeVoegen;

    private Activiteit currentActiviteit;
    private final PropertyChangeSupport subject;

    public ActiviteitenBeheerder() {
        repository = new GenericDaoJpa<>(Activiteit.class);
        activiteitBegeleiderRepo = new GenericDaoJpa<>(ActiviteitBegeleider.class);
        activiteitDeelnemerRepo = new GenericDaoJpa<>(ActiviteitDeelnemer.class);

        gebruikerRepository = new GenericDaoJpa<>(AGebruiker.class);
        activiteiten = FXCollections.observableArrayList(repository.findAll());
        filteredList = new FilteredList<>(activiteiten, p -> true);
        sortedList = new SortedList<>(filteredList,
                Comparator.comparing(Activiteit::getStartDatum)
                        .thenComparing(Activiteit::getTitel)
        );

        alleGebruikers = FXCollections.observableArrayList(gebruikerRepository.findAll());
        alleGebruikersFilteredList = new FilteredList<>(alleGebruikers, p -> true);
        alleGebruikersSortedList = new SortedList<>(alleGebruikersFilteredList,
                Comparator.comparing(AGebruiker::getNaam)
                        .thenComparing(AGebruiker::getVoornaam)
                        .thenComparing(g -> g.getType().equals(TypeGebruiker.Lid) ? ((Gebruiker) g).getGraad() : null)
        );
        
        deelnemersOmToeTeVoegen = new ArrayList<>();
        begeleidersOmToeTeVoegen = new ArrayList<>();

        subject = new PropertyChangeSupport(this);
    }

    public void addDeelnemer(AGebruiker e) {
        if (currentActiviteit != null) {
            currentActiviteit.addDeelnemer(e);
        } else {
            deelnemersOmToeTeVoegen.add(e);
        }
        specifiekeGebruikers.add(e);
    }

    public void addBegeleider(AGebruiker e) {
        if (currentActiviteit != null) {
            currentActiviteit.addBegeleider(e);
        } else {
            begeleidersOmToeTeVoegen.add(e);
        }
        specifiekeGebruikers.add(e);
    }

    public void removeDeelnemer(AGebruiker e) {
        if (currentActiviteit != null) {
            currentActiviteit.deleteDeelnemer(e);
        } else {
            deelnemersOmToeTeVoegen.remove(e);
        }
        specifiekeGebruikers.remove(e);
    }

    public void removeBegeleider(AGebruiker e) {
        if (currentActiviteit != null) {
            currentActiviteit.deleteBegeleider(e);
        } else {
            begeleidersOmToeTeVoegen.remove(e);
        }
        specifiekeGebruikers.remove(e);
    }   

    public void toonDeelnemers() {
        if (currentActiviteit != null) {
            toonSpecifiekeGebruikers(FXCollections.observableArrayList(currentActiviteit.getDeelnemers()));
        } else {
            toonSpecifiekeGebruikers(FXCollections.observableArrayList(deelnemersOmToeTeVoegen));
        }
    }

    public void toonBegeleiders() {
        if (currentActiviteit != null) {
            toonSpecifiekeGebruikers(FXCollections.observableArrayList(currentActiviteit.getBegeleiders()));
        } else {
            toonSpecifiekeGebruikers(FXCollections.observableArrayList(begeleidersOmToeTeVoegen));
        }
    }

    public ObservableList<AGebruiker> getSpecifiekeGebruikers() {
        return specifiekeGebruikersSortedList;
    }

    public ObservableList<AGebruiker> getAlleGebruikers() {
        return alleGebruikersSortedList;
    }

    private void toonSpecifiekeGebruikers(List<AGebruiker> gebruikers) {
        specifiekeGebruikers = FXCollections.observableArrayList(gebruikers);

        specifiekeGebruikersSortedList = new SortedList<>(specifiekeGebruikers,
                Comparator.comparing(AGebruiker::getNaam)
                        .thenComparing(AGebruiker::getVoornaam)
        );

        List<String> gebruikersnamen = specifiekeGebruikers.stream().map(ag -> ag.getGebruikersnaam()).collect(Collectors.toList());
        alleGebruikersFilteredList.setPredicate(gebruiker -> {
            return !gebruikersnamen.contains(gebruiker.getGebruikersnaam());
        });
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
                    : (DateConverter.calendarToLocalDate(act.getStartDatum()).isAfter(from) || DateConverter.calendarToLocalDate(act.getStartDatum()).isEqual(from));
            boolean untilFilter = untilLeeg ? true
                    : (DateConverter.calendarToLocalDate(act.getEindDatum()).isBefore(until) || DateConverter.calendarToLocalDate(act.getEindDatum()).isEqual(until));

            return titelFilter && typeFilter && fromFilter && untilFilter;
        });
    }

    public List<ActiviteitBegeleider> getActiviteitenBegeleiders() {
        return activiteitBegeleiderRepo.findAll();
    }

    public List<ActiviteitDeelnemer> getActiviteitenDeelnemers() {
        return activiteitDeelnemerRepo.findAll();
    }

    private void updateBegeleidersEnDeelnemers() {
        deelnemersOmToeTeVoegen.forEach(gebruiker -> currentActiviteit.addDeelnemer(gebruiker));
        deelnemersOmToeTeVoegen.clear();
        begeleidersOmToeTeVoegen.forEach(gebruiker -> currentActiviteit.addBegeleider(gebruiker));
        begeleidersOmToeTeVoegen.clear();
    }

    // CRUD
    public void create(Activiteit activiteit) {
        currentActiviteit = activiteit;
        updateBegeleidersEnDeelnemers();
        GenericDaoJpa.startTransaction();
        repository.insert(activiteit);
        GenericDaoJpa.commitTransaction();
        activiteiten.add(activiteit);
    }

    public void modify(Activiteit activiteit) {
        activiteit.setId(currentActiviteit.getId());
        activiteit.setActiviteitDeelnemers(currentActiviteit.getActiviteitDeelnemers());
        activiteit.setActiviteitBegeleiders(currentActiviteit.getActiviteitBegeleiders());
        GenericDaoJpa.startTransaction();
        repository.update(activiteit);
        GenericDaoJpa.commitTransaction();
        activiteiten.set(activiteiten.indexOf(currentActiviteit), activiteit);
        activiteiten.notifyAll();
    }

    public void delete() {
        Activiteit act = currentActiviteit;
        for(Activiteit a : activiteiten){
            if(a.getId() == act.getId()){
                act = a;
            }
        }
        GenericDaoJpa.startTransaction();
        repository.delete(act);
        GenericDaoJpa.commitTransaction();
        activiteiten.remove(act);
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

    public void clearTabellen() {
        specifiekeGebruikers.clear();
    }

}
