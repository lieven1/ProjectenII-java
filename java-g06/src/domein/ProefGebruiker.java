package domein;

import java.util.Date;
import java.util.regex.Pattern;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "3")
public class ProefGebruiker extends AGebruiker {
    // Constructors
    public ProefGebruiker(String gebruikersnaam, Date inschrijvingsdatum, String naam, String voornaam, String telefoonnummer, String email){
        this.setGebruikersnaam(gebruikersnaam);
        this.setInschrijvingsDatum(inschrijvingsdatum);
        this.setNaam(naam);
        this.setVoornaam(voornaam);
        this.setTelefoonnummer(telefoonnummer);
        this.setEmail(email);
        this.setType(TypeGebruiker.Proefgebruiker);
    }
    
    @Override
    protected final void setTelefoonnummer(String _telefoonnummer){
        if(_telefoonnummer == null || _telefoonnummer.isBlank())
            throw new IllegalArgumentException("Telefoonnummer mag geen lege waarde bevatten.");
        if(! Pattern.compile("((?:\\+|00)[17](?: |\\-)?|(?:\\+|00)[1-9]\\d{0,2}(?: |\\-)?|(?:\\+|00)1\\-\\d{3}(?: |\\-)?)?(0\\d|\\([0-9]{3}\\)|[1-9]{0,3})(?:((?: |\\-)[0-9]{2}){4}|((?:[0-9]{2}){4})|((?: |\\-)[0-9]{3}(?: |\\-)[0-9]{4})|([0-9]{7}))").matcher(_telefoonnummer).matches())
            throw new IllegalArgumentException("Ongeldig formaat voor telefoonnummer.");
        this._telefoonnummer = _telefoonnummer;
    }
}
