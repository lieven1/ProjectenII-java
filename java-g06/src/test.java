
import domein.Adres;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class test {
    public static void main(String[] args){
        findAllAdressen();
    }
    
    private static EntityManager entityManager;

    public static List<Adres> findAllAdressen() {
    EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("Taijitan");
    entityManager = factory.createEntityManager();
    /*
    entityManager.getTransaction().begin();
    List<Adres> listAdres = entityManager.createQuery(
            "SELECT p FROM Adres p").getResultList();
    entityManager.getTransaction().commit();
    entityManager.close();
    factory.close();
    if (listAdres == null) {
        System.out.println("No adresses found . ");
    } else {
        for (Adres adres : listAdres) {
        System.out.println(adres.getAdresId());
        }
    }

    return listAdres;*/
    return null;
    }
}
