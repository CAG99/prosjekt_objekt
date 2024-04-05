package project;

import java.util.Arrays;
import java.util.List;

public class BilFarge implements Konfigurere{
    private String farge;
    private List<String> tilgjengeligeFarger = Arrays.asList("Rød", "Blå", "Svart", "Hvit");

    @Override
    public String hentBeskrivelse() {
        return "Nåværende farge: " + farge;
    }

    @Override
    public void oppdaterKonfigurasjon(String konfigurasjon) {
        if (validerKonfigurasjon(konfigurasjon)) {
            this.farge = konfigurasjon;
        }
    }

    @Override
    public boolean validerKonfigurasjon(String konfigurasjon) {
        return tilgjengeligeFarger.contains(konfigurasjon);
    }

    @Override
    public List<String> tilgjengeligeAlternativer() {
        return tilgjengeligeFarger;
    } 
}
