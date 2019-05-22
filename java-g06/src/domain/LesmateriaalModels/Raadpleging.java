package domain.LesmateriaalModels;

import domain.GebruikerModels.AGebruiker;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Raadpleging {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "RaadplegingId")
    private int id;
    @Column(name = "Tijdstip")
    private LocalTime tijdstip;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Gebruikersnaam")
    private AGebruiker gebruiker;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LesmateriaalId")
    private Lesmateriaal lesmateriaal;

    public Raadpleging(LocalTime tijdstip, AGebruiker gebruiker, Lesmateriaal lesmateriaal) {
        setTijdstip(tijdstip);
        setGebruiker(gebruiker);
        setLesmateriaal(lesmateriaal);
    }
    
    public Raadpleging(){}
    
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
