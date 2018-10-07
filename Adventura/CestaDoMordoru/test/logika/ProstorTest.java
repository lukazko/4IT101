/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/*******************************************************************************
 * Testovací třída ProstorTest slouží ke komplexnímu otestování třídy ... 
 *
 * @author    Lukáš Kubík
 * @version   1.00
 */
public class ProstorTest {
    private logika.Prostor prostor1;
    private logika.Prostor prostor2;
    private logika.Prostor prostor3;
    
    private logika.Vec vec1;
    private logika.Vec vec2;

    /***************************************************************************
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */
    @Before
    public void setUp() {
        prostor1 = new logika.Prostor("Dům", "hlavní prostor");
        prostor2 = new logika.Prostor("Sklep", "sklep v domě");
        prostor3 = new logika.Prostor("Dvorek", "prostor před domem");
        prostor1.setVychod(prostor2);
        prostor1.setVychod(prostor3);
        prostor2.setVychod(prostor1);
        prostor3.setVychod(prostor1);
        vec1 = new logika.Vec("Víno", true, false);
        vec2 = new logika.Vec("Lustr", false, false);
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každého testu.
     */
    @After
    public void tearDown() {
    }
    
    @Test
    public void testProstory() {		
        assertEquals(prostor2, prostor1.vratSousedniProstor("Sklep")); //jsou průchozí
        assertEquals(prostor3, prostor1.vratSousedniProstor("Dvorek"));
        assertEquals(prostor1, prostor2.vratSousedniProstor("Dům"));
        assertEquals(null, prostor2.vratSousedniProstor("Dvorek")); //nelze projít do prostoru, s kterým nesousedím

        assertEquals(true, prostor1.vlozVec(vec1)); //v prostoru může být více věcí
        assertEquals(true, prostor1.vlozVec(vec2));
        
        assertNull(prostor1.najdiVec("Žena")); //nelze najít to, co se nenachází v aktuálním prostoru  
        assertEquals(vec1, prostor1.odeberVec("Víno")); //vec lze odebrat
        assertNull(prostor1.najdiVec("Víno")); //pak už se nenachází v prostoru
    }
}
