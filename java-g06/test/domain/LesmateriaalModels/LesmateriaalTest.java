package domain.LesmateriaalModels;

import domain.GebruikerModels.Gradatie;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class LesmateriaalTest {
    private Lesmateriaal l1;
    private String naam, beschrijving, videoId;
    private Gradatie graad;
    private Thema thema;
    private List<Foto> fotos;
    private List<Raadpleging> raadplegingen;
    
    public LesmateriaalTest(){
        naam = "oefeningtitel";
        beschrijving = "Lorem ipsum dolor sit amet, has reque suscipiantur ad, an duo hinc habeo omnes, ex eam eirmod probatus. Vis cu dicant vocibus urbanitas, nostro facilisi eu nam, vim an aeque adolescens. Nec consequat moderatius ex. Eruditi graecis blandit vix eu, vel aperiri praesent id, ancillae scribentur ex eos.Lorem ipsum dolor sit amet, has reque suscipiantur ad, an duo hinc habeo omnes, ex eam eirmod probatus. Vis cu dicant vocibus urbanitas, nostro facilisi eu nam, vim an aeque adolescens. Nec consequat moderatius ex. Eruditi graecis blandit vix eu, vel aperiri praesent id, ancillae scribentur ex eos.Lorem ipsum dolor sit amet, has reque suscipiantur ad, an duo hinc habeo omnes, ex eam eirmod probatus. Vis cu dicant vocibus urbanitas, nostro facilisi eu nam, vim an aeque adolescens. Nec consequat moderatius ex. Eruditi graecis blandit vix eu, vel aperiri praesent id, ancillae scribentur ex eos.";
        videoId = "pD3T7WNsw6k";
        thema = new Thema("Sprongen");
        graad = Gradatie.IchiKyu;
        fotos = new ArrayList<>();
        raadplegingen = new ArrayList<>();
    }
    
    // Constructor
    @Test
    public void maakLesmateriaal_ValidGegevens_MaaktLesmateriaal() {
        l1 = new Lesmateriaal(naam, beschrijving, graad, thema, videoId, fotos, raadplegingen);
        Assert.assertEquals(naam, l1.getNaam());
        Assert.assertEquals(beschrijving, l1.getBeschrijving());
        Assert.assertEquals(graad, l1.getGraad());
        Assert.assertEquals(thema, l1.getThema());
        Assert.assertEquals(videoId, l1.getVideoId());
        Assert.assertEquals(fotos, l1.getFotos());
        Assert.assertEquals(raadplegingen, l1.getRaadplegingen());
    }
    
    // Naam
    @Test
    public void setNaam_Valid_Wijzigt() {
        l1 = new Lesmateriaal(naam, beschrijving, graad, thema, videoId, fotos, raadplegingen);
        l1.setNaam("anothername");
        Assert.assertEquals("anothername", l1.getNaam());
    }
    @Test(expected = IllegalArgumentException.class)
    public void setNaam_Empty_throwsIllegalArgumentException() {
        l1 = new Lesmateriaal(naam, beschrijving, graad, thema, videoId, fotos, raadplegingen);
        l1.setNaam("");
    }
    @Test(expected = IllegalArgumentException.class)
    public void setNaam_Null_throwsIllegalArgumentException() {
        l1 = new Lesmateriaal(naam, beschrijving, graad, thema, videoId, fotos, raadplegingen);
        l1.setNaam(null);
    }
    
    // Beschrijving
    @Test
    public void setBeschrijving_Valid_Wijzigt() {
        l1 = new Lesmateriaal(naam, beschrijving, graad, thema, videoId, fotos, raadplegingen);
        l1.setBeschrijving("anotherbeschrijving");
        Assert.assertEquals("anotherbeschrijving", l1.getBeschrijving());
    }
    @Test
    public void setBeschrijving_Empty_Wijzigt() {
        l1 = new Lesmateriaal(naam, beschrijving, graad, thema, videoId, fotos, raadplegingen);
        l1.setBeschrijving("");
        Assert.assertEquals("", l1.getBeschrijving());
    }
    @Test
    public void setBeschrijving_Null_Wijzigt() {
        l1 = new Lesmateriaal(naam, beschrijving, graad, thema, videoId, fotos, raadplegingen);
        l1.setBeschrijving(null);
        Assert.assertEquals(null, l1.getBeschrijving());    
    }
    
    // Graad
    @Test
    public void setGraad_Valid_Wijzigt() {
        l1 = new Lesmateriaal(naam, beschrijving, graad, thema, videoId, fotos, raadplegingen);
        l1.setGraad(Gradatie.RokkuKyu);
        Assert.assertEquals(Gradatie.RokkuKyu, l1.getGraad());
    }
    @Test(expected = IllegalArgumentException.class)
    public void setGraad_Null_throwsIllegalArgumentException() {
        l1 = new Lesmateriaal(naam, beschrijving, graad, thema, videoId, fotos, raadplegingen);
        l1.setGraad(null);
    }
    
    // Thema
    @Test
    public void setThema_Valid_Wijzigt() {
        Thema newThema = new Thema("thema");
        l1 = new Lesmateriaal(naam, beschrijving, graad, thema, videoId, fotos, raadplegingen);
        l1.setThema(newThema);
        Assert.assertEquals(newThema, l1.getThema());
    }
    @Test(expected = IllegalArgumentException.class)
    public void setThema_Null_throwsIllegalArgumentException() {
        l1 = new Lesmateriaal(naam, beschrijving, graad, thema, videoId, fotos, raadplegingen);
        l1.setThema(null);
    }
    
    // VideoId
    @Test
    public void setVideoId_Valid_Wijzigt() {
        l1 = new Lesmateriaal(naam, beschrijving, graad, thema, videoId, fotos, raadplegingen);
        l1.setVideoId("anotherVideoId");
        Assert.assertEquals("anotherVideoId", l1.getVideoId());
    }
    @Test
    public void setVideoId_Empty_Wijzigt() {
        l1 = new Lesmateriaal(naam, beschrijving, graad, thema, videoId, fotos, raadplegingen);
        l1.setVideoId("");
        Assert.assertEquals("", l1.getVideoId());
    }
    @Test
    public void setVideoId_Null_Wijzigt() {
        l1 = new Lesmateriaal(naam, beschrijving, graad, thema, videoId, fotos, raadplegingen);
        l1.setVideoId(null);
        Assert.assertEquals(null, l1.getVideoId());    
    }
}
