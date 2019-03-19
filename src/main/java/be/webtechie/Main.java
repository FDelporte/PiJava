package be.webtechie;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.util.Locale;

public class Main extends Application {

    // https://openjfx.io/openjfx-docs/#install-javafx

    @Override
    public void start(Stage stage) {
        final String javaVersion = System.getProperty("java.version");
        final String javaFxVersion = System.getProperty("javafx.version");

        var webView = new WebView();
        webView.getEngine().load("https://webtechie.be"); // DoorBird HTML widget "http://xxx/bha-api/view.html"

        Scene scene = new Scene(webView, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}