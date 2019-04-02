package domein;

import java.util.Date;
import org.junit.Assert;
import org.junit.Test;

public class ProefGebruikerTest {
    private ProefGebruiker p1;
    private String Gebruikersnaam, Naam, Voornaam, Telefoonnummer, Email;
    private Date InschrijvingsDatum;

    public ProefGebruikerTest() {
        Gebruikersnaam = "Proefgebruiker";
        Naam = "Doe";
        Voornaam = "John";
        Telefoonnummer = "092432004";
        Email = "gebruiker1@taijitan.be";
        InschrijvingsDatum = new Date(2019, 1, 10);
    }

    // Constructor
    @Test
    public void maakAdres_ValidGegevens_MaaktAdres() {
        p1 = new ProefGebruiker(Gebruikersnaam, InschrijvingsDatum, Naam, Voornaam, Telefoonnummer, Email);
        Assert.assertEquals("ProefGebruiker", p1.getClass().getSimpleName());
        Assert.assertEquals(Gebruikersnaam, p1.getGebruikersnaam());
        Assert.assertEquals(Naam, p1.getNaam());
        Assert.assertEquals(Voornaam, p1.getVoornaam());
        Assert.assertEquals(Telefoonnummer, p1.getTelefoonnummer());
        Assert.assertEquals(Email, p1.getEmail());
        Assert.assertEquals(InschrijvingsDatum, p1.getInschrijvingsDatum());
        Assert.assertEquals(TypeGebruiker.Proefgebruiker, p1.getType());
    }
    
    // Gebruikersnaam
    @Test(expected = IllegalArgumentException.class)
    public void maakProefGebruiker_Gebruikersnaam_Empty_throwsIllegalArgumentException() {
        Gebruikersnaam = "";
        p1 = new ProefGebruiker(Gebruikersnaam, InschrijvingsDatum, Naam, Voornaam, Telefoonnummer, Email);
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakProefGebruiker_Gebruikersnaam_Null_throwsIllegalArgumentException() {
        Gebruikersnaam = null;
        p1 = new ProefGebruiker(Gebruikersnaam, InschrijvingsDatum, Naam, Voornaam, Telefoonnummer, Email);
    }
    
    // Inschrijvingsdatum
    @Test(expected = IllegalArgumentException.class)
    public void maakProefGebruiker_Inschrijvingsdatum_Null_throwsIllegalArgumentException() {
        InschrijvingsDatum = null;
        p1 = new ProefGebruiker(Gebruikersnaam, InschrijvingsDatum, Naam, Voornaam, Telefoonnummer, Email);
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakProefGebruiker_Inschrijvingsdatum_InToekomst_throwsIllegalArgumentException() {
        Date now = new Date();
        InschrijvingsDatum = new Date(now.getYear() + 3);
        p1 = new ProefGebruiker(Gebruikersnaam, InschrijvingsDatum, Naam, Voornaam, Telefoonnummer, Email);
    }
    
    // Naam
    @Test(expected = IllegalArgumentException.class)
    public void maakProefGebruiker_Naam_Empty_throwsIllegalArgumentException() {
        Naam = "";
        p1 = new ProefGebruiker(Gebruikersnaam, InschrijvingsDatum, Naam, Voornaam, Telefoonnummer, Email);
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakProefGebruiker_Naam_Null_throwsIllegalArgumentException() {
        Naam = null;
        p1 = new ProefGebruiker(Gebruikersnaam, InschrijvingsDatum, Naam, Voornaam, Telefoonnummer, Email);
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakProefGebruiker_Naam_TeVeelKarakters_throwsIllegalArgumentException() {
        Naam = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        p1 = new ProefGebruiker(Gebruikersnaam, InschrijvingsDatum, Naam, Voornaam, Telefoonnummer, Email);
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakProefGebruiker_Naam_BevatOngeldigeKarakters_throwsIllegalArgumentException() {
        Naam = "?doe";
        p1 = new ProefGebruiker(Gebruikersnaam, InschrijvingsDatum, Naam, Voornaam, Telefoonnummer, Email);
    }
    
    // Voornaam
    @Test(expected = IllegalArgumentException.class)
    public void maakProefGebruiker_Voornaam_Empty_throwsIllegalArgumentException() {
        Voornaam = "";
        p1 = new ProefGebruiker(Gebruikersnaam, InschrijvingsDatum, Naam, Voornaam, Telefoonnummer, Email);
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakProefGebruiker_Voornaam_Null_throwsIllegalArgumentException() {
        Voornaam = null;
        p1 = new ProefGebruiker(Gebruikersnaam, InschrijvingsDatum, Naam, Voornaam, Telefoonnummer, Email);
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakProefGebruiker_Voornaam_TeVeelKarakters_throwsIllegalArgumentException() {
        Voornaam = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        p1 = new ProefGebruiker(Gebruikersnaam, InschrijvingsDatum, Naam, Voornaam, Telefoonnummer, Email);
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakProefGebruiker_Voornaam_BevatOngeldigeKarakters_throwsIllegalArgumentException() {
        Voornaam = "?doe";
        p1 = new ProefGebruiker(Gebruikersnaam, InschrijvingsDatum, Naam, Voornaam, Telefoonnummer, Email);
    }
    
    // Telefoonnummer
    @Test(expected = IllegalArgumentException.class)
    public void maakProefGebruiker_Telefoonnummer_Empty_throwsIllegalArgumentException() {
        Telefoonnummer = "";
        p1 = new ProefGebruiker(Gebruikersnaam, InschrijvingsDatum, Naam, Voornaam, Telefoonnummer, Email);
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakProefGebruiker_Telefoonnummer_Null_throwsIllegalArgumentException() {
        Telefoonnummer = null;
        p1 = new ProefGebruiker(Gebruikersnaam, InschrijvingsDatum, Naam, Voornaam, Telefoonnummer, Email);
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakProefGebruiker_Telefoonnummer_OngeldigeWaarde_throwsIllegalArgumentException() {
        Telefoonnummer = "02222";
        p1 = new ProefGebruiker(Gebruikersnaam, InschrijvingsDatum, Naam, Voornaam, Telefoonnummer, Email);
    }
    
    // Email
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Email_Empty_throwsIllegalArgumentException() {
        Email = "";
        p1 = new ProefGebruiker(Gebruikersnaam, InschrijvingsDatum, Naam, Voornaam, Telefoonnummer, Email);
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Email_Null_throwsIllegalArgumentException() {
        Email = null;
        p1 = new ProefGebruiker(Gebruikersnaam, InschrijvingsDatum, Naam, Voornaam, Telefoonnummer, Email);
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Email_OngeldigeWaarde_throwsIllegalArgumentException() {
        Email = "Emailadres@emailadres";
        p1 = new ProefGebruiker(Gebruikersnaam, InschrijvingsDatum, Naam, Voornaam, Telefoonnummer, Email);
    }
}
