/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.GebruikerModels.AGebruiker;
import domain.GebruikerModels.Gebruiker;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.eclipse.persistence.jpa.config.Cascade;

/**
 *
 * @author Steve
 */
@Entity(name = "Activiteit")
@Table(name = "Activiteit")
public class Activiteit {

    @Id
    @Column(name = "ActiviteitId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "Naam")
    private String titel;
    @Column(name = "Type")
    private String type;
    @Column(name = "StartDatum")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar startDatum;
    @Column(name = "EindDatum")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar eindDatum;
    @Column(name = "MaxAantalDeelnemers")
    private int maxAantalDeelnemers;
    @OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name = "ActiviteitId")
    private List<ActiviteitDeelnemer> activiteitDeelnemers;
    @OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name = "ActiviteitId")
    private List<ActiviteitBegeleider> activiteitBegeleiders;

    public Activiteit(String titel, String type, Calendar startDatum, Calendar eindDatum, int maxAantalDeelnemers, List<ActiviteitDeelnemer> deelnemers, List<ActiviteitBegeleider> begeleiders) {
        setTitel(titel);
        setType(type);
        setStartDatum(startDatum);
        setEindDatum(eindDatum);
        setMaxAantalDeelnemers(maxAantalDeelnemers);
        setActiviteitDeelnemers(deelnemers);
        setActiviteitBegeleiders(begeleiders);
    }

    public Activiteit() {

    }

    public boolean isVolzet() {
        return activiteitDeelnemers.size() == maxAantalDeelnemers;
    }

    public void addDeelnemer(AGebruiker deelnemer) {
        if (isVolzet()) {
            throw new IllegalArgumentException("De activiteit is al volzet.");
        }
        activiteitDeelnemers.add(new ActiviteitDeelnemer(this, deelnemer));
    }

    public void deleteDeelnemer(AGebruiker deelnemer) {
        List<ActiviteitDeelnemer> deelnemers = new ArrayList<>();
        for (ActiviteitDeelnemer acd : activiteitDeelnemers) {
            if (!(acd.getGebruiker().getGebruikersnaam().equals(deelnemer.getGebruikersnaam()) && acd.getActiviteit().getId() == this.id)) {
                deelnemers.add(acd);
            }
        }
        this.activiteitDeelnemers = deelnemers;
    }

    public void addBegeleider(AGebruiker begeleider) {
        activiteitBegeleiders.add(new ActiviteitBegeleider(this, begeleider));
    }

    public void deleteBegeleider(AGebruiker begeleider) {
        List<ActiviteitBegeleider> begeleiders = new ArrayList<>();
        for (ActiviteitBegeleider acb : activiteitBegeleiders) {
            if (!(acb.getGebruiker().getGebruikersnaam().equals(begeleider.getGebruikersnaam()) && acb.getActiviteit().getId() == this.id)) {
                begeleiders.add(acb);
            }
        }
        this.activiteitBegeleiders = begeleiders;
    }

    /*
        Getters en setters
     */
    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        if (titel.length() < 1) {
            throw new IllegalArgumentException("Een type is verplicht.");
        }
        this.titel = titel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type.length() < 1) {
            throw new IllegalArgumentException("Een type is verplicht.");
        }
        this.type = type;
    }

    public Calendar getStartDatum() {
        return startDatum;
    }

    public void setStartDatum(Calendar startDatum) {
        if (eindDatum != null && startDatum.after(eindDatum)) {
            throw new IllegalArgumentException("De einddatum mag niet eerder plaatsvinden dan de startdatum.");
        }
        this.startDatum = startDatum;
    }

    public Calendar getEindDatum() {
        return eindDatum;
    }

    public void setEindDatum(Calendar eindDatum) {
        if (startDatum != null && startDatum.after(eindDatum)) {
            throw new IllegalArgumentException("De einddatum mag niet eerder plaatsvinden dan de startdatum.");
        }
        this.eindDatum = eindDatum;
    }

    public int getMaxAantalDeelnemers() {
        return maxAantalDeelnemers;
    }

    public void setMaxAantalDeelnemers(int maxAantalDeelnemers) {
        if (maxAantalDeelnemers < 1) {
            throw new IllegalArgumentException("Het maximum aantal deelnemers moet minstens 0 zijn.");
        }
        /*
        if (deelnemers != null && maxAantalDeelnemers < deelnemers.size()) {
            throw new IllegalArgumentException("Het maximum aantal deelnemers kan niet lager liggen dan het aantal werkelijke deelnemers");
        }
         */
        this.maxAantalDeelnemers = maxAantalDeelnemers;
    }

    public List<ActiviteitDeelnemer> getActiviteitDeelnemers() {
        return activiteitDeelnemers;
    }

    public void setActiviteitDeelnemers(List<ActiviteitDeelnemer> activiteitDeelnemers) {
        /*
        if (deelnemers.size() > maxAantalDeelnemers) {
            throw new IllegalArgumentException("Er kunnen niet meer leden ingeschreven zijn dan er maximum toegelaten zijn.");
        }
         */
        this.activiteitDeelnemers = activiteitDeelnemers;
    }

    public List<ActiviteitBegeleider> getActiviteitBegeleiders() {
        return activiteitBegeleiders;
    }

    public void setActiviteitBegeleiders(List<ActiviteitBegeleider> activiteitBegeleiders) {
        /*
        if (begeleiders.size() < 1) {
            throw new IllegalArgumentException("Er moet minstens één begeleider zijn.");
        }
         */
        this.activiteitBegeleiders = activiteitBegeleiders;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAantalDeelnemers() {
        return 0;
    }

}
