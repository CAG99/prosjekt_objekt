package project;

import java.awt.Desktop;
import java.io.FileNotFoundException;
import java.net.URI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextInputDialog;
import java.util.Optional;


public class AppKontroller {
    private String modell;
    private String farge;
    private String felger;
    private BestillBil bestillinger;

    // Konstruktør som oppretter en ny liste med bestillinger
    public AppKontroller(){
        bestillinger = new BestillBil();
    }

    // Tekstfelt
    @FXML private TextField epost;
    @FXML private TextField pris;
    // Knapper
    @FXML private Button knapp1;
    @FXML private Button knapp2;
    @FXML private Button knapp3;
    @FXML private Button suv_knapp;
    @FXML private Button sedan_knapp;
    @FXML private Button standard_knapp;
    @FXML private Button sport_knapp;
    // Bilde av bilen
    @FXML private ImageView bildeVisning;


    // Endrer farger på knappene
    @FXML
    public void initialize() {
        knapp1.setStyle("-fx-background-color: black; -fx-text-fill: white;");
        knapp2.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
        knapp3.setStyle("-fx-background-color: red; -fx-text-fill: white;");
    }

    // Metode for å endre farge på knapper når markert
    private void markereKnapp(Button aktivKnapp) {        
        // Markere den aktive knappen med en blå kant og lys bakgrunn
        aktivKnapp.setStyle("-fx-border-color: blue; -fx-border-width: 2px; -fx-background-color: lightblue;");
    }

    // Metode for å avslutte programmet med "Avslutt knapp"
    @FXML
    private void avsluttProgram(ActionEvent event) {
        System.exit(0);
    }

    // Metode for å sende mail til kundeservice :) 
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

    // Metoder for alle knappene. Trykker man feks først på SUV, og deretter på Sedan overskrives modell-variabelen 
    // Implementerer metoden for å vise at knappen er markert
    @FXML
    public void suv(){
        modell = "SUV";
        markereKnapp(suv_knapp);
        sedan_knapp.setStyle("");
    }
    @FXML
    public void sedan(){
        modell = "Sedan";
        markereKnapp(sedan_knapp);
        suv_knapp.setStyle("");
    }
    @FXML
    public void rød(){
       farge = "Rød";
       markereKnapp(knapp3);
       knapp1.setStyle("-fx-background-color: black; -fx-text-fill: white;");
       knapp2.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
    }
    @FXML
    public void svart(){
        farge = "Svart";
        markereKnapp(knapp1);
        knapp2.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
        knapp3.setStyle("-fx-background-color: red; -fx-text-fill: white;");
    }
    @FXML
    public void blå(){
        farge = "Blå";
        markereKnapp(knapp2);
        knapp1.setStyle("-fx-background-color: black; -fx-text-fill: white;");
        knapp3.setStyle("-fx-background-color: red; -fx-text-fill: white;");
    }
    @FXML
    public void standard(){
        felger = "Standard";
        markereKnapp(standard_knapp);
        sport_knapp.setStyle("");
    } 
    @FXML
    public void sport(){
        felger = "Sport";
        markereKnapp(sport_knapp);
        standard_knapp.setStyle("");
    }
    
    // Metode for å hente bestilling (lese fra fil)
    @FXML
    public void visBestilling() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Hent Bestilling");
        dialog.setHeaderText("Søk etter bestilling");
        dialog.setContentText("Vennligst skriv inn din e-post:");

        // Viser dialogboksen og venter på input
        Optional<String> epost_input = dialog.showAndWait();

        epost_input.ifPresent(epost -> {
            try {
                
                if (bestillinger.hentBestillinger(epost) == null) { // Sjekker om det IKKE er knyttet en bestilling til eposten
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Feil!");
                    alert.setHeaderText("Finner ikke bestilling");
                    alert.setContentText("Det finnes ingen bestilling knyttet til e-posten du skrev inn"); // Setter feilmeldingsteksten manuelt 
                    alert.showAndWait(); // Viser dialogboksen og venter på at brukeren lukker den

                } else { // Hvis det finnes en bestilling knyttet til eposten
                    Bil bil = bestillinger.hentBestillinger(epost);
                    String bildeStreng = bil.getModell() + bil.getFarge() + bil.getFelger()  + ".jpg";
                    Priskalkulator kalk = new Priskalkulator();
                    double pris = kalk.beregnPris(bil);

                    // Oppretter en ny scene med VBox
                    VBox root = new VBox();
                    root.setSpacing(10); // Setter avstand mellom elementene

                    // Laster inn bildet
                    Image bilde = new Image("file:/Users/caspergavin/VSCode/TDT4100/prosjekt_objekt/src/main/java/project/bilder/" + bildeStreng);
                    ImageView bildeView = new ImageView(bilde);
                    
                    // Tilpasser størrelsen på bildet 
                    bildeView.setFitHeight(240); // Høyde
                    bildeView.setPreserveRatio(true); // Bevarer bildeforholdet

                    // Labels for e-post og pris
                    Label epostLabel = new Label("E-post: " + epost);
                    Label prisLabel = new Label("Pris: " + pris + " kr");

                    // Legger til bildet, epost og pris til VBox
                    root.getChildren().addAll(bildeView, epostLabel, prisLabel);

                    // Oppretter et nytt vindu
                    Scene scene = new Scene(root, 300, 400); // Juster størrelsen etter behov
                    Stage nyttVindu = new Stage();
                    nyttVindu.setTitle("Bilinformasjon");
                    nyttVindu.setScene(scene);

                    // Viser det nye vinduet
                    nyttVindu.show();
                }
            
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
            }
        });
    }
    
    // Metode for å vise bilde og pris når man konfigurerer bilen
    @FXML
    public void resultat(){
        // Viser / endrer bilde
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
        Priskalkulator kalkulator = new Priskalkulator();
        double bilPris = kalkulator.beregnPris(bil);
        pris.setText(bilPris + " kr (ink. MVA)");
    }

    // Metode som lager en bestilling og skriver til fil
    @FXML
    public void bestill(){

        if (modell == null || farge == null || felger == null) { // Sjekker om det mangler en av valgene (modell, farge, felger)
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Feil!");
            alert.setHeaderText("Mangler spesifikasjon");
            alert.setContentText("Må velge modell, farge og felger for å bestille"); // Setter feilmeldingsteksten manuelt
            alert.showAndWait(); // Viser dialogboksen og venter på at brukeren lukker den

        } else { // Hvis alle valgene er gjort opprettes en bestilling
            try {
                String epostStr = epost.getText();
                
                if (bestillinger.sjekkEpost(epostStr)) {
                    Bil bil = new Bil();
                    bil.setModell(modell);
                    bil.setFarge(farge);
                    bil.setFelger(felger);

                    //Legger inn bestilling og skriver til fil
                    bestillinger.leggTilBestilling(epostStr, bil); 
                    bestillinger.skrivBestillingTilFil();
                }

            } catch (IllegalArgumentException e) { // Dersom eposten mangler '@'
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Feil!");
                alert.setHeaderText("Ugyldig e-post");
                alert.setContentText(e.getMessage()); // Setter feilmeldingsteksten
                alert.showAndWait(); // Viser dialogboksen og venter på at brukeren lukker den
            }

            catch (IllegalStateException e) { // Dersom eposten mangler gyldig landskode
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Feil!");
                alert.setHeaderText("For mange bestillinger");
                alert.setContentText(e.getMessage()); // Setter feilmeldingsteksten
                alert.showAndWait(); // Viser dialogboksen og venter på at brukeren lukker den
            }
        }
        
        

                
    }
}