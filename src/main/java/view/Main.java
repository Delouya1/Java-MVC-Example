package view;

import controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Model;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxml = new FXMLLoader();
        BorderPane root = fxml.load(getClass().getResource("Window.fxml").openStream());
        WindowController wc = fxml.getController();
        wc.paint();
        Model m = new Model("properties.txt");

        Scene scene = new Scene(root, 300, 250);
        Controller c = new Controller(m,wc);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}