import domein.Adres;
import domein.Gebruiker;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class test {
    private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("Taijitan"); 
    private static final EntityManager ENTITYMANAGER = EMF.createEntityManager();

    public static void main(String[] args) {
        findAllAdressen();
        findAllGebruikers();
        ENTITYMANAGER.close();
        EMF.close();
    }

    public static List<Adres> findAllAdressen() {
        ENTITYMANAGER.getTransaction().begin();
        List<Adres> listAdres = ENTITYMANAGER.createQuery(
                "SELECT p FROM Adres p").getResultList();
        ENTITYMANAGER.getTransaction().commit();
        if (listAdres == null) {
            System.out.println("No adresses found . ");
        } else {
            listAdres.forEach((adres) -> {
                System.out.println(adres.getAdresId());
            });
        }
        return listAdres;
    }

    public static List<Gebruiker> findAllGebruikers() {
        ENTITYMANAGER.getTransaction().begin();
        List<Gebruiker> listGebruiker = ENTITYMANAGER.createQuery(
                "SELECT p FROM Gebruiker p").getResultList();
        ENTITYMANAGER.getTransaction().commit();
        if (listGebruiker == null) {
            System.out.println("No users found . ");
        } else {
            listGebruiker.forEach((gebruiker) -> {
                System.out.println(gebruiker.getGebruikersnaam());
            });
        }
        return listGebruiker;
    }
}
