package project;

public class Bil implements BilOppsett{
    // Attributter:
    private String modell;
    private String farge;
    private String felger;

    // Get-metoder
    @Override
    public String getFarge() {
        return farge;
    }

    @Override
    public String getModell() {
        return modell;
    }

    @Override
    public String getFelger() {
        return felger;
    }

    // Set-metoder
    @Override
    public void setModell(String modell) {
        this.modell = modell;
    }

    @Override
    public void setFarge(String farge) {
        this.farge = farge;
    }

    @Override
    public void setFelger(String felger) {
        this.felger = felger;
    }

    @Override
    public String toString() {
        return "Bil [modell=" + modell + ", farge=" + farge + ", felger=" + felger + "]";
    }

    
}