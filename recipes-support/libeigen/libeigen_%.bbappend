
SRC_URI = "https://gitlab.com/libeigen/eigen/-/archive/${PV}/eigen-${PV}.tar.bz2 \
           file://eigen-disable-tests.patch"

SRC_URI[md5sum] = "83a8c2ead75719150eb78170391a3ac8"
SRC_URI[sha256sum] = "d16bdde41acc93213cd2b675db20369e6850211040a4f1a0f2a4c008ca359fad"

S = "${WORKDIR}/eigen-${PV}"
