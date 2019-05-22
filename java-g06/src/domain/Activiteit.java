/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.GebruikerModels.AGebruiker;
import domain.GebruikerModels.Adres;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
    @Column(name = "Emailadres")
    private String emailadres;
    @Column(name = "Telefoonnummer")
    private String telefoonnummer;
    @Column(name = "Contactpersoon")
    private String contactpersoon;
    @JoinColumn(name = "AdresId")
    @OneToOne(cascade = CascadeType.PERSIST)
    private Adres adres;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ActiviteitId")
    private List<ActiviteitDeelnemer> activiteitDeelnemers;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ActiviteitId")
    private List<ActiviteitBegeleider> activiteitBegeleiders;

    public Activiteit(String titel, String type, Calendar startDatum, Calendar eindDatum, int maxAantalDeelnemers) {
        setTitel(titel);
        setType(type);
        setStartDatum(startDatum);
        setEindDatum(eindDatum);
        setMaxAantalDeelnemers(maxAantalDeelnemers);
        activiteitDeelnemers = new ArrayList<>();
        activiteitBegeleiders = new ArrayList<>();
    }
    
    public Activiteit(String titel, String type, Calendar startDatum, Calendar eindDatum, int maxAantalDeelnemers, List<ActiviteitDeelnemer> deelnemers, List<ActiviteitBegeleider> begeleiders) {
        this(titel, type, startDatum, eindDatum, maxAantalDeelnemers);
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
        if (activiteitBegeleiders.size() <= 1) {
            throw new IllegalArgumentException("Er moet minstens één begeleider aanwezig zijn.");
        }
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
    public String getEmailadres() {
        return emailadres;
    }

    public void setEmailadres(String emailadres) {
        if (emailadres == null || emailadres.isBlank()) {
            throw new IllegalArgumentException("Email mag geen lege waarde bevatten.");
        }
        if (!Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]+$").matcher(emailadres).matches()) {
            throw new IllegalArgumentException("Ongeldig formaat voor Emailadres.");
        }
        this.emailadres = emailadres;
    }

    public String getTelefoonnummer() {
        return telefoonnummer;
    }

    public void setTelefoonnummer(String telefoonnummer) {
        if (telefoonnummer != null && !telefoonnummer.isBlank()
                && !Pattern.compile("((?:\\+|00)[17](?: |\\-)?|(?:\\+|00)[1-9]\\d{0,2}(?: |\\-)?|(?:\\+|00)1\\-\\d{3}(?: |\\-)?)?(0\\d|\\([0-9]{3}\\)|[1-9]{0,3})(?:((?: |\\-)[0-9]{2}){4}|((?:[0-9]{2}){4})|((?: |\\-)[0-9]{3}(?: |\\-)[0-9]{4})|([0-9]{7}))").matcher(telefoonnummer).matches()) {
            throw new IllegalArgumentException("Ongeldig formaat voor telefoonnummer.");
        }
        this.telefoonnummer = telefoonnummer;
    }

    public String getContactpersoon() {
        return contactpersoon;
    }

    public void setContactpersoon(String contactpersoon) {
        this.contactpersoon = contactpersoon;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

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
        if (maxAantalDeelnemers <= 1) {
            throw new IllegalArgumentException("Het maximum aantal deelnemers moet minstens 1 zijn.");
        }

        if (activiteitDeelnemers != null && maxAantalDeelnemers < activiteitDeelnemers.size()) {
            throw new IllegalArgumentException("Het maximum aantal deelnemers kan niet lager liggen dan het aantal werkelijke deelnemers");
        }

        this.maxAantalDeelnemers = maxAantalDeelnemers;
    }

    public List<ActiviteitDeelnemer> getActiviteitDeelnemers() {
        return activiteitDeelnemers;
    }

    public void setActiviteitDeelnemers(List<ActiviteitDeelnemer> activiteitDeelnemers) {
        if (activiteitDeelnemers.size() > maxAantalDeelnemers) {
            throw new IllegalArgumentException("Er kunnen niet meer leden ingeschreven zijn dan er maximum toegelaten zijn.");
        }
        this.activiteitDeelnemers = activiteitDeelnemers;
    }

    public List<ActiviteitBegeleider> getActiviteitBegeleiders() {
        return activiteitBegeleiders;
    }

    public void setActiviteitBegeleiders(List<ActiviteitBegeleider> activiteitBegeleiders) {
        if (activiteitBegeleiders.size() < 1) {
            throw new IllegalArgumentException("Er moet minstens één begeleider zijn.");
        }
        this.activiteitBegeleiders = activiteitBegeleiders;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAantalDeelnemers() {
        return activiteitDeelnemers.size();
    }
    
    public List<AGebruiker> getDeelnemers(){
        List<AGebruiker> gebruikers = new ArrayList<>();
        for(ActiviteitDeelnemer ad : activiteitDeelnemers){
            gebruikers.add(ad.getGebruiker());
        }
        return gebruikers;
    }
    
    public List<AGebruiker> getBegeleiders(){
        List<AGebruiker> gebruikers = new ArrayList<>();
        for(ActiviteitBegeleider ab : activiteitBegeleiders){
            gebruikers.add(ab.getGebruiker());
        }
        return gebruikers;
    }
    


}
