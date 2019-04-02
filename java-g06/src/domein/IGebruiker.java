package domein;

import java.util.Date;

public interface IGebruiker {
    public String getGebruikersnaam();
    public Date getInschrijvingsDatum();
    public String getNaam();
    public String getVoornaam();
    public String getEmail();
    public String getTelefoonnummer();
    public TypeGebruiker getType();
}
