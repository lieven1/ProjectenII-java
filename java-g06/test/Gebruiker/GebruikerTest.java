package Gebruiker;

import domain.GebruikerModels.TypeGebruiker;
import domain.GebruikerModels.Lesformule;
import domain.GebruikerModels.Gradatie;
import domain.GebruikerModels.Gebruiker;
import domain.GebruikerModels.Geslacht;
import domain.GebruikerModels.Adres;
import java.util.Calendar;
import static java.util.Calendar.YEAR;
import java.util.Date;
import java.util.GregorianCalendar;
import org.junit.Assert;
import org.junit.Test;

public class GebruikerTest {
    private Gebruiker g1;
    private String Gebruikersnaam, Rijksregisternummer, Naam, Voornaam, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders;
    private Calendar Geboortedatum, Inschrijvingsdatum;
    private Geslacht Geslacht;
    private Adres Adres;
    private Gradatie Gradatie;
    private TypeGebruiker TypeGebruiker;
    private Lesformule Lesformule;

    public GebruikerTest() {
        Gebruikersnaam = "Gebruiker1";
        Rijksregisternummer = "11111111111";
        Voornaam = "John";
        Naam = "Doe";
        Geboorteplaats = "Gent";
        Telefoonnummer = "092432004";
        Gsmnummer = "0476585152";
        Email = "gebruiker1@taijitan.be";
        Emailouders = "oudersGebruiker1@taijitan.be";
        Geboortedatum = new GregorianCalendar(2002, 3, 12);
        Inschrijvingsdatum = new GregorianCalendar(2019, 1, 10);
        Geslacht = Geslacht.Man;
        Adres = new Adres("Belgie", "9000", "Gent", "Valentin Vaerwyckweg", "1");
        Gradatie = Gradatie.JuichiDan;
        TypeGebruiker = TypeGebruiker.Lid;
        Lesformule = new Lesformule(1, "Woensdag");
    }

