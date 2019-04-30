/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

/**
 *
 * @author Steve
 */
class Foto {
    
    
    private String Bestandsnaam;
    private String extensie;

    public Foto(String Bestandsnaam, String extensie) {
        this.Bestandsnaam = Bestandsnaam;
        this.extensie = extensie;
    }

    public String getBestandsnaam() {
        return Bestandsnaam;
    }

    public void setBestandsnaam(String Bestandsnaam) {
        this.Bestandsnaam = Bestandsnaam;
    }

    public String getExtensie() {
        return extensie;
    }

    public void setExtensie(String extensie) {
        this.extensie = extensie;
    }
    
    
    
}
