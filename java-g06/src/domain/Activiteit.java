/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

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
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ActiviteitId")
    private List<ActiviteitDeelnemer> activiteitDeelnemers;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ActiviteitId")
    private List<ActiviteitBegeleider> activiteitBegeleiders;

    public Activiteit(String titel, String type, Calendar startDatum, Calendar eindDatum, int maxAantalDeelnemers, List<Gebruiker> deelnemers, List<Gebruiker> begeleiders) {
        setTitel(titel);
        setType(type);
        setStartDatum(startDatum);
        setEindDatum(eindDatum);
        setMaxAantalDeelnemers(maxAantalDeelnemers);

        setDeelnemers(deelnemers);
        setBegeleiders(begeleiders);

        activiteitDeelnemers = new ArrayList<>();
        activiteitBegeleiders = new ArrayList<>();
    }

    public Activiteit() {

    }

    public boolean isVolzet() {
        return activiteitDeelnemers.size() == maxAantalDeelnemers;
    }

    public void addDeelnemer(Gebruiker deelnemer) {
        if (isVolzet()) {
            throw new IllegalArgumentException("De activiteit is al volzet.");
        }
        activiteitDeelnemers.add(new ActiviteitDeelnemer(this, deelnemer));
    }

    public void deleteDeelnemer(Gebruiker deelnemer) {
        ActiviteitDeelnemer actd = activiteitDeelnemers.stream().filter(ad -> ad.getActiviteit().getId() == this.id && ad.getGebruiker().getGebruikersnaam().equals(deelnemer.getGebruikersnaam())).findFirst().orElse(null);
        activiteitDeelnemers.remove(actd);
    }

    public void addBegeleider(Gebruiker begeleider) {
        activiteitBegeleiders.add(new ActiviteitBegeleider(this, begeleider));
    }

    public void deleteBegeleider(Gebruiker begeleider) {
        if (activiteitBegeleiders.size() < 2) {
            throw new IllegalArgumentException("Er moet minstens één begeleider zijn.");
        }
        ActiviteitBegeleider actb = activiteitBegeleiders.stream().filter(ab -> ab.getActiviteit().getId() == this.id && ab.getGebruiker().getGebruikersnaam().equals(begeleider.getGebruikersnaam())).findFirst().orElse(null);
        activiteitBegeleiders.remove(actb);
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

    /*
    public List<Gebruiker> getDeelnemers() {
        return deelnemers;
    }
     */
    public void setDeelnemers(List<Gebruiker> deelnemers) {
        if (deelnemers.size() > maxAantalDeelnemers) {
            throw new IllegalArgumentException("Er kunnen niet meer leden ingeschreven zijn dan er maximum toegelaten zijn.");
        }
        // this.deelnemers = deelnemers;
    }

    /*
    public List<Gebruiker> getBegeleiders() {
        return begeleiders;
    }
     */
    public void setBegeleiders(List<Gebruiker> begeleiders) {
        /*
        if (begeleiders.size() < 1) {
            throw new IllegalArgumentException("Er moet minstens één begeleider zijn.");
        }
         */
        //this.begeleiders = begeleiders;
    }

    public List<ActiviteitDeelnemer> getActiviteitDeelnemers() {
        return activiteitDeelnemers;
    }

    public void setActiviteitDeelnemers(List<ActiviteitDeelnemer> activiteitDeelnemers) {
        this.activiteitDeelnemers = activiteitDeelnemers;
    }

    public List<ActiviteitBegeleider> getActiviteitBegeleiders() {
        return activiteitBegeleiders;
    }

    public void setActiviteitBegeleiders(List<ActiviteitBegeleider> activiteitBegeleiders) {
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
