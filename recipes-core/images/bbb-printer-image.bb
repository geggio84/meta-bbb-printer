# This include is for the common setting used to build TISDK images.  These images
# include both host and target side content. 
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"

IMAGE_FSTYPES = "tar.gz wic.xz wic.bmap"

inherit rootfs_ipk
inherit image_types
inherit remove-net-rules
inherit core-image

IMAGE_LINGUAS = ""

COMPATIBLE_MACHINE = "bbb-printer"

IMAGE_FEATURES += "package-management splash"
IMAGE_FEATURES[type] = "list"

DISTRO_FEATURES:remove = " wayland"

# Set DTB filters for each machine.  Use "unknown" by default to avoid
# picking up DTB files for devices with not DTB support.
DTB_FILTER = "unknown"
DTB_FILTER_ti33x = "am335x"

TOOLCHAIN_SUFFIX = "-tisdk"

# Base system and bbb-printer applications
IMAGE_INSTALL += "\
    packagegroup-core-boot \
    bbb-marlin \
    update-scripts \
    pru-firmware \
    udev-extraconf \
    dropbear \
    bbb-boot-health \
    update-scripts-pwrbtn \
"

# Qt GUI runtime (eglfs/linuxfb with tslib touchscreen input)
IMAGE_INSTALL += "\
    qtbase \
    qtbase-plugins \
    qtdeclarative \
    qtdeclarative-plugins \
    qtdeclarative-qmlplugins \
    qtsvg \
    qtsvg-plugins \
    liberation-fonts \
"

# Touchscreen and hardware tools
IMAGE_INSTALL += "\
    tslib-conf \
    tslib-calibrate \
    tslib-tests \
    evtest \
    i2c-tools \
"

# Uncomment a whole block to add that group of packages.

# Qt optional modules
#IMAGE_INSTALL += "\
#    qt3d-qmlplugins \
#    qtlocation-plugins \
#    qtlocation-qmlplugins \
#    qtmultimedia-plugins \
#    qtmultimedia-qmlplugins \
#"

# Shell and command-line utilities
IMAGE_INSTALL += "\
    bash \
    bc \
    procps \
    ncurses \
    ncurses-terminfo \
    ncurses-tools \
    curl \
"

# Storage and filesystems
IMAGE_INSTALL += "\
    e2fsprogs-tune2fs \
    util-linux-fdisk \
    util-linux-mkfs \
    util-linux-sfdisk \
    mmc-utils \
    nfs-utils-client \
"

# Networking
IMAGE_INSTALL += "\
    openssh-sftp-server \
    iptables \
    ethtool \
    softap-udhcpd-config \
    smcroute \
    ptpd \
    strongswan \
    switch-config \
"

# Security and crypto
IMAGE_INSTALL += "\
    openssl \
    openssl-misc \
    openssl-engines \
    cryptodev-module \
"

# Audio
IMAGE_INSTALL += "\
    alsa-utils \
    alsa-utils-aplay \
    alsa-utils-alsamixer \
    alsa-utils-aconnect \
    alsa-utils-iecset \
    alsa-utils-midi \
    alsa-utils-speakertest \
    alsa-server \
    pulseaudio \
    pulseaudio-server \
    pulseaudio-misc \
    pulseaudio-module-loopback \
"

# Graphics, video and multimedia
IMAGE_INSTALL += "\
    packagegroup-arago-gst \
    libgbm \
    libdrm-tests \
    fbset \
    freetype \
    jpeg-tools \
    v4l-utils \
    yavta \
"

# Hardware debug tools
IMAGE_INSTALL += "\
    input-utils \
    eventdump \
    devmem2 \
    rwmem \
    omapconf \
    pru-icss \
"

# Development and debugging
#IMAGE_INSTALL += "\
#    gdbserver \
#    strace \
#    perf \
#    trace-cmd \
#    packagegroup-core-tools-debug \
#    packagegroup-core-buildessential \
#"

# Benchmarks and tests
#IMAGE_INSTALL += "\
#    iozone3 \
#    lmbench \
#    nbench-byte \
#    ltp-ddt \
#"

# Arago distro extras and libraries
#IMAGE_INSTALL += "\
#    arago-feed-config \
#    arago-gpl-notice \
#    libpcre \
#"

RRECOMMENDS_${PN} += "\
    kernel-modules \
    "

export IMAGE_BASENAME = "bbb-printer-image"
