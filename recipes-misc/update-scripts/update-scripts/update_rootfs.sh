#!/bin/sh

mkdir -p /media/mmcblk1p2

mount /dev/mmcblk1p2 /media/mmcblk1p2

rm -rf /media/mmcblk1p2/*

scp matteo@192.168.85.9:/tftpboot/beaglebone/bbb-printer-image.tar.gz /media/mmcblk1p2/

cd /media/mmcblk1p2/

tar -zxvf bbb-printer-image.tar.gz

cd

sync

umount /media/mmcblk1p2
