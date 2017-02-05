# This include is for the common setting used to build TISDK images.  These images
# include both host and target side content. 
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"

IMAGE_FSTYPES = "tar.gz"

inherit rootfs_ipk
inherit image_types
inherit remove-net-rules
inherit core-image

IMAGE_LINGUAS = ""

ROOTFS_POSTPROCESS_COMMAND += "make_zimage_symlink_relative;"

COMPATIBLE_MACHINE = "bbb-printer"

IMAGE_FEATURES += "sdk_base package-management splash"
IMAGE_FEATURES[type] = "list"

DISTRO_FEATURES_remove += " wayland"

# Define our always included sdk package group as the IMAGE_INSTALL settings
# like you would expect.
FEATURE_PACKAGES_sdk_base = "${IMAGE_INSTALL}"

TARGET_IMAGES ?= "arago-base-tisdk-image"

# path to install the meta-toolchain package in the SDK
TISDK_TOOLCHAIN_PATH ?= "linux-devkit"

# meta toolchain recipe to build and package as part of the tisdk image
TISDK_TOOLCHAIN ?= "meta-toolchain-arago"
TOOLCHAIN_SUFFIX ?= "-sdk"

# helper function for generating a set of strings based on a list.  Taken
# from the image.bbclass.
def string_set(iterable):
    return ' '.join(set(iterable))

# Add a dependency for the do_populate_sdk function of the TIDSK_TOOLCHAIN
# variable which will force us to build the toolchain first so that it will be
# available for packaging
do_rootfs[depends] += "${@string_set('%s:do_populate_sdk' % pn for pn in (d.getVar("TISDK_TOOLCHAIN", True) or "").split())}"

# Variable to specify the name of SPL
DEPLOY_SPL_NAME ?= "MLO-${MACHINE}"

U-BOOT_SRC = "${PREFERRED_PROVIDER_virtual/bootloader}-src"
KERNEL_SRC = "${PREFERRED_PROVIDER_virtual/kernel}-src"

VIRTUAL-RUNTIME_dev_manager = "udev"
VIRTUAL-RUNTIME_init_manager = "sysvinit"
VIRTUAL-RUNTIME_initscripts = "initscripts"
VIRTUAL-RUNTIME_login_manager = "tinylogin shadow"

# List of packages to remove from the host package.  This is usually due to
# licensing issues and unneeded dependencies.
HOST_CLEANUP_PACKAGES = ""
TOOLCHAIN_CLEANUP_PACKAGES = "libgnutls-dev libgnutls-extra26 libgnutls-openssl27 libtasn1-dev"

# Set DTB filters for each machine.  Use "unknown" by default to avoid
# picking up DTB files for devices with not DTB support.
DTB_FILTER = "unknown"
DTB_FILTER_ti33x = "am335x"

TISDK_TOOLCHAIN = "meta-toolchain-arago-tisdk"
TOOLCHAIN_SUFFIX = "-tisdk"

ARAGO_IMAGE_EXTRA_INSTALL ?= ""

