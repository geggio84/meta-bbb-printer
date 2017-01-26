#!/bin/sh

mkdir -p /media/mmcblk1p1

mount /dev/mmcblk1p1 /media/mmcblk1p1

scp matteo@192.168.85.9:/tftpboot/beaglebone/\{splash.bmp,recovery_splash.bmp\} /media/mmcblk1p1/boot/

umount /media/mmcblk1p1
