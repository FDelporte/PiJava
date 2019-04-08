sudo /opt/jdk-11.0.2/bin/java \
	--module-path=/opt/tilesfx11 \
	--add-modules=eu.hansolo.tilesfx \
	-jar /home/pi/git/PiJava/out/pijava-0.1-SNAPSHOT.jar \
	-Dprism.verbose=true \
	-Dembedded=monocle \
	-Dglass.platform=Monocle

#	--module-path=/opt/tilesfx11 \
#	--add-modules=eu.hansolo \
