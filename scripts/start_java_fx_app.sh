
sudo /opt/jdk-11/bin/java \
	--module-path=/opt/armv6hf-sdk/lib \
	--add-modules=javafx.controls \
	-jar /home/pi/git/PiJava/out/pijava-0.1-SNAPSHOT.jar \
	-Dprism.verbose=true \
	-Dembedded=monocle \
	-Dglass.platform=Monocle 

# error message
# Missing JavaFX application class be.webtechie.Main

