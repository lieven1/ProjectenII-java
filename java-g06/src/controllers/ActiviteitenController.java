/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domain.Activiteit;
import beheerders.ActiviteitenBeheerder;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;

/**
 *
 * @author Steve
 */
public class ActiviteitenController {
    
    private ActiviteitenBeheerder beheerder;
    private ObservableList<ObservableValue<Activiteit>> activiteiten;

    public ActiviteitenController(ActiviteitenBeheerder beheerder) {
        this.beheerder = beheerder;
        activiteiten = new SimpleListProperty<>();
        
        activiteiten.addAll(beheerder.getAllActiviteiten().stream().map(act -> new SimpleObjectProperty<>(act)).collect(Collectors.toList()));
        activiteiten.forEach(actPropr -> actPropr.addListener(this::activiteitChanged));
        
        activiteiten.addListener((ListChangeListener<ObservableValue<Activiteit>>)this::activiteitenListChanged);
    }
    
    public void create(Activiteit act){
        beheerder.createActiviteit(act);
        activiteiten.add(new SimpleObjectProperty<>(act)); 
    }   
    
    public void modify(Activiteit act){
        // 
    }
    
    public void delete(ObservableValue<Activiteit> act){
        beheerder.remove(act.getValue());
        activiteiten.remove(act);
    }
    
    private void activiteitChanged(ObservableValue<? extends Activiteit> observable, Activiteit oldValue, Activiteit newValue){
        beheerder.modifyActiviteit(oldValue, newValue);
    }
    
    private void activiteitenListChanged(Change<? extends ObservableValue<Activiteit>> change){
        // nodig?
    }
        
}
