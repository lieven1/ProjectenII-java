package domein;

import org.junit.Assert;
import org.junit.Test;

public class AdresTest {
    private Adres a1;
    private int adresId;
    private String Land, Postcode, Stad, Straat, Nummer;

    public AdresTest() {
        adresId = 1;
        Land = "Belgie";
        Postcode = "9000";
        Stad = "Gent";
        Straat = "Valentin Vaerwyckweg";
        Nummer = "1";
    }

    // Constructor
    @Test
    public void maakAdres_ValidGegevens_MaaktAdres() {
        a1 = new Adres(adresId, Land, Postcode, Stad, Straat, Nummer);
        Assert.assertEquals("Adres", a1.getClass().getSimpleName());
        Assert.assertEquals(adresId, a1.getAdresId());
        Assert.assertEquals(Land, a1.getLand());
        Assert.assertEquals(Postcode, a1.getPostcode());
        Assert.assertEquals(Stad, a1.getStad());
        Assert.assertEquals(Straat, a1.getStraat());
        Assert.assertEquals(Nummer, a1.getNummer());
    }
    @Test
    public void maakAdres_ValidGegevens_MaaktAdresZonderId() {
        a1 = new Adres(Land, Postcode, Stad, Straat, Nummer);
        Assert.assertEquals("Adres", a1.getClass().getSimpleName());
        Assert.assertEquals(Land, a1.getLand());
        Assert.assertEquals(Postcode, a1.getPostcode());
        Assert.assertEquals(Stad, a1.getStad());
        Assert.assertEquals(Straat, a1.getStraat());
        Assert.assertEquals(Nummer, a1.getNummer());
    }

    // Land
    @Test(expected = IllegalArgumentException.class)
    public void maakAdres_Land_Empty_throwsIllegalArgumentException() {
        Land = "";
        a1 = new Adres(Land, Postcode, Stad, Straat, Nummer);
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakAdres_Land_Null_throwsIllegalArgumentException() {
        Land = null;
        a1 = new Adres(Land, Postcode, Stad, Straat, Nummer);
    }

    // Postcode
    @Test(expected = IllegalArgumentException.class)
    public void maakAdres_Postcode_Empty_throwsIllegalArgumentException() {
        Postcode = "";
        a1 = new Adres(Land, Postcode, Stad, Straat, Nummer);
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakAdres_Postcode_Null_throwsIllegalArgumentException() {
        Postcode = null;
        a1 = new Adres(Land, Postcode, Stad, Straat, Nummer);
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakAdres_Postcode_OngeldigeWaarde_throwsIllegalArgumentException() {
        Postcode = "88888888888888";
        a1 = new Adres(Land, Postcode, Stad, Straat, Nummer);
    }
    
    // Stad
    @Test(expected = IllegalArgumentException.class)
    public void maakAdres_Stad_Empty_throwsIllegalArgumentException() {
        Stad = "";
        a1 = new Adres(Land, Postcode, Stad, Straat, Nummer);
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakAdres_Stad_Null_throwsIllegalArgumentException() {
        Stad = null;
        a1 = new Adres(Land, Postcode, Stad, Straat, Nummer);
    }
    
    // Straat
    @Test(expected = IllegalArgumentException.class)
    public void maakAdres_Straat_Empty_throwsIllegalArgumentException() {
        Straat = "";
        a1 = new Adres(Land, Postcode, Stad, Straat, Nummer);
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakAdres_Straat_Null_throwsIllegalArgumentException() {
        Straat = null;
        a1 = new Adres(Land, Postcode, Stad, Straat, Nummer);
    }
    
    // Nummer
    @Test(expected = IllegalArgumentException.class)
    public void maakAdres_Nummer_Empty_throwsIllegalArgumentException() {
        Nummer = "";
        a1 = new Adres(Land, Postcode, Stad, Straat, Nummer);
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakAdres_Nummer_Null_throwsIllegalArgumentException() {
        Nummer = null;
        a1 = new Adres(Land, Postcode, Stad, Straat, Nummer);
    }
    
    // WijzigAdres
    
    @Test
    public void wijzigAdres_ValidGegevens_WijzigtAdres() {
        a1 = new Adres(adresId, Land, Postcode, Stad, Straat, Nummer);
        Land = "Nederland";
        Postcode = "1200";
        Stad = "Heidene";
        Straat = "Hoofdstraat";
        Nummer = "15B";
        
        a1.wijzigAdres(Land, Postcode, Stad, Straat, Nummer);
        Assert.assertEquals("Adres", a1.getClass().getSimpleName());
        Assert.assertEquals(Land, a1.getLand());
        Assert.assertEquals(Postcode, a1.getPostcode());
        Assert.assertEquals(Stad, a1.getStad());
        Assert.assertEquals(Straat, a1.getStraat());
        Assert.assertEquals(Nummer, a1.getNummer());
    }
}
