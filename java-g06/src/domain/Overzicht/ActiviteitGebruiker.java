/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.Overzicht;

/**
 *
 * @author boris
 */
public class ActiviteitGebruiker {

    private String naam;
    private String voornaam;
    private String telefoon;
    private String email;

    public ActiviteitGebruiker(String naam, String voornaam, String telefoon, String email) {
        this.naam = naam;
        this.voornaam = voornaam;
        this.telefoon = telefoon;
        this.email = email;
    }

    public String getNaam() {
        return naam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getTelefoon() {
        return telefoon;
    }

    public String getEmail() {
        return email;
    }

}
