package Gebruiker.Models;

public class Lesformule {
    // Declarations
    private final int _id;
    private final String _titelText;
    
    // Constructors
    public Lesformule(int id, String titelText){
        this._id = id;
        this._titelText = titelText;
    }
    
    // Getters
    public int getId() {
        return _id;
    }

    public String getTitelText() {
        return _titelText;
    }
}
