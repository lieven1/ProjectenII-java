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
import domain.ActiviteitBegeleider;
import domain.ActiviteitDeelnemer;
import domain.GebruikerModels.AGebruiker;
import java.util.List;
import java.util.Set;

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

    public List<ActiviteitBegeleider> getActiviteitenBegeleiders() {
        return beheerder.getActiviteitenBegeleiders();
    }

    public List<ActiviteitDeelnemer> getActiviteitenDeelnemers() {
        return beheerder.getActiviteitenDeelnemers();
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

    public ObservableList<AGebruiker> getAlleGebruikers() {
        return beheerder.getAlleGebruikers();
    }

    public ObservableList<AGebruiker> getSpecifiekeGebruikers() {
        return beheerder.getSpecifiekeGebruikers();
    }

    public void toonDeelnemers() {
        beheerder.toonDeelnemers();
    }

    public void toonBegeleiders() {
        beheerder.toonBegeleiders();
    }

    public void setCurrentActiviteit(Activiteit activiteit) {
        beheerder.setCurrentActiviteit(activiteit);
    }

    public void addSelectedAlleGebruiker(AGebruiker gebruiker) {
        beheerder.addSelectedAlleGebruiker(gebruiker);
    }

    public Set<AGebruiker> getSelectedAlleGebruikers() {
        return beheerder.getSelectedAlleGebruikers();
    }

    public void clearSelectedAlleGebruikers() {
        beheerder.clearSelectedAlleGebruikers();
    }

    public void addSelectedSpecifiekeGebruiker(AGebruiker gebruiker) {
        beheerder.addSelectedSpecifiekeGebruiker(gebruiker);
    }

    public Set<AGebruiker> getSelectedSpecifiekeGebruikers() {
        return beheerder.getSelectedSpecifiekeGebruikers();
    }

    public void clearSelectedSpecifiekeGebruikers() {
        beheerder.clearSelectedSpecifiekeGebruikers();
    }

    public void addDeelnemer(AGebruiker e) {
        beheerder.addDeelnemer(e);
    }

    public void addBegeleider(AGebruiker e) {
        beheerder.addBegeleider(e);
    }

    public void removeDeelnemer(AGebruiker e) {
        beheerder.removeDeelnemer(e);
    }

    public void removeBegeleider(AGebruiker e) {
        beheerder.removeBegeleider(e);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        beheerder.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        beheerder.removePropertyChangeListener(pcl);
    }
}
