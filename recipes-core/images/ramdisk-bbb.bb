DESCRIPTION = "A simple ramdisk for bbb-printer"
LICENSE = "MIT"

IMAGE_CLASSES += "image_types_uboot"
INHERIT += "image_types_uboot"

# Image in ext2 format and with U-Boot header
IMAGE_FSTYPES := "ext2.gz ext2.gz.u-boot tar.gz"
IMAGE_ROOTFS_SIZE = "32768"
IMAGE_ROOTFS_SIZE_ext2 = "32768"

IMAGE_FEATURES += "ssh-server-dropbear"

EXTRA_IMAGE_FEATURES += "debug-tweaks"

IMAGE_INSTALL += " \
				packagegroup-core-boot \
				${CORE_IMAGE_EXTRA_INSTALL} \
				update-scripts \
				"

IMAGE_LINGUAS = " "

inherit core-image
