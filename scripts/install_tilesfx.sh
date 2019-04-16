#!/usr/bin/env bash

# Make sure we are in the home directory
cd /home/pi

# Download the TilesFX module from Maven
wget -O tilesfx-11.1.jar http://central.maven.org/maven2/eu/hansolo/tilesfx/11.1/tilesfx-11.1.jar

# Make a directory in opt
sudo mkdir /opt/tilesfx11

# Move the downloaded file to opt
sudo mv tilesfx-11.1.jar /opt/tilesfx11

echo "TilesFX 11 was downloaded and installed"
