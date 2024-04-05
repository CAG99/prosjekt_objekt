package project;

import java.util.List;

public interface Konfigurere {
    // Returnerer den nåværende konfigurasjonens beskrivelse som en streng
    String hentBeskrivelse();

    // Oppdaterer konfigurasjonen basert på brukerinput
    void oppdaterKonfigurasjon(String konfigurasjon);

    // Validerer om det foreslåtte konfigurasjonsvalget er gyldig
    boolean validerKonfigurasjon(String konfigurasjon);

    // Henter en liste over tilgjengelige alternativer for konfigurasjonen
    List<String> tilgjengeligeAlternativer();
}
