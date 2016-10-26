DESCRIPTON = "Startup scripts"

LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

PR = "0"

S = "${WORKDIR}"

SRC_URI = " \
	file://update_kernel_emmc.sh \
	file://update_kernel_sd.sh \
	file://update_splash_emmc.sh \
	file://update_splash_sd.sh \
	file://update_u-boot_emmc.sh \
	file://update_u-boot_sd.sh \
	file://update_ramdisk.sh \
	file://update_rootfs.sh \
	file://pwrbtncheck.sh \
	file://pwrbtn.sh \
	"

S = "${WORKDIR}"

do_install() {

	install -d ${D}${sysconfdir}/init.d
	install -d ${D}${sysconfdir}/rcS.d
	install -d ${D}/home/root/update_scripts

	install -p -m 0755 pwrbtncheck.sh ${D}${sysconfdir}/init.d/
	install -p -m 0755 pwrbtn.sh ${D}${sysconfdir}/init.d/
	install -p -m 0755 update_kernel_emmc.sh ${D}/home/root/update_scripts/
	install -p -m 0755 update_kernel_sd.sh ${D}/home/root/update_scripts/
	install -p -m 0755 update_splash_emmc.sh ${D}/home/root/update_scripts/
	install -p -m 0755 update_splash_sd.sh ${D}/home/root/update_scripts/
	install -p -m 0755 update_u-boot_emmc.sh ${D}/home/root/update_scripts/
	install -p -m 0755 update_u-boot_sd.sh ${D}/home/root/update_scripts/
	install -p -m 0755 update_rootfs.sh ${D}/home/root/update_scripts/
	install -p -m 0755 update_ramdisk.sh ${D}/home/root/update_scripts/

	ln -sf ../init.d/pwrbtn.sh  ${D}${sysconfdir}/rcS.d/S90pwrbtn
}

FILES_${PN} = "/home/root/* ${sysconfdir}/*"
