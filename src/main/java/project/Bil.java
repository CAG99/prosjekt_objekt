package project;

public class Bil {
    private String eier;
    private Bilmodell modell;
    private BilFarge farge;
    private Felger felger;

    public Bil(){
        modell = new Bilmodell();
        farge = new BilFarge();
        felger = new Felger();
    }

    public static void main(String[] args) {
        Bil ny = new Bil();

        System.out.println(ny.farge.tilgjengeligeAlternativer());      
    }
    


    
}