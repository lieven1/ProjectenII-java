/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domain.Activiteit;
import domain.GebruikerModels.AGebruiker;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Steve
 */
public class ActiviteitenDao {

    private List<AGebruiker> gebruikers;
    private List<Activiteit> activiteiten;

    public ActiviteitenDao() {
        activiteiten = new ArrayList<>();
        Calendar start = Calendar.getInstance();
        start.set(2019, 05, 1, 18, 00);
        Calendar eind = Calendar.getInstance();
        eind.set(2019, 5, 1, 20, 00);
        eind.add(Calendar.DAY_OF_MONTH, 2);
        for (int i = 0; i < 5; i++) {
            start.add(Calendar.DAY_OF_MONTH, 7*i);
            eind.add(Calendar.DAY_OF_MONTH, 7*i);
            Activiteit act = new Activiteit("activiteit_" + i, "demo", start, eind, 25, new ArrayList<>(), new ArrayList<>());
            act.setId(i);
            activiteiten.add(act);        
        }
    }

    public List findAll() {
        return activiteiten;
    }

    public Object get(String id) {
        return activiteiten.stream().filter(act -> act.getId() == Integer.parseInt(id)).findFirst();
    }

    public Object update(Object object) {
        Activiteit activiteit = (Activiteit) object;
        Activiteit toReplace = activiteiten.stream().filter(act -> act.getId() == activiteit.getId()).findFirst().orElse(null);
        activiteiten.remove(toReplace);
        activiteiten.add((Activiteit)object);
        return object;
    }

    public void delete(Object object) {
        activiteiten.remove(object);
        // e-mail sturen naar deelnemers
    }

    public void insert(Object object) {
        activiteiten.add((Activiteit)object);
    }

    public boolean exists(String id) {
        return activiteiten.stream().anyMatch(act -> act.getId() == Integer.parseInt(id));
    }

}
