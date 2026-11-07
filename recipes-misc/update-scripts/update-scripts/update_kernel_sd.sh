#!/bin/sh
# Update the recovery uSD kernel and device tree (boot partition, /boot).
. /usr/share/update-scripts/update-common.sh
update_open

zimage=$(need_update zImage) || exit 1
dtb=$(need_update am335x-bbb-printer.dtb) || exit 1
mount_target ${SD}p1
mkdir -p $TARGET_MNT/boot
cp "$zimage" "$dtb" $TARGET_MNT/boot/ || die "copying kernel failed"
finish
