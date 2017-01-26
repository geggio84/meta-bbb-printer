#
# This file was derived from the 'Hello World!' example recipe in the
# Yocto Project Development Manual.
#

DESCRIPTION = "BBB Printer Marlin firmware"
DEPENDS = ""
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRCREV = "ebf76f824e724c77abb8a94412c88d1d6d72bcfb"
SRCBRANCH="BB_Marlin"
SRC_URI = "git://github.com/geggio84/Marlin.git;branch=${SRCBRANCH}"

S = "${WORKDIR}/git"

do_compile() {
	unset LDFLAGS
	unset CFLAGS
	unset CPPFLAGS
	oe_runmake TARGET=Marlin
}

do_install() {
	install -d ${D}/home/root/marlin
	install -m 755 out/Marlin.elf ${D}/home/root/marlin
}

PARALLEL_MAKE = ""

BBCLASSEXTEND = "native"

FILES_${PN}-dbg += "/home/root/marlin/.debug/*"
FILES_${PN} += "/home/root/marlin/*"
