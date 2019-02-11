#!/bin/bash

echo "Checking the Raspian version"
echo ""

echo -n "Debian version: "
cat /etc/debian_version

echo -n "Kernel version: "
uname -r

echo ""

echo "Full version info: "
uname -a

echo ""
echo "Even more version info: "
cat /proc/version
