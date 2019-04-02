package domein;

import java.util.Date;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GebruikerTest {
    private Gebruiker g1;
    
    @Before
    public void before() {
        g1 = new Gebruiker("Gebruiker1", "11111111111", new Date(2019, 3, 12), "John", "Doe", Geslacht.Man, new Date(2001, 8, 13), "Gent", "092432004", "0476585152", "gebruiker1@taijitan.be", "oudersGebruiker1@taijitan.be", new Adres("Belgie", "9000", "Gent", "Valentin Vaerwyckweg", "1"), 15, Gradatie.JuniDan, TypeGebruiker.Lid, new Lesformule(1, "Woensdag"));
    }
    
    @Test
    public void maakGebruiker_ValidGegevens() {
        Assert.assertEquals("Gebruiker", g1.getClass().getName());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_InvalidGegevens_throwsIllegalArgumentException() {
        Assert.assertThrows(IllegalArgumentException.class, () -> { 
            g1 = new Gebruiker(null, null, null, null, null, null, null, null, null, null, null, null, new Adres(null, null, null, null, null), 0, null, null, new Lesformule(0, null));
        });
    }
    
    @Test
    public void wijzigGebruiker_InvalidGebruikersnaam_throwsIllegalArgumentException() {
        
    }
}
