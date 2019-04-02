package domein;

import java.util.Date;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GebruikerTest {

    private Gebruiker g1;
    private String Gebruikersnaam, Rijksregisternummer, Naam, Voornaam, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders;
    private Date Geboortedatum, Inschrijvingsdatum;
    private Geslacht Geslacht;
    private Adres Adres;
    private Gradatie Gradatie;
    private TypeGebruiker TypeGebruiker;
    private Lesformule Lesformule;
    private int Punten;

    @Before
    public void before() {
        Gebruikersnaam = "Gebruiker1";
        Rijksregisternummer = "11111111111";
        Voornaam = "John";
        Naam = "Doe";
        Geboorteplaats = "Gent";
        Telefoonnummer = "092432004";
        Gsmnummer = "0476585152";
        Email = "gebruiker1@taijitan.be";
        Emailouders = "oudersGebruiker1@taijitan.be";
        Geboortedatum = new Date(2002, 3, 12);
        Inschrijvingsdatum = new Date(2019, 1, 10);
        Geslacht = Geslacht.Man;
        Adres = new Adres("Belgie", "9000", "Gent", "Valentin Vaerwyckweg", "1");
        Gradatie = Gradatie.JuichiDan;
        TypeGebruiker = TypeGebruiker.Lid;
        Lesformule = new Lesformule(1, "Woensdag");
        Punten = 15;
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Punten, Gradatie, TypeGebruiker, Lesformule);
    }

    // Constructor
    @Test
    public void maakGebruiker_ValidGegevens_MaaktGebruiker() {
        Assert.assertEquals("Gebruiker", g1.getClass().getSimpleName());
    }

    // Gebruikersnaam
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Gebruikersnaam_Empty_throwsIllegalArgumentException() {
        Gebruikersnaam = "";
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Punten, Gradatie, TypeGebruiker, Lesformule);
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Gebruikersnaam_Null_throwsIllegalArgumentException() {
        Gebruikersnaam = null;
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Punten, Gradatie, TypeGebruiker, Lesformule);
    }
    
    // Rijksregisternummer
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Rijksregisternummer_Empty_throwsIllegalArgumentException() {
        Rijksregisternummer = "";
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Punten, Gradatie, TypeGebruiker, Lesformule);
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Rijksregisternummer_Null_throwsIllegalArgumentException() {
        Rijksregisternummer = null;
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Punten, Gradatie, TypeGebruiker, Lesformule);
    }
    
    // Inschrijvingsdatum
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Inschrijvingsdatum_Null_throwsIllegalArgumentException() {
        Inschrijvingsdatum = null;
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Punten, Gradatie, TypeGebruiker, Lesformule);
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Inschrijvingsdatum_InToekomst_throwsIllegalArgumentException() {
        Date now = new Date();
        Inschrijvingsdatum = new Date(now.getYear() + 3);
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Punten, Gradatie, TypeGebruiker, Lesformule);
    }
    
    // Naam
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Naam_Empty_throwsIllegalArgumentException() {
        Naam = "";
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Punten, Gradatie, TypeGebruiker, Lesformule);
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Naam_Null_throwsIllegalArgumentException() {
        Naam = null;
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Punten, Gradatie, TypeGebruiker, Lesformule);
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Naam_TeVeelKarakters_throwsIllegalArgumentException() {
        Naam = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Punten, Gradatie, TypeGebruiker, Lesformule);
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Naam_BevatOngeldigeKarakters_throwsIllegalArgumentException() {
        Naam = "?doe";
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Punten, Gradatie, TypeGebruiker, Lesformule);
    }
    
    // Voornaam
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Voornaam_Empty_throwsIllegalArgumentException() {
        Voornaam = "";
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Punten, Gradatie, TypeGebruiker, Lesformule);
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Voornaam_Null_throwsIllegalArgumentException() {
        Voornaam = null;
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Punten, Gradatie, TypeGebruiker, Lesformule);
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Voornaam_TeVeelKarakters_throwsIllegalArgumentException() {
        Voornaam = "Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Punten, Gradatie, TypeGebruiker, Lesformule);
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Voornaam_BevatOngeldigeKarakters_throwsIllegalArgumentException() {
        Voornaam = "?john";
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Punten, Gradatie, TypeGebruiker, Lesformule);
    }
    
    // Geslacht
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Geslacht_Null_throwsIllegalArgumentException() {
        Geslacht = null;
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Punten, Gradatie, TypeGebruiker, Lesformule);
    }
    
    // Geboortedatum
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Geboortedatum_Null_throwsIllegalArgumentException() {
        Geboortedatum = null;
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Punten, Gradatie, TypeGebruiker, Lesformule);
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Geboortedatum_InToekomst_throwsIllegalArgumentException() {
        Date now = new Date();
        Geboortedatum = new Date(now.getYear() + 3);
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Punten, Gradatie, TypeGebruiker, Lesformule);
    }
    
    // Geboorteplaats
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Geboorteplaats_Empty_throwsIllegalArgumentException() {
        Geboorteplaats = "";
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Punten, Gradatie, TypeGebruiker, Lesformule);
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Geboorteplaats_Null_throwsIllegalArgumentException() {
        Geboorteplaats = null;
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Punten, Gradatie, TypeGebruiker, Lesformule);
    }
    
    // Telefoonnummer
    @Test
    public void maakGebruiker_Telefoonnummer_Empty_MaaktGebruiker() {
        Telefoonnummer = "";
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Punten, Gradatie, TypeGebruiker, Lesformule);
        Assert.assertEquals("Gebruiker", g1.getClass().getSimpleName());
    }
    @Test
    public void maakGebruiker_Telefoonnummer_Null_MaaktGebruiker() {
        Telefoonnummer = null;
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Punten, Gradatie, TypeGebruiker, Lesformule);
        Assert.assertEquals("Gebruiker", g1.getClass().getSimpleName());
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Telefoonnummer_OngeldigeWaarde_throwsIllegalArgumentException() {
        Telefoonnummer = "02222";
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Punten, Gradatie, TypeGebruiker, Lesformule);
    }
    
    // Gsmnummer
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Gsmnummer_Empty_throwsIllegalArgumentException() {
        Gsmnummer = "";
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Punten, Gradatie, TypeGebruiker, Lesformule);
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Gsmnummer_Null_throwsIllegalArgumentException() {
        Gsmnummer = null;
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Punten, Gradatie, TypeGebruiker, Lesformule);
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Gsmnummer_OngeldigeWaarde_throwsIllegalArgumentException() {
        Gsmnummer = "02222";
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Punten, Gradatie, TypeGebruiker, Lesformule);
    }
    
    // Email
    
    // Emailouders
    
    // Adres
    
    // Punten
    
    // Gradatie
    
    // TypeGebruiker
    
    // Lesformule
    
}
