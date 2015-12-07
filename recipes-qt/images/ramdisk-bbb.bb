DESCRIPTION = "A simple ramdisk for bbb-printer"
LICENSE = "MIT"

IMAGE_CLASSES += "image_types_uboot"

IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"

IMAGE_FEATURES += "ssh-server-openssh"

IMAGE_INSTALL += " \
				packagegroup-core-boot \
				${CORE_IMAGE_EXTRA_INSTALL} \
				busybox \
				"

inherit core-image

IMAGE_ROOTFS_SIZE = "8192"