package persistentie;

import domein.AGebruiker;
import domein.Adres;
import domein.Gebruiker;
import domein.Geslacht;
import domein.Gradatie;
import domein.Lesformule;
import domein.ProefGebruiker;
import domein.TypeGebruiker;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class GebruikerMapper {

    private final List<AGebruiker> _gebruikers;

    public GebruikerMapper() {
        this._gebruikers = new ArrayList<>();
        Calendar inschrijvingsDatum = getCalendar(2019, 2, 12);
        Calendar geboorteDatum = getCalendar(2000, 10, 10);
        Random rand = new Random();
        List<Lesformule> lesformules = new ArrayList<>();
        lesformules.add(new Lesformule(1, "Dinsdag"));
        lesformules.add(new Lesformule(2, "Woensdag"));

        for (int i = 0; i < 100; i++) {
            this._gebruikers.add(new Gebruiker(
                    String.format("Gebruiker-%d", i),
                    String.format("111111111%02d", i),
                    inschrijvingsDatum,
                    "Naam",
                    "Voornaam",
                    Geslacht.Man,
                    geboorteDatum,
                    "Gent",
                    "052256751",
                    "0473545457",
                    String.format("gebruiker-%d@taijitan.be", i),
                    String.format("Oudersgebruiker-%d@taijitan.be", i),
                    new Adres("Belgie", "9000", "Gent", "Valentyn Vaerwyckweg", "1"),
                    Gradatie.values()[rand.nextInt(17) + 1],
                    TypeGebruiker.Lid,
                    lesformules.get(rand.nextInt(1))
            ));
        }
        for (int i = 0; i < 10; i++) {
            this._gebruikers.add(new ProefGebruiker(
                    String.format("GebruikerP-%d", i),
                    inschrijvingsDatum,
                    "Naam",
                    "Voornaam",
                    "052256751",
                    String.format("gebruiker-%d@taijitan.be", i)
            ));
        }
        for (int i = 0; i < 4; i++) {
            this._gebruikers.add(new Gebruiker(
                    String.format("Beheerder-%d", i),
                    String.format("111111112%02d", i),
                    inschrijvingsDatum,
                    "Naam",
                    "Voornaam",
                    Geslacht.Man,
                    geboorteDatum,
                    "Gent",
                    "052256751",
                    "0473545457",
                    String.format("beheerder-%d@taijitan.be", i),
                    String.format("Oudersbeheerder-%d@taijitan.be", i),
                    new Adres("Belgie", "9000", "Gent", "Valentyn Vaerwyckweg", "1"),
                    Gradatie.values()[rand.nextInt(17) + 1],
                    TypeGebruiker.Lid,
                    lesformules.get(rand.nextInt(1))
            ));
        }
    }

    public List<AGebruiker> getAll() {
        return _gebruikers;
    }

    private Calendar getCalendar(int year, int month, int date){
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, date);
        return cal;
    }
}
