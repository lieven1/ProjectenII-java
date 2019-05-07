package domain.GebruikerModels;

public class Lesformule {
    // Declarations
    private final int id;
    private final String titelText;
    
    // Constructors
    public Lesformule(int id, String titelText){
        this.id = id;
        this.titelText = titelText;
    }
    
    // Getters
    public int getId() {
        return id;
    }

    public String getTitelText() {
        return titelText;
    }
}
