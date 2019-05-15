/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StartUp;

import domain.Activiteit;
import domain.LesmateriaalModels.Lesmateriaal;
import java.util.List;
import persistentie.GenericDaoJpa;

/**
 *
 * @author Steve
 */
public class TestActiviteitenDB {

    public static void main(String[] args) {
        GenericDaoJpa<Activiteit> activiteitenRepo = new GenericDaoJpa<>(Activiteit.class);
        GenericDaoJpa<Lesmateriaal> lesmateriaalRepo = new GenericDaoJpa<>(Lesmateriaal.class);

        List<Lesmateriaal> lesmaterialen = lesmateriaalRepo.findAll();

        List<Activiteit> activititeiten = activiteitenRepo.findAll();
        for (Activiteit act : activititeiten) {
            System.out.println(act);
        }

    }

}
