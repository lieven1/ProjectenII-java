package domein;

import java.util.Date;
import java.util.regex.Pattern;

public class Gebruiker {
    // Declarations
    private final String _gebruikersnaam, _rijksregisternummer; 
    private String _naam, _voornaam, _geboorteplaats, 
        _telefoonnummer, _gsmnummer, _email, _emailOuders;
    private Date _inschrijvingsDatum, _geboorteDatum;
    private int _punten;
    private Geslacht _geslacht;
    private Adres _adres;
    private Gradatie _graad;
    private TypeGebruiker _type;
    private Lesformule _lesformule;
    
    // Constructors
    public Gebruiker(String gebruikersnaam, String rijksregisternummer, Date inschrijvingsdatum, String naam, String voornaam, Geslacht geslacht, Date geboortedatum, String geboorteplaats, String telefoonnummer, String gsmnummer, String email, String emailOuders, Adres adres, int punten, Gradatie graad, TypeGebruiker type, Lesformule lesformule){
        if(gebruikersnaam == null || gebruikersnaam.isBlank())
            throw new IllegalArgumentException("Gebruikersnaam mag geen lege waarde bevatten.");
        if(rijksregisternummer == null || rijksregisternummer.isBlank())
            throw new IllegalArgumentException("Rijksregisternummer mag geen lege waarde bevatten.");
        if(! Pattern.compile("^\\d{11}$").matcher(rijksregisternummer).matches())
            throw new IllegalArgumentException("Ongeldige waarde voor rijksregisternummer");
        
        this._gebruikersnaam = gebruikersnaam;
        this._rijksregisternummer = rijksregisternummer;
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
        this.setAdres(adres);
        this.setPunten(punten);
        this.setGraad(graad);
        this.setType(type);
        this.setLesformule(lesformule);
    }
    
    // Getters 
    public String getGebruikersnaam() {
        return _gebruikersnaam;
    }

    public String getRijksregisternummer() {
        return _rijksregisternummer;
    }

    public String getNaam() {
        return _naam;
    }

    public String getVoornaam() {
        return _voornaam;
    }

    public String getGeboorteplaats() {
        return _geboorteplaats;
    }

    public String getTelefoonnummer() {
        return _telefoonnummer;
    }

    public String getGsmnummer() {
        return _gsmnummer;
    }

    public String getEmail() {
        return _email;
    }

    public String getEmailOuders() {
        return _emailOuders;
    }

    public Date getInschrijvingsDatum() {
        return _inschrijvingsDatum;
    }

    public Date getGeboorteDatum() {
        return _geboorteDatum;
    }

    public int getPunten() {
        return _punten;
    }

    public Geslacht getGeslacht() {
        return _geslacht;
    }

    public Adres getAdres() {
        return _adres;
    }

    public Gradatie getGraad() {
        return _graad;
    }

    public TypeGebruiker getType() {
        return _type;
    }

    public Lesformule getLesformule() {
        return _lesformule;
    }
    
    // Setters
    private void setNaam(String _naam) {
        if(_naam == null || _naam.isBlank())
            throw new IllegalArgumentException("Naam mag geen lege waarde bevatten.");
        if(_naam.length() > 50)
            throw new IllegalArgumentException("Naam mag niet bestaan uit meer dan 50 karakters.");
        if(! Pattern.compile("^([A-Za-z]{1}[a-z ,.'-]+)+$").matcher(_naam).matches())
            throw new IllegalArgumentException(String.format("%s%n%s%n%s%n%s", 
                    "Ongeldige waarde voor naam.",
                    "Naam begint met een letter.",
                    "Naam kan de tekens , . ' en - bevatten.",
                    "Naam kan uit meerdere woorden bestaan."));
        
        this._naam = _naam;
    }

    private void setVoornaam(String _voornaam) {
        if(_voornaam == null || _voornaam.isBlank())
            throw new IllegalArgumentException("Voornaam mag geen lege waarde bevatten.");
        if(_voornaam.length() > 50)
            throw new IllegalArgumentException("Voornaam mag niet bestaan uit meer dan 50 karakters.");
        if(! Pattern.compile("^([A-Z]{1}[a-z ,.'-]+)+$").matcher(_voornaam).matches())
            throw new IllegalArgumentException(String.format("%s%n%s%n%s%n%s", 
                    "Ongeldige waarde voor voornaam.",
                    "Voornaam begint met een hoofdletter.",
                    "Voornaam kan de tekens , . ' en - bevatten.",
                    "Voornaam kan uit meerdere woorden bestaan."));
        
        this._voornaam = _voornaam;
    }

    private void setGeboorteplaats(String _geboorteplaats) {
        if(_geboorteplaats == null || _geboorteplaats.isBlank())
            throw new IllegalArgumentException("Geboorteplaats mag geen lege waarde bevatten.");
        
        this._geboorteplaats = _geboorteplaats;
    }

