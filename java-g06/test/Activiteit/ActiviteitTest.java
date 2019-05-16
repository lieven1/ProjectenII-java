/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activiteit;

import domain.GebruikerModels.Gebruiker;
import domain.Activiteit;
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
    private final List<Gebruiker> validDeelnemers;
    private final List<Gebruiker> validBegeleiders;
    private Activiteit validActiviteit;

    public ActiviteitTest() {
        validType = "TestActiviteit";
        validTitel = "Activiteit";
        validStartDatum = new GregorianCalendar();
        validEindDatum = new GregorianCalendar();
        validEindDatum.add(Calendar.DAY_OF_MONTH, 5);
        validMaxAantalDeelnemers = 10;
        validDeelnemers = Arrays.asList(new Gebruiker(), new Gebruiker(), new Gebruiker());
        validBegeleiders = Arrays.asList(new Gebruiker(), new Gebruiker());
        validActiviteit = new Activiteit(validTitel, validType, validStartDatum, validEindDatum, validMaxAantalDeelnemers, validDeelnemers, validBegeleiders);
    }

    @After
    public void resetValidActiviteitInstance() {
        validActiviteit = new Activiteit(validTitel, validType, validStartDatum, validEindDatum, validMaxAantalDeelnemers, validDeelnemers, validBegeleiders);
    }

    @Test(expected=IllegalArgumentException.class)
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
        Calendar start = (GregorianCalendar)validActiviteit.getEindDatum().clone();
        start.add(Calendar.DAY_OF_MONTH, 1);
        validActiviteit.setStartDatum(start);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetMaxAantalDeelnemersLagerDanDeelnemersThrowsIllegalArgumentException() {
        validActiviteit.setMaxAantalDeelnemers(validActiviteit.getDeelnemers().size() - 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetMaxAantalDeelnemersKleinderDanNulThrowsIllegalArgumentException() {
        validActiviteit.setMaxAantalDeelnemers(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDeelnemersTeVeelThrowsIllegalArgumentException() {
        List<Gebruiker> deelnemers = new ArrayList<>();
        for (int i = 0; i < validActiviteit.getMaxAantalDeelnemers() + 1; i++) {
            deelnemers.add(new Gebruiker());
        }
        validActiviteit.setDeelnemers(deelnemers);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddDeelnemerTeVeelThrowsIllegalArgumentException() {
        List<Gebruiker> deelnemers = new ArrayList<>();
        for (int i = 0; i < validMaxAantalDeelnemers; i++) {
            deelnemers.add(new Gebruiker());
        }
        Activiteit instance = new Activiteit(validTitel, validType, validStartDatum, validEindDatum, validMaxAantalDeelnemers, deelnemers, validBegeleiders);
        instance.addDeelnemer(new Gebruiker());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testVerwijderEnigeBegeleiderThrowsIllegalArgumentException() {
        Gebruiker begeleider = new Gebruiker();
        Activiteit instance = new Activiteit(validTitel, validType, validStartDatum, validEindDatum, validMaxAantalDeelnemers, validDeelnemers, Arrays.asList(begeleider));
        instance.deleteBegeleider(begeleider);        
    }

}
