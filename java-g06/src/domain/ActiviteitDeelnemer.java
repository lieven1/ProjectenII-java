/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.GebruikerModels.AGebruiker;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Steve
 */
@Entity(name="ActiviteitDeelnemer")
public class ActiviteitDeelnemer {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ActiviteitDeelnemerId")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ActiviteitId")
    private Activiteit activiteit;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="Gebruikersnaam")
    private AGebruiker gebruiker;

    public ActiviteitDeelnemer(int id, Activiteit activiteit, AGebruiker gebruiker) {
        this.id = id;
        this.activiteit = activiteit;
        this.gebruiker = gebruiker;
    }

    public ActiviteitDeelnemer() {
    }

    public ActiviteitDeelnemer(Activiteit activiteit, AGebruiker gebruiker) {
        this.activiteit = activiteit;
        this.gebruiker = gebruiker;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Activiteit getActiviteit() {
        return activiteit;
    }

    public void setActiviteit(Activiteit activiteit) {
        this.activiteit = activiteit;
    }

    public AGebruiker getGebruiker() {
        return gebruiker;
    }

    public void setGebruiker(AGebruiker gebruiker) {
        this.gebruiker = gebruiker;
    }   
    
}
