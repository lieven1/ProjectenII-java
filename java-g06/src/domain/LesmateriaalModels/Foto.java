package domain.LesmateriaalModels;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Foto implements Serializable {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "Bestandsnaam")
    private String bestandsnaam;
    @Column(name = "Extensie")
    private String extensie;
    
    /*public static Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
        "cloud_name", "dkbfdmful",
        "api_key", "863719494787761",
        "api_secret", "DpGFihSrXeYjXljkQiaFcaqR14c"));*/
    
    public Foto(String bestandsnaam, String extensie){
        setBestandsnaam(bestandsnaam);
        setExtensie(extensie);
    }
    
    public Foto(){}

    public String getBestandsnaam() {
        return bestandsnaam;
    }

    public void setBestandsnaam(String bestandsnaam) {
        if(bestandsnaam == null || bestandsnaam.isBlank())
            throw new IllegalArgumentException("Bestandsnaam mag geen lege waarde bevatten.");
        
        this.bestandsnaam = bestandsnaam;
    }

    public String getExtensie() {
        return extensie;
    }

    public void setExtensie(String extensie) {
        if(extensie == null || extensie.isBlank())
            throw new IllegalArgumentException("Extensie mag geen lege waarde bevatten.");
        
        this.extensie = extensie;
    }
    
    @Override
    public String toString() {
        return bestandsnaam.concat(".".concat(extensie));
    }
}
