package project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BilApp extends Application{

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Bil App");
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Brukergrensesnitt.fxml"))));
        primaryStage.show();
    }

    public static void main(String[] args) {
        BilApp.launch(args);
    }
    
}