package project;

import java.util.Arrays;
import java.util.List;

public class Bilmodell implements Konfigurere {
    private String modell;
    private List<String> tilgjengeligeModeller = Arrays.asList("Sedan", "SUV");

    @Override
    public String hentBeskrivelse() {
        return "Nåværende bilmodell: " + modell;
    }

    @Override
    public void oppdaterKonfigurasjon(String konfigurasjon) {
        if (validerKonfigurasjon(konfigurasjon)) {
            this.modell = konfigurasjon;
        }
    }

    @Override
    public boolean validerKonfigurasjon(String konfigurasjon) {
        return tilgjengeligeModeller.contains(konfigurasjon);
    }

    @Override
    public List<String> tilgjengeligeAlternativer() {
        return tilgjengeligeModeller;
    }
}

