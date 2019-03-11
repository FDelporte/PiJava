package be.webtechie;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.tools.Location;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Locale;

public class Main extends Application {

    // https://openjfx.io/openjfx-docs/#install-javafx

    @Override
    public void start(Stage stage) {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");

        Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");

        var clockTile = TileBuilder.create()
                .skinType(Tile.SkinType.CLOCK)
                .prefSize(200, 200)
                .title("Clock Tile")
                .text("Whatever text")
                .dateVisible(true)
                .locale(Locale.US)
                .running(true)
                .build();

        var worldTile = TileBuilder.create()
                .prefSize(200, 200)
                .skinType(Tile.SkinType.WORLDMAP)
                .title("WorldMap Tile")
                .text("Whatever text")
                .textVisible(false)
                .build();

        var mapTile = TileBuilder.create()
                .skinType(Tile.SkinType.MAP)
                .prefSize(500, 200)
                .title("Map")
                .text("Some text")
                .description("Description")
                .currentLocation(new Location(50.900028, 3.017390, "Home", Tile.TileColor.MAGENTA.color))
                .pointsOfInterest(new Location(50.901098, 3.019505, "School", Tile.TileColor.RED.color),
                        new Location(50.900131, 3.021212, "Shop", Tile.TileColor.BLUE.color))
                .mapProvider(Tile.MapProvider.TOPO)
                .build();

        Scene scene = new Scene(new HBox(clockTile, worldTile, mapTile), 800, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}