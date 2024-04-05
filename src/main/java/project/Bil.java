package project;

import java.util.Arrays;
import java.util.List;

public class Bil {
    // Hva som skal velges:
    private String modell;
    private String farge;
    private String felger;

    // Lister med valgmuligheter:
    private List<String> modellValg = Arrays.asList("Sedan", "SUV");
    private List<String> fargerValg = Arrays.asList("Rød", "Blå", "Svart");
    private List<String> felgerValg = Arrays.asList("Standard", "Sport");

    public Bil(){
        
    }

    // get metoder
    public String getFarge() {
        return farge;
    }

    public String getModell() {
        return modell;
    }

    public String getFelger() {
        return felger;
    }

    // set metoder
    public void setModell(String modell) {
        this.modell = modell;
    }

    public void setFarge(String farge) {
        this.farge = farge;
    }

    public void setFelger(String felger) {
        this.felger = felger;
    }

    @Override
    public String toString() {
        return "Bil [modell=" + modell + ", farge=" + farge + ", felger=" + felger + "]";
    }

    

    
}