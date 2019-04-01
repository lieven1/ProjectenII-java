package domein;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Bono
 */
public class AdresTest {
    
    public AdresTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getAdresId method, of class Adres.
     */
    @Test
    public void testGetAdresId() {
        System.out.println("getAdresId");
        Adres instance = null;
        int expResult = 0;
        int result = instance.getAdresId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLand method, of class Adres.
     */
    @Test
    public void testGetLand() {
        System.out.println("getLand");
        Adres instance = null;
        String expResult = "";
        String result = instance.getLand();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPostcode method, of class Adres.
     */
    @Test
    public void testGetPostcode() {
        System.out.println("getPostcode");
        Adres instance = null;
        String expResult = "";
        String result = instance.getPostcode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStad method, of class Adres.
     */
    @Test
    public void testGetStad() {
        System.out.println("getStad");
        Adres instance = null;
        String expResult = "";
        String result = instance.getStad();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStraat method, of class Adres.
     */
    @Test
    public void testGetStraat() {
        System.out.println("getStraat");
        Adres instance = null;
        String expResult = "";
        String result = instance.getStraat();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNummer method, of class Adres.
     */
    @Test
    public void testGetNummer() {
        System.out.println("getNummer");
        Adres instance = null;
        String expResult = "";
        String result = instance.getNummer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of wijzigAdres method, of class Adres.
     */
    @Test
    public void testWijzigAdres() {
        System.out.println("wijzigAdres");
        String land = "";
        String postcode = "";
        String stad = "";
        String straat = "";
        String nummer = "";
        Adres instance = null;
        instance.wijzigAdres(land, postcode, stad, straat, nummer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
