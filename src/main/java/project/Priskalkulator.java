package project;

public class Priskalkulator {
    private String modell;
    private String farge;
    private String felger;

    // Metode for å beregne pris 
    public double beregnPris(Bil bil){
        modell = bil.getModell();
        farge = bil.getFarge();
        felger = bil.getFelger();

        int prisModell = 0;
        int prisFarge = 0;
        int prisFelger = 0;
        double mva = 1.25;
        
        switch (modell) { // Bruker switch for å legge til riktig pris basert på modell
            case "SUV":
                prisModell = 1034990;
                break;
        
            case "Sedan":
                prisModell = 989990;
                break;
        }

        switch (farge) { // Bruker switch for å legge til riktig pris basert på farge
            case "Rød":
                prisFarge = 24000;
                break;
        
            case "Svart":
                prisFarge = 15000;
                break;
                
            case "Blå":
                prisFarge = 15000;
                break;
        }

        switch (felger) { // Bruker switch for å legge til riktig pris basert på felger
            case "Sport":
                if (modell == "SUV") { // Ulik pris for sportsfelger til SUV og sedan 
                    prisFelger = 53000;
                }
                else {
                    prisFelger = 43000;
                }
                break;
        
            case "Standard":
                prisFelger = 0;
                break;
        }
        
        return (prisModell+prisFarge+prisFelger)*mva; // Metoden returnerer prisen som en double 
    }
}

// Liste over priser: 

// SUV - 1 034 990  
// SUV - svart = 15 000
// SUV - blå = 15 000
// SUV - rød = 24 000
// SUV - felg standard = 0
// SUV - felg sport = 53 000
// moms 1.25 %
 
// Sedan - 989 990 
// Sedan - svart = 15 000
// Sedan - blå = 15 000
// Sedan - rød = 24 000
// Sedan - felg standard = 0
// Sedan - felg sport = 43 000
// moms 1.25 %