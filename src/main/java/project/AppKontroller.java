package project;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AppKontroller {
    @FXML private TextField epost;
    @FXML private TextArea pris;

    private String modell;
    private String farge;
    private String felger;

    @FXML
    public void suv(){
        modell = "SUV";
    }
    @FXML
    public void sedan(){
        modell = "Sedan";
    }
    @FXML
    public void rød(){
       farge = "Rød";
    }
    @FXML
    public void svart(){
        farge = "Svart";
    }
    @FXML
    public void blå(){
        farge = "Blå";
    }
    @FXML
    public void standard(){
        felger = "Standard";
    } 
    @FXML
    public void sport(){
        felger = "Sport";
    }

    @FXML
    public void bestill(){
        String epost = this.epost.getText();
        BestillBil bestilling = new BestillBil();
        Bil bil = new Bil();
        bil.setModell(modell);
        bil.setFarge(farge);
        bil.setFelger(felger);

        bestilling.leggTilBestilling(bil);

    }
}