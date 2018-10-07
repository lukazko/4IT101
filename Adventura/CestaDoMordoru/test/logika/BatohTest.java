package logika;

import logika.Hra;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra
 *
 * @author    Lukáš Kubík
 * @version   1.00
 */
public class BatohTest {
    private Batoh batoh1;
    
    private logika.Vec vec1;
    private logika.Vec vec2;
    private logika.Vec vec3;
    private logika.Vec vec4;

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
        batoh1 = new Batoh();
        
        vec1 = new logika.Vec("Víno", true, false);
        vec2 = new logika.Vec("Lustr", true, false);
        vec3 = new logika.Vec("Písek", true, false);
        vec4 = new logika.Vec("Dvěře", true, false);
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    /***************************************************************************
     * Testuje průběh hry, po zavolání každěho příkazu testuje, zda hra končí
     * a v jaké aktuální místnosti se hráč nachází.
     * Při dalším rozšiřování hry doporučujeme testovat i jaké věci nebo osoby
     * jsou v místnosti a jaké věci jsou v batohu hráče.
     */
    @Test
    public void testKapacita() {
        assertEquals(true, batoh1.vlozVec(vec1));
        assertEquals(true, batoh1.vlozVec(vec2));
        assertEquals(true, batoh1.vlozVec(vec3));
        assertEquals(false, batoh1.vlozVec(vec4));
    }
}
