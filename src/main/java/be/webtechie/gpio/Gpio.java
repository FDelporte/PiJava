package be.webtechie.gpio;

import java.io.BufferedReader;
import java.io.IOException;
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
        final List<String> cmd = new ArrayList<>();
        cmd.add("gpio");
        cmd.add("mode");
        cmd.add(String.valueOf(pin));
        cmd.add("out");

        execute(cmd);
    }

    /**
     * Set the state of the pin high or low.
     *
     * @param pin The pin number according to the WiringPi numbering scheme
     * @param on True or False
     */
    public static void setPinState(final int pin, final boolean on) {
        final List<String> cmd = new ArrayList<>();
        cmd.add("gpio");
        cmd.add("write");
        cmd.add(String.valueOf(pin));
        cmd.add(on ? " 1" : " 0");

        execute(cmd);
    }

    /**
     * Execute the given command.
     *
     * @param cmd List of string commands to be executed.
     */
    private static void execute(List<String> cmd) {
        ProcessBuilder processBuilder = new ProcessBuilder();

        // Run this on Windows, cmd, /c = terminate after this run
        processBuilder.command(cmd);

        try {
            Process process = processBuilder.start();

            // blocked :(
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();

            System.out.println("Command exited with error code : " + exitCode);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
}