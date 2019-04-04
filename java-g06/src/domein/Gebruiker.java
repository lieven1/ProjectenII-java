package domein;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class Gebruiker extends AGebruiker {
    // Declarations
    private final String _rijksregisternummer; 
    private String _geboorteplaats, _gsmnummer, _emailOuders;
    private Calendar _geboorteDatum;
    private int _punten;
    private Geslacht _geslacht;
    private Adres _adres;
    private Gradatie _graad;
    private Lesformule _lesformule;
    
    // Constructors
    public Gebruiker(String gebruikersnaam, String rijksregisternummer, Calendar inschrijvingsdatum, String naam, String voornaam, Geslacht geslacht, Calendar geboortedatum, String geboorteplaats, String telefoonnummer, String gsmnummer, String email, String emailOuders, Adres adres, int punten, Gradatie graad, TypeGebruiker type, Lesformule lesformule){
        if(gebruikersnaam == null || gebruikersnaam.isBlank())
            throw new IllegalArgumentException("Gebruikersnaam mag geen lege waarde bevatten.");
        if(rijksregisternummer == null || rijksregisternummer.isBlank())
            throw new IllegalArgumentException("Rijksregisternummer mag geen lege waarde bevatten.");
        if(! Pattern.compile("^\\d{11}$").matcher(rijksregisternummer).matches())
            throw new IllegalArgumentException("Ongeldige waarde voor rijksregisternummer");
        
        this._gebruikersnaam = gebruikersnaam;
        this._rijksregisternummer = rijksregisternummer;
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
        this.setPunten(punten);
        this.setGraad(graad);
        this.setTypeG(type);
        this.setLesformule(lesformule);
    }
    
    // Getters 
    public String getRijksregisternummer() {
        return _rijksregisternummer;
    }

    public String getGeboorteplaats() {
        return _geboorteplaats;
    }

    public String getGsmnummer() {
        return _gsmnummer;
    }

    public String getEmailOuders() {
        return _emailOuders;
    }

    public Calendar getGeboorteDatum() {
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

    public Lesformule getLesformule() {
        return _lesformule;
    }
    
    // Setters
    private void setGeboorteplaats(String _geboorteplaats) {
        if(_geboorteplaats == null || _geboorteplaats.isBlank())
            throw new IllegalArgumentException("Geboorteplaats mag geen lege waarde bevatten.");
        
        this._geboorteplaats = _geboorteplaats;
    }

    private void setGsmnummer(String _gsmnummer) {
        if(_gsmnummer == null || _gsmnummer.isBlank())
            throw new IllegalArgumentException("Gsmnummer mag geen lege waarde bevatten.");
        if(! Pattern.compile("((?:\\+|00)[17](?: |\\-)?|(?:\\+|00)[1-9]\\d{0,2}(?: |\\-)?|(?:\\+|00)1\\-\\d{3}(?: |\\-)?)?(0\\d|\\([0-9]{3}\\)|[1-9]{0,3})(?:((?: |\\-)[0-9]{2}){4}|((?:[0-9]{2}){4})|((?: |\\-)[0-9]{3}(?: |\\-)[0-9]{4})|([0-9]{7}))").matcher(_gsmnummer).matches())
            throw new IllegalArgumentException("Ongeldig formaat voor gsmnummer.");
        
        this._gsmnummer = _gsmnummer;
    }
    
    private void setEmailOuders(String _emailOuders) {
        if(_emailOuders != null && !_emailOuders.isBlank()){
            if(! Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]+$").matcher(_emailOuders).matches())
                throw new IllegalArgumentException("Ongeldig formaat voor Emailadres.");
        }
        
        this._emailOuders = _emailOuders;
    }

    private void setGeboorteDatum(Calendar _geboorteDatum) {
        if(_geboorteDatum == null)
            throw new IllegalArgumentException("Geboortedatum mag geen lege waarde bevatten.");
        if(_geboorteDatum.after(Calendar.getInstance()))
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

    @Override
    protected void setType(TypeGebruiker _type) {
        this.setTypeG(_type);
    }
    private void setTypeG(TypeGebruiker _type) {
        if(_type == null)
            _type = TypeGebruiker.Lid;
        if(_type == TypeGebruiker.Proefgebruiker)
            throw new IllegalArgumentException("Een bestaande gebruiker kan geen proefgebruiker worden.");
        
        this._type = _type;
    }

    private void setLesformule(Lesformule _lesformule) {
        this._lesformule = _lesformule;
    }
    
    // Methods
    public void wijzigGegevens(Calendar inschrijvingsdatum, String naam, String voornaam, Geslacht geslacht, Calendar geboortedatum, String geboorteplaats, String telefoonnummer, String gsmnummer, String email, String emailOuders, int punten, Gradatie graad, TypeGebruiker type, Lesformule lesformule, String land, String postcode, String stad, String straat, String nummer){
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
