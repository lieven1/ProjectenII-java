package domain.GebruikerModels;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@NamedQueries({
    @NamedQuery(name = "AGebruiker.findAll",
            query = "Select g from Gebruiker g")
})
@Table(name = "Gebruiker")
@DiscriminatorColumn(name = "TypeGebruiker", discriminatorType = DiscriminatorType.INTEGER)
public abstract class AGebruiker implements Serializable {
    // Declarations
    @Id
    @Column(name = "Gebruikersnaam")
    protected String gebruikersnaam;
    @Column(name = "Naam")
    protected String naam;
    @Column(name = "Voornaam")
    protected String voornaam;
    @Column(name = "Telefoonnummer")
    protected String telefoonnummer;
    @Column(name = "Email")
    protected String email;
    @Column(name = "Inschrijvingsdatum")
    @Temporal(javax.persistence.TemporalType.DATE)
    protected Calendar inschrijvingsDatum;
    @Column(name = "TypeGebruiker")
    protected TypeGebruiker type;

    // Getters
    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public String getNaam() {
        return naam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getTelefoonnummer() {
        return telefoonnummer;
    }

    public String getEmail() {
        return email;
    }

    public Calendar getInschrijvingsDatum() {
        return inschrijvingsDatum;
    }

    public TypeGebruiker getType() {
        return type;
    }
    
    // Setters
    protected void setGebruikersnaam(String _gebruikersnaam) {
        if(_gebruikersnaam == null || _gebruikersnaam.isBlank())
            throw new IllegalArgumentException("Gebruikersnaam mag geen lege waarde bevatten.");
        this.gebruikersnaam = _gebruikersnaam;
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
        this.naam = _naam;
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
        this.voornaam = _voornaam;
    }

    protected void setTelefoonnummer(String _telefoonnummer) {
        if(_telefoonnummer != null && !_telefoonnummer.isBlank()){
            if(! Pattern.compile("((?:\\+|00)[17](?: |\\-)?|(?:\\+|00)[1-9]\\d{0,2}(?: |\\-)?|(?:\\+|00)1\\-\\d{3}(?: |\\-)?)?(0\\d|\\([0-9]{3}\\)|[1-9]{0,3})(?:((?: |\\-)[0-9]{2}){4}|((?:[0-9]{2}){4})|((?: |\\-)[0-9]{3}(?: |\\-)[0-9]{4})|([0-9]{7}))").matcher(_telefoonnummer).matches())
                throw new IllegalArgumentException("Ongeldig formaat voor telefoonnummer.");
        }
        this.telefoonnummer = _telefoonnummer;
    }

    protected void setEmail(String _email) {
        if(_email == null || _email.isBlank())
            throw new IllegalArgumentException("Email mag geen lege waarde bevatten.");
        if(! Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]+$").matcher(_email).matches())
            throw new IllegalArgumentException("Ongeldig formaat voor Emailadres.");
        this.email = _email;
    }

    protected void setInschrijvingsDatum(Calendar _inschrijvingsDatum) {
        if(_inschrijvingsDatum == null)
            throw new IllegalArgumentException("Inschrijvingsdatum mag geen lege waarde bevatten.");
        if(_inschrijvingsDatum.after(new GregorianCalendar()))
            throw new IllegalArgumentException("Inschrijvingsdatum mag niet in de toekomst liggen.");
        this.inschrijvingsDatum = _inschrijvingsDatum;
    }

    protected void setType(TypeGebruiker _type) {
        this.type = _type;
    }  
}
