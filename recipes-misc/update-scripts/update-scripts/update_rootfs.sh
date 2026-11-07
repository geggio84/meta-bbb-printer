#!/bin/sh
# Replace the eMMC root filesystem with bbb-printer-image*.rootfs.tar.gz.
. /usr/share/update-scripts/update-common.sh
require_recovery
update_open

tarball=$(need_update 'bbb-printer-image*.tar.gz') || exit 1
mount_target ${EMMC}p2
echo "Erasing eMMC rootfs..."
rm -rf $TARGET_MNT/* $TARGET_MNT/.[!.]* 2>/dev/null
echo "Extracting $tarball..."
tar -xzf "$tarball" -C $TARGET_MNT || die "extracting $tarball failed"

reset_bootcount
finish
