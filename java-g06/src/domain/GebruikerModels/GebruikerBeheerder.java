package domain.GebruikerModels;

import java.util.List;
import persistentie.GenericDao;
import persistentie.GenericDaoJpa;

public class GebruikerBeheerder {
    private final GenericDao<AGebruiker> gebruikerRepo;
    
    public GebruikerBeheerder(){
        gebruikerRepo = new GenericDaoJpa<>(AGebruiker.class);
    }
    
    public List<AGebruiker> getAll(){
        return gebruikerRepo.findAll();
    }
    
    public void create(AGebruiker gebruiker){
        GenericDaoJpa.startTransaction();
        gebruikerRepo.insert(gebruiker);
        GenericDaoJpa.commitTransaction();
    }
    
    public void modify(AGebruiker oldGeb, AGebruiker gebruiker){
        GenericDaoJpa.startTransaction();
        gebruikerRepo.delete(oldGeb);
        GenericDaoJpa.commitTransaction();
        GenericDaoJpa.startTransaction();
        gebruikerRepo.insert(gebruiker);
        GenericDaoJpa.commitTransaction();
    }
    
    public void delete(AGebruiker gebruiker){
        GenericDaoJpa.startTransaction();
        gebruikerRepo.delete(gebruiker);
        GenericDaoJpa.commitTransaction();
    }
}