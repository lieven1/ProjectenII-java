package domain.GebruikerModels;

import java.util.Calendar;
import java.util.regex.Pattern;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("2")
public class Gebruiker extends AGebruiker {
    // Declarations
    @Column(name = "Rijksregisternummer")
    private String rijksregisternummer;
    @Column(name = "Geboorteplaats")
    private String geboorteplaats;
    @Column(name = "Gsmnummer")
    private String gsmnummer;
    @Column(name = "EmailOuders")
    private String emailOuders;
    @Column(name = "Geboortedatum")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar geboorteDatum;
    @Column(name = "Geslacht")
    @Enumerated(EnumType.ORDINAL)
    private Geslacht geslacht;
    @JoinColumn(name = "AdresId")
    @OneToOne(cascade = CascadeType.PERSIST)
    private Adres adres;
    @Column(name = "Gradatie")    
    @Enumerated(EnumType.ORDINAL)
    private Gradatie graad;
    @Transient
    private Lesformule lesformule;

    
    // Constructors
    public Gebruiker(String gebruikersnaam, String rijksregisternummer, Calendar inschrijvingsdatum, String naam, String voornaam, Geslacht geslacht, Calendar geboortedatum, String geboorteplaats, String telefoonnummer, String gsmnummer, String email, String emailOuders, Adres adres, Gradatie graad, TypeGebruiker type, Lesformule lesformule){
        if(gebruikersnaam == null || gebruikersnaam.isBlank())
            throw new IllegalArgumentException("Gebruikersnaam mag geen lege waarde bevatten.");
        if(rijksregisternummer == null || rijksregisternummer.isBlank())
            throw new IllegalArgumentException("Rijksregisternummer mag geen lege waarde bevatten.");
        if(! Pattern.compile("^\\d{11}$").matcher(rijksregisternummer).matches())
            throw new IllegalArgumentException("Ongeldige waarde voor rijksregisternummer");
        // Toevoegen geboortedatum check (6cijfers)
        // Toevoegen GeslachtCheck: even=vrouw (3cijfers)
        // Toevoegen rijksregisternummercheck modulo 97 (2cijfers)
        
        this.gebruikersnaam = gebruikersnaam;
        this.rijksregisternummer = rijksregisternummer;
        super.setInschrijvingsDatum(inschrijvingsdatum);
        super.setNaam(naam);
        super.setVoornaam(voornaam);
        this.setGeslacht(geslacht);
        this.setGeboorteDatum(geboortedatum);
        this.setGeboorteplaats(geboorteplaats);
        super.setTelefoonnummer(telefoonnummer);
        this.setGsmnummer(gsmnummer);
        super.setEmail(email);
        this.setEmailOuders(emailOuders);
        this.setAdres(adres);
        this.setGraad(graad);
        this.setTypeG(type);
        this.setLesformule(lesformule);
    }
    public Gebruiker(){}
  
    // Getters 
    public String getRijksregisternummer() {
        return rijksregisternummer;
    }

    public String getGeboorteplaats() {
        return geboorteplaats;
    }

    public String getGsmnummer() {
        return gsmnummer;
    }

    public String getEmailOuders() {
        return emailOuders;
    }

    public Calendar getGeboorteDatum() {
        return geboorteDatum;
    }
    
    public Geslacht getGeslacht() {
        return geslacht;
    }

    public Adres getAdres() {
        return adres;
    }

    public Gradatie getGraad() {
        return graad;
    }

    public Lesformule getLesformule() {
        return lesformule;
    }
    
    // Setters
    private void setGeboorteplaats(String _geboorteplaats) {
        if(_geboorteplaats == null || _geboorteplaats.isBlank())
            throw new IllegalArgumentException("Geboorteplaats mag geen lege waarde bevatten.");
        
        this.geboorteplaats = _geboorteplaats;
    }

    private void setGsmnummer(String _gsmnummer) {
        if(_gsmnummer == null || _gsmnummer.isBlank())
            throw new IllegalArgumentException("Gsmnummer mag geen lege waarde bevatten.");
        if(! Pattern.compile("((?:\\+|00)[17](?: |\\-)?|(?:\\+|00)[1-9]\\d{0,2}(?: |\\-)?|(?:\\+|00)1\\-\\d{3}(?: |\\-)?)?(0\\d|\\([0-9]{3}\\)|[1-9]{0,3})(?:((?: |\\-)[0-9]{2}){4}|((?:[0-9]{2}){4})|((?: |\\-)[0-9]{3}(?: |\\-)[0-9]{4})|([0-9]{7}))").matcher(_gsmnummer).matches())
            throw new IllegalArgumentException("Ongeldig formaat voor gsmnummer.");
        
        this.gsmnummer = _gsmnummer;
    }
    
    private void setEmailOuders(String _emailOuders) {
        if(_emailOuders != null && !_emailOuders.isBlank()){
            if(! Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]+$").matcher(_emailOuders).matches())
                throw new IllegalArgumentException("Ongeldig formaat voor Emailadres.");
        }
        
        this.emailOuders = _emailOuders;
    }

    private void setGeboorteDatum(Calendar _geboorteDatum) {
        if(_geboorteDatum == null)
            throw new IllegalArgumentException("Geboortedatum mag geen lege waarde bevatten.");
        if(_geboorteDatum.after(Calendar.getInstance()))
            throw new IllegalArgumentException("Geboortedatum mag niet in de toekomst liggen.");
        
        this.geboorteDatum = _geboorteDatum;
    }

    private void setGeslacht(Geslacht _geslacht) {
        if(_geslacht == null)
            throw new IllegalArgumentException("Geslacht mag geen lege waarde bevatten.");
        
        this.geslacht = _geslacht;
    }

    private void setAdres(Adres _adres) {
        if(_adres == null)
            throw new IllegalArgumentException("Adres mag geen lege waarde bevatten.");
        
        this.adres = _adres;
    }

    private void setGraad(Gradatie _graad) {
        if(_graad == null)
            _graad = Gradatie.RokkuKyu;
        
        this.graad = _graad;
    }

    @Override
    protected void setType(TypeGebruiker _type) {
        this.setTypeG(_type);
    }
    private void setTypeG(TypeGebruiker _type) {
        if(_type == null)
            _type = TypeGebruiker.Lid;
        if(_type == TypeGebruiker.Proefgebruiker)
            throw new IllegalArgumentException("Een bestaande gebruiker kan geen proefgebruiker worden.");
        
        this.type = _type;
    }

    private void setLesformule(Lesformule _lesformule) {
        this.lesformule = _lesformule;
    }
    
    // Methods
    public void wijzigGegevens(Calendar inschrijvingsdatum, String naam, String voornaam, Geslacht geslacht, Calendar geboortedatum, String geboorteplaats, String telefoonnummer, String gsmnummer, String email, String emailOuders, Gradatie graad, TypeGebruiker type, Lesformule lesformule, String land, String postcode, String stad, String straat, String nummer){
        // Gebruikersnaam en Rijksregisternummer kunnen niet gewijzigd worden.
        this.setInschrijvingsDatum(inschrijvingsdatum);
        this.setNaam(naam);
        this.setVoornaam(voornaam);
        this.setGeslacht(geslacht);
        this.setGeboorteDatum(geboortedatum);
        this.setGeboorteplaats(geboorteplaats);
        this.setTelefoonnummer(telefoonnummer);
        this.setGsmnummer(gsmnummer);
        this.setEmail(email);
        this.setEmailOuders(emailOuders);
        this.setGraad(graad);
        this.setType(type);
        this.setLesformule(lesformule);
        this.adres.wijzigAdres(land, postcode, stad, straat, nummer);
    }
}
