/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StartUp;

import domain.Activiteit;
import domain.GebruikerModels.AGebruiker;
import java.util.List;
import persistentie.GenericDaoJpa;

/**
 *
 * @author Steve
 */
public class TestActiviteitenDB {

    public static void main(String[] args) {
        GenericDaoJpa<Activiteit> activiteitenRepo = new GenericDaoJpa<>(Activiteit.class);
        GenericDaoJpa<AGebruiker> gebruikerRepo = new GenericDaoJpa<>(AGebruiker.class);

        List<Activiteit> acts = activiteitenRepo.findAll();
        Activiteit act = acts.get(0);
        List<AGebruiker> gebruikers = gebruikerRepo.findAll();
        AGebruiker g = gebruikers.get(0);

     
        
        
        GenericDaoJpa.startTransaction();
        activiteitenRepo.delete(act);
        GenericDaoJpa.commitTransaction();

    }

}
