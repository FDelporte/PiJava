package be.webtechie;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        FxScreen demoScreen = new FxScreen();

        Scene scene = new Scene(demoScreen.getScreen(), 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}