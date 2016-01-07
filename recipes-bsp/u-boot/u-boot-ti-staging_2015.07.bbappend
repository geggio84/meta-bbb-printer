
SRCREV = "ti2015.01"

FILESEXTRAPATHS_prepend := "${THISDIR}/patches:"

SRC_URI += "file://0001-Add-support-for-BeagleBone-power-off.patch"
SRC_URI += "file://0002-Add-video-drivers-for-BeagleBone.patch"
SRC_URI += "file://0003-Add-Beaglebone-LCD-support.patch"
SRC_URI += "file://0004-Add-bbb-printer-pin-mux.patch"
SRC_URI += "file://0005-Update-some-env-variables.patch"
SRC_URI += "file://0006-Try-to-boot-from-eMMC-first-and-then-from-uSD.patch"
SRC_URI += "file://0007-Move-am335x_rtc_poweroff-so-it-is-compiled-also-on-s.patch"
SRC_URI += "file://0008-Add-shutdown-command.patch"
SRC_URI += "file://0009-Fix-LCD-initialization-on-board-init-function.patch"
SRC_URI += "file://0010-Add-automatic-update-of-splash-image-address.patch"
SRC_URI += "file://0011-Fix-Video-init-on-beaglebone.patch"
SRC_URI += "file://0012-Update-environment-for-ramdisk-boot.patch"
SRC_URI += "file://0013-Add-support-for-recovery-boot.patch"
SRC_URI += "file://0014-Add-some-AM335x-PWM-defines.patch"
SRC_URI += "file://0015-Add-minimal-support-for-PWM-Backlight-with-fixed-dut.patch"
SRC_URI += "file://0016-Add-ramdisk-size-parameter-on-kernel-bootcmd-line.patch"
SRC_URI += "file://0017-Revert-video-color-swap-for-32-BIT.patch"
SRC_URI += "file://0018-Fix-16-bit-LCD-configuration.patch"
