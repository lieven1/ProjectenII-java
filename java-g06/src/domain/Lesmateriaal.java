package domain;

import java.util.List;

/**
 *
 * @author Steve
 */
public class Lesmateriaal {
    
    private String naam;
    private String beschrijving;
    private Gradatie gradatie;
    private Thema thema;
    private String videoId;
    private List<Foto> fotos;
    private List<Raadpleging> raadplegingen;

    public Lesmateriaal(String naam, String beschrijving, Gradatie gradatie, Thema thema, String videoId, List<Foto> fotos, List<Raadpleging> raadplegingen) {
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.gradatie = gradatie;
        this.thema = thema;
        this.videoId = videoId;
        this.fotos = fotos;
        this.raadplegingen = raadplegingen;
    }
    
    
    
    /*
    Getters en setters
    */

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public Gradatie getGradatie() {
        return gradatie;
    }

    public void setGradatie(Gradatie gradatie) {
        this.gradatie = gradatie;
    }

    public Thema getThema() {
        return thema;
    }

    public void setThema(Thema thema) {
        this.thema = thema;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public List<Foto> getFotos() {
        return fotos;
    }

    public void setFotos(List<Foto> fotos) {
        this.fotos = fotos;
    }

    public List<Raadpleging> getRaadplegingen() {
        return raadplegingen;
    }

    public void setRaadplegingen(List<Raadpleging> raadplegingen) {
        this.raadplegingen = raadplegingen;
    }
    
    
    
    
    
  
    
}
