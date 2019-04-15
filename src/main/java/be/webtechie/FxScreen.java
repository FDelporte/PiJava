package be.webtechie;

import be.webtechie.gpio.Gpio;
import eu.hansolo.tilesfx.Tile.SkinType;
import eu.hansolo.tilesfx.TileBuilder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import javafx.geometry.Pos;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Helper function to create our GUI.
 */
public class FxScreen {

    /**
     * The pins we are using in our example.
     */
    private static final int PIN_LED = 3;
    private static final int PIN_BUTTON = 5;

    /**
     * Data series used on the charts.
     */
    private final XYChart.Series<String, Number> seriesTemperatureInside;
    private final XYChart.Series<String, Number> seriesTemperatureOutside;
    private final XYChart.Series<String, Number> seriesButton;

    /**
     * Previous state of the button so we can update the chart when it changes.
     */
    private boolean buttonIsPressed = false;

    /**
     * Flag to keep the threads running or stop then when the close button is clicked.
     */
    private static boolean running = true;

    /**
     * The screen.
     */
    private final HBox screen;

    /**
     * Constructor.
     */
    public FxScreen() {
        // Initialize the pins
        Gpio.initiatePin(PIN_LED, "out");
        Gpio.initiatePin(PIN_BUTTON, "in");

        // Setup the line chart data series
        this.seriesTemperatureInside = new XYChart.Series();
        this.seriesTemperatureInside.setName("Inside temperature");

        this.seriesTemperatureOutside = new XYChart.Series();
        this.seriesTemperatureOutside.setName("Outside temperature");

        this.seriesButton = new XYChart.Series();
        this.seriesButton.setName("Button pressed");

        // Start thread which will generated dummy data
        this.startDemoData();

        // Start thread which read the button state
        this.startButtonRead();

        // Build the screen
        this.screen = buildScreen();
    }

    /**
     * Builds the GUI for our FX application.
     *
     * @return The GUI as a HBox.
     */
    public HBox getScreen() {
        return this.screen;
    }

    /**
     * Build the screen.
     *
     * @return
     */
    private HBox buildScreen() {
        // Get the Java version info
        final String javaVersion = System.getProperty("java.version");
        final String javaFxVersion = System.getProperty("javafx.version");

        // Define our local setting (used by the clock)
        var locale = new Locale("nl", "be");

        // Tile with the Java info
        var textTile = TileBuilder.create()
                .skinType(SkinType.TEXT)
                .prefSize(250, 200)
                .title("Version info")
                .description("Java: " + javaVersion + "\nJavaFX: " + javaFxVersion)
                .descriptionAlignment(Pos.TOP_CENTER)
                .textVisible(true)
                .build();

        // Tile with a clock
        var clockTile = TileBuilder.create()
                .skinType(SkinType.CLOCK)
                .prefSize(250, 200)
                .title("Clock")
                .dateVisible(true)
                .valueVisible(false)
                .textVisible(false)
                .locale(locale)
                .running(true)
                .build();

        // Tile with a switch button to turn our LED on or off
        var ledSwitchTile = TileBuilder.create()
                .skinType(SkinType.SWITCH)
                .prefSize(250, 200)
                .title("Gpio " + PIN_LED)
                .roundedCorners(false)
                .build();

        ledSwitchTile.setOnSwitchReleased(e -> Gpio.setPinState(PIN_LED, ledSwitchTile.isActive()));

        // Tile with an exit button to end the application
        var exitButton = new Button("Exit");
        exitButton.setOnAction(e -> endApplication());

        var exitTile = TileBuilder.create()
                .skinType(SkinType.CUSTOM)
                .prefSize(250, 200)
                .title("Quit the application")
                .graphic(exitButton)
                .roundedCorners(false)
                .build();

        // Line chart example which will get random data from a thread
        var tempartureLineChartTile = TileBuilder.create()
                .skinType(SkinType.SMOOTHED_CHART)
                .prefSize(550, 400)
                .title("Random temperature chart")
                //.animated(true)
                .smoothing(false)
                .series(this.seriesTemperatureInside, this.seriesTemperatureOutside)
                .build();

        // Line chart example which will get the button state from a thread
        var buttonLineChartTile = TileBuilder.create()
                .skinType(SkinType.SMOOTHED_CHART)
                .prefSize(550, 400)
                .title("Button GPIO state")
                //.animated(true)
                .smoothing(false)
                .series(this.seriesButton)
                .build();

        var tiles = new VBox(textTile, clockTile, ledSwitchTile, exitTile); // mapTile
        tiles.setMinWidth(250);

        return new HBox(tiles, new VBox(tempartureLineChartTile, buttonLineChartTile)); // webView
    }

    /**
     * Thread to generate random test temperatures.
     */
    private void startDemoData() {
        Thread t = new Thread(() -> {
            while (running) {
                var timeStamp = new SimpleDateFormat("HH.mm.ss").format(new Date());
                this.seriesTemperatureInside.getData().add(new XYChart.Data(timeStamp, this.randomNumber(17,25)));
                this.seriesTemperatureOutside.getData().add(new XYChart.Data(timeStamp, this.randomNumber(-10,30)));

                try {
                    Thread.sleep(2500);
                } catch (InterruptedException ex) {
                    System.err.println("Data thread got interrupted");
                }
            }
        });

        t.start();
    }

    /**
     * Thread to read the button state.
     */
    private void startButtonRead() {
        Thread t = new Thread(() -> {
            while (running) {
                var buttonPressed = Gpio.getPinState(PIN_BUTTON);

                if (buttonIsPressed != buttonPressed) {
                    buttonIsPressed = buttonPressed;

                    var timeStamp = new SimpleDateFormat("HH.mm.ss").format(new Date());
                    this.seriesButton.getData().add(new XYChart.Data(timeStamp, buttonPressed ? 100 : 0));
                }

                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    System.err.println("Data thread got interrupted");
                }
            }
        });

        t.start();
    }

    /**
     * Generate random number between the given limits.
     *
     * @param min
     * @param max
     * @return
     */
    private int randomNumber(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    /**
     * Stop the threads and close the application.
     */
    private void endApplication() {
        this.running = false;

        ((Stage) this.screen.getScene().getWindow()).close();
    }
}