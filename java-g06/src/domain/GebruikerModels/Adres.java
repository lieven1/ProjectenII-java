package domain.GebruikerModels;

import java.io.Serializable;
import java.util.regex.Pattern;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Adres implements Serializable {
    // Declarations
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "AdresId")
    private int adresId;
    @Column(name = "Land")
    private String land;
    @Column(name = "Postcode")
    private String postcode;
    @Column(name = "Stad")
    private String stad;
    @Column(name = "Straat")
    private String straat;
    @Column(name = "Nummer")
    private String nummer;

    // Constructors
    public Adres(int adresId, String land, String postcode, String stad, String straat, String nummer){
        this(land, postcode, stad, straat, nummer);
        this.adresId = adresId;
    }
    
    public Adres(String land, String postcode, String stad, String straat, String nummer){
        this.setLand(land);
        this.setPostcode(postcode);
        this.setStad(stad);
        this.setStraat(straat);
        this.setNummer(nummer);
    }
    public Adres() {}
    
    // Getters
    public int getAdresId() {
        return adresId;
    }

    public String getLand() {
        return land;
    }
    
    public String getPostcode() {
        return postcode;
    }

    public String getStad() {
        return stad;
    }

    public String getStraat() {
        return straat;
    }

    public String getNummer() {
        return nummer;
    }
    
    // Setters
    private void setLand(String _land) {
        if(_land == null || _land.isBlank())
            throw new IllegalArgumentException("Land mag geen lege waarde bevatten.");
        
        this.land = _land;
    }
    
    private void setPostcode(String _postcode) {
        if(_postcode == null || _postcode.isBlank())
            throw new IllegalArgumentException("Postcode mag geen lege waarde bevatten.");
        if(! Pattern.compile("^\\d{4}[A-Z]?$").matcher(_postcode).matches())
            throw new IllegalArgumentException("Ongeldige waarde voor postcode.");
        
        this.postcode = _postcode;
    }

    private void setStad(String _stad) {
        if(_stad == null || _stad.isBlank())
            throw new IllegalArgumentException("Stad mag geen lege waarde bevatten.");
        if(! Pattern.compile("^[a-z A-Z]+$").matcher(_stad).matches())
            throw new IllegalArgumentException("Ongeldige waarde voor stad.");
        
        this.stad = _stad;
    }

    private void setStraat(String _straat) {
        if(_straat == null || _straat.isBlank())
            throw new IllegalArgumentException("Straat mag geen lege waarde bevatten.");
        if(! Pattern.compile("^[a-z A-Z]+$").matcher(_straat).matches())
            throw new IllegalArgumentException("Ongeldige waarde voor straat.");
        
        this.straat = _straat;
    }

    private void setNummer(String _nummer) {
        if(_nummer == null || _nummer.isBlank())
            throw new IllegalArgumentException("Nummer mag geen lege waarde bevatten.");
        if(! Pattern.compile("^[0-9]+([\\/][a-zA-Z0-9])?[a-zA-Z]?$").matcher(_nummer).matches())
            throw new IllegalArgumentException("Ongeldige waarde voor nummer.");
        
        this.nummer = _nummer;
    }
    
    // Methods
    public void wijzigAdres(String land, String postcode, String stad, String straat, String nummer) {
        this.setLand(land);
        this.setPostcode(postcode);
        this.setStad(stad);
        this.setStraat(straat);
        this.setNummer(nummer);
    }
}
