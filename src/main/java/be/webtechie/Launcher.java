package be.webtechie;

/**
 * An additional Launcher class is needed to be able to start the JavaFX application from a JAR file.
 * https://stackoverflow.com/questions/53533486/how-to-open-javafx-jar-file-with-jdk-11
 * https://stackoverflow.com/questions/52653836/maven-shade-javafx-runtime-components-are-missing/52654791#52654791
 *
 * The generated JAR in the out directory can be started on Windows with following command:
 * "c:\Program Files\Java\jdk-11.0.2\bin\java.exe"
 *      --module-path "c:\Program Files\Java\javafx-sdk-11.0.2\lib"
 *      --add-modules=javafx.controls
 *      -jar pijava-0.1-SNAPSHOT.jar
 */
public class Launcher {
    public static void main(String[] args) {
        Main.main(args);
    }
}
