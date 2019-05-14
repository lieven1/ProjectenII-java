package domain.LesmateriaalModels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Thema {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ThemaId")
    private int id;
    @Column(name= "Naam")
    private String naam;
    
    public Thema(String naam){
        setNaam(naam);
    }
    public Thema() {}

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        if(naam == null)
            throw new IllegalArgumentException("Thema mag geen lege waarde bevatten.");
        
        this.naam = naam;
    }    
    
    @Override
    public String toString() {
        return naam;
    }
}
