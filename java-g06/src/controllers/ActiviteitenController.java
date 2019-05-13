/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domain.Activiteit;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;

/**
 *
 * @author Steve
 */
public class ActiviteitenController {

    private ActiviteitenBeheerder beheerder;

    public ActiviteitenController(ActiviteitenBeheerder beheerder) {
        this.beheerder = beheerder;
    }

    public ObservableList<Activiteit> getActiviteiten() {
        return beheerder.getActiviteiten();
    }

    public void create(Activiteit act) {
        beheerder.createActiviteit(act);
    }

    public void modify(Activiteit act) {
        beheerder.modifyActiviteit(act);
    }

    public void delete(ObservableValue<Activiteit> act) {
        beheerder.remove(act.getValue());
    }

    public void veranderFilter(String naam, String type, LocalDate from, LocalDate until) {
        beheerder.veranderFilter(naam, type, from, until);
    }

    public void setCurrentActiviteit(Activiteit activiteit) {
        beheerder.setCurrentActiviteit(activiteit);
    }
}


