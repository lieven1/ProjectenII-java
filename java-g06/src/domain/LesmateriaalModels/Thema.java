package domain.LesmateriaalModels;

public final class Thema {
    private int id;
    private String naam;
    
    public Thema(String naam){
        setNaam(naam);
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        if(naam == null)
            throw new IllegalArgumentException("Thema mag geen lege waarde bevatten.");
        
        this.naam = naam;
    }
}
