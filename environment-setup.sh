#!/bin/sh

# OE Build Enviroment Setup Script
#
print_image_names() {
    SOURCES="${1}"
    FOLDERS=$(find "${SOURCES}" -type d -a -iname images|grep recipes-core|sed -e "s/.*sources\///g"|cut -d '/' -f1|sort -u -r)
    for FOLDER in ${FOLDERS}
    do
        RECO=""
        if [ "${FOLDER}" = "meta-arago" ]; then
            RECO="[recommended]"
        fi
        printf '%s\n' "From ${FOLDER}${RECO}:"
        F_IMAGE_FOLDERS=$(find "${SOURCES}/${FOLDER}" -type d -a -iname images|grep recipes-core)
        for IMG_FOLDER in ${F_IMAGE_FOLDERS}
        do
            F_IMAGES=$(find "${IMG_FOLDER}" -iname '*.bb')
            if [ -n "${F_IMAGES}" ]; then
                for img in ${F_IMAGES}
                do
                    name=$(basename "${img}"|sed 's/\.bb$//g')
                    summary=$(grep SUMMARY "${img}"|cut -d '=' -f2| sed 's/["/]//g')
                    if [ -z "${summary}" ]; then
                        summary="No Summary available"
                    fi
                    printf '%s\n' "    ${name}: ${summary}"
                done
            fi
        done
    done
}

oebase=`pwd`

if [ $# -lt 1 ]; then
    echo "ERROR: Missing build directory"
    exit 1
fi

builddir="$oebase/$1"

# Populate the following variables depending on the value of oebase
sourcedir="$oebase/sources"

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
cp -f $sourcedir/meta-bbb-printer/conf/conf_bbb_printer/bblayers.conf.sample $confdir/bblayers.conf

# Now add the layers we have configured to the BBLAYERS variable
sed -e "s|##COREBASE##|$sourcedir|g" \
		$sourcedir/meta-bbb-printer/conf/conf_bbb_printer/bblayers.conf.sample > $confdir/bblayers.conf

# First copy the template file
cp -f $sourcedir/meta-bbb-printer/conf/conf_bbb_printer/local.conf.sample $confdir/local.conf

# set the number of threads
threads=$(cat /proc/cpuinfo | grep -c processor)
sed -i "s/^PARALLEL_MAKE.*/PARALLEL_MAKE = \"-j ${threads}\"/" $confdir/local.conf
sed -i "s/^BB_NUMBER_THREADS.*/BB_NUMBER_THREADS = \"${threads}\"/" $confdir/local.conf

# Set OEBASE to where the build and source directories reside
# NOTE: Do NOT place a trailing / on the end of OEBASE.
export OEBASE=${oebase}

# try to find out bitbake directory
BITBAKEDIR=$(find ${OEBASE}/sources -name "*bitbake*")
for f in ${BITBAKEDIR}
do
    if [ -d ${f}/bin ]
    then
        PATH="${f}/bin:$PATH"
    fi
done

# check for any scripts directories in the top-level of the repos and add those
# to the PATH
SCRIPTS=$(find ${OEBASE}/sources -maxdepth 2 -name "scripts" -type d)
for s in ${SCRIPTS}
do
    PATH="${s}:$PATH"
done

unset BITBAKEDIR
unset SCRIPTS
export PATH
export BUILDDIR=${builddir}
export BB_ENV_PASSTHROUGH_ADDITIONS="MACHINE DISTRO TCMODE TCLIBC http_proxy ftp_proxy https_proxy all_proxy ALL_PROXY no_proxy SSH_AGENT_PID SSH_AUTH_SOCK BB_SRCREV_POLICY SDKMACHINE BB_NUMBER_THREADS PARALLEL_MAKE GIT_PROXY_COMMAND GIT_PROXY_IGNORE SOCKS5_PASSWD SOCKS5_USER OEBASE META_SDK_PATH TOOLCHAIN_TYPE TOOLCHAIN_BRAND TOOLCHAIN_BASE TOOLCHAIN_PATH TOOLCHAIN_PATH_ARMV5 TOOLCHAIN_PATH_ARMV7 TOOLCHAIN_PATH_ARMV8 EXTRA_TISDK_FILES TISDK_VERSION ARAGO_BRAND ARAGO_RT_ENABLE ARAGO_SYSTEST_ENABLE ARAGO_KERNEL_SUFFIX TI_SECURE_DEV_PKG_CAT TI_SECURE_DEV_PKG_AUTO TI_SECURE_DEV_PKG_K3 ARAGO_SYSVINIT SYSFW_FILE"

cd $builddir

cat << EOF
You can then start building using the bitbake command.
Your build environment has been configured with:

    MACHINE=$MACHINE
    SDKMACHINE=$SDKMACHINE
    DISTRO=$DISTRO
    EULA=$EULA

For example:
    bitbake <target>

Common targets are:
EOF
print_image_names "${sourcedir}"
