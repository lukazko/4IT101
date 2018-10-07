package logika;

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
public class HraTest {
    private Hra hra1;

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
        hra1 = new Hra();
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
    public void testSpatnyKonec1() {
        //začínáme v Roklince, kde je možné sebrat Prsten
        assertEquals("Roklinka", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(true, hra1.getHerniPlan().getAktualniProstor().obsahujeVec("Prsten"));
        hra1.zpracujPrikaz("seber Prsten");
        assertNull(hra1.getHerniPlan().getAktualniProstor().najdiVec("Prsten")); //po sebrání není v místnosti
        assertNotNull(hra1.getHerniPlan().getBatoh().getVec("Prsten")); //ale v batohu
        
        // nelze sebrat nesebratelný předmět
        hra1.zpracujPrikaz("jdi Rozcesti");
        hra1.zpracujPrikaz("jdi Prusmyk");
        hra1.zpracujPrikaz("seber Kopa_snehu");
        assertNull(hra1.getHerniPlan().getBatoh().getVec("Kopa_snehu"));
        
        // V Železném pase zemře Gandalf
        hra1.zpracujPrikaz("jdi Rozcesti");
        hra1.zpracujPrikaz("jdi Zelezny_pas");
        assertEquals(false, hra1.getHerniPlan().existujeSpolecnik("Gandalf"));
        
        //Morii nelze projít bez Gandalfa
        hra1.zpracujPrikaz("jdi Rozcesti");
        hra1.zpracujPrikaz("jdi Morie");
        assertEquals(true, hra1.konecHry()); 
    }
    
    @Test
    public void testSpatnyKonec2() {
        // s Gandalfem se lze dostat až za Morii
        hra1.zpracujPrikaz("seber Prsten");
        hra1.zpracujPrikaz("jdi Rozcesti");
        hra1.zpracujPrikaz("jdi Morie");
        hra1.zpracujPrikaz("jdi Lorien");
        
        //Nelze nést v batohu víc než 3 věci
        hra1.zpracujPrikaz("seber Lembas");
        hra1.zpracujPrikaz("seber Neviditelny_plast");
        hra1.zpracujPrikaz("jdi Reka");
        hra1.zpracujPrikaz("seber Ryba");
        assertNull(hra1.getHerniPlan().getBatoh().getVec("Ryba"));
        
        //pokud půjdu severní cestou zemřou mi postupně všichni společníci
        hra1.zpracujPrikaz("jdi Amon_Hen");
        assertEquals(false, hra1.getHerniPlan().existujeSpolecnik("Aragorn"));
        hra1.zpracujPrikaz("jdi Emyn_Muil");
        assertEquals(false, hra1.getHerniPlan().existujeSpolecnik("Sam"));
        
        //pokud u černé brány nemám aktivovaný neviditelný plášť, je po mně
        hra1.zpracujPrikaz("jdi Cerna_brana");
        assertEquals(true, hra1.getHerniPlan().jeProhra());
    }
    
    @Test
    public void testVyherniCesta1() {
        hra1.zpracujPrikaz("seber Prsten");
        hra1.zpracujPrikaz("jdi Rozcesti");
        hra1.zpracujPrikaz("jdi Morie");
        hra1.zpracujPrikaz("jdi Lorien");
        hra1.zpracujPrikaz("seber Neviditelny_plast");
        hra1.zpracujPrikaz("jdi Reka");
        hra1.zpracujPrikaz("jdi Amon_Hen");
        hra1.zpracujPrikaz("jdi Emyn_Muil");
        hra1.zpracujPrikaz("pouzij Neviditelny_plast");
        hra1.zpracujPrikaz("jdi Cerna_brana");
        hra1.zpracujPrikaz("jdi Hora_osudu");
        hra1.zpracujPrikaz("zahod Prsten");
        assertEquals(true, hra1.getHerniPlan().jeVyhra());
    }
    
    @Test
    public void testUkonceniHry() {
        hra1.zpracujPrikaz("konec"); //funkčnost příkazu konec
        assertEquals(true, hra1.konecHry());
    }
}
