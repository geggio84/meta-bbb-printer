SUMMARY = "Basic BBB printer image with xserver support"

LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

IMAGE_FEATURES += "package-management ssh-server-dropbear nfs-server"

DISTRO_FEATURES_remove = "ipv6"

inherit core-image populate_sdk_qt5

IMAGE_INSTALL += "\
			kernel-modules \
			update-scripts \
			packagegroup-core-full-cmdline \
			packagegroup-cli-tools \
			packagegroup-cli-tools-debug \
			packagegroup-core-buildessential \
			packagegroup-base-wifi \
			packagegroup-base-bluetooth \
			packagegroup-base-usbgadget \
			packagegroup-base-usbhost \
			openssh-sftp \
			openssh-sftp-server \
			packagegroup-core-buildessential pkgconfig \
			boost cmake zlib glib-2.0 \
			cpufrequtils \
			cairo pango fontconfig freetype pulseaudio dbus \
			alsa-lib alsa-tools alsa-state alsa-utils-alsaconf \
			i2c-tools \
			packagegroup-core-x11-xserver \
			packagegroup-core-x11-utils \
			xterm \
			xinput \
			xinput-calibrator \
			packagegroup-qt5-base \
			packagegroup-qt5-fonts \
			packagegroup-qt5-extra \
			packagegroup-qt5-graphics \
			qt5-env \
			"

#PACKAGE_EXCLUDE = "matchbox-wm matchbox-terminal"
