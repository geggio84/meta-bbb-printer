#!/bin/sh

mkdir -p /media/mmcblk1p1
mkdir -p /media/mmcblk0p1

mount /dev/mmcblk1p1 /media/mmcblk1p1
mount /dev/mmcblk0p1 /media/mmcblk0p1

cp /media/mmcblk1p1/boot/bbb_printer.dtb /media/mmcblk0p1/boot/
cp /media/mmcblk0p1/boot/bbb_printer.dtb /media/mmcblk0p1/boot/am335x-boneblack.dtb
cp /media/mmcblk1p1/boot/zImage /media/mmcblk0p1/boot/

umount /media/mmcblk1p1
umount /media/mmcblk0p1
