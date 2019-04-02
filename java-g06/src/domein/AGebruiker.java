package domein;

import java.util.Date;
import java.util.regex.Pattern;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Gebruiker")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class AGebruiker {
    // Declarations
    @Id
    protected String _gebruikersnaam;
    protected String _naam, _voornaam, _telefoonnummer, _email;
    @Temporal(TemporalType.TIMESTAMP)
    protected Date _inschrijvingsDatum;
    protected TypeGebruiker _type;

    // Getters
    public String getGebruikersnaam() {
        return _gebruikersnaam;
    }

    public String getNaam() {
        return _naam;
    }

    public String getVoornaam() {
        return _voornaam;
    }

    public String getTelefoonnummer() {
        return _telefoonnummer;
    }

    public String getEmail() {
        return _email;
    }

    public Date getInschrijvingsDatum() {
        return _inschrijvingsDatum;
    }

    public TypeGebruiker getType() {
        return _type;
    }
    
    // Setters
    protected void setGebruikersnaam(String _gebruikersnaam) {
        if(_gebruikersnaam == null || _gebruikersnaam.isBlank())
            throw new IllegalArgumentException("Gebruikersnaam mag geen lege waarde bevatten.");
        this._gebruikersnaam = _gebruikersnaam;
    }

    protected void setNaam(String _naam) {
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

    protected void setVoornaam(String _voornaam) {
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

    protected void setTelefoonnummer(String _telefoonnummer) {
        if(_telefoonnummer != null && !_telefoonnummer.isBlank()){
            if(! Pattern.compile("((?:\\+|00)[17](?: |\\-)?|(?:\\+|00)[1-9]\\d{0,2}(?: |\\-)?|(?:\\+|00)1\\-\\d{3}(?: |\\-)?)?(0\\d|\\([0-9]{3}\\)|[1-9]{0,3})(?:((?: |\\-)[0-9]{2}){4}|((?:[0-9]{2}){4})|((?: |\\-)[0-9]{3}(?: |\\-)[0-9]{4})|([0-9]{7}))").matcher(_telefoonnummer).matches())
                throw new IllegalArgumentException("Ongeldig formaat voor telefoonnummer.");
        }
        this._telefoonnummer = _telefoonnummer;
    }

    protected void setEmail(String _email) {
        if(_email == null || _email.isBlank())
            throw new IllegalArgumentException("Email mag geen lege waarde bevatten.");
        if(! Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]+$").matcher(_email).matches())
            throw new IllegalArgumentException("Ongeldig formaat voor Emailadres.");
        this._email = _email;
    }

    protected void setInschrijvingsDatum(Date _inschrijvingsDatum) {
        if(_inschrijvingsDatum == null)
            throw new IllegalArgumentException("Inschrijvingsdatum mag geen lege waarde bevatten.");
        if(_inschrijvingsDatum.compareTo(new Date()) < 0)
            throw new IllegalArgumentException("Inschrijvingsdatum mag niet in de toekomst liggen.");
        this._inschrijvingsDatum = _inschrijvingsDatum;
    }

    protected void setType(TypeGebruiker _type) {
        this._type = _type;
    }  
}
