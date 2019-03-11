#!/usr/bin/env bash

# Script from http://docs.gluonhq.com/embedded/

# Make sure we are in the home directory
sudo apt-get update
sudo apt-get install libgtk2.0.0
sudo apt-get install libxtst6
cd /usr/lib
sudo ln -s /opt/vc/lib/libbrcmEGL.so libEGL.so
sudo ln -s /opt/vc/lib/libbrcmGLESv2.so libGLESv2.so

echo "Packages configured for graphical software like JavaFX"