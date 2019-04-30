/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.GebruikerModels.Gebruiker;
import java.util.Calendar;

/**
 *
 * @author Steve
 */
class Raadpleging {
    
    private Calendar Tijdstip;
    private Gebruiker gebruiker;
    private Lesmateriaal lesmateriaal;

    public Raadpleging(Calendar Tijdstip, Gebruiker gebruiker, Lesmateriaal lesmateriaal) {
        this.Tijdstip = Tijdstip;
        this.gebruiker = gebruiker;
        this.lesmateriaal = lesmateriaal;
    }

    public Calendar getTijdstip() {
        return Tijdstip;
    }

    public void setTijdstip(Calendar Tijdstip) {
        this.Tijdstip = Tijdstip;
    }

    public Gebruiker getGebruiker() {
        return gebruiker;
    }

    public void setGebruiker(Gebruiker gebruiker) {
        this.gebruiker = gebruiker;
    }

    public Lesmateriaal getLesmateriaal() {
        return lesmateriaal;
    }

    public void setLesmateriaal(Lesmateriaal lesmateriaal) {
        this.lesmateriaal = lesmateriaal;
    }
    
    
}
