
IMAGE_ROOTFS_SIZE = "16384"

FILESEXTRAPATHS_append := "${THISDIR}/patches:"

SRC_URI_append = " \
	file://0001-Initial-support-for-bbb-printer.patch \
	file://defconfig \
"

KERNEL_DEVICETREE_ti33x = "bbb-printer.dtb"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += "kernel-module-g_multi"
KERNEL_MODULE_AUTOLOAD = "g_multi"
KERNEL_MODULE_PROBECONF = "g_multi"
module_conf_g_multi = "options g_multi file=/dev/mmcblk0p3 removable=1"
