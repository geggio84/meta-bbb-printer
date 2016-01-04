DESCRIPTION = "A simple ramdisk for bbb-printer"
LICENSE = "MIT"

IMAGE_CLASSES += "image_types_uboot"
INHERIT += "image_types_uboot"

#IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"
IMAGE_FSTYPES="ext2.gz.u-boot"

IMAGE_FEATURES += "ssh-server-openssh"

IMAGE_INSTALL += " \
				packagegroup-core-boot \
				${CORE_IMAGE_EXTRA_INSTALL} \
				update-scripts \
				"

inherit core-image
