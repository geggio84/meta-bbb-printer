#!/bin/sh

mkdir -p /media/mmcblk1p1

mount /dev/mmcblk1p1 /media/mmcblk1p1

scp matteo@192.168.85.9:/tftpboot/beaglebone/\{MLO,u-boot.img\} /media/mmcblk1p1/

umount /media/mmcblk1p1
