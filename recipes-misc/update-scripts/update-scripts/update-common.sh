# Common helpers for the bbb-printer update scripts (sourced, not executed).
#
# Update files are read from the uSD partition labelled "UPDATE". In the
# recovery ramdisk that partition is exported to the PC as a USB drive on
# the OTG port: the PC copies the files, ejects the drive, then the script
# detaches it from USB, mounts it read-only, applies the update and
# re-exports it.

EMMC=/dev/mmcblk1
SD=/dev/mmcblk0
UPDATE_MNT=/media/update
TARGET_MNT=/media/target
TARGET2_MNT=/media/target2

die() {
	echo "ERROR: $*" >&2
	exit 1
}

is_mounted() {
	grep -q " $1 " /proc/mounts
}

# Device of the running root filesystem ("rootfs"/"none" in the ramdisk)
root_dev() {
	awk '$2 == "/" { dev = $1 } END { print dev }' /proc/mounts
}

running_from_emmc() {
	case "$(root_dev)" in
	${EMMC}p*|/dev/root) return 0 ;;
	esac
	return 1
}

# Refuse to rewrite the eMMC system we are running from
require_recovery() {
	running_from_emmc && die "running from the eMMC system: boot the uSD recovery to do this"
	return 0
}

update_dev() {
	blkid 2>/dev/null | grep 'LABEL="UPDATE"' | cut -d: -f1 | head -n 1
}

# Mount the update storage read-only, taking it away from the PC
update_open() {
	UPDATE_DEV=$(update_dev)
	[ -n "$UPDATE_DEV" ] || die "no partition labelled UPDATE found (is the recovery uSD card inserted?)"
	command -v usb-gadget >/dev/null && usb-gadget detach
	mkdir -p $UPDATE_MNT
	if ! mount -o ro "$UPDATE_DEV" $UPDATE_MNT; then
		command -v usb-gadget >/dev/null && usb-gadget attach
		die "cannot mount $UPDATE_DEV"
	fi
	trap update_close EXIT
}

# Unmount everything and give the update storage back to the PC
update_close() {
	cd /
	sync
	for m in $TARGET2_MNT $TARGET_MNT $UPDATE_MNT; do
		is_mounted $m && umount $m
	done
	command -v usb-gadget >/dev/null && usb-gadget attach
}

# Newest file in the update storage matching the given pattern(s).
# Prints nothing if there is none.
find_update() {
	(cd $UPDATE_MNT && ls -t $@ 2>/dev/null | head -n 1)
}

# Like find_update, but fail if missing. Prints the full path.
need_update() {
	f=$(find_update "$@")
	[ -n "$f" ] || die "$* not found on the USB drive"
	echo "$UPDATE_MNT/$f"
}

# mount_target <device> [mountpoint]
mount_target() {
	mp=${2:-$TARGET_MNT}
	mkdir -p $mp
	mount "$1" $mp || die "cannot mount $1"
}

# Mark the eMMC system as good again: U-Boot boots it on next reset
reset_bootcount() {
	command -v bootcount >/dev/null && bootcount reset
}

finish() {
	sync
	echo "Done."
}
