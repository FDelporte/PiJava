package be.webtechie.gpio;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;

public class GPIO {
    // https://funprojects.blog/2018/03/18/control-raspberry-pi-gpio-with-java/
    // curl -s get.pi4j.com | sudo bash
    public static void toggleLed() {
        try {
            System.out.println("GPIO Digital Output Example ... started.");

            // Get a handle to the GPIO controller
            final GpioController gpio = GpioFactory.getInstance();

            // Creating the pin object
            final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_22);

            // Turn pin on and wait 2 seconds
            pin.high();
            System.out.println("Pin High");
            Thread.sleep(2000);

            // Turn off and wait 2 seconds
            pin.low();
            System.out.println("Pin Low");
            Thread.sleep(2000);

            // Turn pin on for 2 second and then off
            System.out.println("Pin High for 2 seconds");
            pin.pulse(2000, true);

            // Release the GPIO controller resources
            gpio.shutdown();
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }
}
