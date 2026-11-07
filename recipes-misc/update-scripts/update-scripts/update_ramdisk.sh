#!/bin/sh
# Update the recovery ramdisk on the uSD card (boot/ramdisk-bbb.gz).
. /usr/share/update-scripts/update-common.sh
update_open

ramdisk=$(need_update 'ramdisk-bbb*.cpio.gz') || exit 1
mount_target ${SD}p1
mkdir -p $TARGET_MNT/boot
cp "$ramdisk" $TARGET_MNT/boot/ramdisk-bbb.gz || die "copying ramdisk failed"
finish
