package project;

import java.awt.Desktop;
import java.net.URI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AppKontroller {
    @FXML private TextField epost;
    @FXML private TextField pris;

    private String modell;
    private String farge;
    private String felger;

    @FXML
    private Button knapp1;
    
    @FXML
    private Button knapp2;
    
    @FXML
    private Button knapp3;

    @FXML
    public void initialize() {
        knapp1.setStyle("-fx-background-color: black; -fx-text-fill: white;");
        knapp2.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
        knapp3.setStyle("-fx-background-color: red; -fx-text-fill: white;");
    }

    @FXML
    private Label feilmeldingLabel; 


    @FXML
    private void avsluttProgram(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void mailTilKundeservice(ActionEvent event) {
        try {
            Desktop.getDesktop().mail(new URI("mailto:kundeservice@vedvin.no"));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Feil!");
            alert.setHeaderText("Får ikke åpnet epostklienten");
            alert.setContentText(e.getMessage()); // Setter feilmeldingsteksten
            alert.showAndWait(); // Viser dialogboksen og venter på at brukeren lukker den
        }
    }

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
    private ImageView bildeVisning;

    @FXML
    public void resultat(){
        // Endre bilde i appen
        String bildeStreng = modell + farge + felger + ".jpg";
        Image bilde = new Image("file:/Users/caspergavin/VSCode/TDT4100/prosjekt_objekt/src/main/java/project/bilder/" + bildeStreng);
        bildeVisning.setImage(bilde);

        // Vise pris i appen
        // Må først lage et nytt bil-objekt

        Bil bil = new Bil();
        bil.setModell(modell);
        bil.setFarge(farge);
        bil.setFelger(felger);

        // Bruker deretter metoden regnet ut i Priskalkulator-klassen
        Priskalkulator kalkulator = new Priskalkulator(bil);
        double bilPris = kalkulator.beregnPris();
        pris.setText(bilPris + " kr (ink. MVA)");

    }

    @FXML
    public void bestill(){
        
        try {
            String epostStr = epost.getText();
            BestillBil bestilling = new BestillBil();

            if (bestilling.sjekkEpost(epostStr)) {
                Bil bil = new Bil();
                bil.setModell(modell);
                bil.setFarge(farge);
                bil.setFelger(felger);

                //Legger inn bestilling og skriver til fil
                bestilling.leggTilBestilling(epostStr, bil);
                bestilling.skrivBestillingTilFil(epostStr, bil);
            }

        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Feil!");
            alert.setHeaderText("Ugyldig e-post");
            alert.setContentText(e.getMessage()); // Setter feilmeldingsteksten
            alert.showAndWait(); // Viser dialogboksen og venter på at brukeren lukker den
        }

        //Lage ny bestilling 
        // BestillBil bestilling = new BestillBil();
 
        


        
        

        
    }
}