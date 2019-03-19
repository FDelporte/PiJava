package be.webtechie;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.Tile.SkinType;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.tools.Location;
import java.util.Locale;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class MainWithTilesFX extends Application {

    // https://openjfx.io/openjfx-docs/#install-javafx

    @Override
    public void start(Stage stage) {
        final String javaVersion = System.getProperty("java.version");
        final String javaFxVersion = System.getProperty("javafx.version");

        var locale = new Locale("nl", "be");

        var textTile = TileBuilder.create()
                .skinType(SkinType.TEXT)
                .prefSize(200, 200)
                .title("Version info")
                .description("Java: " + javaVersion + "\nJavaFX: " + javaFxVersion)
                .descriptionAlignment(Pos.TOP_CENTER)
                .textVisible(true)
                .build();

        var clockTile = TileBuilder.create()
                .skinType(SkinType.CLOCK)
                .prefSize(200, 200)
                .title("Klok")
                .dateVisible(true)
                .valueVisible(false)
                .textVisible(false)
                .locale(locale)
                .running(true)
                .build();

        var worldTile = TileBuilder.create()
                .prefSize(200, 200)
                .skinType(SkinType.WORLDMAP)
                .title("WorldMap Tile")
                .text("Whatever text")
                .textVisible(false)
                .build();

        var mapTile = TileBuilder.create()
                .skinType(SkinType.MAP)
                .prefSize(200, 200)
                .title("Map")
                .text("Some text")
                .description("Description")
                .currentLocation(new Location(50.900028, 3.017390, "Home", Tile.TileColor.MAGENTA.color))
                .pointsOfInterest(new Location(50.901098, 3.019505, "School", Tile.TileColor.RED.color),
                        new Location(50.900131, 3.021212, "Shop", Tile.TileColor.BLUE.color))
                .mapProvider(Tile.MapProvider.TOPO)
                .build();

        var ledButton = new Button("Toggle LED on GPIO 22");

        /* ledButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                GPIO.toggleLed();
            }
        }); */

        var ledButtonTile = TileBuilder.create()
                .skinType(SkinType.CUSTOM)
                .prefSize(200, 200)
                .title("GPIO")
                .text("Trigger LED toggling on the board")
                .graphic(ledButton)
                .roundedCorners(false)
                .build();

        var webView = new WebView();
        webView.getEngine().load("https://webtechie.be"); // DoorBird HTML widget "http://xxx/bha-api/view.html"

        Scene scene = new Scene(new HBox(new VBox(textTile, clockTile, worldTile, mapTile, ledButtonTile), webView), 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}