IMAGE_INSTALL += "\
    bbb-marlin \
    update-scripts \
    pru-firmware \
    packagegroup-core-boot \
    bash \
    udev-extraconf \
    ${ARAGO_IMAGE_EXTRA_INSTALL} \
    input-utils \
    cpuloadgen \
    timestamping \
    omapconf \
    bonnie++ \
    hdparm \
    iozone3 \
    iperf \
    lmbench \
    rt-tests \
    evtest \
    bc \
    memtester \
    libdrm-tests \
    powertop \
    stress \
    yavta \
    rng-tools \
    perf \
    v4l-utils \
    smcroute \
    rwmem \
    cpuburn-neon \
    pulseaudio-misc \
    kselftests \
    cpuset \
    procps \
    libgbm \
    omap5-sgx-ddk-um-linux \
    omapdrm-pvr \
    qtbase-plugins \
    qtdeclarative-plugins \
    qtdeclarative-qmlplugins \
    qtlocation-plugins \
    qtlocation-qmlplugins \
    qtmultimedia-plugins \
    qtmultimedia-qmlplugins \
    qt3d-qmlplugins \
    qtwebkit-qmlplugins \
    qtquick1-plugins \
    qtquick1-qmlplugins \
    am-sysinfo \
    dt \
    gdbserver \
    oprofile \
    nbench-byte \
    trace-cmd \
    arm-benchmarks \
    dropbear \
    openssh-sftp-server \
    ptpd \
    libdrm-kms \
    valgrind \
    stream \
    u-boot-fw-utils \
    mmc-utils \
    switch-config \
    pru-icss \
    strongswan \
    packagegroup-core-buildessential \
    packagegroup-core-tools-debug \
    git \
    wireless-tools \
    htop \
    iw \
    softap-udhcpd-config \
    obexd \
    obex-client \
    pulseaudio \
    pulseaudio-server \
    pulseaudio-module-loopback \
    eventdump \
    wlconf \
    wpa-supplicant-wl18xx \
    crda \
    hostap-daemon-wl18xx \
    ti-wifi-utils-wl18xx \
    wl18xx-target-scripts \
    iptables \
    bluez4 \
    bluez4-agent \
    bluez-hcidump \
    uim \
    bt-firmware \
    openssl \
    openssl-misc \
    openssl-engines \
    cryptodev-module \
    cryptodev-tests \
    packagegroup-arago-gst \
    screenshot \
    alsa-lib \
    alsa-utils-aplay \
    module-init-tools \
    mtd-utils \
    mtd-utils-ubifs \
    curl \
    initscript-telnetd \
    ethtool \
    thermal-init \
    devmem2 \
    tcpdump \
    alsa-conf \
    alsa-conf-base \
    alsa-server \
    alsa-utils \
    alsa-utils-aconnect \
    alsa-utils-alsaconf \
    alsa-utils-alsactl \
    alsa-utils-alsamixer \
    alsa-utils-amixer \
    alsa-utils-iecset \
    alsa-utils-midi \
    alsa-utils-speakertest \
    alsa-state \
    tslib-conf \
    tslib-calibrate \
    tslib-tests \
    ncurses \
    ncurses-terminfo \
    ncurses-tools \
    e2fsprogs \
    e2fsprogs-e2fsck \
    e2fsprogs-mke2fs \
    e2fsprogs-tune2fs \
    dosfstools \
    util-linux-fdisk \
    util-linux-mkfs \
    util-linux-sfdisk \
    util-linux-fsck \
    fbset \
    usbutils \
    i2c-tools \
    iproute2 \
    iproute2-tc \
    strace \
    zlib \
    libpng12 \
    jpeg \
    jpeg-tools \
    freetype \
    thttpd \
    dbus \
    expat \
    glib-2.0 \
    libxml2 \
    libpcre \
    iptables \
    iperf \
    arago-gpl-notice \
    arago-feed-config \
    nfs-utils-client \
"

BAD_RECOMMENDATIONS += " \
    ti-llvm3.6-dev \
    opencl-monitor-dev \
    libulm-dev \
    gdbserver-c6x-dev \
    coreutils \
    coreutils-dev \
"

# ltp-ddt

RDEPENDS_${PN} += "\
    am-sysinfo-src \
    arm-benchmarks-src \
    oprofile-example-src \
    omapconf-src \
    pru-icss-src \
    cryptodev-module-src \
    omapdrm-pvr-src \
    ti-tisdk-setup \
    ti-tisdk-makefile \
    ${U-BOOT_SRC} \
    ${KERNEL_SRC} \
    tisdk-install \
    libgbm-dev \
    omap5-sgx-ddk-um-linux-dev \
    "

RRECOMMENDS_${PN} += "\
    kernel-modules \
    "

export IMAGE_BASENAME = "bbb-printer-image"
