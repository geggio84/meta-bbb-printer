DESCRIPTION = "PRU0 firmware and the service starting it: PRU0 runs the \
Marlin stepper firmware (built from the Marlin PRU_stepper sources). PRU1 \
is not used: never started, it stays halted."

LICENSE = "GPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-3.0-or-later;md5=1c76c4cc354acaac30ed4d5eefea7245"

PR = "r3"

require recipes-bbb-printer/bbb-marlin/bbb-marlin-src.inc
require recipes-ti/includes/ti-paths.inc

SRC_URI = " \
	${MARLIN_SRC_URI} \
	file://0001-PRU_stepper-port-to-the-kernel-6.x-PRU-remoteproc-RPMsg.patch \
	file://pru-start \
	file://pru-start.service \
	file://60-rpmsg-pru.rules \
	"

S = "${WORKDIR}/git"

# TI PRU C compiler and PRU Software Support Package v6 (headers, rpmsg_lib)
DEPENDS = "ti-cgt-pru-native pru-icss"

inherit systemd

PACKAGE_ARCH = "${MACHINE_ARCH}"

PRU_CGT = "${TI_CGT_PRU_INSTALL_DIR}"
PRU_CFLAGS = "-v3 -O2 --display_error_number --endian=little --hardware_mac=on"
PRU_LFLAGS = "--reread_libs --warn_sections --stack_size=0x100 --heap_size=0x100"

do_configure[noexec] = "1"

do_compile() {
	cd ${S}/PRU_stepper
	rm -rf gen && mkdir -p gen/include

	# Only the PRU Software Support Package headers: the rest of the
	# sysroot include dir is glibc, which must not shadow the TI libc.
	for h in pru_rpmsg.h pru_types.h pru_virtio_ids.h pru_virtio_ring.h \
	         pru_virtqueue.h rsc_types.h am335x; do
		ln -s ${STAGING_INCDIR}/$h gen/include/$h
	done
	for src in main.c stepper_pru.c; do
		${PRU_CGT}/bin/clpru ${PRU_CFLAGS} \
			--include_path=${PRU_CGT}/include \
			--include_path=gen/include \
			--include_path=gen/include/am335x \
			--obj_directory=gen --pp_directory=gen -ppd -ppa \
			-fe gen/${src%.c}.object $src
	done
	${PRU_CGT}/bin/clpru ${PRU_CFLAGS} -z \
		-i${PRU_CGT}/lib -i${PRU_CGT}/include ${PRU_LFLAGS} \
		-o gen/PRU_stepper.out gen/main.object gen/stepper_pru.object \
		-mgen/PRU_stepper.map AM335x_PRU.cmd \
		--library=libc.a --library=${STAGING_LIBDIR}/rpmsg_lib.lib
}

do_install() {
	install -d ${D}${nonarch_base_libdir}/firmware

	install -m 644 ${S}/PRU_stepper/gen/PRU_stepper.out ${D}${nonarch_base_libdir}/firmware

	ln -sf PRU_stepper.out ${D}${nonarch_base_libdir}/firmware/am335x-pru0-fw

	install -d ${D}${sbindir} ${D}${systemd_system_unitdir}
	install -m 0755 ${WORKDIR}/pru-start ${D}${sbindir}/
	install -m 0644 ${WORKDIR}/pru-start.service ${D}${systemd_system_unitdir}/

	install -d ${D}${nonarch_base_libdir}/udev/rules.d
	install -m 0644 ${WORKDIR}/60-rpmsg-pru.rules ${D}${nonarch_base_libdir}/udev/rules.d/
}

FILES:${PN} += " \
	${nonarch_base_libdir}/firmware/* \
	${sbindir} \
	${systemd_system_unitdir} \
	${nonarch_base_libdir}/udev/rules.d \
	"

SYSTEMD_SERVICE:${PN} = "pru-start.service"

# PRU remoteproc, PRUSS and RPMsg char device drivers are modules
RRECOMMENDS:${PN} = "kernel-module-pru-rproc kernel-module-pruss kernel-module-rpmsg-char"

# PRU ELF binaries: not for the ARM, do not strip or split them
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INSANE_SKIP:${PN} = "arch buildpaths"
