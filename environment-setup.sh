#!/bin/sh

# OE Build Enviroment Setup Script
#
oebase=`pwd`

# Populate the following variables depending on the value of oebase
sourcedir="$oebase/src"
builddir="$oebase/build"
confdir="$builddir/conf"

# If directories do not exist then create them
for f in sourcedir builddir confdir
do
	eval t="$"$f
	if [ ! -d $t ]
	then
		mkdir -p $t
	fi
done

# First copy the template file
cp -f $OECORELAYERCONFPATH $confdir/bblayers.conf

# Now add the layers we have configured to the BBLAYERS variable
sed -e "s|##COREBASE##|$sourcedir|g" \
		$OECORELAYERCONFPATH > $confdir/bblayers.conf
	SHOWYPDOC=yes

# First copy the template file
cp -f $OECORELOCALCONFPATH $confdir/local.conf

# set the number of threads
threads=`cat /proc/cpuinfo | grep -c processor`
sed -i "s/^PARALLEL_MAKE.*/PARALLEL_MAKE = \"-j ${threads}\"/" $confdir/local.conf
sed -i "s/^BB_NUMBER_THREADS.*/BB_NUMBER_THREADS = \"${threads}\"/" $confdir/local.conf

# Set OEBASE to where the build and source directories reside
# NOTE: Do NOT place a trailing / on the end of OEBASE.
export OEBASE=${oebase}

# try to find out bitbake directory
BITBAKEDIR=`find ${OEBASE}/src -name "*bitbake*"`
for f in ${BITBAKEDIR}
do
    if [ -d ${f}/bin ]
    then
        PATH="${f}/bin:$PATH"
    fi
done

# check for any scripts directories in the top-level of the repos and add those
# to the PATH
SCRIPTS=`find ${OEBASE}/src -maxdepth 2 -name "scripts" -type d`
for s in ${SCRIPTS}
do
    PATH="${s}:$PATH"
done

unset BITBAKEDIR
unset SCRIPTS
export PATH
export BUILDDIR=${builddir}
export BB_ENV_EXTRAWHITE="MACHINE DISTRO TCMODE TCLIBC http_proxy ftp_proxy https_proxy all_proxy ALL_PROXY no_proxy SSH_AGENT_PID SSH_AUTH_SOCK BB_SRCREV_POLICY SDKMACHINE BB_NUMBER_THREADS PARALLEL_MAKE GIT_PROXY_COMMAND GIT_PROXY_IGNORE SOCKS5_PASSWD SOCKS5_USER OEBASE META_SDK_PATH TOOLCHAIN_TYPE TOOLCHAIN_BRAND EXTRA_TISDK_FILES TISDK_VERSION ARAGO_BRAND ARAGO_RT_ENABLE ARAGO_SYSTEST_ENABLE ARAGO_KERNEL_SUFFIX TI_SECURE_DEV_PKG_AM4 TI_SECURE_DEV_PKG_DRA7 TI_SECURE_DEV_PKG_CAT TI_SECURE_DEV_PKG_AUTO"

cd $builddir

cat << EOF
################################################################################

You can start building using the bitbake command.  You will likely want
to set the MACHINE option if you have not done so in your local.conf file.

For example:
    MACHINE=xxxxx bitbake <target>

Common targets are:
    core-image-minimal
    core-image-sato
    meta-toolchain
    meta-toolchain-sdk
    adt-installer
    meta-ide-support
EOF
