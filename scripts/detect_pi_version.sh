#!/bin/bash

# Based on 
# https://raspberrypi.stackexchange.com/questions/4677/how-can-i-tell-if-i-am-using-the-hard-float-or-the-soft-float-version-of-debian

echo "Checking the Debian float type"
echo ""

echo "Soft float?"

echo -n "   By checking dpkg for armel (if > 0): "
dpkg -l | grep armel | wc -l

echo -n "   By checking if directory exists: "
if [ -d "/lib/arm-linux-gnueabi" ]; then
	echo "Yes"
else
	echo "No"
fi

echo ""

echo "Hard float?"

echo -n "   By checking dpkg for armhf (if > 0): "
dpkg -l | grep armhf | wc -l

echo -n "   By checking if directory exists: "
if [ -d "/lib/arm-linux-gnueabihf" ]; then
	echo "Yes"
else
	echo "No"
fi
 
echo ""

echo -n "dpkg architecture: "
dpkg --print-architecture

