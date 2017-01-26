#!/bin/sh

mkdir -p /media/mmcblk0p1

mount /dev/mmcblk0p1 /media/mmcblk0p1

scp matteo@192.168.85.9:/tftpboot/beaglebone/ramdisk-bbb.gz /media/mmcblk0p1/boot/

umount /media/mmcblk0p1