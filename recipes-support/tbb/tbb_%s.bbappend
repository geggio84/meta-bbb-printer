
SRC_URI = "http://threadingbuildingblocks.org/sites/default/files/software_releases/source/tbb41_${PRDATE}oss_src.tgz \
           file://cross-compile.patch \
           file://allow-to-build-for-older-arm-cores.patch \
           file://tbb.pc"

S = "${WORKDIR}/tbb41_${PRDATE}oss/"

SRC_URI[md5sum] = "ed4af7ccfa122f16cf9920b241633a3a"
SRC_URI[sha256sum] = "32fd5979971b772caa96d40646cee585ed0070516ba2dbbcb1f9b6033d08a92d"

