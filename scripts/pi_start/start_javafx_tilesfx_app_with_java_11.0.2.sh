sudo /opt/jdk-11.0.2/bin/java \
	--module-path=/opt/tilesfx11 \
	--add-modules=eu.hansolo.tilesfx \
	-jar ../../out/pijava-0.1-SNAPSHOT.jar \
	-Dprism.verbose=true \
	-Dembedded=monocle \
	-Dglass.platform=Monocle
