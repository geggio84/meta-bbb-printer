DESCRIPTON = "Add PRU cores Firmwares"

LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

S = "${WORKDIR}"

SRC_URI = " \
	file://PRU_Halt.out \
	file://PRU_stepper.out \
	"

S = "${WORKDIR}"

do_install() {

	install -d ${D}/lib/firmware

	install -m 644 PRU_Halt.out ${D}/lib/firmware
	install -m 644 PRU_stepper.out ${D}/lib/firmware

	ln -sf PRU_stepper.out ${D}/lib/firmware/am335x-pru0-fw
	ln -sf PRU_Halt.out ${D}/lib/firmware/am335x-pru1-fw
}

FILES_${PN} = "/lib/firmware/*"

INSANE_SKIP_${PN} = "arch"
