# U-Boot for bbb-printer: BeagleBoard.org u-boot (meta-beagle) + board patches

COMPATIBLE_MACHINE:bbb-printer = "bbb-printer"

# Fork of beagleboard/u-boot; branch v2025.10-Beagle == upstream SRCREV
UBOOT_GIT_URI:bbb-printer = "git://github.com/geggio84/u-boot-bb.org.git"

FILESEXTRAPATHS:prepend := "${THISDIR}/patches:"

SRC_URI:append:bbb-printer = " \
    file://0001-Add-support-for-BeagleBone-power-off.patch \
    file://0002-Add-video-drivers-for-BeagleBone.patch \
    file://0003-Add-Beaglebone-LCD-support.patch \
    file://0004-configs-am335x_evm-drop-SPL-Ymodem-support-to-free-S.patch \
    file://0005-Add-bbb-printer-pin-mux.patch \
    file://0006-Update-some-env-variables.patch \
    file://0007-Try-to-boot-from-eMMC-first-and-then-from-uSD.patch \
    file://0008-Move-am335x_rtc_poweroff-so-it-is-compiled-also-on-s.patch \
    file://0009-Add-shutdown-command.patch \
    file://0010-Fix-Video-init-on-beaglebone.patch \
    file://0011-Update-environment-for-ramdisk-boot.patch \
    file://0012-Add-support-for-recovery-boot.patch \
    file://0013-Add-some-AM335x-PWM-defines.patch \
    file://0014-Add-minimal-support-for-PWM-Backlight-with-fixed-dut.patch \
    file://0015-Add-ramdisk-size-parameter-on-kernel-bootcmd-line.patch \
    file://0016-Fix-16-bit-LCD-configuration.patch \
    file://0017-Update-pinmux-for-new-PCB-rev.0.patch \
    file://0018-Load-am335x-bbb-printer.dtb-by-default.patch \
"
