
SRC_URI = "https://pypi.python.org/packages/source/M/M2Crypto/M2Crypto-${PV}.tar.gz \
           file://0001-setup.py-link-in-sysroot-not-in-host-directories.patch \
           file://dont_try_build_with_SSLv2_when_it_is_not_available.patch"
