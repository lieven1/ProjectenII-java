package Gebruiker;

import Gebruiker.Models.AGebruiker;
import java.util.List;
import persistentie.GenericDao;
import persistentie.GenericDaoJpa;

public class GebruikerController {
    private final GenericDao<AGebruiker> gebruikerRepo;
    private final List<AGebruiker> gebruikerList;
    
    public GebruikerController(){
        gebruikerRepo = new GenericDaoJpa<>(AGebruiker.class);
        gebruikerList = gebruikerRepo.findAll();
    }
    
    public List<AGebruiker> getGebruikerList(){
        return gebruikerList;
    }
}
