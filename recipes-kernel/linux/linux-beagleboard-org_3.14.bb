SECTION = "kernel"
DESCRIPTION = "Linux kernel from Beagleboard.org"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

inherit kernel

require recipes-kernel/linux/linux-dtb.inc
require recipes-kernel/linux/setup-defconfig.inc

# Look in the generic major.minor directory for files
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-3.14:"

# Pull in the devicetree files into the rootfs
RDEPENDS_kernel-base += "kernel-devicetree"

# Add a run-time dependency for the PM firmware to be installed
# on the target file system.
RDEPENDS_kernel-base_append_ti33x = " am33x-cm3"

# Default is to package all dtb files for ti33x devices unless building
# for the specific beaglebone machine.
KERNEL_DEVICETREE_beaglebone = "bbb_printer.dtb am335x-bone.dtb am335x-boneblack.dtb"

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"

COMPATIBLE_MACHINE = "ti33x"

S = "${WORKDIR}/git"

BRANCH = "3.14"

# Corresponds to tag 3.14.43-ti-r66
SRCREV = "2e28c16dbfd8a8c8ff74b0ca9bc29d8317eee825"
PV = "3.14.43"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR_append = "-ti-r66-${SRCPV}"
PR = "${MACHINE_KERNEL_PR}"

#KERNEL_CONFIG_FRAGMENTS_append_ti33x = " ${WORKDIR}/remove-extra-firmware.cfg"

KERNEL_GIT_URI = "git://github.com/beagleboard/linux.git"
KERNEL_GIT_PROTOCOL = "git"
SRC_URI += "${KERNEL_GIT_URI};protocol=${KERNEL_GIT_PROTOCOL};branch=${BRANCH};nobranch=1"
SRC_URI += "file://0001-spidev-fix-warning-on-spidev-compatible.patch"
SRC_URI += "file://0002-spi-omap2-mcspi-add-gpio-cs-support.patch"
SRC_URI += "file://0003-pwm-pca9685-various-fixes.patch"
SRC_URI += "file://0004-w1-w1-term-change-w1-resolution-to-decrease-conversi.patch"
SRC_URI += "file://0005-fb-da8xx-fb-add-splashscreen-continuity-from-u-boot-.patch"
SRC_URI += "file://0006-ARM-dts-add-BBB-printer-devicetree.patch"
SRC_URI += "file://0007-ARM-configs-add-BBB-printer-defconfig.patch"
SRC_URI += "file://defconfig"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += "kernel-module-g_multi"
KERNEL_MODULE_AUTOLOAD = "g_multi"
KERNEL_MODULE_PROBECONF = "g_multi"
module_conf_g_multi = "options g_multi file=/dev/mmcblk0p3 removable=1"

INSANE_SKIP_kernel-dev += "debug-files"
