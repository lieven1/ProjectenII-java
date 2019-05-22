/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activiteit;

import domain.GebruikerModels.Gebruiker;
import domain.Activiteit;
import domain.ActiviteitBegeleider;
import domain.ActiviteitDeelnemer;
import domain.GebruikerModels.Gradatie;
import domain.GebruikerModels.Lesformule;
import domain.GebruikerModels.TypeGebruiker;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.junit.After;
import org.junit.Test;

/**
 *
 * @author Steve
 */
public class ActiviteitTest {

    private final String validTitel;
    private final String validType;
    private final Calendar validStartDatum;
    private final Calendar validEindDatum;
    private final int validMaxAantalDeelnemers;
    private final Gebruiker validGebruiker;
    private final List<ActiviteitDeelnemer> validDeelnemers;
    private final List<ActiviteitBegeleider> validBegeleiders;
    private Activiteit validActiviteit;

    public ActiviteitTest() {
        validType = "TestActiviteit";
        validTitel = "Activiteit";
        validStartDatum = new GregorianCalendar();
        validEindDatum = new GregorianCalendar();
        validEindDatum.add(Calendar.DAY_OF_MONTH, 5);
        validMaxAantalDeelnemers = 10;
        validGebruiker = new Gebruiker("testnaam", "11111111111", Calendar.getInstance(), "Naam", "Voornaam", domain.GebruikerModels.Geslacht.Man, new GregorianCalendar(2002, 3, 12), "Gent", "092432004", "0476585152", "gebruiker1@taijitan.be", "gebruiker11@taijitan.be", "België", "9000", "Gent", "Voskenslaan", "102", Gradatie.GoDan, TypeGebruiker.Lid, new Lesformule(1, "Woensdag"));
        validDeelnemers = Arrays.asList(new ActiviteitDeelnemer(validActiviteit, new Gebruiker()), new ActiviteitDeelnemer(validActiviteit, new Gebruiker()), new ActiviteitDeelnemer(validActiviteit, new Gebruiker()));
        validBegeleiders = Arrays.asList(new ActiviteitBegeleider(validActiviteit, new Gebruiker()), new ActiviteitBegeleider(validActiviteit, new Gebruiker()));
        validActiviteit = new Activiteit(validTitel, validType, validStartDatum, validEindDatum, validMaxAantalDeelnemers, validDeelnemers, validBegeleiders);
    }

    @After
    public void resetValidActiviteitInstance() {
        validActiviteit = new Activiteit(validTitel, validType, validStartDatum, validEindDatum, validMaxAantalDeelnemers, validDeelnemers, validBegeleiders);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInitLegeTitelThrowsIllegalArgumentException() {
        Activiteit instance = new Activiteit("", validType, validStartDatum, validEindDatum, validMaxAantalDeelnemers, validDeelnemers, validBegeleiders);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetLegeTitelThrowsIllegalArgumentException() {
        validActiviteit.setTitel("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInitLeegTypeThrowsIllegalArgumentException() {
        Activiteit instance = new Activiteit(validTitel, "", validStartDatum, validEindDatum, validMaxAantalDeelnemers, validDeelnemers, validBegeleiders);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetLeegTypeThrowsIllegalArgumentException() {
        validActiviteit.setType("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInitStartDatumNaEindDatumThrowsIllegalArgumentException() {
        Calendar start = new GregorianCalendar();
        start.add(Calendar.DAY_OF_MONTH, 1);
        Calendar eind = new GregorianCalendar();
        Activiteit instance = new Activiteit(validTitel, validType, start, eind, validMaxAantalDeelnemers, validDeelnemers, validBegeleiders);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetStartDatumNaEindDatumThrowsIllegalArgumentException() {
        Calendar start = (GregorianCalendar) validActiviteit.getEindDatum().clone();
        start.add(Calendar.DAY_OF_MONTH, 1);
        validActiviteit.setStartDatum(start);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetMaxAantalDeelnemersLagerDanDeelnemersThrowsIllegalArgumentException() {
        validActiviteit.setMaxAantalDeelnemers(validActiviteit.getActiviteitDeelnemers().size() - 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetMaxAantalDeelnemersKleinderDanNulThrowsIllegalArgumentException() {
        validActiviteit.setMaxAantalDeelnemers(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDeelnemersTeVeelThrowsIllegalArgumentException() {
        List<ActiviteitDeelnemer> deelnemers = new ArrayList<>();
        for (int i = 0; i < validActiviteit.getMaxAantalDeelnemers() + 1; i++) {
            deelnemers.add(new ActiviteitDeelnemer(validActiviteit, new Gebruiker()));
        }
        validActiviteit.setActiviteitDeelnemers(deelnemers);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddDeelnemerTeVeelThrowsIllegalArgumentException() {
        List<ActiviteitDeelnemer> deelnemers = new ArrayList<>();
        for (int i = 0; i < validMaxAantalDeelnemers; i++) {
            deelnemers.add(new ActiviteitDeelnemer(validActiviteit, new Gebruiker()));
        }
        Activiteit instance = new Activiteit(validTitel, validType, validStartDatum, validEindDatum, validMaxAantalDeelnemers, deelnemers, validBegeleiders);
        instance.addDeelnemer(new Gebruiker());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testVerwijderEnigeBegeleiderThrowsIllegalArgumentException() {
        Activiteit instance = new Activiteit(validTitel, validType, validStartDatum, validEindDatum, validMaxAantalDeelnemers, validDeelnemers, Arrays.asList(new ActiviteitBegeleider(validActiviteit, validGebruiker)));
        instance.deleteBegeleider(validGebruiker);
    }

}
