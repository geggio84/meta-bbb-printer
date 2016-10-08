SUMMARY = "A Qt5 image with all examples and demos from meta-qt5"

LICENSE = "MIT"

inherit core-image populate_sdk_qt5

IMAGE_FEATURES += "package-management ssh-server-openssh splash"

IMAGE_INSTALL += "\
			kernel-modules \
			update-scripts \
			packagegroup-qt5-base \
			packagegroup-qt5-fonts \
			packagegroup-qt5-graphics \
			packagegroup-qt5-web \
			packagegroup-qt5-extra \
			packagegroup-qt5-machine-related \
			qt5ledscreen \
			quitbattery \
			quitindicators \
			gstreamer1.0-meta-base \
			gstreamer1.0-meta-video \
			gstreamer1.0-libav \
			gstreamer1.0-plugins-bad-fbdevsink \
			gstreamer1.0-plugins-bad-mpegpsdemux \
			packagegroup-core-full-cmdline \
			packagegroup-base-wifi \
			packagegroup-base-bluetooth \
			packagegroup-base-usbgadget \
			openssh-sftp \
			openssh-sftp-server \
			packagegroup-core-buildessential pkgconfig \
			boost cmake zlib glib-2.0 \
			ruby \
			cpufrequtils \
			valgrind \
			cairo pango fontconfig freetype pulseaudio dbus \
			alsa-lib alsa-tools alsa-state alsa-utils-alsaconf \
			i2c-tools \
			libdrm \
			ntp \
			qt5-env \
			evtest \
			nfs-exports \
			pru-firmware \
			tslib-conf tslib-tests tslib-calibrate tslib \
			bbb-marlin \
			"
