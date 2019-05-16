/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.Overzicht;

import java.util.regex.Pattern;

/**
 *
 * @author Boris
 */
public class Aanwezigheid {

    private String naam, voornaam, aanwezig, ingeschreven, lesformule, email, telefoon, gradatie;

    public Aanwezigheid(String naam, String voornaam, String aanwezig, String ingeschreven, String lesformule, String email, String telefoon, String gradatie) {
        this.naam = naam;
        this.voornaam = voornaam;
        this.aanwezig = aanwezig;
        this.ingeschreven = ingeschreven;
        this.lesformule = lesformule;
        this.email = email;
        this.telefoon = telefoon;
        this.gradatie = gradatie;
    }

    public String getNaam() {
        return naam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getAanwezig() {
        return aanwezig;
    }

    public String getIngeschreven() {
        return ingeschreven;
    }

    public String getLesformule() {
        return lesformule;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefoon() {
        return telefoon;
    }

    public String getGradatie() {
        return gradatie;
    }

    public void setNaam(String _naam) {
//        if(_naam == null || _naam.isBlank())
//            throw new IllegalArgumentException("Naam mag geen lege waarde bevatten.");
//        if(_naam.length() > 50)
//            throw new IllegalArgumentException("Naam mag niet bestaan uit meer dan 50 karakters.");
//        if(! Pattern.compile("^([A-Za-z]{1}[a-z ,.'-]+)+$").matcher(_naam).matches())
//            throw new IllegalArgumentException(String.format("%s%n%s%n%s%n%s", 
//                    "Ongeldige waarde voor naam.",
//                    "Naam begint met een letter.",
//                    "Naam kan de tekens , . ' en - bevatten.",
//                    "Naam kan uit meerdere woorden bestaan."));
        this.naam = _naam;
    }

    public void setVoornaam(String _voornaam) {
//        if(_voornaam == null || _voornaam.isBlank())
//            throw new IllegalArgumentException("Voornaam mag geen lege waarde bevatten.");
//        if(_voornaam.length() > 50)
//            throw new IllegalArgumentException("Voornaam mag niet bestaan uit meer dan 50 karakters.");
//        if(! Pattern.compile("^([A-Z]{1}[a-z ,.'-]+)+$").matcher(_voornaam).matches())
//            throw new IllegalArgumentException(String.format("%s%n%s%n%s%n%s", 
//                    "Ongeldige waarde voor voornaam.",
//                    "Voornaam begint met een hoofdletter.",
//                    "Voornaam kan de tekens , . ' en - bevatten.",
//                    "Voornaam kan uit meerdere woorden bestaan."));
        this.voornaam = _voornaam;
    }

    public void setAanwezig(String aanwezig) {
        this.aanwezig = aanwezig;
    }

    public void setIngeschreven(String ingeschreven) {
        this.ingeschreven = ingeschreven;
    }

    public void setLesformule(String lesformule) {
        this.lesformule = lesformule;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefoon(String telefoon) {
        this.telefoon = telefoon;
    }

    public void setGradatie(String gradatie) {
        this.gradatie = gradatie;
    }

}
