#!/bin/sh

mkdir -p /media/mmcblk1p1

mount /dev/mmcblk1p1 /media/mmcblk1p1

scp matteo@192.168.85.9:/tftpboot/beaglebone/\{bbb_printer.dtb.dtb,zImage\} /media/mmcblk1p1/boot/
cp /media/mmcblk1p1/boot/bbb_printer.dtb /media/mmcblk1p1/boot/am335x-boneblack.dtb

umount /media/mmcblk1p1
