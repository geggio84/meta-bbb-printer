
# Ugly workaround for do_compileconfigs task error for subsequents run.
do_compileconfigs_prepend() {
	rm -f ${D}/lib/modules/${KERNEL_VERSION}/build
	KERNEL_SRC_PATH="/usr/src/kernel"
	kerneldir=${D}${KERNEL_SRC_PATH}
	rm -rf $kerneldir/Documentation
	mkdir -p "${D}/lib/modules/${KERNEL_VERSION}-systest"
	touch "${D}/lib/modules/${KERNEL_VERSION}-systest/build"
	touch "${D}/lib/modules/${KERNEL_VERSION}-systest/source"
	mkdir -p "${D}/lib/modules/${KERNEL_VERSION}-systest/kernel"
}

COMPATIBLE_MACHINE_bbb_printer = "bbb_printer"

IMAGE_ROOTFS_SIZE = "16384"

FILESEXTRAPATHS_prepend := "${THISDIR}/linux-ti-staging:"

SRC_URI += "file://0001-Initial-support-for-bbb-printer.patch"
SRC_URI += "file://0002-spidev-fix-warning-on-spidev.patch"
SRC_URI += "file://0003-Add-pwr-button-for-tps65217-pmic-on-beaglebone-black.patch"
SRC_URI += "file://0004-Fix-some-device-tree-entries.patch"
SRC_URI += "file://0005-Add-beaglebone-defconfig.patch"
SRC_URI += "file://0006-Fix-device-tree-SPI-entry.patch"
SRC_URI += "file://0007-Fix-Touchscreen-IRQ.patch"
SRC_URI += "file://0008-Modify-backlight-default-intensity.patch"
SRC_URI += "file://0009-defconfig-Add-missing-PCA9685-driver.patch"
SRC_URI += "file://0010-defconfig-rename-to-bbb_printer_defconfig.patch"
SRC_URI += "file://0011-defconfig-update-defconfig-for-4.1.13-and-fix-USB-ga.patch"
SRC_URI += "file://0012-defconfig-disable-fb-console.patch"
SRC_URI += "file://0013-defconfig-enable-NFS-server-support.patch"
SRC_URI += "file://defconfig"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += "kernel-module-g_serial"
KERNEL_MODULE_AUTOLOAD = "g_serial"
