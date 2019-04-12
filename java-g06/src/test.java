import Gebruiker.GebruikerController;

public class test {
    public static void main(String[] args) {
        GebruikerController gc = new GebruikerController();
        gc.getGebruikerList().forEach((g) -> {
            System.out.printf("%s - %s - %s - %s%n", g.getGebruikersnaam(), g.getVoornaam(), g.getType(), g.getInschrijvingsDatum().getTime());
        });
    }
}
