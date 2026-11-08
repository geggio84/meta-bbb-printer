#!/bin/sh
# Write a full bbb-printer-image wic (boot + rootfs partitions) to eMMC.
. /usr/share/update-scripts/update-common.sh
require_recovery
update_open

img=$(need_update 'bbb-printer-image*.wic.xz' 'bbb-printer-image*.wic') || exit 1
echo "Writing $img to eMMC ($EMMC)..."
for p in ${EMMC}p*; do is_mounted $p && umount $p; done
case "$img" in
*.xz) xzcat "$img" ;;
*) cat "$img" ;;
esac | dd of=$EMMC bs=1M || die "writing $EMMC failed"
sync

grow_rootfs $EMMC

reset_bootcount
finish
echo "Reboot with: reboot -f"
