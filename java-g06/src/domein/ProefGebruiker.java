package domein;

import java.util.Date;
import java.util.regex.Pattern;

public final class ProefGebruiker implements IGebruiker {
    // Declarations
    private final String _gebruikersnaam, _naam, _voornaam, _telefoonnummer, _email;
    private final Date _inschrijvingsDatum;
    private final TypeGebruiker _type;
    
    // Constructors
    public ProefGebruiker(String gebruikersnaam, Date inschrijvingsdatum, String naam, String voornaam, String telefoonnummer, String email){
        // Gebruikersnaam
        if(gebruikersnaam == null || gebruikersnaam.isBlank())
            throw new IllegalArgumentException("Gebruikersnaam mag geen lege waarde bevatten.");
        this._gebruikersnaam = gebruikersnaam;
        
        // Inschrijvingsdatum
        if(inschrijvingsdatum == null)
            throw new IllegalArgumentException("Inschrijvingsdatum mag geen lege waarde bevatten.");
        if(inschrijvingsdatum.compareTo(new Date()) < 0)
            throw new IllegalArgumentException("Inschrijvingsdatum mag niet in de toekomst liggen.");
        this._inschrijvingsDatum = inschrijvingsdatum;
        
        // Naam
        if(naam == null || naam.isBlank())
            throw new IllegalArgumentException("Naam mag geen lege waarde bevatten.");
        if(naam.length() > 50)
            throw new IllegalArgumentException("Naam mag niet bestaan uit meer dan 50 karakters.");
        if(! Pattern.compile("^([A-Za-z]{1}[a-z ,.'-]+)+$").matcher(naam).matches())
            throw new IllegalArgumentException(String.format("%s%n%s%n%s%n%s", 
                    "Ongeldige waarde voor naam.",
                    "Naam begint met een letter.",
                    "Naam kan de tekens , . ' en - bevatten.",
                    "Naam kan uit meerdere woorden bestaan."));
        this._naam = naam;
        
        // Voornaam
        if(voornaam == null || voornaam.isBlank())
            throw new IllegalArgumentException("Voornaam mag geen lege waarde bevatten.");
        if(voornaam.length() > 50)
            throw new IllegalArgumentException("Voornaam mag niet bestaan uit meer dan 50 karakters.");
        if(! Pattern.compile("^([A-Z]{1}[a-z ,.'-]+)+$").matcher(voornaam).matches())
            throw new IllegalArgumentException(String.format("%s%n%s%n%s%n%s", 
                    "Ongeldige waarde voor voornaam.",
                    "Voornaam begint met een hoofdletter.",
                    "Voornaam kan de tekens , . ' en - bevatten.",
                    "Voornaam kan uit meerdere woorden bestaan."));
        this._voornaam = voornaam;
        
        // Telefoonnummer
        if(telefoonnummer == null || telefoonnummer.isBlank())
            throw new IllegalArgumentException("Telefoonnummer mag geen lege waarde bevatten.");
        if(! Pattern.compile("((?:\\+|00)[17](?: |\\-)?|(?:\\+|00)[1-9]\\d{0,2}(?: |\\-)?|(?:\\+|00)1\\-\\d{3}(?: |\\-)?)?(0\\d|\\([0-9]{3}\\)|[1-9]{0,3})(?:((?: |\\-)[0-9]{2}){4}|((?:[0-9]{2}){4})|((?: |\\-)[0-9]{3}(?: |\\-)[0-9]{4})|([0-9]{7}))").matcher(telefoonnummer).matches())
            throw new IllegalArgumentException("Ongeldig formaat voor telefoonnummer.");
        this._telefoonnummer = telefoonnummer;
        
        // Email
        if(email == null || email.isBlank())
            throw new IllegalArgumentException("Email mag geen lege waarde bevatten.");
        if(! Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]+$").matcher(email).matches())
            throw new IllegalArgumentException("Ongeldig formaat voor Emailadres.");
        this._email = email;
        
        // Type
        this._type = TypeGebruiker.Proefgebruiker;
    }
    
    // Getters
    @Override
    public String getGebruikersnaam() {
        return _gebruikersnaam;
    }

    @Override
    public String getNaam() {
        return _naam;
    }

    @Override
    public String getVoornaam() {
        return _voornaam;
    }

    @Override
    public String getTelefoonnummer() {
        return _telefoonnummer;
    }

    @Override
    public String getEmail() {
        return _email;
    }

    @Override
    public Date getInschrijvingsDatum() {
        return _inschrijvingsDatum;
    }

    @Override
    public TypeGebruiker getType() {
        return _type;
    }
}
