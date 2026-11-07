#!/bin/sh
# Update the recovery uSD bootloader (MLO, u-boot.img).
. /usr/share/update-scripts/update-common.sh
update_open

mlo=$(need_update MLO) || exit 1
uboot=$(need_update u-boot.img) || exit 1
mount_target ${SD}p1
cp "$mlo" "$uboot" $TARGET_MNT/ || die "copying bootloader failed"
finish
