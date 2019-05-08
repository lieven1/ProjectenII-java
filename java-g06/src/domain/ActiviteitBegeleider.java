/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.GebruikerModels.Gebruiker;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Steve
 */
@Entity(name = "ActiviteitBegeleider")
public class ActiviteitBegeleider {

    @Id
    @Column(name = "ActiviteitBegeleiderId")
    private int id;

    @ManyToOne
    @JoinColumn(name = "ActiviteitId")
    private Activiteit activiteit;

    @ManyToOne
    @JoinColumn(name = "Gebruikersnaam")
    private Gebruiker gebruiker;

    public ActiviteitBegeleider(int id, Activiteit activiteit, Gebruiker gebruiker) {
        this.id = id;
        this.activiteit = activiteit;
        this.gebruiker = gebruiker;
    }

    public ActiviteitBegeleider() {
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

    public Gebruiker getGebruiker() {
        return gebruiker;
    }

    public void setGebruiker(Gebruiker gebruiker) {
        this.gebruiker = gebruiker;
    }

}
