package domain.LesmateriaalModels;

import domain.GebruikerModels.AGebruiker;
import java.time.LocalTime;

public final class Raadpleging {
    private int id;
    private LocalTime tijdstip;
    private AGebruiker gebruiker;
    private Lesmateriaal lesmateriaal;

    public Raadpleging(LocalTime tijdstip, AGebruiker gebruiker, Lesmateriaal lesmateriaal) {
        setTijdstip(tijdstip);
        setGebruiker(gebruiker);
        setLesmateriaal(lesmateriaal);
    }
    
    public LocalTime getTijdstip() {
        return tijdstip;
    }

    public void setTijdstip(LocalTime tijdstip) {
        if(tijdstip == null)
            throw new IllegalArgumentException("Tijdstip kan geen lege waarde bevatten");
        if(tijdstip.isAfter(LocalTime.now()))
            throw new IllegalArgumentException("Tijdstip kan niet in de toekomst liggen");
        
        this.tijdstip = tijdstip;
    }

    public AGebruiker getGebruiker() {
        return gebruiker;
    }

    public void setGebruiker(AGebruiker gebruiker) {
        if(gebruiker == null)
            throw new IllegalArgumentException("Gebruiker kan geen lege waarde bevatten");
        
        this.gebruiker = gebruiker;
    }

    public Lesmateriaal getLesmateriaal() {
        return lesmateriaal;
    }

    public void setLesmateriaal(Lesmateriaal lesmateriaal) {
        if(lesmateriaal == null)
            throw new IllegalArgumentException("Lesmateriaal kan geen lege waarde bevatten");
        
        this.lesmateriaal = lesmateriaal;
    }
}
