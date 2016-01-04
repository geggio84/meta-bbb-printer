#!/bin/sh

mkdir -p /media/mmcblk1p1
mkdir -p /media/mmcblk0p1

mount /dev/mmcblk1p1 /media/mmcblk1p1
mount /dev/mmcblk0p1 /media/mmcblk0p1

cp /media/mmcblk1p1/MLO /media/mmcblk0p1/
cp /media/mmcblk1p1/u-boot.img /media/mmcblk0p1/

umount /media/mmcblk1p1
umount /media/mmcblk0p1
