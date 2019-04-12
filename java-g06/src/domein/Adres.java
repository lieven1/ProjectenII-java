package domein;

import java.io.Serializable;
import java.util.regex.Pattern;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Adres implements Serializable {
    // Declarations
    @Id
    @Column(name = "AdresId")
    private int _adresId;
    @Column(name = "Land")
    private String _land;
    @Column(name = "Postcode")
    private String _postcode;
    @Column(name = "Stad")
    private String _stad;
    @Column(name = "Straat")
    private String _straat;
    @Column(name = "Nummer")
    private String _nummer;

    // Constructors
    public Adres(int adresId, String land, String postcode, String stad, String straat, String nummer){
        this(land, postcode, stad, straat, nummer);
        _adresId = adresId;
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
        return _adresId;
    }

    public String getLand() {
        return _land;
    }
    
    public String getPostcode() {
        return _postcode;
    }

    public String getStad() {
        return _stad;
    }

    public String getStraat() {
        return _straat;
    }

    public String getNummer() {
        return _nummer;
    }
    
    // Setters
    private void setLand(String _land) {
        if(_land == null || _land.isBlank())
            throw new IllegalArgumentException("Land mag geen lege waarde bevatten.");
        
        this._land = _land;
    }
    
    private void setPostcode(String _postcode) {
        if(_postcode == null || _postcode.isBlank())
            throw new IllegalArgumentException("Postcode mag geen lege waarde bevatten.");
        if(! Pattern.compile("^\\d{4}$").matcher(_postcode).matches())
            throw new IllegalArgumentException("Ongeldige waarde voor postcode.");
        
        this._postcode = _postcode;
    }

    private void setStad(String _stad) {
        if(_stad == null || _stad.isBlank())
            throw new IllegalArgumentException("Stad mag geen lege waarde bevatten.");
        
        this._stad = _stad;
    }

    private void setStraat(String _straat) {
        if(_straat == null || _straat.isBlank())
            throw new IllegalArgumentException("Straat mag geen lege waarde bevatten.");
        
        this._straat = _straat;
    }

    private void setNummer(String _nummer) {
        if(_nummer == null || _nummer.isBlank())
            throw new IllegalArgumentException("Nummer mag geen lege waarde bevatten.");
        
        this._nummer = _nummer;
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
