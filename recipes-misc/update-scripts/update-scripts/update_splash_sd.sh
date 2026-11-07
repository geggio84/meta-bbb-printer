#!/bin/sh
# Update the recovery uSD U-Boot splash images.
. /usr/share/update-scripts/update-common.sh
update_open

files=""
for f in splash.bmp recovery_splash.bmp; do
	[ -f $UPDATE_MNT/$f ] && files="$files $UPDATE_MNT/$f"
done
[ -n "$files" ] || die "no splash.bmp or recovery_splash.bmp on the USB drive"
mount_target ${SD}p1
cp $files $TARGET_MNT/ || die "copying splash failed"
finish