    private void setTelefoonnummer(String _telefoonnummer) {
        if(_telefoonnummer != null && !_telefoonnummer.isBlank()){
            if(! Pattern.compile("((?:\\+|00)[17](?: |\\-)?|(?:\\+|00)[1-9]\\d{0,2}(?: |\\-)?|(?:\\+|00)1\\-\\d{3}(?: |\\-)?)?(0\\d|\\([0-9]{3}\\)|[1-9]{0,3})(?:((?: |\\-)[0-9]{2}){4}|((?:[0-9]{2}){4})|((?: |\\-)[0-9]{3}(?: |\\-)[0-9]{4})|([0-9]{7}))").matcher(_telefoonnummer).matches())
                throw new IllegalArgumentException("Ongeldig formaat voor telefoonnummer.");
        }
        
        this._telefoonnummer = _telefoonnummer;
    }

    private void setGsmnummer(String _gsmnummer) {
        if(_gsmnummer == null || _gsmnummer.isBlank())
            throw new IllegalArgumentException("Gsmnummer mag geen lege waarde bevatten.");
        if(! Pattern.compile("((?:\\+|00)[17](?: |\\-)?|(?:\\+|00)[1-9]\\d{0,2}(?: |\\-)?|(?:\\+|00)1\\-\\d{3}(?: |\\-)?)?(0\\d|\\([0-9]{3}\\)|[1-9]{0,3})(?:((?: |\\-)[0-9]{2}){4}|((?:[0-9]{2}){4})|((?: |\\-)[0-9]{3}(?: |\\-)[0-9]{4})|([0-9]{7}))").matcher(_gsmnummer).matches())
            throw new IllegalArgumentException("Ongeldig formaat voor gsmnummer.");
        
        this._gsmnummer = _gsmnummer;
    }

    private void setEmail(String _email) {
        if(_email == null || _email.isBlank())
            throw new IllegalArgumentException("Email mag geen lege waarde bevatten.");
        if(! Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]+$").matcher(_email).matches())
            throw new IllegalArgumentException("Ongeldig formaat voor Emailadres.");
        
        this._email = _email;
    }

    private void setEmailOuders(String _emailOuders) {
        if(_emailOuders != null && !_emailOuders.isBlank()){
            if(! Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]+$").matcher(_emailOuders).matches())
                throw new IllegalArgumentException("Ongeldig formaat voor Emailadres.");
        }
        
        this._emailOuders = _emailOuders;
    }

    private void setInschrijvingsDatum(Date _inschrijvingsDatum) {
        if(_inschrijvingsDatum == null)
            throw new IllegalArgumentException("Inschrijvingsdatum mag geen lege waarde bevatten.");
        if(_inschrijvingsDatum.compareTo(new Date()) < 0)
            throw new IllegalArgumentException("Inschrijvingsdatum mag niet in de toekomst liggen.");
        
        this._inschrijvingsDatum = _inschrijvingsDatum;
    }

    private void setGeboorteDatum(Date _geboorteDatum) {
        if(_geboorteDatum == null)
            throw new IllegalArgumentException("Geboortedatum mag geen lege waarde bevatten.");
        if(_geboorteDatum.compareTo(new Date()) < 0)
            throw new IllegalArgumentException("Geboortedatum mag niet in de toekomst liggen.");
        
        this._geboorteDatum = _geboorteDatum;
    }

    private void setPunten(int _punten) {
        if(_punten < 0)
            throw new IllegalArgumentException("Punten kan geen negatieve warde bevatten");
        
        this._punten = _punten;
    }

    private void setGeslacht(Geslacht _geslacht) {
        if(_geslacht == null)
            throw new IllegalArgumentException("Geslacht mag geen lege waarde bevatten.");
        
        this._geslacht = _geslacht;
    }

    private void setAdres(Adres _adres) {
        if(_adres == null)
            throw new IllegalArgumentException("Adres mag geen lege waarde bevatten.");
        
        this._adres = _adres;
    }

    private void setGraad(Gradatie _graad) {
        if(_graad == null)
            _graad = Gradatie.RokkuKyu;
        
        this._graad = _graad;
    }

    private void setType(TypeGebruiker _type) {
        if(_type == null)
            _type = TypeGebruiker.Lid;
        
        this._type = _type;
    }

    private void setLesformule(Lesformule _lesformule) {
        this._lesformule = _lesformule;
    }
    
    // Methods
    public void wijzigGegevens(Date inschrijvingsdatum, String naam, String voornaam, Geslacht geslacht, Date geboortedatum, String geboorteplaats, String telefoonnummer, String gsmnummer, String email, String emailOuders, int punten, Gradatie graad, TypeGebruiker type, Lesformule lesformule, String land, String postcode, String stad, String straat, String nummer){
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
        this.setPunten(punten);
        this.setGraad(graad);
        this.setType(type);
        this.setLesformule(lesformule);
        this._adres.wijzigAdres(land, postcode, stad, straat, nummer);
    }
}
