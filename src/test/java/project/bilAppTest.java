package project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    public void leggBilIBestilling(){
        Bil bil2 = new Bil();
        String epost = "mv@gmail.com";

        bil2.setModell("Sedan");
        bil2.setFarge("Blå");
        bil2.setFelger("Sport");

        bestill.leggTilBestilling(epost, bil2);
    }

    @Test
    @DisplayName("Sjekke validering av epost")
    public void validerEpost(){
        assertThrows(RuntimeException.class, () -> {
			bestill.sjekkEpost("hei@hotmail.xn");
		}, "Ugyldig landskode");
        assertThrows(RuntimeException.class, () -> {
			bestill.sjekkEpost("heiAtgmail.no");
		}, "må inneholde alfakrøll");
    }

    @Test
    @DisplayName("Sjekke riktig pris")
    public void beregnPris(){
        Bil bil3 = new Bil();

        bil3.setModell("SUV");
        bil3.setFarge("Rød");
        bil3.setFelger("Sport");

        Priskalkulator kalk = new Priskalkulator(bil3);
        
        assertEquals(kalk.beregnPris(), 1389987.5);
        bil3.setFelger("Standard");
        assertEquals(kalk.beregnPris(), 1323737.5);

    }

    @Test
    @DisplayName("Sjekke riktig skriving til fil")
    public void skriveRikitgTilFil(){
        Bil bil4 = new Bil();
        bil4.setModell("Sedan");
        bil4.setFarge("Blå");
        bil4.setFelger("Sport");

        String forventetTekst = "hei@gmail.no,Sedan,Blå,Sport";

        bestill.leggTilBestilling("hei@gmail.no", bil4);
       
        try {
            bestill.skrivBestillingTilFil();
            BufferedReader leser = new BufferedReader(new FileReader("Bestillinger.txt"));
            String faktiskTekst = leser.readLine();
            leser.close();

            assertEquals(forventetTekst, faktiskTekst);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                java.nio.file.Files.deleteIfExists(java.nio.file.Paths.get("Bestillinger.txt"));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }   
    }

    @Test
    @DisplayName("Sjekke riktig henting av bestilling i fil")
    public void leseRiktigFil(){
        Bil bil5 = new Bil();
        bil5.setModell("Sedan");
        bil5.setFarge("Blå");
        bil5.setFelger("Sport");
        bestill.leggTilBestilling("en@gmail.no", bil5);

        bil5.setFarge("Rød");
        bil5.setFelger("Standard");
        bestill.leggTilBestilling("to@gmail.no", bil5);
        bestill.skrivBestillingTilFil();

        try {
            Bil lestBil1 = bestill.hentBestillinger("en@gmail.no");
            assertEquals("Sedan", lestBil1.getModell());
            assertEquals("Blå", lestBil1.getFarge());
            assertEquals("Sport", lestBil1.getFelger());

            Bil lestBil2 = bestill.hentBestillinger("to@gmail.no");
            assertEquals("Sedan", lestBil2.getModell());
            assertEquals("Rød", lestBil2.getFarge());
            assertEquals("Standard", lestBil2.getFelger());

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
}