package project;

import java.util.Arrays;
import java.util.List;

public class Felger implements Konfigurere {
    private String felgtype;
    private List<String> tilgjengeligeFelger = Arrays.asList("Standard", "Sport", "Luksus");

    @Override
    public String hentBeskrivelse() {
        return "Nåværende felgtype: " + felgtype;
    }

    @Override
    public void oppdaterKonfigurasjon(String konfigurasjon) {
        if (validerKonfigurasjon(konfigurasjon)) {
            this.felgtype = konfigurasjon;
        }
    }

    @Override
    public boolean validerKonfigurasjon(String konfigurasjon) {
        return tilgjengeligeFelger.contains(konfigurasjon);
    }

    @Override
    public List<String> tilgjengeligeAlternativer() {
        return tilgjengeligeFelger;
    }
}
