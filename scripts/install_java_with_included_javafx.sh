#!/usr/bin/env bash

# Script from http://docs.gluonhq.com/embedded/
# Adapted for https://www.bell-sw.com/pages/java-11.0.2

# Make sure we are in the home directory
cd /home/pi

# Download the Java 11 distribution from BellSoft
wget https://download.bell-sw.com/java/11.0.2/bellsoft-jdk11.0.2-linux-arm32-vfp-hflt.tar.gz

# Move the downloaded file to /opt
sudo mv bellsoft-jdk11.0.2-linux-arm32-vfp-hflt.tar.gz /opt

# Use the /opt directory
cd /opt

# Untar the downloaded file
sudo tar -xvzf bellsoft-jdk11.0.2-linux-arm32-vfp-hflt.tar.gz

# Remove the downloaded file
sudo rm bellsoft-jdk11.0.2-linux-arm32-vfp-hflt.tar.gz

echo "Java 11 including JavaFX 11 and Device IO API was downloaded and installed"