package controllers;

import domain.GebruikerModels.AGebruiker;
import domain.GebruikerModels.GebruikerBeheerder;
import java.util.List;

public class GebruikerController {
    private final GebruikerBeheerder beheerder;
    
    public GebruikerController(){
        beheerder = new GebruikerBeheerder();
    }
    
    public List<AGebruiker> getGebruikerList(){
        return beheerder.getGebruikerList();
    }
}
