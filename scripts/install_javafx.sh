#!/usr/bin/env bash

# Script from http://docs.gluonhq.com/embedded/

# Make sure we are in the home directory
cd /home/pi

# Download the JavaFX 11 build from GluonHQ
wget -O armv6hf-sdk-11-ea+25.zip http://gluonhq.com/download/javafx-11-ea-sdk-armv6hf

# Move the downloaded file to /opt
sudo mv armv6hf-sdk-11-ea+25.zip /opt

# Use the /opt directory
cd /opt

# Unzip the downloaded file
sudo unzip armv6hf-sdk-11-ea+25.zip /opt

# Remove the downloaded file
sudo rm armv6hf-sdk-11-ea+25.zip

echo "JavaFX 11 was downloaded and installed"