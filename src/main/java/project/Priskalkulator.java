package project;

public class Priskalkulator {
    private String modell;
    private String farge;
    private String felger;
    
    public Priskalkulator(Bil bil){
        this.modell = bil.getModell();
        this.farge = bil.getFarge();
        this.felger = bil.getFelger();
    }

    public double beregnPris(){
        int prisModell = 0;
        int prisFarge = 0;
        int prisFelger = 0;
        double mva = 1.25;
        
        switch (modell) {
            case "SUV":
                prisModell = 1034990;
                break;
        
            case "Sedan":
                prisModell = 989990;
                break;
        }

        switch (farge) {
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

        switch (felger) {
            case "Sport":
                if (modell == "SUV") {
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
        
        return (prisModell+prisFarge+prisFelger)*mva;
    }
}



// priser: 

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