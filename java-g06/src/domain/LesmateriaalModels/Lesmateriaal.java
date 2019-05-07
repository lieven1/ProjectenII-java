package domain.LesmateriaalModels;

import domain.GebruikerModels.Gradatie;
import java.util.ArrayList;
import java.util.List;

public final class Lesmateriaal {
    private int id;
    private String naam;
    private String beschrijving;
    private Gradatie graad;
    private Thema thema;
    private String videoId;
    private List<Foto> fotos;
    private List<Raadpleging> raadplegingen;

    public Lesmateriaal(String naam, String beschrijving, Gradatie graad, Thema thema, String videoId, List<Foto> fotos, List<Raadpleging> raadplegingen) {
        setNaam(naam);
        setBeschrijving(beschrijving);
        setGraad(graad);
        setThema(thema);
        setVideoId(videoId);
        setFotos(fotos);
        setRaadplegingen(raadplegingen);
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        if(naam == null || naam.isBlank())
            throw new IllegalArgumentException("Naam mag geen lege waarde bevatten.");
        
        this.naam = naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public Gradatie getGraad() {
        return graad;
    }

    public void setGraad(Gradatie graad) {
        if(graad == null)
            throw new IllegalArgumentException("Graad mag geen lege waarde bevatten.");
        
        this.graad = graad;
    }

    public Thema getThema() {
        return thema;
    }

    public void setThema(Thema thema) {
        if(thema == null)
            throw new IllegalArgumentException("Thema mag geen lege waarde bevatten.");
        
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
        if(fotos == null)
            fotos = new ArrayList<>();
        
        this.fotos = fotos;
    }

    public List<Raadpleging> getRaadplegingen() {
        return raadplegingen;
    }

    public void setRaadplegingen(List<Raadpleging> raadplegingen) {
        if(raadplegingen == null)
            raadplegingen = new ArrayList<>();
        
        this.raadplegingen = raadplegingen;
    }
}
