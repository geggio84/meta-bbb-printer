SUMMARY = "Init and USB OTG gadget setup for the bbb-printer recovery ramdisk"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
    file://init \
    file://usb-gadget \
    file://udhcpd-usb0.conf \
    file://motd \
"

S = "${WORKDIR}"

inherit allarch

do_install() {
    install -m 0755 ${S}/init ${D}/init
    install -d ${D}${sbindir} ${D}${sysconfdir}
    install -m 0755 ${S}/usb-gadget ${D}${sbindir}/usb-gadget
    install -m 0644 ${S}/udhcpd-usb0.conf ${D}${sysconfdir}/udhcpd-usb0.conf
    install -m 0644 ${S}/motd ${D}${sysconfdir}/recovery-motd
    install -d ${D}/dev ${D}/proc ${D}/sys ${D}/run ${D}/tmp ${D}/media
    # Initial console for /init: the kernel opens it before devtmpfs is mounted
    mknod -m 0600 ${D}/dev/console c 5 1
}

FILES:${PN} = "/init ${sbindir} ${sysconfdir} /dev /proc /sys /run /tmp /media"

# udhcpd is in the main busybox package (busybox-udhcpd only holds a
# sysvinit script, empty on this systemd distro)
RDEPENDS:${PN} = "busybox dropbear bbb-bootcount"
