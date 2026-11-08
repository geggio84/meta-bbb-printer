SUMMARY = "bbb-printer update scripts and power button handler"
DESCRIPTION = "Scripts updating the eMMC system and the recovery uSD card from \
files on the uSD UPDATE partition (exported as a USB drive on the OTG port by \
the recovery ramdisk), and a service powering off on power button press."
LICENSE = "GPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-3.0-or-later;md5=1c76c4cc354acaac30ed4d5eefea7245"

PR = "3"

SRC_URI = " \
	file://update-common.sh \
	file://update_emmc_image.sh \
	file://update_rootfs.sh \
	file://update_kernel_emmc.sh \
	file://update_u-boot_emmc.sh \
	file://update_splash_emmc.sh \
	file://update_kernel_sd.sh \
	file://update_u-boot_sd.sh \
	file://update_splash_sd.sh \
	file://update_ramdisk.sh \
	file://pwrbtncheck.sh \
	file://pwrbtn.service \
	"

S = "${WORKDIR}"

inherit systemd allarch

UPDATE_SCRIPTS = " \
	update_emmc_image.sh update_rootfs.sh \
	update_kernel_emmc.sh update_u-boot_emmc.sh update_splash_emmc.sh \
	update_kernel_sd.sh update_u-boot_sd.sh update_splash_sd.sh \
	update_ramdisk.sh \
	"

do_install() {
	install -d ${D}${sbindir} ${D}${datadir}/update-scripts
	install -m 0644 ${S}/update-common.sh ${D}${datadir}/update-scripts/
	for f in ${UPDATE_SCRIPTS}; do
		install -m 0755 ${S}/$f ${D}${sbindir}/
	done

	install -m 0755 ${S}/pwrbtncheck.sh ${D}${sbindir}/
	install -d ${D}${systemd_system_unitdir}
	install -m 0644 ${S}/pwrbtn.service ${D}${systemd_system_unitdir}/
}

# update-scripts: main image and recovery ramdisk
# update-scripts-pwrbtn: main image only (systemd service)
PACKAGES =+ "${PN}-pwrbtn"
FILES:${PN}-pwrbtn = "${sbindir}/pwrbtncheck.sh ${systemd_system_unitdir}/pwrbtn.service"
FILES:${PN} = "${sbindir} ${datadir}/update-scripts"

SYSTEMD_PACKAGES = "${PN}-pwrbtn"
SYSTEMD_SERVICE:${PN}-pwrbtn = "pwrbtn.service"
