SUMMARY = "Boot health for bbb-printer: U-Boot boot counter and watchdog"
DESCRIPTION = "bootcount tool to read/reset the U-Boot AM33XX boot counter, \
bbb-printer.target for the printer services, a service resetting the boot \
counter once the target is reached and a systemd hardware watchdog setup. \
Together with U-Boot bootlimit/altbootcmd this makes the board boot the uSD \
recovery ramdisk after 3 failed eMMC boots."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
    file://bootcount \
    file://bbb-printer.target \
    file://bootcount-reset.service \
    file://10-watchdog.conf \
"

S = "${WORKDIR}"

inherit systemd allarch

# bbb-bootcount: the tool alone, also used by the recovery ramdisk
# bbb-boot-health: systemd units and watchdog setup for the main image
PACKAGES =+ "bbb-bootcount"
FILES:bbb-bootcount = "${sbindir}/bootcount"
FILES:${PN} = "${systemd_system_unitdir} ${systemd_unitdir}/system.conf.d"
RDEPENDS:${PN} = "bbb-bootcount"

SYSTEMD_SERVICE:${PN} = "bbb-printer.target bootcount-reset.service"
SYSTEMD_AUTO_ENABLE = "enable"

do_install() {
    install -d ${D}${sbindir}
    install -m 0755 ${S}/bootcount ${D}${sbindir}/bootcount

    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${S}/bbb-printer.target ${D}${systemd_system_unitdir}/
    install -m 0644 ${S}/bootcount-reset.service ${D}${systemd_system_unitdir}/

    install -d ${D}${systemd_unitdir}/system.conf.d
    install -m 0644 ${S}/10-watchdog.conf ${D}${systemd_unitdir}/system.conf.d/
}
