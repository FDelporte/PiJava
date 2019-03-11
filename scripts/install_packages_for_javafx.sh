#!/usr/bin/env bash

# Script from http://docs.gluonhq.com/embedded/

# Update the package list
sudo apt-get update

# Get the packages
sudo apt-get install libgtk2.0.0
sudo apt-get install libxtst6

# Go to usr lib directory
cd /usr/lib

# Make links to the packages
sudo ln -s /opt/vc/lib/libbrcmEGL.so libEGL.so
sudo ln -s /opt/vc/lib/libbrcmGLESv2.so libGLESv2.so

echo "Packages configured for graphical software like JavaFX"