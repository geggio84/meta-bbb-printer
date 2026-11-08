SUMMARY = "U-Boot splash images for bbb-printer"
DESCRIPTION = "splash.bmp (normal boot) and recovery_splash.bmp (recovery \
boot), shown by U-Boot on the 480x272 LCD. They are deployed and put in \
/boot of the boot (FAT) partition by the eMMC and recovery uSD wic images; \
U-Boot loads them from the eMMC and falls back to the uSD card. \
Replace files/*.bmp with your images: 480x272, 16, 24 or 32 bpp BMP \
(smaller images are centered)."

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
	file://splash.bmp \
	file://recovery_splash.bmp \
	"

S = "${WORKDIR}"

inherit deploy nopackages

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_deploy() {
	install -m 0644 ${WORKDIR}/splash.bmp ${DEPLOYDIR}/
	install -m 0644 ${WORKDIR}/recovery_splash.bmp ${DEPLOYDIR}/
}
addtask deploy after do_install before do_build

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "bbb-printer"
