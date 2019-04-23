/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activiteit;

import Gebruiker.Models.Gebruiker;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Steve
 */
public class Activiteit {

    private String titel;
    private String type;
    private Calendar startDatum;
    private Calendar eindDatum;
    private int maxAantalDeelnemers;
    private List<Gebruiker> deelnemers;
    private List<Gebruiker> begeleiders;

    public Activiteit(String titel, String type, Calendar startDatum, Calendar eindDatum, int maxAantalDeelnemers, List<Gebruiker> deelnemers, List<Gebruiker> begeleiders) {
        setTitel(titel);
        setType(type);        
        setStartDatum(startDatum);
        setEindDatum(eindDatum);
        setMaxAantalDeelnemers(maxAantalDeelnemers);
        setDeelnemers(deelnemers);
        setBegeleiders(begeleiders);
    }
    
    public boolean isVolzet(){
        return deelnemers.size() == maxAantalDeelnemers;
    }
    
    public void addDeelnemer(Gebruiker deelnemer){
        if(isVolzet())
            throw new IllegalArgumentException("De activiteit is al volzet.");
        deelnemers.add(deelnemer);
    }
    
    public void deleteDeelnemer(Gebruiker deelnemer){
        deelnemers.remove(deelnemer);
    }
    
    public void addBegeleider(Gebruiker begeleider){
        begeleiders.add(begeleider);
    }
    
    public void deleteBegeleider(Gebruiker begeleider){
        if(begeleiders.size() < 2){
            throw new IllegalArgumentException("Er moet minstens één begeleider zijn.");
        }
        begeleiders.remove(begeleider);
    }

    /*
        Getters en setters
     */
    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        if (type.length() < 1) {
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
        if (eindDatum != null && startDatum.before(eindDatum)) {
            throw new IllegalArgumentException("De einddatum mag niet eerder plaatsvinden dan de startdatum.");
        }
        this.startDatum = startDatum;
    }

    public Calendar getEindDatum() {
        return eindDatum;
    }

    public void setEindDatum(Calendar eindDatum) {
        if (startDatum != null && startDatum.before(eindDatum)) {
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
        this.maxAantalDeelnemers = maxAantalDeelnemers;
    }

    public List<Gebruiker> getDeelnemers() {
        return deelnemers;
    }

    public void setDeelnemers(List<Gebruiker> deelnemers) {
        if (deelnemers.size() > maxAantalDeelnemers) 
            throw new IllegalArgumentException("Er kunnen niet meer leden ingeschreven zijn dan er maximum toegelaten zijn.");        
        this.deelnemers = deelnemers;
    }

    public List<Gebruiker> getBegeleiders() {
        return begeleiders;
    }

    public void setBegeleiders(List<Gebruiker> begeleiders) {
        if (begeleiders.size() < 1) 
            throw new IllegalArgumentException("Er moet minstens één begeleider zijn.");        
        this.begeleiders = begeleiders;
    }

}
