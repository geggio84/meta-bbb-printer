# Kernel for bbb-printer: BeagleBoard.org kernel (meta-beagle) + board patches

COMPATIBLE_MACHINE:bbb-printer = "bbb-printer"

# Fork of beagleboard/linux; branch v6.12.34-ti-arm32-r12 == upstream SRCREV
KERNEL_GIT_URI:bbb-printer = "git://github.com/geggio84/linux-bb.org.git"

# Our directory is searched first, so its "defconfig" (pointing at the
# in-tree bbb_printer_defconfig added by patch 0005) overrides meta-beagle's.
FILESEXTRAPATHS:prepend:bbb-printer := "${THISDIR}/${PN}:"

SRC_URI:append:bbb-printer = " \
    file://0001-spi-spidev-add-compatible-for-bbb-printer-stepper-dr.patch \
    file://0002-w1-therm-add-default_resolution-module-parameter.patch \
    file://0003-Input-tps65218-pwrbutton-optionally-report-AC-power-.patch \
    file://0004-ARM-dts-am335x-add-BeagleBone-Black-3D-printer-board.patch \
    file://0005-ARM-configs-add-bbb_printer_defconfig.patch \
"
