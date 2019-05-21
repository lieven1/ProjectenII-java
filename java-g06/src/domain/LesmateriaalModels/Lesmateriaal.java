package domain.LesmateriaalModels;

import domain.GebruikerModels.Gradatie;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Lesmateriaal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LesmateriaalId")
    private int id;
    @Column(name = "Naam")
    private String naam;
    @Column(name = "Beschrijving")
    private String beschrijving;
    @Column(name = "Graad")
    @Enumerated(EnumType.ORDINAL)
    private Gradatie graad;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ThemaId")
    private Thema thema;
    @Column(name = "VideoId")
    private String videoId;

    @ManyToMany
    @JoinTable(
            name = "FotoLesmateriaal",
            joinColumns = @JoinColumn(name = "LesmateriaalId"),
            inverseJoinColumns = @JoinColumn(name = "FotoId"))
    private List<Foto> fotos;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "LesmateriaalId", nullable = false)
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

    public Lesmateriaal() {
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        if (naam == null || naam.isBlank()) {
            throw new IllegalArgumentException("Naam mag geen lege waarde bevatten.");
        }

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
        if (graad == null) {
            throw new IllegalArgumentException("Graad mag geen lege waarde bevatten.");
        }

        this.graad = graad;
    }

    public Thema getThema() {
        return thema;
    }

    public void setThema(Thema thema) {
        if (thema == null) {
            throw new IllegalArgumentException("Thema mag geen lege waarde bevatten.");
        }

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
        if (fotos == null) {
            fotos = new ArrayList<>();
        }

        this.fotos = fotos;
    }

    public List<Raadpleging> getRaadplegingen() {
        return raadplegingen;
    }

    public void setRaadplegingen(List<Raadpleging> raadplegingen) {
        if (raadplegingen == null) {
            raadplegingen = new ArrayList<>();
        }

        this.raadplegingen = raadplegingen;
    }

    public int getAantalraadplegingen() {
        return raadplegingen.size();
    }
}
