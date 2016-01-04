SUMMARY = "A Qt5 image with all examples and demos from meta-qt5"

LICENSE = "MIT"

inherit core-image populate_sdk_qt5

IMAGE_FEATURES += "package-management ssh-server-dropbear splash"

IMAGE_INSTALL += "\
			kernel-modules \
			update-scripts \
			packagegroup-qt5-base \
			packagegroup-qt5-fonts \
			packagegroup-qt5-graphics \
			packagegroup-qt5-web \
			packagegroup-qt5-extra \
			packagegroup-qt5-machine-related \
			cinematicexperience \
			qt5everywheredemo \
			qt5ledscreen \
			qt5nmapcarousedemo \
			qt5nmapper \
			qtsmarthome \
			quitbattery \
			quitindicators \
			qsiv \
			qt5-demo-extrafiles \
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
			cairo pango fontconfig freetype pulseaudio dbus \
			alsa-lib alsa-tools alsa-state alsa-utils-alsaconf \
			i2c-tools \
			libgles-omap3-x11 \
			omap3-sgx-modules \
			libdrm \
			x11-common \
			xclock \
			xf86-input-evdev \
			xf86-input-mouse \
			xf86-input-tslib \
			xf86-video-fbdev \
			xf86-video-modesetting \
			xf86-video-omapfb \
			xf86-video-omap \
			xinit \
			xinput \
			xinput-calibrator \
			xterm \
			"
