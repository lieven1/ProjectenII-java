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
public class RaadplegingGegevens {

    private String naamGebruiker;
    private String voornaamGebruiker;
    private String datumRaadpleging;

    public RaadplegingGegevens(String naamGebruiker, String voornaamGebruiker, String datumRaadpleging) {
        this.naamGebruiker = naamGebruiker;
        this.voornaamGebruiker = voornaamGebruiker;
        this.datumRaadpleging = datumRaadpleging;
    }

    public String getNaamGebruiker() {
        return naamGebruiker;
    }

    public String getVoornaamGebruiker() {
        return voornaamGebruiker;
    }

    public String getDatumRaadpleging() {
        return datumRaadpleging;
    }

}
