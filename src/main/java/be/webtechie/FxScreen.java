package be.webtechie;

import be.webtechie.gpio.Gpio;
import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.Tile.SkinType;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.tools.Location;
import java.util.Locale;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * Helper function to create our GUI.
 */
public class FxScreen {

    /**
     * The pin we are using in our example.
     */
    private static final int LED_PIN = 17;

    /**
     * Builds the GUI for our FX application.
     *
     * @return The GUI as a HBox.
     */
    public static HBox getScreen() {
        // Initialize the led pin
        Gpio.initiatePin(LED_PIN);

        // Get the Java version info
        final String javaVersion = System.getProperty("java.version");
        final String javaFxVersion = System.getProperty("javafx.version");

        // Define our local setting (used by the clock)
        var locale = new Locale("nl", "be");

        // Tile with the Java info
        var textTile = TileBuilder.create()
                .skinType(SkinType.TEXT)
                .prefSize(200, 200)
                .title("Version info")
                .description("Java: " + javaVersion + "\nJavaFX: " + javaFxVersion)
                .descriptionAlignment(Pos.TOP_CENTER)
                .textVisible(true)
                .build();

        // Tile with a clock
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

        // Tile with a map
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

        // Tile with a switch button to turn our LED on or off
        var ledSwitchTile = TileBuilder.create()
                .skinType(SkinType.SWITCH)
                .prefSize(200, 200)
                .title("Gpio " + LED_PIN)
                .roundedCorners(false)
                .build();

        ledSwitchTile.setOnSwitchPressed(e ->  Gpio.setPinState(LED_PIN, true));
        ledSwitchTile.setOnSwitchReleased(e -> Gpio.setPinState(LED_PIN, false));

        // Tile with an exit button to end the application
        var exitButton = new Button("Exit");
        exitButton.setOnAction(e -> ((Stage) exitButton.getScene().getWindow()).close());

        var exitTile = TileBuilder.create()
                .skinType(SkinType.CUSTOM)
                .prefSize(200, 200)
                .title("Quit the application")
                .graphic(exitButton)
                .roundedCorners(false)
                .build();

        // Webview
        var webView = new WebView();
        webView.getEngine().load("https://webtechie.be"); // DoorBird HTML widget "http://xxx/bha-api/view.html"

        var tiles = new VBox(textTile, clockTile, mapTile, ledSwitchTile, exitTile);
        tiles.setMinWidth(200);

        return new HBox(tiles, webView);
    }
}