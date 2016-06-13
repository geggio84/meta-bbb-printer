
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
SRC_URI += "file://0014-Revert-video-color-swap-for-24-and-32-BIT.patch"
SRC_URI += "file://0015-Fix-16bit-LCD-configuration.patch"
SRC_URI += "file://0016-Fix-RGB-Led-strip-color-order.patch"
SRC_URI += "file://0017-spi-omap2-mcspi-Switch-driver-to-use-transfer_one.patch"
SRC_URI += "file://0018-spi-omap2-mcspi-Add-gpio_request-and-init-CS.patch"
SRC_URI += "file://0019-spi-omap2-mcspi-Fix-native-cs-with-new-set_cs.patch"
SRC_URI += "file://0020-spi-omap2-mcspi-Remove-unnecessary-delay.patch"
SRC_URI += "file://0021-spi-omap2-mcspi-Fix-set_cs-function-for-active-high.patch"
SRC_URI += "file://0022-spi-omap2-mcspi-Fix-GPIO-chip-select-support.patch"
SRC_URI += "file://0023-spi-omap2-mcspi-Handle-error-on-gpio_request.patch"
SRC_URI += "file://0024-spi-omap2-mcspi-Prevent-duplicate-gpio_request.patch"
SRC_URI += "file://0025-defconfig-Add-PRU-remoteproc-and-RPMSG-module-driver.patch"
SRC_URI += "file://0026-Update-devicetree-to-support-new-PCB-rev.0.patch"
SRC_URI += "file://0027-Select-external-RTC-device-by-default.patch"
SRC_URI += "file://0028-Fix-disable-hdmi-clock-oscillator.patch"
SRC_URI += "file://0029-Change-SPI0-to-spi-gpio-because-of-4-cs-limitation-o.patch"
SRC_URI += "file://0030-pwm-pca9685-Drop-owner-assignment.patch"
SRC_URI += "file://0031-pwm-pca9685-Fix-several-driver-bugs.patch"
SRC_URI += "file://0032-pwm-pca9685-Support-changing-the-output-frequency.patch"
SRC_URI += "file://0033-pwm-pca9685-fix-full-off-to-full-on-PWM-bug.patch"
SRC_URI += "file://0034-Change-stepper-pins-muxing-to-PRU-direct-output.patch"
SRC_URI += "file://0035-Add-button-on-rotary-encoder.patch"
SRC_URI += "file://0036-w1-w1-term-change-w1-resolution-to-decrease-conversi.patch"
SRC_URI += "file://defconfig"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += "kernel-module-g_serial"
KERNEL_MODULE_AUTOLOAD = "g_serial"
