package be.webtechie.gpio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Change a pin state using WiringPi and command line calls
 * http://wiringpi.com/the-gpio-utility/
 */
public class Gpio {

    /**
     * Initialize the pin so it can be toggled.
     *
     * @param pin The pin number according to the WiringPi numbering scheme
     */
    public static void initiatePin(final int pin) {
        /* final List<String> cmd = new ArrayList<>();
        cmd.add("gpio");
        cmd.add("mode");
        cmd.add(String.valueOf(pin));
        cmd.add("out"); */

        execute("gpio mode " + pin + " out");
    }

    /**
     * Set the state of the pin high or low.
     *
     * @param pin The pin number according to the WiringPi numbering scheme
     * @param on True or False
     */
    public static void setPinState(final int pin, final boolean on) {
        /* final List<String> cmd = new ArrayList<>();
        cmd.add("gpio");
        cmd.add("write");
        cmd.add(String.valueOf(pin));
        cmd.add(on ? " 1" : " 0"); */

        execute("gpio write " + pin + (on ? " 1" : " 0"));
    }

    /**
     * Execute the given command.
     *
     * @param cmd String command to be executed.
     */
    private static void execute(String cmd) {
        try {
            Process p = Runtime.getRuntime().exec(cmd);

            // get the error stream of the process and print it
            InputStream error = p.getErrorStream();
            for (int i = 0; i < error.available(); i++) {
                System.out.println("" + error.read());
            }

            p.destroy();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}