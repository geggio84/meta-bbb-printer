SUMMARY = "bbb-printer recovery ramdisk"
DESCRIPTION = "Small busybox initramfs booted by U-Boot 'ramboot' from the \
uSD card: exports the uSD UPDATE partition as a USB drive on the OTG port, \
provides USB networking with ssh and the update scripts to repair or reflash \
the eMMC system."
LICENSE = "MIT"

# Packages installed as-is: no packagegroup-core-boot, no systemd.
# /init comes from recovery-init. All needed drivers (MMC, USB gadget,
# configfs, vfat, ext4) are built into the kernel.
PACKAGE_INSTALL = " \
    recovery-init \
    update-scripts \
    bbb-bootcount \
    busybox \
    base-files \
    base-passwd \
    dropbear \
    xz \
    dosfstools \
    e2fsprogs-mke2fs \
    e2fsprogs-e2fsck \
"

IMAGE_FEATURES = "empty-root-password allow-empty-password allow-root-login"
IMAGE_LINGUAS = ""

# Plain gzip'ed cpio, loaded by U-Boot as boot/ramdisk-bbb.gz
IMAGE_FSTYPES = "cpio.gz"
IMAGE_NAME_SUFFIX = ""
IMAGE_ROOTFS_SIZE = "8192"
IMAGE_ROOTFS_EXTRA_SPACE = "0"

inherit core-image

COMPATIBLE_MACHINE = "bbb-printer"
