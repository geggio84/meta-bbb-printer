#!/bin/sh
# Update the eMMC U-Boot splash images (boot partition, /boot).
. /usr/share/update-scripts/update-common.sh
update_open

files=""
for f in splash.bmp recovery_splash.bmp; do
	[ -f $UPDATE_MNT/$f ] && files="$files $UPDATE_MNT/$f"
done
[ -n "$files" ] || die "no splash.bmp or recovery_splash.bmp on the USB drive"
mount_target ${EMMC}p1
mkdir -p $TARGET_MNT/boot
cp $files $TARGET_MNT/boot/ || die "copying splash failed"
finish