    private Calendar getCalendar(int year, int month, int date){
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, date);
        return cal;
    }

    private Calendar getCalendar(int year){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        return cal;
    }

    // Constructor
    @Test
    public void maakGebruiker_ValidGegevens_MaaktGebruiker() {        
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Gradatie, TypeGebruiker, Lesformule);
        Assert.assertEquals("Gebruiker", g1.getClass().getSimpleName());
        Assert.assertEquals(Gebruikersnaam, g1.getGebruikersnaam());
        Assert.assertEquals(Rijksregisternummer, g1.getRijksregisternummer());
        Assert.assertEquals(Voornaam, g1.getVoornaam());
        Assert.assertEquals(Naam, g1.getNaam());
        Assert.assertEquals(Geboorteplaats, g1.getGeboorteplaats());
        Assert.assertEquals(Telefoonnummer, g1.getTelefoonnummer());
        Assert.assertEquals(Gsmnummer, g1.getGsmnummer());
        Assert.assertEquals(Email, g1.getEmail());
        Assert.assertEquals(Emailouders, g1.getEmailOuders());
        Assert.assertEquals(Geboortedatum, g1.getGeboorteDatum());
        Assert.assertEquals(Inschrijvingsdatum, g1.getInschrijvingsDatum());
        Assert.assertEquals(Geslacht, g1.getGeslacht());
        Assert.assertEquals(Adres, g1.getAdres());
        Assert.assertEquals(Gradatie, g1.getGraad());
        Assert.assertEquals(TypeGebruiker, g1.getType());
        Assert.assertEquals(Lesformule, g1.getLesformule());
    }

    // Gebruikersnaam
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Gebruikersnaam_Empty_throwsIllegalArgumentException() {
        Gebruikersnaam = "";
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Gradatie, TypeGebruiker, Lesformule);
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Gebruikersnaam_Null_throwsIllegalArgumentException() {
        Gebruikersnaam = null;
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Gradatie, TypeGebruiker, Lesformule);
    }
    
    // Rijksregisternummer
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Rijksregisternummer_Empty_throwsIllegalArgumentException() {
        Rijksregisternummer = "";
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Gradatie, TypeGebruiker, Lesformule);
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Rijksregisternummer_Null_throwsIllegalArgumentException() {
        Rijksregisternummer = null;
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Gradatie, TypeGebruiker, Lesformule);
    }
    
    // Inschrijvingsdatum
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Inschrijvingsdatum_Null_throwsIllegalArgumentException() {
        Inschrijvingsdatum = null;
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Gradatie, TypeGebruiker, Lesformule);
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Inschrijvingsdatum_InToekomst_throwsIllegalArgumentException() {
        Inschrijvingsdatum = new GregorianCalendar();
        Inschrijvingsdatum.add(YEAR, 3);
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Gradatie, TypeGebruiker, Lesformule);
    }
    
    // Naam
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Naam_Empty_throwsIllegalArgumentException() {
        Naam = "";
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Gradatie, TypeGebruiker, Lesformule);
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Naam_Null_throwsIllegalArgumentException() {
        Naam = null;
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Gradatie, TypeGebruiker, Lesformule);
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Naam_TeVeelKarakters_throwsIllegalArgumentException() {
        Naam = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Gradatie, TypeGebruiker, Lesformule);
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Naam_BevatOngeldigeKarakters_throwsIllegalArgumentException() {
        Naam = "?doe";
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Gradatie, TypeGebruiker, Lesformule);
    }
    
    // Voornaam
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Voornaam_Empty_throwsIllegalArgumentException() {
        Voornaam = "";
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Gradatie, TypeGebruiker, Lesformule);
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Voornaam_Null_throwsIllegalArgumentException() {
        Voornaam = null;
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Gradatie, TypeGebruiker, Lesformule);
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Voornaam_TeVeelKarakters_throwsIllegalArgumentException() {
        Voornaam = "Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Gradatie, TypeGebruiker, Lesformule);
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Voornaam_BevatOngeldigeKarakters_throwsIllegalArgumentException() {
        Voornaam = "?john";
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Gradatie, TypeGebruiker, Lesformule);
    }
    
    // Geslacht
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Geslacht_Null_throwsIllegalArgumentException() {
        Geslacht = null;
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Gradatie, TypeGebruiker, Lesformule);
    }
    
    // Geboortedatum
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Geboortedatum_Null_throwsIllegalArgumentException() {
        Geboortedatum = null;
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Gradatie, TypeGebruiker, Lesformule);
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Geboortedatum_InToekomst_throwsIllegalArgumentException() {
        Geboortedatum = new GregorianCalendar();
        Geboortedatum.add(YEAR, 3);
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Gradatie, TypeGebruiker, Lesformule);
    }
    
    // Geboorteplaats
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Geboorteplaats_Empty_throwsIllegalArgumentException() {
        Geboorteplaats = "";
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Gradatie, TypeGebruiker, Lesformule);
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Geboorteplaats_Null_throwsIllegalArgumentException() {
        Geboorteplaats = null;
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Gradatie, TypeGebruiker, Lesformule);
    }
    
    // Telefoonnummer
    @Test
    public void maakGebruiker_Telefoonnummer_Empty_MaaktGebruiker() {
        Telefoonnummer = "";
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Gradatie, TypeGebruiker, Lesformule);
        Assert.assertEquals(Telefoonnummer, g1.getTelefoonnummer());
    }
    @Test
    public void maakGebruiker_Telefoonnummer_Null_MaaktGebruiker() {
        Telefoonnummer = null;
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Gradatie, TypeGebruiker, Lesformule);
        Assert.assertEquals(Telefoonnummer, g1.getTelefoonnummer());
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Telefoonnummer_OngeldigeWaarde_throwsIllegalArgumentException() {
        Telefoonnummer = "02222";
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Gradatie, TypeGebruiker, Lesformule);
    }
    
    // Gsmnummer
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Gsmnummer_Empty_throwsIllegalArgumentException() {
        Gsmnummer = "";
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Gradatie, TypeGebruiker, Lesformule);
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Gsmnummer_Null_throwsIllegalArgumentException() {
        Gsmnummer = null;
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Gradatie, TypeGebruiker, Lesformule);
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Gsmnummer_OngeldigeWaarde_throwsIllegalArgumentException() {
        Gsmnummer = "02222";
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Gradatie, TypeGebruiker, Lesformule);
    }
    
    // Email
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Email_Empty_throwsIllegalArgumentException() {
        Email = "";
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Gradatie, TypeGebruiker, Lesformule);
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Email_Null_throwsIllegalArgumentException() {
        Email = null;
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Gradatie, TypeGebruiker, Lesformule);
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Email_OngeldigeWaarde_throwsIllegalArgumentException() {
        Email = "Emailadres@emailadres";
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Gradatie, TypeGebruiker, Lesformule);
    }
    
    // Emailouders
    @Test
    public void maakGebruiker_Emailouders_Empty_MaaktGebruiker() {
        Emailouders = "";
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Gradatie, TypeGebruiker, Lesformule);
        Assert.assertEquals(Emailouders, g1.getEmailOuders());
    }
    @Test
    public void maakGebruiker_Emailouders_Null_MaaktGebruiker() {
        Emailouders = null;
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Gradatie, TypeGebruiker, Lesformule);
        Assert.assertEquals(Emailouders, g1.getEmailOuders());
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Emailouders_OngeldigeWaarde_throwsIllegalArgumentException() {
        Emailouders = "Emailadres@emailadres";
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Gradatie, TypeGebruiker, Lesformule);
    }
    
    // Adres
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_Adres_Null_throwsIllegalArgumentException() {
        Adres = null;
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Gradatie, TypeGebruiker, Lesformule);
    }
   
    // Gradatie
    @Test
    public void maakGebruiker_Gradatie_Null_MaaktGebruiker_RokkuKyu() {
        Gradatie = null;
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Gradatie, TypeGebruiker, Lesformule);
        Assert.assertEquals(Gradatie.RokkuKyu, g1.getGraad());
    }
    
    // TypeGebruiker
    @Test
    public void maakGebruiker_TypeGebruiker_Null_MaaktGebruiker_Lid() {
        TypeGebruiker = null;
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Gradatie, TypeGebruiker, Lesformule);
        Assert.assertEquals(TypeGebruiker.Lid, g1.getType());
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakGebruiker_TypeGebruiker_ProefGebruiker_throwsIllegalArgumentException() {
        TypeGebruiker = TypeGebruiker.Proefgebruiker;
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Gradatie, TypeGebruiker, Lesformule);
    }
    
    // Lesformule
    @Test
    public void maakGebruiker_Lesformule_Null_MaaktGebruiker() {
        Lesformule = null;
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Gradatie, TypeGebruiker, Lesformule);
        Assert.assertEquals(Lesformule, g1.getLesformule());
    }
    
    // Wijzig Gegevens
    @Test
    public void WijzigGegevens_ValidGegevens_WijzigtGebruiker() {        
        g1 = new Gebruiker(Gebruikersnaam, Rijksregisternummer, Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Adres, Gradatie, TypeGebruiker, Lesformule);
        Voornaam = "Patricia";
        Naam = "S. Goldman-Rakic";
        Geboorteplaats = "Brussel";
        Telefoonnummer = "092433333";
        Gsmnummer = "0478821109";
        Email = "nieuwEmailadres@hogent.be";
        Emailouders = null;
        Geboortedatum = getCalendar(1998, 10, 10);
        Inschrijvingsdatum = getCalendar(2014, 8, 20);
        Geslacht = Geslacht.Vrouw;
        Adres = new Adres("Belgie", "9200", "Dendermonde", "Zuidlaan", "3B");
        Gradatie = Gradatie.JuichiDan;
        TypeGebruiker = TypeGebruiker.Beheerder;
        Lesformule = new Lesformule(2, "Donderdag");
        
        g1.wijzigGegevens(Inschrijvingsdatum, Naam, Voornaam, Geslacht, Geboortedatum, Geboorteplaats, Telefoonnummer, Gsmnummer, Email, Emailouders, Gradatie, TypeGebruiker, Lesformule, Adres.getLand(), Adres.getPostcode(), Adres.getStad(), Adres.getStraat(), Adres.getNummer());
        
        Assert.assertEquals("Gebruiker", g1.getClass().getSimpleName());
        Assert.assertEquals(Gebruikersnaam, g1.getGebruikersnaam());
        Assert.assertEquals(Rijksregisternummer, g1.getRijksregisternummer());
        Assert.assertEquals(Voornaam, g1.getVoornaam());
        Assert.assertEquals(Naam, g1.getNaam());
        Assert.assertEquals(Geboorteplaats, g1.getGeboorteplaats());
        Assert.assertEquals(Telefoonnummer, g1.getTelefoonnummer());
        Assert.assertEquals(Gsmnummer, g1.getGsmnummer());
        Assert.assertEquals(Email, g1.getEmail());
        Assert.assertEquals(Emailouders, g1.getEmailOuders());
        Assert.assertEquals(Geboortedatum, g1.getGeboorteDatum());
        Assert.assertEquals(Inschrijvingsdatum, g1.getInschrijvingsDatum());
        Assert.assertEquals(Geslacht, g1.getGeslacht());
        Assert.assertEquals(Adres.getLand(), g1.getAdres().getLand());
        Assert.assertEquals(Adres.getPostcode(), g1.getAdres().getPostcode());
        Assert.assertEquals(Adres.getStad(), g1.getAdres().getStad());
        Assert.assertEquals(Adres.getStraat(), g1.getAdres().getStraat());
        Assert.assertEquals(Adres.getNummer(), g1.getAdres().getNummer());
        Assert.assertEquals(Gradatie, g1.getGraad());
        Assert.assertEquals(TypeGebruiker, g1.getType());
        Assert.assertEquals(Lesformule, g1.getLesformule());
    }
}
