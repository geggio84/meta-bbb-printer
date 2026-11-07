SUMMARY = "bbb-printer recovery uSD card image"
DESCRIPTION = "wic image for the recovery uSD card: bootloader, kernel, \
device tree and ramdisk-bbb recovery initramfs, plus an empty UPDATE \
partition for the update files. U-Boot boots it after 3 failed eMMC boots, \
when booting from uSD (S2) or with the front button held at power on."
LICENSE = "MIT"

# The card content comes from the deploy directory (bootimg-partition),
# the rootfs of this image is not used.
PACKAGE_INSTALL = ""
IMAGE_FEATURES = ""
IMAGE_LINGUAS = ""

IMAGE_FSTYPES = "wic.xz wic.bmap"
WKS_FILE = "bbb-recovery-sd.wks"

IMAGE_BOOT_FILES = " \
    MLO \
    u-boot.img \
    ${KERNEL_IMAGETYPE};boot/${KERNEL_IMAGETYPE} \
    am335x-bbb-printer.dtb;boot/am335x-bbb-printer.dtb \
    ramdisk-bbb-${MACHINE}.cpio.gz;boot/ramdisk-bbb.gz \
"

inherit core-image

do_image_wic[depends] += " \
    ramdisk-bbb:do_image_complete \
    virtual/kernel:do_deploy \
    virtual/bootloader:do_deploy \
"

COMPATIBLE_MACHINE = "bbb-printer"
