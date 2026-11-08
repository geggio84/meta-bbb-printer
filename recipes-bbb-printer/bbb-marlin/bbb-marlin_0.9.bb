#
# Marlin Firmware porting for BBB-printer
#

DESCRIPTION = "BBB Printer Marlin firmware"
DEPENDS = ""
LICENSE = "GPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-3.0-or-later;md5=1c76c4cc354acaac30ed4d5eefea7245"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${PV}:"

require bbb-marlin-src.inc

SRC_URI = " \
    ${MARLIN_SRC_URI} \
    file://marlin.service \
    file://usb-serial-gadget \
    file://usb-serial-gadget.service \
"

S = "${WORKDIR}/git"

do_compile() {
	oe_runmake TARGET=Marlin
}

inherit systemd

do_install() {
	install -d ${D}/home/root/marlin
	install -m 755 out/Marlin.elf ${D}/home/root/marlin/

	install -d ${D}${sbindir} ${D}${systemd_system_unitdir}
	install -m 0755 ${WORKDIR}/usb-serial-gadget ${D}${sbindir}/
	install -m 0644 ${WORKDIR}/marlin.service ${D}${systemd_system_unitdir}/
	install -m 0644 ${WORKDIR}/usb-serial-gadget.service ${D}${systemd_system_unitdir}/
}

SYSTEMD_SERVICE:${PN} = "marlin.service usb-serial-gadget.service"
RDEPENDS:${PN} = "pru-firmware"

PARALLEL_MAKE = ""

FILES:${PN} += "/home/root/marlin/* ${sbindir} ${systemd_system_unitdir}"
FILES:${PN}-dbg += "/home/root/marlin/.debug/"
