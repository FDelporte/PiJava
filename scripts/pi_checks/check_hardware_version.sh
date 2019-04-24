#!/bin/bash

echo "Checking the PI harware version"
echo ""

echo ""
echo "CPU info: "
cat /proc/cpuinfo

echo ""

echo "CPU current values: "
vcgencmd measure_temp
vcgencmd measure_clock arm
vcgencmd measure_volts core

echo ""

echo -n "Memory: "
free -h
