package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jsmile.License;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Morbidade hospitalar - Cidades de Santa Catarina - abr/2019 a abr/2020");
        primaryStage.setScene(new Scene(root, 640, 480));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        new License();
        launch(args);
    }
}
