DESCRIPTON = "NFS Exports config"

LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

PR = "0"

S = "${WORKDIR}"

FILESEXTRAPATHS_prepend := "${THISDIR}/nfs-exports:"

SRC_URI = " \
	file://exports \
	"

S = "${WORKDIR}"

do_install() {

	install -d ${D}${sysconfdir}
	install -d ${D}/home/root/projects

	install -p -m 0755 exports ${D}${sysconfdir}/
}

FILES_${PN} = "/home/root/* ${sysconfdir}/*"
