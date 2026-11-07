#!/bin/sh
# Update the eMMC kernel and device tree (boot partition), and the
# kernel modules (rootfs) if modules-bbb-printer.tgz is provided.
. /usr/share/update-scripts/update-common.sh
update_open

zimage=$(need_update zImage) || exit 1
dtb=$(need_update am335x-bbb-printer.dtb) || exit 1
modules=$(find_update 'modules-bbb-printer*.tgz')

mount_target ${EMMC}p1
cp "$zimage" "$dtb" $TARGET_MNT/ || die "copying kernel failed"

if [ -n "$modules" ]; then
	if running_from_emmc; then
		dest=/
	else
		mount_target ${EMMC}p2 $TARGET2_MNT
		dest=$TARGET2_MNT
	fi
	echo "Installing kernel modules..."
	tar -xzf "$UPDATE_MNT/$modules" -C $dest || die "extracting $modules failed"
else
	echo "WARNING: no modules-bbb-printer*.tgz: kernel modules not updated"
fi

running_from_emmc || reset_bootcount
finish
