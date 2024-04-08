package project.testing;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;

import project.Bil;
import project.BestillBil;

public class bilAppTest {
    private BestillBil bestill;

    @BeforeEach
    public void setup(){
        bestill = new BestillBil();
    }

    @Test
    @DisplayName("Sjekke riktig valg av spesifikasjoner")
    public void lagBil(){
        Bil bil1 = new Bil();

        bil1.setModell("SUV");
        bil1.setFarge("Rød");
        bil1.setFelger("Sport");
        assertTrue(bil1.getModell() == "SUV" && bil1.getFarge() == "Rød" && bil1.getFelger() == "Sport");

        //Sjekker at kun SUV endres til Sedan
        bil1.setModell("Sedan");
        assertTrue(bil1.getModell() == "Sedan" && bil1.getFarge() == "Rød" && bil1.getFelger() == "Sport");
    }

    @Test
    @DisplayName("Sjekke at riktig bil blir lagt i bestillinger")
    public void leggBilIBestilling{
        Bil bil2 = new Bil();
        String epost = "mv@gmail.com";

        bil2.setModell("Sedan");
        bil2.setFarge("Blå");
        bil2.setFelger("Sport");

        bestill.leggTilBestilling(epost, bil2);
        assertEquals("Rød", bestill.b)


    
}