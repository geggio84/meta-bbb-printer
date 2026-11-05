DESCRIPTON = "Add PRU cores Firmwares"

LICENSE = "GPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-3.0-or-later;md5=1c76c4cc354acaac30ed4d5eefea7245"

S = "${WORKDIR}"

SRC_URI = " \
	file://PRU_Halt.out \
	file://PRU_stepper.out \
	"

S = "${WORKDIR}"

do_install() {

	install -d ${D}${nonarch_base_libdir}/firmware

	install -m 644 PRU_Halt.out ${D}${nonarch_base_libdir}/firmware
	install -m 644 PRU_stepper.out ${D}${nonarch_base_libdir}/firmware

	ln -sf PRU_stepper.out ${D}${nonarch_base_libdir}/firmware/am335x-pru0-fw
	ln -sf PRU_Halt.out ${D}${nonarch_base_libdir}/firmware/am335x-pru1-fw
}

FILES:${PN} += "${nonarch_base_libdir}/firmware/*"

INSANE_SKIP:${PN} = "arch"
