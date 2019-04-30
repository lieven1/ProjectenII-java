/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain.GebruikerModels;

import java.util.List;
import persistentie.GenericDao;
import persistentie.GenericDaoJpa;

public class GebruikerBeheerder {
    
private final GenericDao<AGebruiker> gebruikerRepo;
    private final List<AGebruiker> gebruikerList;
    
    public GebruikerBeheerder(){
        gebruikerRepo = new GenericDaoJpa<>(AGebruiker.class);
        gebruikerList = gebruikerRepo.findAll();
    }
    
    public List<AGebruiker> getGebruikerList(){
        return gebruikerList;
    }
}