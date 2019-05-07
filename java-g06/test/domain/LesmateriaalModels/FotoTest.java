package domain.LesmateriaalModels;

import org.junit.Assert;
import org.junit.Test;

public class FotoTest {
    private Foto f1;
    private String bestandsnaam, extensie;
    
    public FotoTest() {
        bestandsnaam = "foto";
        extensie = "jpg";
    }
    
    // Constructor
    @Test
    public void maakFoto_ValidGegevens_maaktFoto() {
        f1 = new Foto(bestandsnaam, extensie);
        Assert.assertEquals(bestandsnaam, f1.getBestandsnaam());
        Assert.assertEquals(extensie, f1.getExtensie());
    }
    
    // Bestandsnaam
    @Test
    public void setBestandsnaam_Valid_Wijzigt() {
        f1 = new Foto(bestandsnaam, extensie);
        f1.setBestandsnaam("anotherimage");
        Assert.assertEquals("anotherimage", f1.getBestandsnaam());
    }
    @Test(expected = IllegalArgumentException.class)
    public void setBestandsnaam_Empty_throwsIllegalArgumentException() {
        f1 = new Foto(bestandsnaam, extensie);
        f1.setBestandsnaam("");
    }
    @Test(expected = IllegalArgumentException.class)
    public void setBestandsnaam_Null_throwsIllegalArgumentException() {
        f1 = new Foto(bestandsnaam, extensie);
        f1.setBestandsnaam(null);
    }
    
    // Extensie
    @Test
    public void setExtensie_Valid_Wijzigt() {
        f1 = new Foto(bestandsnaam, extensie);
        f1.setExtensie("png");
        Assert.assertEquals("png", f1.getExtensie());
    }
    @Test(expected = IllegalArgumentException.class)
    public void setExtensie_Empty_throwsIllegalArgumentException() {
        f1 = new Foto(bestandsnaam, extensie);
        f1.setExtensie("");
    }
    @Test(expected = IllegalArgumentException.class)
    public void setExtensie_Null_throwsIllegalArgumentException() {
        f1 = new Foto(bestandsnaam, extensie);
        f1.setExtensie(null);
    }
}
