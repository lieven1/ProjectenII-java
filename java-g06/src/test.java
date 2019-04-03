
import domein.AGebruiker;
import domein.Adres;
import domein.DomeinController;
import domein.Gebruiker;
import domein.Geslacht;
import domein.Gradatie;
import domein.TypeGebruiker;
import java.util.Date;
import java.util.List;

public class test {
    public static void main(String[] args){
        DomeinController dc = new DomeinController();
        List<AGebruiker> _gebruikers = dc.getAllGebruikers();
        _gebruikers.forEach((gebruiker) -> {
            System.out.printf("%s - %b%n", gebruiker.getGebruikersnaam(), gebruiker instanceof Gebruiker);
        });
        try{
        dc.addGebruiker((new Gebruiker(
                    String.format("Gebruiker-%d", 1),
                    String.format("111111111%02d", 1),
                    new Date(2000, 0, 0),
                    "Naam",
                    "Voornaam",
                    Geslacht.Man,
                    new Date(2000, 0, 0),
                    "Gent",
                    "052256751",
                    "0473545457",
                    String.format("gebruiker-%d@taijitan.be", 1),
                    String.format("Oudersgebruiker-%d@taijitan.be", 1),
                    new Adres("Belgie", "9000", "Gent", "Valentyn Vaerwyckweg", "1"),
                    100,
                    Gradatie.GoKyu,
                    TypeGebruiker.Lid,
                    null)
            ));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
