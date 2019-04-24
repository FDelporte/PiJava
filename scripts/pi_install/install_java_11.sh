#!/usr/bin/env bash

# Script from http://docs.gluonhq.com/embedded/

# Make sure we are in the home directory
cd /home/pi

# Download the Java 11 distribution from BellSoft
wget https://github.com/bell-sw/Liberica/releases/download/jdk11%2B28/bellsoft-jdk11+28-linux-arm32-vfp-hflt.tar.gz

# Move the downloaded file to /opt
sudo mv bellsoft-jdk11+28-linux-arm32-vfp-hflt.tar.gz /opt

# Use the /opt directory
cd /opt

# Untar the downloaded file
sudo tar -xvzf bellsoft-jdk11+28-linux-arm32-vfp-hflt.tar.gz

# Remove the downloaded file
sudo rm bellsoft-jdk11+28-linux-arm32-vfp-hflt.tar.gz

echo "Java 11 was downloaded and installed"