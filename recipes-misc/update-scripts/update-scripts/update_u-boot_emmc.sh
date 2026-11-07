#!/bin/sh
# Update the eMMC bootloader (MLO, u-boot.img on the boot partition).
. /usr/share/update-scripts/update-common.sh
update_open

mlo=$(need_update MLO) || exit 1
uboot=$(need_update u-boot.img) || exit 1
mount_target ${EMMC}p1
cp "$mlo" "$uboot" $TARGET_MNT/ || die "copying bootloader failed"

running_from_emmc || reset_bootcount
finish
