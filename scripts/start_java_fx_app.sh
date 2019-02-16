sudo /opt/jdk-11/bin/java \
	--module-path=/opt/armv6hf-sdk/lib \
	--add-modules=javafx.controls \
	-cp classes/ \
	-Dprism.verbose=true \
	-Dembedded=monocle \
	-Dglass.platform=Monocle sample.Main
