#
# Marlin Firmware porting for BBB-printer
#

DESCRIPTION = "BBB Printer Marlin firmware"
DEPENDS = ""
LICENSE = "GPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-3.0-or-later;md5=1c76c4cc354acaac30ed4d5eefea7245"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${PV}:"

SRCREV = "ebf76f824e724c77abb8a94412c88d1d6d72bcfb"
SRCBRANCH="BB_Marlin"
SRC_URI = "git://github.com/geggio84/Marlin.git;protocol=https;branch=${SRCBRANCH}"

S = "${WORKDIR}/git"

do_compile() {
	oe_runmake TARGET=Marlin
}

do_install() {
	install -d ${D}/home/root/marlin
	install -m 755 out/Marlin.elf ${D}/home/root/marlin/
}

PARALLEL_MAKE = ""

BBCLASSEXTEND = "native"

FILES:${PN} += "/home/root/marlin/*"
FILES:${PN}-dbg += "/home/root/marlin/.debug/"
